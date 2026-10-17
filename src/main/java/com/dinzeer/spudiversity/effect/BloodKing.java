package com.dinzeer.spudiversity.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import net.minecraft.resources.ResourceLocation;

public class BloodKing extends MobEffect {
    private static final ResourceLocation ATTACK_DAMAGE_UUID = ResourceLocation.fromNamespaceAndPath("spudiversity", "blood_strength_attack_damage");
    public BloodKing() {
        super(MobEffectCategory.BENEFICIAL, -26368);
    }

    @Override
    public void removeAttributeModifiers(AttributeMap attributes) {
        super.removeAttributeModifiers(attributes);
        AttributeInstance attr = attributes.getInstance(Attributes.ATTACK_DAMAGE);
        if (attr != null) attr.removeModifier(ATTACK_DAMAGE_UUID);
    }

    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        AttributeInstance attr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attr != null) {
            attr.removeModifier(ATTACK_DAMAGE_UUID);
            attr.addTransientModifier(new AttributeModifier(ATTACK_DAMAGE_UUID,
                    0.4 * (double)(amplifier + 1), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        }

        return true;
    }
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

}
