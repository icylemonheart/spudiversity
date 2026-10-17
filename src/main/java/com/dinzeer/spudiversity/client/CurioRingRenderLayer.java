package com.dinzeer.spudiversity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class CurioRingRenderLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private static final float RADIUS = 1.0f;
    private static final float ROTATION_SPEED = 0.06f;
    private static final float ITEM_SCALE = 0.5f;

    public CurioRingRenderLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight,
                       AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        if (!ModList.get().isLoaded("curios")) return;

        List<ItemStack> charms = getPotatoCharms(player);
        if (charms.isEmpty()) return;

        float totalRotation = (player.tickCount + partialTicks) * ROTATION_SPEED;
        float eyeHeight = player.getEyeHeight();
        int count = charms.size();

        for (int i = 0; i < count; i++) {
            float angle = totalRotation + (float) (i * 2 * Math.PI / count);

            poseStack.pushPose();

            // Ring center: at eye height, 0.2 blocks behind the player's head
            poseStack.translate(0, eyeHeight-2.2, 0);

            // Position on the ring (radius 1, X-Z plane)
            float x = (float) (Math.cos(angle) * RADIUS);
            float z = (float) (Math.sin(angle) * RADIUS);
            poseStack.translate(x, 0, z);

            // Face outward from ring center, then tilt flat for a halo look
//            poseStack.mulPose(Axis.YP.rotationDegrees((float) Math.toDegrees(angle) + 180));
//            poseStack.mulPose(Axis.XP.rotationDegrees(90));

            poseStack.mulPose(Axis.YP.rotationDegrees(180 - player.yBodyRot));
            poseStack.scale(ITEM_SCALE, ITEM_SCALE, ITEM_SCALE);

            Minecraft.getInstance().getItemRenderer().renderStatic(
                    charms.get(i),
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
    }

    private List<ItemStack> getPotatoCharms(AbstractClientPlayer player) {
        List<ItemStack> items = new ArrayList<>();
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            ICurioStacksHandler potatoHandler = handler.getCurios().get("potato");
            if (potatoHandler != null) {
                for (int i = 0; i < potatoHandler.getSlots(); i++) {
                    ItemStack stack = potatoHandler.getStacks().getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        items.add(stack);
                    }
                }
            }
        });
        return items;
    }
}
