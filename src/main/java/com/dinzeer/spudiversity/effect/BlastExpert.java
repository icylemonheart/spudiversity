package com.dinzeer.spudiversity.effect;

import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class BlastExpert extends MobEffect {
    public BlastExpert() {
        super(MobEffectCategory.BENEFICIAL, 0xFF5533);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        if (!(event.getSource().getEntity() instanceof LivingEntity attacker)) {
            return;
        }
        LivingEntity target = event.getEntity();
        if (target == attacker) {
            return;
        }
        if (event.getSource().is(DamageTypes.EXPLOSION) || event.getSource().is(DamageTypes.PLAYER_EXPLOSION)) {
            return;
        }

        MobEffectInstance effect = attacker.getEffect(SpEffect.BlastExpert);
        if (effect == null || attacker.distanceTo(target) <= 5.0F) {
            return;
        }

        Level level = target.level();
        if (level.isClientSide) {
            return;
        }

        int count = effect.getAmplifier() + 1;
        for (int i = 0; i < count; i++) {
            PrimedTnt tnt = new PrimedTnt(level, target.getX(), target.getY() + 7.0, target.getZ(), null);
            level.addFreshEntity(tnt);
        }
    }
}
