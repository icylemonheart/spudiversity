package com.dinzeer.spudiversity.effect;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.Random;

@EventBusSubscriber
public class BlazeEnd extends MobEffect {
    private static final Random RANDOM = new Random();

    public BlazeEnd() {
        super(MobEffectCategory.BENEFICIAL, -26368);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.isOnFire()) {
            entity.igniteForSeconds(1);
        }

        if (entity.tickCount % 20 == 0) {
            entity.hurt(entity.damageSources().onFire(), 1.0F);
        }

        if (entity.level().isClientSide && RANDOM.nextFloat() < 0.3F) {
            spawnFlameParticles(entity);
        }

        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    private void spawnFlameParticles(LivingEntity entity) {
        for (int i = 0; i < 3; i++) {
            double x = entity.getX() + (RANDOM.nextDouble() - 0.5) * entity.getBbWidth();
            double y = entity.getY() + RANDOM.nextDouble() * entity.getBbHeight();
            double z = entity.getZ() + (RANDOM.nextDouble() - 0.5) * entity.getBbWidth();
            entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.FLAME,
                    x, y, z, 0, 0.05, 0);
        }
    }

    @SubscribeEvent
    public static void onLivingHeal(LivingHealEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(SpEffect.BlazeEnd) && entity.isOnFire()) {
            // 当燃烧期间阻止所有治疗
            event.setCanceled(true);

            // 添加阻止治疗的粒子效果
            if (entity.level().isClientSide) {
                for (int i = 0; i < 5; i++) {
                    double x = entity.getX() + (RANDOM.nextDouble() - 0.5) * entity.getBbWidth();
                    double y = entity.getY() + RANDOM.nextDouble() * entity.getBbHeight();
                    double z = entity.getZ() + (RANDOM.nextDouble() - 0.5) * entity.getBbWidth();
                    entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.SMOKE,
                            x, y, z, 0, 0.1, 0);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        DamageSource source = event.getSource();
        LivingEntity target = event.getEntity();

        // 检查攻击者是否有BlazeEnd效果
        if (source.getEntity() instanceof LivingEntity attacker) {
            if (attacker.hasEffect(SpEffect.BlazeEnd)) {
                // 点燃目标
                target.igniteForSeconds(5); // 点燃5秒

                // 对目标造成额外火焰伤害
                float extraDamage = event.getAmount() * 0.3F; // 额外30%伤害
                target.hurt(attacker.damageSources().onFire(), extraDamage);

                // 添加攻击特效
                if (attacker.level().isClientSide) {
                    spawnAttackParticles(target);
                }

                // 播放火焰音效
                attacker.playSound(net.minecraft.sounds.SoundEvents.BLAZE_SHOOT,
                        0.5F, 1.0F);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerAttack(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (player.hasEffect(SpEffect.BlazeEnd) ) {
                LivingEntity target = event.getEntity();
                if (!target.isOnFire()) {
                    target.igniteForSeconds(5);
                }
                if (player.level().isClientSide) {
                    spawnAttackParticles(target);
                }
            }
        }
    }

    private static void spawnAttackParticles(LivingEntity target) {
        for (int i = 0; i < 8; i++) {
            double x = target.getX() + (RANDOM.nextDouble() - 0.5) * target.getBbWidth();
            double y = target.getY() + RANDOM.nextDouble() * target.getBbHeight();
            double z = target.getZ() + (RANDOM.nextDouble() - 0.5) * target.getBbWidth();
            target.level().addParticle(net.minecraft.core.particles.ParticleTypes.FLAME,
                    x, y, z,
                    (RANDOM.nextDouble() - 0.5) * 0.1,
                    RANDOM.nextDouble() * 0.1,
                    (RANDOM.nextDouble() - 0.5) * 0.1);
        }
    }
}
