package com.dinzeer.spudiversity.item.common;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;

public class ChargedCreeperPotato extends CreeperPotato {
    public ChargedCreeperPotato(Properties properties) {
        super(properties);
    }

    @Override
    protected float explosionRadius() {
        return 15.0F;
    }

    @Override
    protected void afterExplosion(Player player, Level level) {
        int bolts = 5 + player.getRandom().nextInt(6);
        for (int i = 0; i < bolts; i++) {
            double angle = player.getRandom().nextDouble() * Math.PI * 2.0;
            double distance = 6.0 + player.getRandom().nextDouble() * (explosionRadius() - 6.0);
            double x = player.getX() + Math.cos(angle) * distance;
            double z = player.getZ() + Math.sin(angle) * distance;
            int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING, (int) x, (int) z);

            LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
            if (bolt != null) {
                bolt.moveTo(x, y, z);
                level.addFreshEntity(bolt);
            }
        }
    }
}
