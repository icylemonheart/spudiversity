package com.dinzeer.spudiversity.Item.ex;

import com.dinzeer.legendreliclib.effect.LReffect;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.entity.DiamondSwordEntity;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;
@Mod.EventBusSubscriber
public class LootKingPotato extends Item implements ICurioItem {
    private static final UUID LootKingPotao_UUID = UUID.nameUUIDFromBytes("LootKingPotao".getBytes());
    public LootKingPotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(9)
                .saturationMod(1f)
                .build()));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        if (slotContext.entity() instanceof ServerPlayer player){
            if (!player.hasEffect(LReffect.SUPER_STRENGTH.get())){
            FastMakeEntityUtil.FastEffect(player, LReffect.SUPER_STRENGTH.get(),0);
            }
            if (!player.hasEffect(MobEffects.ABSORPTION)){
                FastMakeEntityUtil.FastEffect(player,MobEffects.ABSORPTION,2);
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();
        modifiers.put(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                        LootKingPotao_UUID,
                        "LootKingPotao Boost",
                        0.1,
                        AttributeModifier.Operation.MULTIPLY_TOTAL
                )
        );
        modifiers.put(
                Attributes.ARMOR,
                new AttributeModifier(
                        LootKingPotao_UUID,
                        "LootKingPotao Boost",
                        0.1,
                        AttributeModifier.Operation.MULTIPLY_TOTAL
                )
        );
        modifiers.put(
                Attributes.LUCK,
                new AttributeModifier(
                        LootKingPotao_UUID,
                        "LootKingPotao Boost",
                        1,
                        AttributeModifier.Operation.ADDITION
                )
        );
        return modifiers;
    }

}
