package com.dinzeer.spudiversity.registry;

import com.dinzeer.spudiversity.entity.AirBladeProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.spudiversity.Spudiversity.MODID;

public class SpEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<AirBladeProjectile>> AIR_BLADE =
            ENTITIES.register("air_blade",
                    () -> EntityType.Builder.<AirBladeProjectile>of(AirBladeProjectile::new, MobCategory.MISC)
                            .sized(0.1F, 0.1F) // 非常小的碰撞箱
                            .clientTrackingRange(4)
                            .updateInterval(1) // 每刻更新
                            .noSummon() // 无法通过刷怪蛋召唤
                            .fireImmune() // 防火
                            .build("air_blade"));

}
