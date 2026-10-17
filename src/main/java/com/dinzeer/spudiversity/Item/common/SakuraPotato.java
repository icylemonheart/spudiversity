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
        LivingEntity player=entity;
        if (player!=null) {
            FastMakeEntityUtil.FastEffectByMinute(player, SpEffect.SakuraFantasy, 1,3);
        }

        if (player instanceof ServerPlayer serverPlayer) {
            int deathCount = serverPlayer.getStats().getValue(Stats.CUSTOM.get(Stats.DEATHS));
            if (player.level().getLevelData().isHardcore()){
                deathCount=9000;
            }
            if (deathCount > 100) {
                if (CurioCompat.hasItemInCurio(player, SpPotato.CakeKingPotato.get())){
                    if (player.getOffhandItem().is(SpItem.the_end_core.get())){
                        player.getOffhandItem().shrink(1);
                        FastMakeEntityUtil.FastHurt(player,player,player.getMaxHealth()*0.1f, DamageTypes.FELL_OUT_OF_WORLD);
                        serverPlayer.experienceLevel= serverPlayer.experienceLevel-30;









                        ItemStack resultStack = new ItemStack(SpPotato.TangxingPotato.get(), 1);
                        if (!serverPlayer.getInventory().add(resultStack)) {
                            serverPlayer.drop(resultStack, false);
                        }

                    }
                }
            }
        }





        return super.finishUsingItem(stack, world, entity);
    }
}
