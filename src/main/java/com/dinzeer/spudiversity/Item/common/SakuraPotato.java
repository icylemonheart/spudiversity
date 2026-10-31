package com.dinzeer.spudiversity.item.common;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.registry.SpEffect;
import com.dinzeer.spudiversity.registry.SpItem;
import com.dinzeer.spudiversity.registry.SpPotato;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SakuraPotato extends Item {
    public SakuraPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(6)
                .saturationModifier(0.5f)
                .alwaysEdible()
                .build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (entity != null) {
            FastMakeEntityUtil.FastEffectByMinute(entity, SpEffect.SakuraFantasy, 1, 3);
        }

        if (entity instanceof ServerPlayer serverPlayer) {
            boolean obtained = false;
            int deathCount = serverPlayer.getStats().getValue(Stats.CUSTOM.get(Stats.DEATHS));
            if (serverPlayer.level().getLevelData().isHardcore()) {
                deathCount = 9000;
            }
            if (deathCount > 100
                    && CurioCompat.hasItemInCurio(serverPlayer, SpPotato.CakeKingPotato.get())
                    && serverPlayer.getOffhandItem().is(SpItem.the_end_core.get())) {
                serverPlayer.getOffhandItem().shrink(1);
                FastMakeEntityUtil.FastHurt(serverPlayer, serverPlayer,
                        serverPlayer.getMaxHealth() * 0.1f, DamageTypes.FELL_OUT_OF_WORLD);
                serverPlayer.experienceLevel -= 30;
                giveTangxing(serverPlayer);
                obtained = true;
            }
            if (!obtained
                    && serverPlayer.level().getLevelData().isHardcore()
                    && consumeEndCore(serverPlayer)) {
                giveTangxing(serverPlayer);
            }
        }

        return super.finishUsingItem(stack, world, entity);
    }

    private static void giveTangxing(ServerPlayer player) {
        ItemStack resultStack = new ItemStack(SpPotato.TangxingPotato.get(), 1);
        if (!player.getInventory().add(resultStack)) {
            player.drop(resultStack, false);
        }
    }

    private static boolean consumeEndCore(ServerPlayer player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.is(SpItem.the_end_core.get())) {
                stack.shrink(1);
                return true;
            }
        }
        return false;
    }
}
