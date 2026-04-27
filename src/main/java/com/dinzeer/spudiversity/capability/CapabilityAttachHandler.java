package com.dinzeer.spudiversity.capability;

import com.dinzeer.spudiversity.Item.cappotato.AlchemistPotato;
import com.dinzeer.spudiversity.capability.impl.AlchemistPotatoCapability;
import com.dinzeer.spudiversity.capability.impl.PlayerKillBossAbilities;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.dinzeer.spudiversity.Spudiversity.MODID;

@Mod.EventBusSubscriber
public class CapabilityAttachHandler {
    public static final ResourceLocation PERMANENT_ABILITIES =
            new ResourceLocation(MODID, "permanent_abilities");
    public static final ResourceLocation KillBossesABILITIES=
            new ResourceLocation(MODID, "kill_bosses_abilities");
    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(PERMANENT_ABILITIES, new PlayerPermanentAbilities.Provider());
            event.addCapability(KillBossesABILITIES, new PlayerKillBossAbilities.Provider());
        }
    }
    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof AlchemistPotato) {
            event.addCapability(new ResourceLocation(MODID, "alchemist_potato_cap"),
                    new AlchemistPotatoCapability.Provider());
        }
    }

}