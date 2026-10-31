package com.dinzeer.spudiversity.item.common;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CreeperPotato extends Item {
    public CreeperPotato(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(4)
                .saturationModifier(0.3f)
                .alwaysEdible()
                .build()));
    }

    protected float explosionRadius() {
        return 3.0F;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            explode(player, level);
        }
        return super.finishUsingItem(stack, level, entity);
    }

    private void explode(Player player, Level level) {
        boolean wasInvulnerable = player.getAbilities().invulnerable;
        player.getAbilities().invulnerable = true;
        try {
            level.explode(player, player.getX(), player.getY(), player.getZ(),
                    explosionRadius(), Level.ExplosionInteraction.MOB);
        } finally {
            player.getAbilities().invulnerable = wasInvulnerable;
        }

        afterExplosion(player, level);
    }

    protected void afterExplosion(Player player, Level level) {
    }
}
