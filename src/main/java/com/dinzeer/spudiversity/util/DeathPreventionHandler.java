package com.dinzeer.spudiversity.util;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

/**
 * 通用免死效果处理器（类似不死图腾）
 * 支持冷却计数、最大触发次数、效果恢复等
 */
public class DeathPreventionHandler {




    /**
     * 尝试为玩家触发免死效果（从Curio饰品中查找指定物品）
     *
     * @param player       玩家
     * @param event        死亡事件
     * @param targetItem   触发免死所需的饰品物品
     * @param cooldown 冷却描述
     */
    public static boolean tryPreventDeath(Player player, LivingDeathEvent event,
                                          Item targetItem, int cooldown) {

        cooldown = cooldown * 20;

        ItemStack curioItem = CurioCompat.findCurioItem(player, targetItem).orElse(null);
        if (curioItem == null || curioItem.isEmpty()) {
            return false;
        }
        // 冷却检查
        if (player.getCooldowns().isOnCooldown(curioItem.getItem())) {
            return false;
        }


        player.getCooldowns().addCooldown(curioItem.getItem(), cooldown);

        event.setCanceled(true);
        player.setHealth(player.getMaxHealth());
        FastMakeEntityUtil.FastEffect(player, MobEffects.DAMAGE_RESISTANCE, 2);
        FastMakeEntityUtil.FastEffect(player, MobEffects.REGENERATION, 2);
        FastMakeEntityUtil.FastEffect(player, MobEffects.FIRE_RESISTANCE, 1);
        // 播放音效（仅在服务端）
        if (!player.level().isClientSide) {
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        return true;
    }


}
