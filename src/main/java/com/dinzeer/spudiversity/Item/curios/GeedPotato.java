package com.dinzeer.spudiversity.Item.curios;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class GeedPotato extends Item implements ICurioItem {
    public GeedPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(9)
                .saturationMod(1f)
                .build()));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        if (slotContext.entity() instanceof ServerPlayer player){
            FastMakeEntityUtil.FastEffect(player, MobEffects.DAMAGE_RESISTANCE, 2);
            FastMakeEntityUtil.FastEffect(player, MobEffects.ABSORPTION, 1);
            FastMakeEntityUtil.FastEffect(player, MobEffects.SATURATION, 1);
            FastMakeEntityUtil.FastEffect(player, MobEffects.DAMAGE_BOOST, 1);
            FastMakeEntityUtil.FastEffect(player, MobEffects.MOVEMENT_SPEED, 1);
            FastMakeEntityUtil.FastEffect(player, MobEffects.NIGHT_VISION, 1);
            player.removeEffect(MobEffects.DARKNESS);

           player.level().getEntitiesOfClass(Wolf.class, player.getBoundingBox().inflate(10),(dog)->{
               FastMakeEntityUtil.FastEffect(dog, MobEffects.DAMAGE_BOOST, 1);
               return dog.isTame() && dog.isAlive();
           });


        }
    }
}
