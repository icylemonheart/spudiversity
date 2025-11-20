package com.dinzeer.spudiversity.Item.ex;

import com.dinzeer.legendreliclib.lib.util.WaitingTick;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.BlockPos;

public class OceanPotato extends Item {
    public OceanPotato(Properties p_41383_) {
        super(p_41383_.durability(500)); // 设置物品耐久度
    }

    // Ⅰ. 水压豁免：水下挖掘速度恒为陆地200%
    @Override
    public float getDestroySpeed(ItemStack stack, net.minecraft.world.level.block.state.BlockState state) {
        Player player = (Player) stack.getEntityRepresentation(); // 获取当前使用物品的玩家
        if (player != null && player.isEyeInFluid(net.minecraft.tags.FluidTags.WATER)) {
            return super.getDestroySpeed(stack, state) * 2.0f; // 挖掘速度提升至200%
        }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // 检查耐久是否足够
        if (stack.getDamageValue() >= stack.getMaxDamage()) {
            return InteractionResultHolder.fail(stack); // 耐久不足时无法使用
        }

        // 消耗1点耐久
        stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));

        // 获取玩家朝向的方向和范围
        Vec3 lookVec = player.getViewVector(1.0F);
        AABB area = new AABB(player.position(), player.position().add(lookVec.scale(10))).inflate(1);

        // 对生物效果
        world.getEntitiesOfClass(LivingEntity.class, area).forEach(entity -> {
            if (entity != player) { // 排除玩家自身
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2)); // 缓慢III（3秒）
                entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0)); // 发光（10秒）

                // 新增：在玩家和目标之间生成泡沫粒子
                Vec3 playerPos = player.getEyePosition(1.0F);
                Vec3 targetPos = entity.getEyePosition(1.0F);
                for (int i = 0; i < 30; i++) {
                    int finalI = i;
                    WaitingTick.schedule(i, () -> {
                        double t = finalI / 29.0; // 线性插值参数
                        double particleX = playerPos.x + (targetPos.x - playerPos.x) * t;
                        double particleY = playerPos.y + (targetPos.y - playerPos.y) * t;
                        double particleZ = playerPos.z + (targetPos.z - playerPos.z) * t;
                        world.addParticle(net.minecraft.core.particles.ParticleTypes.BUBBLE,
                                particleX, particleY, particleZ,
                                0, 0, 0); // 泡沫粒子
                    });
                }
            }
        });

        if (player.isEyeInFluid(FluidTags.WATER)) {
            ItemStack offhandStack = player.getOffhandItem();
            if (!offhandStack.isEmpty() && offhandStack.getItem() instanceof DiggerItem diggerItem) {
                BlockPos origin = player.blockPosition().relative(player.getDirection(), 1);


                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dz = -1; dz <= 1; dz++) {
                            BlockPos targetPos = origin.offset(dx+1, dy+1, dz);
                            world.destroyBlock(targetPos, true, player);
                        }
                    }
                }
            }
        }

        return InteractionResultHolder.success(stack);
    }
}