package com.dinzeer.spudiversity.item.compat.tw;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.spudiversity.registry.SpTwPotato;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import twilightforest.entity.projectile.TwilightWandBolt;

public class DuskPotato extends Item implements ICurioItem {
    public DuskPotato(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide() || !ModList.get().isLoaded("twilightforest")) {
            return;
        }
        if (!CurioCompat.hasItemInCurio(player, SpTwPotato.DuskPotato.get())) {
            return;
        }
        int roll = player.getRandom().nextInt(100);
        if (roll < 10) {
            SmallFireball fireball = new SmallFireball(player.level(), player, new Vec3(
                    player.getLookAngle().x * 2.0,
                    player.getLookAngle().y * 2.0,
                    player.getLookAngle().z * 2.0));
            fireball.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
            player.level().addFreshEntity(fireball);
        } else if (roll < 60) {
            player.level().addFreshEntity(new TwilightWandBolt(player.level(), player));
        }
    }
}
