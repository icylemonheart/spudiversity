package com.dinzeer.spudiversity.effect;

import com.dinzeer.spudiversity.registry.SpEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class NoMobsBooby extends MobEffect {

    public NoMobsBooby() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFF00); // 黄色效果粒子
    }
    
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            Level level = player.level();

            player.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 3.0F);
            player.playSound(SoundEvents.VILLAGER_TRADE, 1.0F, 3.0F);
            player.playSound(SoundEvents.ENDER_DRAGON_GROWL, 1.0F, 1.0F);
            player.playSound(SoundEvents.GENERIC_EXPLODE, 1.0F, 4.0F);
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

                if (Minecraft.getInstance().player == player) {
                    Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(Items.WHITE_STAINED_GLASS));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player && player.hasEffect(SpEffect.NoMobsBooby.get())) {
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
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // 持续应用效果
    }
    @Override
    public boolean isInstantenous() {
        return false;
    }
}