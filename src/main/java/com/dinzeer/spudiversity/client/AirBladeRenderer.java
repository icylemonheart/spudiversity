package com.dinzeer.spudiversity.client;

import com.dinzeer.spudiversity.entity.AirBladeProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class AirBladeRenderer extends EntityRenderer<AirBladeProjectile> {
    public AirBladeRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    // 不渲染任何模型
    @Override
    public void render(AirBladeProjectile entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        // 空实现 - 不渲染模型
    }

    @Override
    public ResourceLocation getTextureLocation(AirBladeProjectile entity) {
        return null; // 无纹理
    }
}
