package com.dinzeer.spudiversity.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class VillageSun extends MobEffect {
    public VillageSun() {
        super(MobEffectCategory.BENEFICIAL, 0xFFA500);
    }



}