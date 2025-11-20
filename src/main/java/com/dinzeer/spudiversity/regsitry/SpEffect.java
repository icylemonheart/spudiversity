package com.dinzeer.spudiversity.regsitry;

import com.dinzeer.spudiversity.effect.*;
// 新增导入

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.spudiversity.Spudiversity.MODID;

public class SpEffect {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
    public static final RegistryObject<CopperPotato> CopperPotato = REGISTRY.register("copper_potato", CopperPotato::new);
    public static final RegistryObject<FurnaceBlessing> FurnaceBlessing = REGISTRY.register("furnace_blessing", FurnaceBlessing::new);
    public static final RegistryObject<VillageSun> VillageSun = REGISTRY.register("village_sun", VillageSun::new);
    public static final RegistryObject<NoMobsBooby> NoMobsBooby = REGISTRY.register("no_mobs_booby", NoMobsBooby::new);
    public static final RegistryObject<ForHaven> ForHaven= REGISTRY.register("for_haven", ForHaven::new);
    public static final RegistryObject<RainBow> RainBow = REGISTRY.register("rain_bow", RainBow::new);
    public static final RegistryObject<BambooGive> BambooGive = REGISTRY.register("bamboo_give", BambooGive::new);
    public static final RegistryObject<IcePotato> IcePotato = REGISTRY.register("ice_potato", IcePotato::new);
    public static final RegistryObject<BlazeEnd> BlazeEnd = REGISTRY.register("blaze_end", BlazeEnd::new);
    public static final RegistryObject<SakuraFantasy> SakuraFantasy = REGISTRY.register("sakura_fantasy", SakuraFantasy::new);
    public static final RegistryObject<EveryThing> EveryThing = REGISTRY.register("every_thing", EveryThing::new);
    public static final RegistryObject<WeAreNull> WeAreNull = REGISTRY.register("we_are_null", WeAreNull::new);
    public static final RegistryObject<WaveBlood> WaveBlood = REGISTRY.register("wave_blood", WaveBlood::new);
    public static final RegistryObject<BloodKing> BloodKing = REGISTRY.register("blood_king", BloodKing::new);
}