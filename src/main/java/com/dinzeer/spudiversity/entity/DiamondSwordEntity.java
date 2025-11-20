package com.dinzeer.spudiversity.entity;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.spudiversity.regsitry.SpEntities;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nullable;
import java.util.UUID;

public class DiamondSwordEntity extends Projectile {
    private UUID ownerUUID;
    private LivingEntity attackTarget;
    private int attackCount;
    private int returnTicks;
    private int orbitPosition;
    private Vec3 prevPosition;

    public DiamondSwordEntity(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    public DiamondSwordEntity(Level level, Player owner, int position) {
        this(SpEntities.SWORD_ENTITY.get(), level);
        this.ownerUUID = owner.getUUID();
        this.orbitPosition = position;
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = getOwner();
        if (this.level().isClientSide){return;}
        if (owner == null) {
            discard();
            return;
        }

        if (attackTarget != null) {
            handleAttack();
        } else {
            handleOrbit(owner);
            findAttackTarget(owner);
        }
        if (!CurioCompat.hasItemInCurio(owner, SpPotato.LootKingPotato.get())){
            this.discard();
        }
        // 保存当前位置用于下一帧旋转计算
        prevPosition = position();
    }

    // 新增：根据移动方向更新旋转角度
    protected void updateRotation() {
        Vec3 currentPos = position();
        Vec3 movement = currentPos.subtract(prevPosition);

        if (movement.lengthSqr() > 0.001) {

            double dx = movement.x;
            double dz = movement.z;
            float yaw = (float) Math.toDegrees(Math.atan2(dz, dx));
            this.setYRot(yaw);


            double dy = movement.y;
            double horizontalDist = Math.sqrt(dx * dx + dz * dz);
            float pitch = (float) Math.toDegrees(Math.atan2(dy, horizontalDist));
            this.setXRot(pitch);
        }
    }

    // 优化：提高旋转速度和移动响应性
    private void handleOrbit(LivingEntity owner) {
        // 提高旋转速度 (0.05 → 0.1)
        double angle = (owner.tickCount ) + (orbitPosition * Math.PI * 2 / getSwordCount(owner));
        double x = owner.getX() + Math.cos(angle) * 1.5; // 增加轨道半径
        double z = owner.getZ() + Math.sin(angle) * 1.5;
        double y = owner.getY() + 1.5; // 提高高度

        // 提高移动响应性 (0.2 → 0.5)
        Vec3 targetPos = new Vec3(x, y, z);
        Vec3 currentPos = position();
        Vec3 movement = targetPos.subtract(currentPos).scale(0.5);
        setPos(currentPos.add(movement));
    }

    // 优化：提高索敌范围
    private void findAttackTarget(LivingEntity owner) {
        for (LivingEntity entity : level().getEntitiesOfClass(LivingEntity.class,
                getBoundingBox().inflate(15), e -> // 范围15 → 15格
                        e != owner &&
                                !e.isAlliedTo(owner) &&
                                e.isAlive())) {
            attackTarget = entity;
            attackCount = 0;
            returnTicks = 0;
            break;
        }
    }

    // 优化：提高攻击速度和流畅度
    private void handleAttack() {
        if (!attackTarget.isAlive() || attackCount >= 3) {
            returnToOrbit();
            return;
        }

        if (returnTicks > 0) {
            returnTicks--;
            return;
        }

        // 提高攻击速度 (20 ticks → 15 ticks)
        double progress = Math.min(1, (tickCount % 15) / 15.0);
        Vec3 start = position();
        Vec3 end = attackTarget.position().add(0, 0.5, 0);

        // 优化：更平滑的贝塞尔曲线控制点
        Vec3 direction = end.subtract(start).normalize();
        Vec3 control = start.add(direction.scale(0.5 * start.distanceTo(end)))
                .add(0, 3 + (start.distanceTo(end) * 0.2), 0);

        // 二次贝塞尔曲线
        Vec3 position = calculateBezier(progress, start, control, end);
        setPos(position);

        // 检查是否击中
        if (progress > 0.8 && distanceTo(attackTarget) < 2.0) {
            attackTarget.hurt(level().damageSources().magic(), 6.0F);
            attackCount++;
            returnTicks = 8; // 减少延迟
        }
    }

    private Vec3 calculateBezier(double t, Vec3 p0, Vec3 p1, Vec3 p2) {
        double u = 1 - t;
        double x = u * u * p0.x + 2 * u * t * p1.x + t * t * p2.x;
        double y = u * u * p0.y + 2 * u * t * p1.y + t * t * p2.y;
        double z = u * u * p0.z + 2 * u * t * p1.z + t * t * p2.z;
        return new Vec3(x, y, z);
    }

    private void returnToOrbit() {
        attackTarget = null;
        returnTicks = 0;
    }

    private int getSwordCount(LivingEntity owner) {
        return (int) level().getEntitiesOfClass(
                DiamondSwordEntity.class,
                owner.getBoundingBox().inflate(15), // 增加范围匹配索敌范围
                e -> e.getOwner() == owner
        ).size();
    }


    @Nullable
    @Override
    public LivingEntity getOwner() {
        if (ownerUUID != null && level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel) level()).getEntity(ownerUUID);
            return entity instanceof LivingEntity ? (LivingEntity) entity : null;
        }
        return null;
    }


    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        ownerUUID = tag.getUUID("Owner");
        orbitPosition = tag.getInt("OrbitPos");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putUUID("Owner", ownerUUID);
        tag.putInt("OrbitPos", orbitPosition);
    }
}