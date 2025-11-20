package com.dinzeer.spudiversity.village;



import com.dinzeer.spudiversity.regsitry.SpPotato;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

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
                            SpPotato.UndyingPotato.get().getDefaultInstance(), // 出售的唱片
                            4, // 最大交易次数
                            5, // 经验值
                            0.05f // 价格乘数
                    ));



    }
        public static void addVillageTrade(VillagerTradesEvent event, String villager, int level, VillagerTrades.ItemListing listing) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            VillagerProfession profession = event.getType();
            if (profession.name() != null) {
                if (profession.name().equals(villager)) {
                    ((List)trades.get(level)).add(listing);
                }

            }
        }


}
