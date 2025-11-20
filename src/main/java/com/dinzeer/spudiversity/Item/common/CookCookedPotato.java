package com.dinzeer.spudiversity.Item.common;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CookCookedPotato extends Item {
    public CookCookedPotato(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!player.level().isClientSide) {

            FastMakeEntityUtil.FastHurt(target,player, 5.0F,DamageTypes.MAGIC);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }


            spawnHurtParticles(target);

            player.playSound(net.minecraft.sounds.SoundEvents.PLAYER_ATTACK_STRONG,
                    1.0F, 1.0F);
        }

        return InteractionResult.sidedSuccess(player.level().isClientSide);
    }

    private void spawnHurtParticles(LivingEntity target) {
        // 生成伤害粒子效果
        if (target.level().isClientSide) {
            for (int i = 0; i < 5; i++) {
                double x = target.getX() + (target.getRandom().nextDouble() - 0.5) * target.getBbWidth();
                double y = target.getY() + target.getRandom().nextDouble() * target.getBbHeight();
                double z = target.getZ() + (target.getRandom().nextDouble() - 0.5) * target.getBbWidth();
                target.level().addParticle(net.minecraft.core.particles.ParticleTypes.DAMAGE_INDICATOR,
                        x, y, z, 0, 0.1, 0);
            }
        }
    }
}
