package com.dinzeer.spudiversity.item.speaical;

import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.client.ClientHooks;
import com.dinzeer.spudiversity.item.base.PotatoItemBase;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.PerpetualPotatoes;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;

@EventBusSubscriber
public class StarPotato extends PotatoItemBase {
    public StarPotato(Properties properties) {
        super(properties, "for_love");
    }
    @Override
    protected void applyCrystalEffect(Player player) {

    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof Player player){
            PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
            if (store != null && store.getActivePotato().equals("for_love")){
                FoodData foodData = player.getFoodData();
                foodData.setFoodLevel(20);
                foodData.setSaturation(1);
            }
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        return AdvancedGradientTextHelper.createGradientText(super.getDescription().getString(),
                ColorPreset.BLUE_ICE,
                AdvancedGradientTextHelper.GradientMode.WAVE);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        tooltipList.add(PerpetualPotatoes.tag());
        for (int i = 0; i < 7; i++) {
            tooltipList.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.StarPotato, i)).withStyle(ChatFormatting.LIGHT_PURPLE));
        }
        tooltipList.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.StarPotato, 7),
                returnName(itemStack)
        ).withStyle(ChatFormatting.LIGHT_PURPLE));

        if (Screen.hasShiftDown()) {
            // 具体效果与获取方式
            tooltipList.add(PerpetualPotatoes.effectsHeader());
            tooltipList.add(PerpetualPotatoes.body(LangUtil.FastMakeItemTooltip(SpPotato.StarPotato, 8)));
            tooltipList.add(PerpetualPotatoes.obtainHeader());
            tooltipList.add(PerpetualPotatoes.body(LangUtil.FastMakeItemTooltip(SpPotato.StarPotato, 9)));
        } else {
            tooltipList.add(PerpetualPotatoes.shiftHint());
        }
        PerpetualPotatoes.appendActiveStatus(tooltipList, false);
    }




    public static String returnName(ItemStack itemStack){
        Player player = net.neoforged.fml.loading.FMLEnvironment.dist == net.neoforged.api.distmarker.Dist.CLIENT
                ? ClientHooks.getLocalPlayer() : null;
        if (player != null) {
            return player.getName().getString();
        }
        CompoundTag tag = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        if (tag.contains("ObtainedByPlayerName")){
            return tag.getString("ObtainedByPlayerName");
        }
        return "???";
    }
}
