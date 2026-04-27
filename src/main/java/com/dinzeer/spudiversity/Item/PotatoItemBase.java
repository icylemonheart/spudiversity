package com.dinzeer.spudiversity.Item;

import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
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
        if (entity instanceof Player player) {
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(abilities -> {
                abilities.setActivePotato(crystalType);
            });
            
            // 应用水晶效果
            applyCrystalEffect(player);
            
            // 消耗物品（创造模式除外）
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        
        return super.finishUsingItem(stack, level, entity);
    }
    
    // 抽象方法，子类实现具体效果
    protected abstract void applyCrystalEffect(Player player);
}