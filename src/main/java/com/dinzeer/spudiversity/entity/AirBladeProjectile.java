package com.dinzeer.spudiversity.entity;

import com.dinzeer.spudiversity.regsitry.SpEntities;
import com.dinzeer.spudiversity.util.ProjectileUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AirBladeProjectile extends Projectile {

    // 实体配置
    private static final int MAX_LIFETIME = 20 * 20; // 20秒 (20 ticks * 20)
    private static final int MAX_PENETRATION = 3;    // 最大穿透数
    private float damageMultiplier = 1.0f;           // 伤害乘数
    private int penetrationCount = 0;                // 当前穿透数
    private final Set<UUID> hitEntities = new HashSet<>(); // 已命中实体

    // 所有者跟踪
    private UUID ownerUUID;

    public AirBladeProjectile(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
        this.noPhysics = true; // 无物理碰撞
    }

    public AirBladeProjectile(Level level, LivingEntity owner, float damageMultiplier) {
        this(SpEntities.AIR_BLADE.get(), level);
        this.setOwner(owner);
        this.damageMultiplier = damageMultiplier;
        this.setPos(owner.getX(), owner.getEyeY(), owner.getZ());

        // 设置初始速度
        Vec3 lookVec = owner.getLookAngle();
        this.setDeltaMovement(lookVec.scale(1.5));
    }

    @Override
    public void tick() {
        super.tick();
        // 检查生命周期
        if (this.tickCount > MAX_LIFETIME) {
            this.discard();
            return;
        }

        // 生成粒子效果 (横扫之刃)
        if (!this.level().isClientSide) {
            spawnParticles();
        }

        // 应用重力 (可选)
        if (!this.isNoGravity()) {
            ProjectileUtil.applyGravity(this, 0.01f);
        }

        // 计算旋转角度
        float[] rotations = ProjectileUtil.getProjectileRotation(this);
        this.setYRot(rotations[0]);
        this.setXRot(rotations[1]);

        // 移动实体
        Vec3 movement = this.getDeltaMovement();
        this.setPos(
                this.getX() + movement.x,
                this.getY() + movement.y,
                this.getZ() + movement.z
        );

        // 检测碰撞
        checkCollisions();
    }

    private void spawnParticles() {
        if (this.level() instanceof ServerLevel serverLevel) {
            // 横扫之刃粒子
            serverLevel.sendParticles(
                    ParticleTypes.SWEEP_ATTACK,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    3, // 数量
                    0.1, 0.1, 0.1, // 偏移
                    0.0 // 速度
            );
        }
    }

    private void checkCollisions() {

        // 使用自定义工具类检测碰撞
        HitResult hitResult = ProjectileUtil.getHitResult(
                this,
                this::canHitEntity
        );

        if (hitResult != null) {
            if (hitResult.getType() == HitResult.Type.ENTITY) {
                onHitEntity((EntityHitResult) hitResult);
            } else if (hitResult.getType() == HitResult.Type.BLOCK) {
                onHit(hitResult);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity hitEntity = result.getEntity();
        LivingEntity owner = this.getOwner();

        // 检查是否已命中过此实体
        if (hitEntities.contains(hitEntity.getUUID())) {
            return;
        }

        // 应用伤害
        if (owner != null) {
            // 计算伤害 (基于玩家伤害 * 乘数)
            float baseDamage = (float) (owner.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.1f);
            float damage = baseDamage * damageMultiplier;

            // 创建PlayerAttack类型的伤害源
            DamageSource damageSource = this.damageSources().playerAttack(
                    owner instanceof Player ? (Player) owner : null
            );
            hitEntity.invulnerableTime = 0;
            hitEntity.hurt(damageSource, damage);
        }

        // 标记已命中
        hitEntities.add(hitEntity.getUUID());
        penetrationCount++;

        // 检查是否达到最大穿透数
        if (penetrationCount >= MAX_PENETRATION) {
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        // 方块碰撞直接消失
        if (result.getType() == HitResult.Type.BLOCK) {
            this.discard();
        }
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

    public void setOwner(@Nullable LivingEntity owner) {
        if (owner != null) {
            this.ownerUUID = owner.getUUID();
        }
    }

    @Override
    protected void defineSynchedData() {
        // 无需同步数据
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        // 只攻击非盟友实体
        LivingEntity owner = getOwner();
        return super.canHitEntity(entity) &&
                owner != null &&
                !entity.isAlliedTo(owner) &&
                entity != owner;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        // 空气刃无法被伤害
        return false;
    }
}