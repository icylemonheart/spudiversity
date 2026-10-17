package com.dinzeer.spudiversity.event;

import net.neoforged.fml.common.EventBusSubscriber;



import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.spudiversity.registry.SpDataComponents;
import com.dinzeer.spudiversity.registry.SpPotato;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.List;
import java.util.Random;
@EventBusSubscriber
public class AlchemistPotatoEventHandler {
    private static final Random RANDOM = new Random();


    @SubscribeEvent
    public static void onPlayerHurt(LivingIncomingDamageEvent event) {
        Entity sourceEntity = event.getSource().getEntity();
        if (!(sourceEntity instanceof LivingEntity player)) return;
        if (!CurioCompat.hasItemInCurio(player, SpPotato.AlchemistPotato.get())) return;
        ItemStack potato = CurioCompat.findCurioItem(player, SpPotato.AlchemistPotato.get()).get();
        if (potato.isEmpty()) return;
        List<ItemStack> potions = potato.getOrDefault(SpDataComponents.ALCHEMIST_POTIONS.get(), List.of());
        if (!potions.isEmpty()) {
            ItemStack selectedPotion = potions.get(RANDOM.nextInt(potions.size()));
            applySplashPotionEffect(event.getEntity(), selectedPotion, player.level());
        }
    }






    private static void applySplashPotionEffect(LivingEntity target, ItemStack potionStack, Level level) {
        if (!(level instanceof ServerLevel serverLevel)) return;
        net.minecraft.world.item.alchemy.PotionContents contents = potionStack.getOrDefault(net.minecraft.core.component.DataComponents.POTION_CONTENTS, net.minecraft.world.item.alchemy.PotionContents.EMPTY);
        List<MobEffectInstance> effects = new java.util.ArrayList<>();
        contents.getAllEffects().forEach(effects::add);
        if (effects.isEmpty()) return;
        ThrownPotion thrownpotion = new ThrownPotion(serverLevel, target);
        thrownpotion.setItem(potionStack.copy());
        thrownpotion.setPos(target.getX(), target.getY() + 5, target.getZ());
        thrownpotion.setDeltaMovement(0, -0.5, 0);
        serverLevel.addFreshEntity(thrownpotion);

    }
}
