package com.dinzeer.spudiversity.client;

import com.dinzeer.spudiversity.entity.DiamondSwordEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SwordRenderer extends EntityRenderer<DiamondSwordEntity> {
    private final ItemRenderer itemRenderer;

    public SwordRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(DiamondSwordEntity entity, float yaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int light) {
        poseStack.pushPose();

        // 模型放大一倍
        poseStack.scale(2.0F, 2.0F, 2.0F);


        poseStack.mulPose(Axis.YP.rotationDegrees(180 - entity.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot()));

        // 调整位置使模型居中
        poseStack.translate(0, -0.25, 0);

        itemRenderer.renderStatic(
                new ItemStack(Items.DIAMOND_SWORD),
                ItemDisplayContext.GROUND,
                light,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                buffer,
                entity.level(),
                entity.getId()
        );

        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(DiamondSwordEntity entity) {
        return null;
    }
}