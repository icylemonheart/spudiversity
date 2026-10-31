package com.dinzeer.spudiversity.registry;

import com.dinzeer.spudiversity.effect.*;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.dinzeer.spudiversity.Spudiversity.MODID;

public class SpEffect {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, MODID);
    public static final DeferredHolder<MobEffect, CopperPotato> CopperPotato = REGISTRY.register("copper_potato", CopperPotato::new);
    public static final DeferredHolder<MobEffect, FurnaceBlessing> FurnaceBlessing = REGISTRY.register("furnace_blessing", FurnaceBlessing::new);
    public static final DeferredHolder<MobEffect, VillageSun> VillageSun = REGISTRY.register("village_sun", VillageSun::new);
    public static final DeferredHolder<MobEffect, NoMobsBooby> NoMobsBooby = REGISTRY.register("no_mobs_booby", NoMobsBooby::new);
    public static final DeferredHolder<MobEffect, ForHaven> ForHaven= REGISTRY.register("for_haven", ForHaven::new);
    public static final DeferredHolder<MobEffect, RainBow> RainBow = REGISTRY.register("rain_bow", RainBow::new);
    public static final DeferredHolder<MobEffect, BambooGive> BambooGive = REGISTRY.register("bamboo_give", BambooGive::new);
    public static final DeferredHolder<MobEffect, IcePotato> IcePotato = REGISTRY.register("ice_potato", IcePotato::new);
    public static final DeferredHolder<MobEffect, BlazeEnd> BlazeEnd = REGISTRY.register("blaze_end", BlazeEnd::new);
    public static final DeferredHolder<MobEffect, SakuraFantasy> SakuraFantasy = REGISTRY.register("sakura_fantasy", SakuraFantasy::new);
    public static final DeferredHolder<MobEffect, EveryThing> EveryThing = REGISTRY.register("every_thing", EveryThing::new);
    public static final DeferredHolder<MobEffect, WeAreNull> WeAreNull = REGISTRY.register("we_are_null", WeAreNull::new);
    public static final DeferredHolder<MobEffect, WaveBlood> WaveBlood = REGISTRY.register("wave_blood", WaveBlood::new);
    public static final DeferredHolder<MobEffect, BloodKing> BloodKing = REGISTRY.register("blood_king", BloodKing::new);
    public static final DeferredHolder<MobEffect, UndeadTransformationEffect> UndeadTransformationEffect = REGISTRY.register("undead_transformation_effect", UndeadTransformationEffect::new);
    public static final DeferredHolder<MobEffect, GlassType> GlassType = REGISTRY.register("glass_type", GlassType::new);
    public static final DeferredHolder<MobEffect, RadiantLight> RadiantLight = REGISTRY.register("radiant_light", RadiantLight::new);
    public static final DeferredHolder<MobEffect, InvisibleWaiting> InvisibleWaiting = REGISTRY.register("invisible_waiting", InvisibleWaiting::new);
    public static final DeferredHolder<MobEffect, BurnWithDawn> BurnWithDawn = REGISTRY.register("burn_with_dawn", BurnWithDawn::new);
    public static final DeferredHolder<MobEffect, Misty> Misty = REGISTRY.register("misty", Misty::new);
    public static final DeferredHolder<MobEffect, BlastExpert> BlastExpert = REGISTRY.register("blast_expert", BlastExpert::new);
    public static final DeferredHolder<MobEffect, ServerDisconnect> ServerDisconnect = REGISTRY.register("server_disconnect", ServerDisconnect::new);
}
