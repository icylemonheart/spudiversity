package com.dinzeer.spudiversity.mixin;


import com.dinzeer.spudiversity.regsitry.SpEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public class BlockStateBaseMixin {

    @Inject( method = "getCollisionShape(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onGetCollisionShape(BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (context instanceof EntityCollisionContext entityContext) {
            Entity entity = entityContext.getEntity();
            if (entity instanceof LivingEntity player) {
                BlockBehaviour.BlockStateBase state = (BlockBehaviour.BlockStateBase) (Object) this;
                if (player.hasEffect(SpEffect.GlassType.get())) {

                    if (state.is(BlockTags.IMPERMEABLE)||state.is(Tags.Blocks.GLASS)||state.is(Tags.Blocks.GLASS_PANES)) {
                        cir.setReturnValue(Shapes.empty());
                    }
                }

                if (player.hasEffect(SpEffect.Misty.get())) {
                    if (player.isShiftKeyDown()){
                    double playerY = player.getY();
                    double blockY = pos.getY();
                    if (blockY == Math.floor(playerY) || blockY == Math.floor(playerY) + 1) {
                        cir.setReturnValue(Shapes.empty());
                    }
                    }
                }
            }
            if (entity instanceof Projectile target){
                if(target.getOwner() instanceof LivingEntity player){
                    MobEffectInstance instance = player.getEffect(SpEffect.GlassType.get());
                    if (instance != null) {
                        BlockBehaviour.BlockStateBase state = (BlockBehaviour.BlockStateBase) (Object) this;
                        if (state.is(BlockTags.IMPERMEABLE)||state.is(Tags.Blocks.GLASS)||state.is(Tags.Blocks.GLASS_PANES)) { // 玻璃、玻璃板、遮光玻璃等
                            cir.setReturnValue(Shapes.empty());
                        }
                    }
                }
            }
        }
    }
}