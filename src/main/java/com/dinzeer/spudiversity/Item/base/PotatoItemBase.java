package com.dinzeer.spudiversity.item.base;

import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.event.PlayerEventHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class PotatoItemBase extends Item {
    private final String crystalType;
    
    public PotatoItemBase(Properties properties, String crystalType) {
        super(properties);
        this.crystalType = crystalType;
    }
    
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof ServerPlayer player) {
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(abilities -> {
                abilities.setActivePotato(crystalType);
            });
            
            applyCrystalEffect(player);
            PlayerEventHandler.syncCapabilities(player);
            
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        
        return super.finishUsingItem(stack, level, entity);
    }
    
    protected abstract void applyCrystalEffect(Player player);
}