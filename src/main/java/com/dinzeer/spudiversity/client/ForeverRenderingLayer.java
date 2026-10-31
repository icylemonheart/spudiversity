// ForeverRenderingLayer.java
package com.dinzeer.spudiversity.client;

import com.dinzeer.spudiversity.api.PerpetualPotatoRenderRegistry;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.config.ClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class ForeverRenderingLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    /**
     * @deprecated External mods should use {@link PerpetualPotatoRenderRegistry#registerMain}
     * from common initialization instead.
     */
    @Deprecated
    public static void registerPotato(String key, Supplier<? extends Item> itemSupplier) {
        PerpetualPotatoRenderRegistry.registerMain(
                key,
                () -> itemSupplier.get().getDefaultInstance()
        );
    }

    // 旋转半径（水平方向）
    private static final float RADIUS = 0.9f;

    // 旋转动画速度（弧度/每 tick）
    private static final float ROTATION_SPEED = 0.08f;

    // 物品缩放大小
    private static final float ITEM_SCALE = 0.5f;

    // 垂直偏移量（相对于玩家脚部，使物品围绕头部）
    private static final float BASE_Y_OFFSET = 0f;

    // 潜行时的额外垂直偏移
    private static final float CROUCH_Y_OFFSET = -0.15f;

    // 两个物品之间的角度差（弧度，180度）
    private static final float ANGLE_OFFSET = (float) Math.PI;

    public ForeverRenderingLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
                       float netHeadYaw, float headPitch) {

        if (!ClientConfig.CONFIG.renderPerpetualPotatoes.get()) {
            return;
        }

        List<ItemStack> starRelics = getPotato(player);
        if (starRelics.isEmpty()) return;

        int count = Math.min(starRelics.size(), 2);
        if (count == 0) return;

        float totalRotation = (player.tickCount + partialTicks) * ROTATION_SPEED;

        float yOffset = BASE_Y_OFFSET;
        if (player.isCrouching()) {
            yOffset += CROUCH_Y_OFFSET;
        }

        for (int i = 0; i < count; i++) {
            float angle = totalRotation + (i == 1 ? ANGLE_OFFSET : 0);
            renderStarRelic(poseStack, buffer, packedLight, starRelics.get(i), angle, yOffset, player);
        }
    }


    /**
     * 渲染单个星遗物
     * @param angle 水平旋转角度（弧度）
     * @param yOffset 垂直偏移（相对于玩家脚部）
     */
    private void renderStarRelic(PoseStack poseStack, MultiBufferSource buffer, int packedLight,
                                 ItemStack itemStack, float angle, float yOffset, AbstractClientPlayer player) {

        poseStack.pushPose();

        // 移动到玩家脚部位置（模型坐标系原点通常在脚部）
        poseStack.translate(0.0f, yOffset, 0.0f);

        // 计算水平位置：绕Y轴旋转半径距离
        float x = (float) (Math.cos(angle) * RADIUS);
        float z = (float) (Math.sin(angle) * RADIUS);
        poseStack.translate(x, 0.0f, z);

        // 让物品始终面向玩家（绕Y轴旋转180度，使其正面朝向相机）
        poseStack.mulPose(Axis.YP.rotationDegrees(180));

        // 添加轻微的上下浮动效果（使用正弦波，每个物品独立相位）
        float floatOffset = (float) Math.sin((player.tickCount * 0.1f) + (angle * 2)) * 0.03f;
        poseStack.translate(0.0f, floatOffset, 0.0f);

        // 缩放物品
        poseStack.scale(ITEM_SCALE, ITEM_SCALE, ITEM_SCALE);

        // 渲染物品
        Minecraft.getInstance().getItemRenderer().renderStatic(
                itemStack,
                ItemDisplayContext.FIXED,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                buffer,
                player.level(),
                player.getId()
        );

        poseStack.popPose();
    }

    /**
     * 获取玩家当前生效的永续土豆渲染栈：一个 MAIN + 一个 OFF。
     */
    private List<ItemStack> getPotato(AbstractClientPlayer player) {
        List<ItemStack> relics = new ArrayList<>();
        PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
        if (store != null) {
            addMain(relics, store.getActivePotato());
            addOff(relics, store.getActivePotatoOff());
        }
        return relics;
    }

    private static void addMain(List<ItemStack> relics, String key) {
        if (key == null || key.isEmpty()) {
            return;
        }
        ItemStack stack = PerpetualPotatoRenderRegistry.getMainStack(key);
        if (!stack.isEmpty()) {
            relics.add(stack);
        }
    }

    private static void addOff(List<ItemStack> relics, String key) {
        if (key == null || key.isEmpty()) {
            return;
        }
        ItemStack stack = PerpetualPotatoRenderRegistry.getOffStack(key);
        if (!stack.isEmpty()) {
            relics.add(stack);
        }
    }

}
