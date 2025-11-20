package com.dinzeer.spudiversity.regsitry;

import com.dinzeer.spudiversity.entity.AirBladeProjectile;
import com.dinzeer.spudiversity.entity.DiamondSwordEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "yourmodid");

    public static final RegistryObject<EntityType<AirBladeProjectile>> AIR_BLADE =
            ENTITIES.register("air_blade",
                    () -> EntityType.Builder.<AirBladeProjectile>of(AirBladeProjectile::new, MobCategory.MISC)
                            .sized(0.1F, 0.1F) // 非常小的碰撞箱
                            .clientTrackingRange(4)
                            .updateInterval(1) // 每刻更新
                            .noSummon() // 无法通过刷怪蛋召唤
                            .fireImmune() // 防火
                            .build("air_blade"));
    public static final RegistryObject<EntityType<DiamondSwordEntity>> SWORD_ENTITY =
            ENTITIES.register("sword_entity", () ->
                    EntityType.Builder.<DiamondSwordEntity>of(DiamondSwordEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .updateInterval(1) // 每刻更新
                            .noSummon()
                            .fireImmune()
                            .build("sword_entity"));
}
