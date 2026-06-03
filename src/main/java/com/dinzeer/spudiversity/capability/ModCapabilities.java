// ModCapabilities.java
package com.dinzeer.spudiversity.capability;

import com.dinzeer.spudiversity.capability.impl.IAlchemistPotatoCap;
import com.dinzeer.spudiversity.capability.impl.PlayerKillBossAbilities;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCapabilities {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerPermanentAbilities.class);
        event.register(PlayerKillBossAbilities.class);
        event.register(IAlchemistPotatoCap.class);
    }
}