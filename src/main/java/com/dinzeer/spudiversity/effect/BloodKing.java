package com.dinzeer.spudiversity.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class BloodKing extends MobEffect {
    private static final UUID ATTACK_DAMAGE_UUID = UUID.nameUUIDFromBytes("blood_strength_attack_damage".getBytes());
    public BloodKing() {
        super(MobEffectCategory.BENEFICIAL, -26368);
    }

    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier) {
        super.removeAttributeModifiers(entity, attributes, amplifier);
        AttributeInstance attr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        attr.removeModifier(ATTACK_DAMAGE_UUID);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        AttributeInstance attr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attr != null) {
            attr.removeModifier(ATTACK_DAMAGE_UUID);
            attr.addTransientModifier(new AttributeModifier(ATTACK_DAMAGE_UUID,
                    "Blood Strength",
                    0.4 * (double)(amplifier + 1), AttributeModifier.Operation.MULTIPLY_TOTAL));
        }

    }
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

}
