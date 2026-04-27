package com.dinzeer.spudiversity.Item.curios;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.regsitry.SpEffect;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import com.dinzeer.spudiversity.util.DeathPreventionHandler;
import com.dinzeer.spudiversity.util.FlightUtil;
import com.dinzeer.spudiversity.util.lang.CnLangUtil;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;
@Mod.EventBusSubscriber
public class EdelweissPotato extends Item implements ICurioItem {
    private static final UUID EdelweissPotato_UUID = UUID.nameUUIDFromBytes("EdelweissPotato".getBytes());
    public EdelweissPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(9)
                .saturationMod(1f)
                .build()));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        FastMakeEntityUtil.FastEffect(slotContext.entity(), SpEffect.InvisibleWaiting.get(),1);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
           if (slotContext.entity() instanceof Player player){
               FlightUtil.grantFlight(player);
           }
         CuriosApi.getSlotHelper().setSlotsForType("potato",slotContext.entity(), 4);

    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            FlightUtil.revokeFlight(player);
        }
        CuriosApi.getSlotHelper().setSlotsForType("potato",slotContext.entity(), 3);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();
        modifiers.put(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                        EdelweissPotato_UUID,
                        "EdelweissPotato Boost",
                        2,
                        AttributeModifier.Operation.ADDITION
                )
        );
        modifiers.put(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(
                        EdelweissPotato_UUID,
                        "EdelweissPotato Boost",
                        0.1,
                        AttributeModifier.Operation.MULTIPLY_TOTAL
                )
        );
        return modifiers;
    }



    @SubscribeEvent
    public static void LivingDeathEvent(LivingDeathEvent event){
        if (event.getEntity() instanceof Player player){
            if (CurioCompat.hasItemInCurio(player, SpPotato.EdelweissPotato.get())){
                DeathPreventionHandler.tryPreventDeath(player, event, SpPotato.EdelweissPotato.get(),  120);
            }
        }
    }









    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tipList, TooltipFlag TooltipFlag) {
        super.appendHoverText(itemStack, level, tipList, TooltipFlag);
            tipList.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpPotato.EdelweissPotato,0)).withStyle(style -> style.withColor(16757497)));
            tipList.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpPotato.EdelweissPotato,1)).withStyle(style -> style.withColor(16757497)));
            tipList.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpPotato.EdelweissPotato,2)).withStyle(style -> style.withColor(16757497)));
            tipList.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpPotato.EdelweissPotato,3)).withStyle(style -> style.withColor(16757497)));
            tipList.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpPotato.EdelweissPotato,4)).withStyle(style -> style.withColor(16757497)));
            tipList.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpPotato.EdelweissPotato,5)).withStyle(style -> style.withColor(16757497)));
            tipList.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpPotato.EdelweissPotato,6)).withStyle(style -> style.withColor(16757497)));
            tipList.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpPotato.EdelweissPotato,7)).withStyle(style -> style.withColor(16757497)));
    }
}
