package com.dinzeer.spudiversity.effect;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class InvisibleWaiting  extends MobEffect {
    private static final ResourceLocation INVISIBLE_WAITING_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "invisible_waiting_strength");
    public InvisibleWaiting() {
        super(MobEffectCategory.BENEFICIAL, 16757497);
    }



    @SubscribeEvent
    public static void LivingHurt(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof LivingEntity player){
            if (player.hasEffect(SpEffect.InvisibleWaiting)){
                if (event.getEntity().isOnFire()){
                    event.setAmount(event.getAmount()*1.15f);
                }
               if (event.getSource().is(DamageTypes.IN_FIRE)||event.getSource().is(DamageTypes.MAGIC)){
                   return;
               }
                FastMakeEntityUtil.FastHurt(event.getEntity(),player, event.getAmount()*0.15f, DamageTypes.IN_FIRE);
            }
        }
    }





    // 新增效果结束时的清理逻辑
    @Override
    public void removeAttributeModifiers(AttributeMap attributes) {
        super.removeAttributeModifiers(attributes);
        AttributeInstance attr = attributes.getInstance(Attributes.ATTACK_DAMAGE);
        if (attr != null) attr.removeModifier(INVISIBLE_WAITING_ID);
    }
    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        AttributeInstance attr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attr != null) {
            attr.removeModifier(INVISIBLE_WAITING_ID);
            attr.addTransientModifier(new AttributeModifier(
                    INVISIBLE_WAITING_ID,
                    0.12D * (amplifier + 1),
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ));
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true; // 持续生效
    }


}
