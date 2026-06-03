package com.dinzeer.spudiversity.effect;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber
public class BurnWithDawn extends MobEffect {
    public BurnWithDawn() {
        super(MobEffectCategory.BENEFICIAL, 7685110);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onLivingHurt(LivingHurtEvent event) {
        {
            LivingEntity user=event.getEntity();
            if (user.hasEffect(SpEffect.BurnWithDawn.get())){
                if (event.getSource().is(DamageTypes.ON_FIRE)||
                        event.getSource().is(DamageTypes.IN_FIRE)||
                        event.getSource().is(DamageTypes.LAVA)||
                        event.getSource().is(DamageTypes.FIREBALL)){
                    user.heal(event.getAmount());
                    event.setAmount(0);
                }
            }
        }
        if (event.getSource().getEntity() instanceof LivingEntity player){
            if (player.hasEffect(SpEffect.BurnWithDawn.get())){
            if (event.getSource().is(DamageTypes.PLAYER_ATTACK)||event.getSource().is(DamageTypes.MOB_ATTACK)){
                FastMakeEntityUtil.FastHurt(event.getEntity(),player, event.getAmount(),DamageTypes.ON_FIRE);
                event.setAmount(0);
            }
            }
        }
    }





}
