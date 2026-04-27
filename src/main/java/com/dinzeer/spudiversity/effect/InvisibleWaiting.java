package com.dinzeer.spudiversity.effect;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.regsitry.SpEffect;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import com.dinzeer.spudiversity.util.DeathPreventionHandler;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;
@Mod.EventBusSubscriber
public class InvisibleWaiting  extends MobEffect {
    private static final UUID InvisibleWaiting_UUID = UUID.nameUUIDFromBytes("InvisibleWaiting".getBytes());
    public InvisibleWaiting() {
        super(MobEffectCategory.BENEFICIAL, 16757497);
    }



    @SubscribeEvent
    public static void LivingHurt(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof LivingEntity player){
            if (player.hasEffect(SpEffect.InvisibleWaiting.get())){
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
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier) {
        super.removeAttributeModifiers(entity, attributes, amplifier);
        AttributeInstance attr = entity.getAttribute(Attributes.ATTACK_DAMAGE);

        attr.removeModifier(InvisibleWaiting_UUID);

    }
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        AttributeInstance attr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attr != null) {
            attr.removeModifier(InvisibleWaiting_UUID);
            attr.addTransientModifier(new AttributeModifier(
                    InvisibleWaiting_UUID,
                    "Super Strength",
                    0.12D * (amplifier + 1),
                    AttributeModifier.Operation.MULTIPLY_BASE
            ));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // 持续生效
    }


}
