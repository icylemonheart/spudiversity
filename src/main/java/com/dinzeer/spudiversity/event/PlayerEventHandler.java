package com.dinzeer.spudiversity.event;

import com.dinzeer.spudiversity.capability.PlayerKillBossAbilities;
import com.dinzeer.spudiversity.capability.PlayerPermanentAbilities;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber
public class PlayerEventHandler {
    private static final UUID DEFENSE_BOOST_UUID = UUID.nameUUIDFromBytes("DRAGON_POTATO".getBytes());
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        // 玩家死亡重生时复制能力数据
        if (!event.isWasDeath()) return;
        event.getOriginal().reviveCaps();
      event.getOriginal().getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(oldStore -> {
          event.getEntity().getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(newStore -> {
              newStore.deserializeNBT(oldStore.serializeNBT());
          });
      });
        event.getOriginal().getCapability(PlayerKillBossAbilities.CAPABILITY).ifPresent(oldStore -> {
            event.getEntity().getCapability(PlayerKillBossAbilities.CAPABILITY).ifPresent(newStore -> {
                newStore.deserializeNBT(oldStore.serializeNBT());
            });
        });
        event.getOriginal().invalidateCaps();

    }

@SubscribeEvent
public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player){
            event.getEntity().getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(Store -> {
                switch (Store.getActivePotato()) {
                    case "fire_dragon_potato", "lightning_dragon_potato", "ice_dragon_potato","for_love" -> applyIceAndFire(player);
                }
            });
        }
}


    private static void applyIceAndFire(Player player) {
        AttributeInstance armorAttr = player.getAttribute(Attributes.MAX_HEALTH);
        if (armorAttr != null) {
            armorAttr.removeModifier(DEFENSE_BOOST_UUID);
            armorAttr.addTransientModifier(new AttributeModifier(
                    DEFENSE_BOOST_UUID,
                    "Dragon Potato Boost",
                    0.5D,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            ));
        }
    }
}