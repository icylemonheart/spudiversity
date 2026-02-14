package com.dinzeer.spudiversity.Item.speaical;

import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.Item.PotatoItemBase;
import com.dinzeer.spudiversity.capability.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.effect.FurnaceBlessing;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Stack;

@Mod.EventBusSubscriber
public class StarPotato extends PotatoItemBase {
    public StarPotato(Properties properties) {
        super(properties, "for_love");
    }
    @Override
    protected void applyCrystalEffect(Player player) {

    }

    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player){
            event.getEntity().getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(Store -> {
                if (Store.getActivePotato().equals("for_love")){
                    FoodData foodData = player.getFoodData();
                    foodData.setFoodLevel(20);
                    foodData.setSaturation(1);
                }
            }
            );
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        return AdvancedGradientTextHelper.createGradientText(super.getDescription().getString(),
                ColorPreset.BLUE_ICE,
                AdvancedGradientTextHelper.GradientMode.WAVE);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        if (Screen.hasAltDown()){
            for (int i = 0; i < 7; i++){
                tooltipList.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.StarPotato,i)).withStyle(ChatFormatting.LIGHT_PURPLE));
            }
            tooltipList.add(Component.translatable(LangUtil.FastMakeItemTooltip(SpPotato.StarPotato,7),
                    returnName(itemStack)
                    ).withStyle(ChatFormatting.LIGHT_PURPLE));
        }else {
            tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_alt",
                    Component.literal("Alt").withStyle(ChatFormatting.LIGHT_PURPLE)).withStyle(ChatFormatting.DARK_PURPLE));
        }
    }




    public static String returnName(ItemStack itemStack){
        if (itemStack.hasTag()){
            if (itemStack.getTag().contains("ObtainedByPlayerName")){
                return itemStack.getTag().getString("ObtainedByPlayerName");
            };
        }
        return "XXXXX";
    }
}
