package com.dinzeer.spudiversity.effect;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.spudiversity.client.ClientHooks;
import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class NoMobsBooby extends MobEffect {

    public NoMobsBooby() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFF00); // 黄色效果粒子
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            Level level = player.level();

            if (!level.isClientSide() && player.tickCount % 20 == 0) {
                // 伤害吸收V
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 60, 4, false, false));

                // 每秒修复装备耐久
                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    ItemStack equipment = player.getItemBySlot(slot);
                    if (equipment.isDamageableItem() && equipment.getDamageValue() > 0) {
                        equipment.setDamageValue(Math.max(0, equipment.getDamageValue() - 10));
                    }
                }

                // 自动反击周围敌人
                for (Monster monster : player.level().getEntitiesOfClass(Monster.class, player.getBoundingBox().inflate(4.0))) {
                    monster.hurt(player.damageSources().playerAttack(player), 4.0F);
                }
            }

            player.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 3.0F);
            player.playSound(SoundEvents.VILLAGER_TRADE, 1.0F, 3.0F);
            player.playSound(SoundEvents.ENDER_DRAGON_GROWL, 1.0F, 1.0F);
            player.playSound(SoundEvents.GENERIC_EXPLODE.value(), 1.0F, 4.0F);
            if (level.isClientSide()) {
                for (int i = 0; i < 5; i++) {
                    double offsetX = (Math.random() * 4 - 2) * 0.5; // 扩大水平偏移范围
                    double offsetY = Math.random() * 3; // 垂直偏移
                    double offsetZ = (Math.random() * 4 - 2) * 0.5;
                    double motionX = (Math.random() * 2 - 1) * 0.5; // 随机速度分量
                    double motionY = Math.random() * 2; // 垂直速度
                    double motionZ = (Math.random() * 2 - 1) * 0.5;
                    level.addParticle(ParticleTypes.EXPLOSION,
                        player.getX() + offsetX,
                        player.getY() + player.getEyeHeight() + offsetY,
                        player.getZ() + offsetZ,
                        motionX, motionY, motionZ);
                }

                if (ClientHooks.isLocalPlayer(player)) {
                    ClientHooks.displayItemActivation(new ItemStack(Items.WHITE_STAINED_GLASS));
                }
            }
        }
        return true;
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player && player.hasEffect(SpEffect.NoMobsBooby)) {
            Level level = player.level();
            if (!level.isClientSide()) {
                BlockPos pos = player.blockPosition();
                for (int i = 0; i < 100; i++) {
                    double x = pos.getX() + Math.random() * 4 - 2;
                    double y = pos.getY() + Math.random() * 4;
                    double z = pos.getZ() + Math.random() * 4 - 2;
                    level.addFreshEntity(new ItemEntity(level, x, y, z, new ItemStack(Items.BAKED_POTATO)));
                }
            }
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true; // 持续应用效果
    }
    @Override
    public boolean isInstantenous() {
        return false;
    }
}
