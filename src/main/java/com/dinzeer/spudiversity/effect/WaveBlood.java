package com.dinzeer.spudiversity.effect;

import com.dinzeer.legendreliclib.effect.LReffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class WaveBlood extends MobEffect {
    public WaveBlood() {
        super(MobEffectCategory.BENEFICIAL, -26368);

    }
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        float damage = 2 * (float)(amplifier + 1);
        entity.hurt(entity.damageSources().magic(), damage);
        return true;
    }
}
