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

import net.minecraft.resources.ResourceLocation;
import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.client.ClientHooks;

public class BambooGive extends MobEffect {
    private static final ResourceLocation UUID_BAMBOO = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "bamboo_give_strength");
    private static final ResourceLocation UUID_JUMP_BOOST = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "bamboo_give_jump");

    private static final java.util.Map<LivingEntity, Boolean> doubleJumpUsed = new java.util.WeakHashMap<>();

    public BambooGive() {
        super(MobEffectCategory.BENEFICIAL, -26368);
    }

    @Override
    public void removeAttributeModifiers(AttributeMap attributes) {
        super.removeAttributeModifiers(attributes);
        AttributeInstance attackAttr = attributes.getInstance(Attributes.ATTACK_DAMAGE);
        AttributeInstance jumpAttr = attributes.getInstance(Attributes.JUMP_STRENGTH);

        if (attackAttr != null) {
            attackAttr.removeModifier(UUID_BAMBOO);
        }
        if (jumpAttr != null) {
            jumpAttr.removeModifier(UUID_JUMP_BOOST);
        }
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        AttributeInstance attackAttr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackAttr != null) {
            attackAttr.removeModifier(UUID_BAMBOO);
            attackAttr.addTransientModifier(new AttributeModifier(
                    UUID_BAMBOO,
                    0.3D * (amplifier + 1),
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ));
        }

        AttributeInstance jumpAttr = entity.getAttribute(Attributes.JUMP_STRENGTH);
        if (jumpAttr != null) {
            jumpAttr.removeModifier(UUID_JUMP_BOOST);
            jumpAttr.addTransientModifier(new AttributeModifier(
                    UUID_JUMP_BOOST,
                    0.5D, // 50% 跳跃提升
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
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
        return true;
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


                if (ClientHooks.isJumpKeyDown()) {
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
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true; // 持续生效
    }
}