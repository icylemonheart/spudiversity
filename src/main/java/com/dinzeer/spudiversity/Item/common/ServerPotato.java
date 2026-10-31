package com.dinzeer.spudiversity.item.common;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ServerPotato extends Item {
    public ServerPotato(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!player.level().isClientSide) {
            float damage = 5.0F;
            if (player.getRandom().nextFloat() < 0.1F) {
                damage *= 1.5F;
                FastMakeEntityUtil.FastEffect(target, SpEffect.ServerDisconnect, 1, 3);
            }
            FastMakeEntityUtil.FastHurt(target, player, damage, DamageTypes.MAGIC);

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            player.playSound(net.minecraft.sounds.SoundEvents.PLAYER_ATTACK_STRONG, 1.0F, 1.0F);
        }

        return InteractionResult.sidedSuccess(player.level().isClientSide);
    }
}
