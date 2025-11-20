package com.dinzeer.spudiversity.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.UUID;

public class BambooGive extends MobEffect {
    private static final UUID UUID_BAMBOO = UUID.nameUUIDFromBytes("UUID_BAMBOO".getBytes());
    private static final UUID UUID_JUMP_BOOST = UUID.nameUUIDFromBytes("UUID_JUMP_BOOST_BAMBOO".getBytes());

    private static final java.util.Map<LivingEntity, Boolean> doubleJumpUsed = new java.util.WeakHashMap<>();

    public BambooGive() {
        super(MobEffectCategory.BENEFICIAL, -26368);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier) {
        super.removeAttributeModifiers(entity, attributes, amplifier);
        AttributeInstance attackAttr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        AttributeInstance jumpAttr = entity.getAttribute(Attributes.JUMP_STRENGTH);

        if (attackAttr != null) {
            attackAttr.removeModifier(UUID_BAMBOO);
        }
        if (jumpAttr != null) {
            jumpAttr.removeModifier(UUID_JUMP_BOOST);
        }


        doubleJumpUsed.remove(entity);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        AttributeInstance attackAttr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackAttr != null) {
            attackAttr.removeModifier(UUID_BAMBOO);
            attackAttr.addTransientModifier(new AttributeModifier(
                    UUID_BAMBOO,
                    "Super Strength",
                    0.3D * (amplifier + 1),
                    AttributeModifier.Operation.MULTIPLY_BASE
            ));
        }

        AttributeInstance jumpAttr = entity.getAttribute(Attributes.JUMP_STRENGTH);
        if (jumpAttr != null) {
            jumpAttr.removeModifier(UUID_JUMP_BOOST);
            jumpAttr.addTransientModifier(new AttributeModifier(
                    UUID_JUMP_BOOST,
                    "Jump Boost",
                    0.5D, // 50% 跳跃提升
                    AttributeModifier.Operation.MULTIPLY_BASE
            ));
        }


        if (entity.isSprinting() && isOnWaterSurface(entity)) {
            Vec3 motion = entity.getDeltaMovement();
            entity.setDeltaMovement(motion.x, 0, motion.z);
            entity.setOnGround(true);
            entity.fallDistance = 0.0F;


            if (entity.level().isClientSide && entity.level().getGameTime() % 5 == 0) {
                spawnWaterWalkingParticles(entity);
            }
        }


        handleDoubleJump(entity);
    }

    private boolean isOnWaterSurface(LivingEntity entity) {
        BlockPos posBelow = entity.blockPosition().below();
        BlockState blockBelow = entity.level().getBlockState(posBelow);


        return blockBelow.is(Blocks.WATER) &&
                entity.getY() - posBelow.getY() <= 1.1 &&
                !entity.isInWater();
    }

    private void spawnWaterWalkingParticles(LivingEntity entity) {

        for(int i = 0; i < 3; ++i) {
            double x = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * entity.getBbWidth();
            double z = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * entity.getBbWidth();
            entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.SPLASH,
                    x, entity.getY(), z, 0, 0, 0);
        }
    }

    private void handleDoubleJump(LivingEntity entity) {
        if (entity instanceof Player player) {

            if (player.onGround()) {
                doubleJumpUsed.put(player, false);
            }


            if (player.level().isClientSide &&
                    !player.onGround() &&
                    !player.isInWater() &&
                    !player.isPassenger() &&
                    player.getDeltaMovement().y < 0) { // 正在下落


                if (net.minecraft.client.Minecraft.getInstance().options.keyJump.isDown()) {
                    Boolean hasUsedDoubleJump = doubleJumpUsed.get(player);
                    if (hasUsedDoubleJump == null || !hasUsedDoubleJump) {

                        performDoubleJump(player);
                        doubleJumpUsed.put(player, true);
                    }
                }
            }
        }
    }

    private void performDoubleJump(LivingEntity entity) {

        Vec3 motion = entity.getDeltaMovement();
        entity.setDeltaMovement(motion.x, 0.8, motion.z); // 向上的速度
        entity.hasImpulse = true;

        if (entity.level().isClientSide) {
            for(int i = 0; i < 8; ++i) {
                double x = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * entity.getBbWidth();
                double z = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * entity.getBbWidth();
                entity.level().addParticle(net.minecraft.core.particles.ParticleTypes.CLOUD,
                        x, entity.getY(), z, 0, 0, 0);
            }
        }

        entity.playSound(net.minecraft.sounds.SoundEvents.PLAYER_ATTACK_KNOCKBACK,
                0.5F, 1.0F);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // 持续生效
    }
}