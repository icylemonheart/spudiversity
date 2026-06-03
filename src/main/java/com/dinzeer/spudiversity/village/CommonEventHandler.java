package com.dinzeer.spudiversity.village;



import com.dinzeer.spudiversity.registry.SpPotato;

import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
@Mod.EventBusSubscriber
public class CommonEventHandler {
        public CommonEventHandler() {
        }

    @SubscribeEvent
    public static void onWandererTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();


        addDiscTrades( rareTrades);
    }
    private static void addDiscTrades(List<VillagerTrades.ItemListing> rareTrades) {


                    rareTrades.add(new BasicItemListing(
                            new ItemStack(Items.EMERALD, 64),
                            SpPotato.UndyingPotato.get().getDefaultInstance(),
                            4, // 最大交易次数
                            5, // 经验值
                            0.05f // 价格乘数
                    ));



    }


}
