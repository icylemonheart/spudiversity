package com.dinzeer.spudiversity.event;



import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.spudiversity.capability.impl.AlchemistPotatoCapability;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Random;
@Mod.EventBusSubscriber
public class AlchemistPotatoEventHandler {
    private static final Random RANDOM = new Random();


    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        Entity sourceEntity = event.getSource().getEntity();
        if (!(sourceEntity instanceof LivingEntity player)) return;
        if (!CurioCompat.hasItemInCurio(player, SpPotato.AlchemistPotato.get())) return;
        int index = RANDOM.nextInt(10);
        ItemStack potato = CurioCompat.findCurioItem(player, SpPotato.AlchemistPotato.get()).get();
        if (potato.isEmpty()) return;
        potato.getCapability(AlchemistPotatoCapability.INSTANCE).ifPresent(cap -> {
            ItemStack selectedPotion = cap.getRandomPotion(index);
            if (!selectedPotion.isEmpty()) {
                applySplashPotionEffect(event.getEntity(), selectedPotion, player.level());
            }
        });
    }





    private static void applySplashPotionEffect(LivingEntity target, ItemStack potionStack, Level level) {
        if (!(level instanceof ServerLevel serverLevel)) return;
        List<MobEffectInstance> effects = PotionUtils.getMobEffects(potionStack);
        if (effects.isEmpty()) return;
        ThrownPotion thrownpotion = new ThrownPotion(serverLevel, target);
        thrownpotion.setItem(potionStack);
        thrownpotion.setPos(target.getX(), target.getY() + 5, target.getZ());
        thrownpotion.setDeltaMovement(0, -0.5, 0);
        serverLevel.addFreshEntity(thrownpotion);

    }
}