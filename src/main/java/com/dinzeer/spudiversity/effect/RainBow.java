package com.dinzeer.spudiversity.effect;

import com.dinzeer.spudiversity.regsitry.SpEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
@Mod.EventBusSubscriber
public class RainBow extends MobEffect {

    public RainBow(){
        super(MobEffectCategory.BENEFICIAL, Color.BLUE.getBlue());
    }

    @SubscribeEvent
    public static void UpdateTick(LivingEvent.LivingTickEvent event){
        if (event.getEntity() instanceof Player player){
            if (player.hasEffect(SpEffect.RainBow.get())){
            applyEffect(player);

            // 恢复其他玩家的饱食度
            player.level().getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(10))
                    .stream()
                    .filter(otherPlayer -> otherPlayer != player && otherPlayer.isAlive())
                    .forEach(otherPlayer -> otherPlayer.getFoodData().eat(1, 0.5F));
        }
        }
    }

    public static void applyEffect(Player player) {
        if (player.level() instanceof ServerLevel serverLevel) {
            Vec3 pos = player.position();
            int particleCount = 50;
            double speed = 0.5;
            for (int i = 0; i < particleCount; i++) {
                double theta = 2 * Math.PI * Math.random();
                double phi = Math.acos(2 * Math.random() - 1);
                double dx = Math.sin(phi) * Math.cos(theta) * speed;
                double dy = Math.sin(phi) * Math.sin(theta) * speed;
                double dz = Math.cos(phi) * speed;
                serverLevel.sendParticles(
                        ParticleTypes.ENTITY_EFFECT,
                        pos.x + (Math.random() - 0.5) * 2,
                        pos.y + 1 + Math.random() * 2,
                        pos.z + (Math.random() - 0.5) * 2,
                        1, // 数量
                        dx, dy, dz,
                        0.1 // 速度乘数
                );
            }
        }
    }
}