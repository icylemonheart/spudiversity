package com.dinzeer.spudiversity.item.compat.tw;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.spudiversity.network.SpawnEnderPaket;
import com.dinzeer.spudiversity.network.SpuNetwork;
import com.dinzeer.spudiversity.registry.SpTwPotato;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DuskPotato extends Item {
    public DuskPotato(Properties properties) {
        super(properties);
    }


    @SubscribeEvent
    public static void onPlayerLeftClick(PlayerInteractEvent.LeftClickEmpty event) {

        if (event.getSide().isClient()) {

            if (CurioCompat.hasItemInCurio(event.getEntity(), SpTwPotato.DuskPotato.get())) {

                SpuNetwork.sendToServer(new SpawnEnderPaket());
            }
        }
    }
}
