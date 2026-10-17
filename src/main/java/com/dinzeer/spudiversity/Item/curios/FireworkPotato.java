package com.dinzeer.spudiversity.item.curios;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.spudiversity.registry.SpPotato;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.ArrowLooseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@EventBusSubscriber
public class FireworkPotato extends Item {
    public FireworkPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(9)
                .saturationModifier(0.2f)
                .build()));
    }

    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onArrowLoose(ArrowLooseEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) return;

        Boolean isPotato = CurioCompat.hasItemInCurio(player, SpPotato.FireworkPotato.get());
        if (!isPotato) return;

        Vec3 look = player.getLookAngle();
        // 计算左右偏5度的方向（绕Y轴旋转）
        double angleRad = Math.toRadians(5);
        Vec3 leftDir = new Vec3(
                look.x * Math.cos(angleRad) - look.z * Math.sin(angleRad),
                look.y,
                look.x * Math.sin(angleRad) + look.z * Math.cos(angleRad)
        ).normalize();

        Vec3 rightDir = new Vec3(
                look.x * Math.cos(-angleRad) - look.z * Math.sin(-angleRad),
                look.y,
                look.x * Math.sin(-angleRad) + look.z * Math.cos(-angleRad)
        ).normalize();

        Vec3 spawnPos = player.getEyePosition();
        double speed = 5.0;

        FireworkRocketEntity leftFirework = createRandomFirework(player.level(),
                spawnPos.x, spawnPos.y, spawnPos.z);
        leftFirework.setDeltaMovement(leftDir.scale(speed));
        player.level().addFreshEntity(leftFirework);

        FireworkRocketEntity rightFirework = createRandomFirework(player.level(),
                spawnPos.x, spawnPos.y, spawnPos.z);
        rightFirework.setDeltaMovement(rightDir.scale(speed));
        player.level().addFreshEntity(rightFirework);
    }

    /**
     * 创建一个带有随机烟花效果的烟花火箭实体
     */
    private static FireworkRocketEntity createRandomFirework(net.minecraft.world.level.Level level,
                                                             double x, double y, double z) {
        ItemStack rocketStack = new ItemStack(Items.FIREWORK_ROCKET);

        List<FireworkExplosion> explosions = new ArrayList<>();
        int explosionCount = RANDOM.nextInt(3) + 1;
        for (int i = 0; i < explosionCount; i++) {
            explosions.add(createRandomExplosion());
        }
        rocketStack.set(DataComponents.FIREWORKS, new Fireworks(RANDOM.nextInt(3), explosions));

        return new FireworkRocketEntity(level, x, y, z, rocketStack);
    }

    /**
     * 生成随机烟花爆炸效果
     */
    private static FireworkExplosion createRandomExplosion() {
        FireworkExplosion.Shape[] shapes = FireworkExplosion.Shape.values();
        FireworkExplosion.Shape shape = shapes[RANDOM.nextInt(shapes.length)];

        IntList colors = IntList.of(randomColors());
        IntList fadeColors = IntList.of();
        if (RANDOM.nextBoolean()) {
            fadeColors = IntList.of(randomColors());
        }

        return new FireworkExplosion(shape, colors, fadeColors,
                RANDOM.nextBoolean(), RANDOM.nextBoolean());
    }

    private static int[] randomColors() {
        int[] colors = new int[RANDOM.nextInt(3) + 1];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = 0xFF000000 | RANDOM.nextInt(0xFFFFFF); // 随机 RGB
        }
        return colors;
    }
}
