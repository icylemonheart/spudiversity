package com.dinzeer.spudiversity.Item.compat.godfox;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.regsitry.SpEffect;
import com.dinzeer.spudiversity.regsitry.SpGodFoxPotato;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.ModAttributes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class AuspiciousPotato extends Item implements ICurioItem {
    private static final UUID DEFENSE_BOOST_UUID = UUID.nameUUIDFromBytes("AuspiciousPotato".getBytes());
    public AuspiciousPotato(Properties properties) {
        super(properties
                .food(new FoodProperties.Builder()
                .nutrition(6)
                .saturationMod(0.5f)
                .alwaysEat()
                .build()).rarity(Rarity.EPIC));
    }
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void BloodPotatoOnHurt(LivingHurtEvent event){
        LivingEntity target = event.getEntity();
        if (event.getSource().getEntity() instanceof LivingEntity player){
            if (CurioCompat.hasItemInCurio(player, SpGodFoxPotato.AuspiciousPotato.get())){
                FastMakeEntityUtil.FastEffect(event.getEntity(), SpEffect.WaveBlood.get(), 1);
                if (event.getEntity().hasEffect(SpEffect.WaveBlood.get())){
                    event.setAmount(event.getAmount()*1.4f);
                }
                if(event.getSource().is(DamageTypes.PLAYER_ATTACK)){
                    FastMakeEntityUtil.FastHurt(target,player , event.getAmount()*0.2f, DamageTypes.IN_FIRE);
                    target.setSecondsOnFire(1);
                    FastMakeEntityUtil.FastHurt(target,player , event.getAmount()*0.1f, DamageTypes.MAGIC);
                }
                int luck= (int) player.getAttributeValue(Attributes.LUCK);
                int random=player.getRandom().nextInt(100);
                int finals=luck + random;
                if (finals<=50){
                    FastMakeEntityUtil.FastHurt(player,target , event.getAmount()*1.2f, DamageTypes.GENERIC_KILL);
                    event.setAmount(event.getAmount()*0.8f);
                    player.sendSystemMessage(Component.translatable("message.spudiversity.lose").withStyle(style -> style.withColor(16729413)));
                }
                if (finals>50){
                    event.setAmount(event.getAmount()*1.2f);
                    player.sendSystemMessage(Component.translatable("message.spudiversity.happy").withStyle(style -> style.withColor(14853693)));
                }
                if (finals>80){
                    event.setAmount(event.getAmount()*1.3f);
                    player.sendSystemMessage(AdvancedGradientTextHelper.createGradientText(Component.translatable("message.spudiversity.very_happy").getString(),
                            ColorPreset.LIGHT_RAINBOW,
                            AdvancedGradientTextHelper.GradientMode.STATIC));
                }





            }
        }
    }












    @SubscribeEvent
    public static void SkyPotato(LivingHealEvent s){
        if (CurioCompat.hasItemInCurio(s.getEntity(), SpGodFoxPotato.AuspiciousPotato.get())){
            if (s.getEntity() instanceof Player player){
                FoodData foodData = player.getFoodData();
                if (foodData.getFoodLevel() > 19) {
                    s.setAmount(s.getAmount() * 1.18f);
                }
            }
        }
    }
    @SubscribeEvent
    public static void SkyPotatoOnHurt(LivingHurtEvent s){
        if (CurioCompat.hasItemInCurio(s.getEntity(), SpGodFoxPotato.AuspiciousPotato.get())){
            if (s.getEntity() instanceof Player player){
                int count= (int) Math.ceil(player.getAttributeValue(Attributes.LUCK)*0.18f);
                s.setAmount(s.getAmount()-count);
            }
        }if (s.getSource().getEntity() instanceof Player player){
            if (CurioCompat.hasItemInCurio(player, SpGodFoxPotato.AuspiciousPotato.get())){
                int count= (int) Math.ceil(player.getAttributeValue(Attributes.LUCK)*0.18f);
                s.setAmount(s.getAmount()+count);
            }
        }
    }
    @SubscribeEvent
    public static void SkyPotatoUpdate(SlashBladeEvent.UpdateEvent s){
        if (s.getEntity() instanceof Player player){
            if (CurioCompat.hasItemInCurio(player, SpGodFoxPotato.AuspiciousPotato.get())){
                if (s.getSlashBladeState().getTranslationKey().equals("item.godfox.truegodfox")){
                    FastMakeEntityUtil.FastEffect(player, SpEffect.VillageSun.get(), 1);
                }
            }
        }
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();
        modifiers.put(
                Attributes.MAX_HEALTH,
                new AttributeModifier(
                        DEFENSE_BOOST_UUID,
                        "AuspiciousPotato Damage Boost",
                        0.20,
                        AttributeModifier.Operation.MULTIPLY_TOTAL
                )
        );
        modifiers.put(
                Attributes.LUCK,
                new AttributeModifier(
                        DEFENSE_BOOST_UUID,
                        "AuspiciousPotato Damage Boost",
                        10,
                        AttributeModifier.Operation.ADDITION
                )
        );
        if (ModList.get().isLoaded("slashblade")) {
            modifiers.put(
                    ModAttributes.SLASHBLADE_DAMAGE.get(),
                    new AttributeModifier(
                            DEFENSE_BOOST_UUID,
                            "AuspiciousPotato Damage Boost",
                            0.05,
                            AttributeModifier.Operation.MULTIPLY_BASE
                    )
            );
        }
        return modifiers;
    }






}
