package com.dinzeer.spudiversity.util;

import com.dinzeer.legendreliclib.effect.LReffect;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber
public class NuclearExplosionScheduler {


    public static void createEllipsoidExplosion(ServerLevel level, Vec3 center, double horizontalRadius, double verticalRadius, @Nullable Entity source) {
        double horizontalRadiusSq = horizontalRadius * horizontalRadius;
        double verticalRadiusSq = verticalRadius * verticalRadius;

        AABB bounds = new AABB(
                center.x - horizontalRadius, center.y - verticalRadius, center.z - horizontalRadius,
                center.x + horizontalRadius, center.y + verticalRadius, center.z + horizontalRadius
        );

        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, bounds);
         if (source instanceof LivingEntity user){
        for (LivingEntity entity : entities) {
            double dx = entity.getX() - center.x;
            double dy = entity.getY() - center.y;
            double dz = entity.getZ() - center.z;

            double normalizedDistanceSq = (dx * dx + dz * dz) / horizontalRadiusSq + (dy * dy) / verticalRadiusSq;

            if (normalizedDistanceSq <= 1.0) {
                double distance = Math.sqrt(normalizedDistanceSq);
                float damage = (float) (user.getAttributeValue(Attributes.ATTACK_DAMAGE)*20f);

                if (damage > 0) {
                    FastMakeEntityUtil.FastHurt(entity, user, damage,DamageTypes.GENERIC_KILL);

                    double knockbackStrength = 3.0 * (1.0 - distance);
                    Vec3 knockback = new Vec3(dx, dy, dz).normalize().scale(knockbackStrength);
                    entity.push(knockback.x, knockback.y, knockback.z);
                }
            }
        }

        }

        int hRadius = (int) horizontalRadius;
        int vRadius = (int) verticalRadius;
        BlockPos centerPos = BlockPos.containing(center);

        List<BlockPos> blocksToRemove = new ArrayList<>();
        List<BlockPos> fluidsToRemove = new ArrayList<>();

        for (int x = -hRadius; x <= hRadius; x++) {
            for (int y = -vRadius; y <= vRadius; y++) {
                for (int z = -hRadius; z <= hRadius; z++) {
                    BlockPos pos = centerPos.offset(x, y, z);
                    BlockState state = level.getBlockState(pos);

                    if (!state.isAir()) {
                        double dx = x + 0.5;
                        double dy = y + 0.5;
                        double dz = z + 0.5;

                        double normalizedDistanceSq = (dx * dx + dz * dz) / horizontalRadiusSq + (dy * dy) / verticalRadiusSq;

                        if (normalizedDistanceSq <= 1.0) {
                            FluidState fluidState = state.getFluidState();

                            if (!fluidState.isEmpty()) {
                                fluidsToRemove.add(pos);

                                if (level.random.nextFloat() < 0.4f) {
                                    level.sendParticles(ParticleTypes.SMOKE,
                                            pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                                            1, 0, 0.1, 0, 0);
                                }
                            } else {
                                Explosion explosion = new Explosion(level, source, null, null, center.x, center.y, center.z, 2.0F, false, Explosion.BlockInteraction.DESTROY);
                                float blastResistance = state.getExplosionResistance(level, pos, explosion);



                                if (blastResistance < 1200.0F) {
                                    blocksToRemove.add(pos);

                                    if (level.random.nextFloat() < 0.3f) {
                                        level.sendParticles(ParticleTypes.EXPLOSION,
                                                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                                                1, 0, 0, 0, 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (BlockPos pos : fluidsToRemove) {
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }

        for (BlockPos pos : blocksToRemove) {
            level.removeBlock(pos, false);
        }

        for (int i = 0; i < 50; i++) {
            double angle = level.random.nextDouble() * Math.PI * 2;
            double height = (level.random.nextDouble() - 0.5) * 2 * verticalRadius;
            double radius = level.random.nextDouble() * horizontalRadius;

            double px = center.x + Math.cos(angle) * radius;
            double py = center.y + height;
            double pz = center.z + Math.sin(angle) * radius;

            level.sendParticles(ParticleTypes.FLAME, px, py, pz, 1, 0, 0, 0, 0);
            level.sendParticles(ParticleTypes.SMOKE, px, py, pz, 1, 0, 0, 0, 0);
        }

        level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, center.x, center.y, center.z, 1, 0, 0, 0, 0);
        level.sendParticles(ParticleTypes.EXPLOSION, center.x, center.y, center.z, 200,
                horizontalRadius * 0.3, verticalRadius * 0.3, horizontalRadius * 0.3, 0);

        level.explode(source, center.x, center.y, center.z, 2.0F, false, Level.ExplosionInteraction.TNT);
        level.playSound(null, center.x, center.y, center.z, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 4.0F, (1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F) * 0.7F);
    }


}
