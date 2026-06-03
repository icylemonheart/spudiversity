// 创建一个新文件：AchievementEventHandler.java
package com.dinzeer.spudiversity.event;

import com.dinzeer.spudiversity.registry.SpPotato;
import net.minecraft.advancements.Advancement;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AchievementEventHandler {
    

    private static final String[] ENDER_DRAGON_ACHIEVEMENTS = {
            "minecraft:end/kill_dragon"
    };
    
    @SubscribeEvent
    public static void onAdvancementEarned(AdvancementEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            Advancement advancement = event.getAdvancement();
            
            // 检查是否获得了与末地相关的成就
            for (String achievementId : ENDER_DRAGON_ACHIEVEMENTS) {
                if (advancement.getId().toString().equals(achievementId)) {
                    // 给玩家一个末影龙土豆
                    ItemStack enderDragonPotato = new ItemStack(SpPotato.EnderDragonPotato.get(), 1);
                    
                    // 检查玩家背包是否有空间
                    if (!player.addItem(enderDragonPotato)) {
                        // 背包满时，物品掉落在玩家脚下
                        player.drop(enderDragonPotato, false);
                    }

                    
                    break;
                }
            }
        }
    }
}