package com.dinzeer.spudiversity.item.compat.avaritia;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.item.base.PotatoItemBase;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.registry.SpAvaPotato;
import com.dinzeer.spudiversity.util.PerpetualPotatoes;
import com.dinzeer.spudiversity.util.lang.CnLangUtil;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class UniversePotato extends PotatoItemBase {
    private static final UUID UniversePotato_UUID = UUID.nameUUIDFromBytes("UniversePotato".getBytes());
    public UniversePotato(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(2000)
                .saturationMod(200)
                .alwaysEat()
                .build()), "universe_potato");
    }



    @SubscribeEvent
    public static void LivingUpdate(TickEvent.PlayerTickEvent event){
        Player player=event.player;
        player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store ->{
            if (store.getActivePotato().equals("universe_potato")){
                applyUniversePotato(player);
            }else {
                AttributeInstance armorAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
                AttributeInstance healAttr = player.getAttribute(Attributes.MAX_HEALTH);
                armorAttr.removeModifier(UniversePotato_UUID);
                healAttr.removeModifier(UniversePotato_UUID);
            }
        });
    }
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void LivingHurt(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player){
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store ->{
                if (store.getActivePotato().equals("universe_potato")){
                    event.setAmount(event.getAmount()*0.5f);
                }
            });
        }
        if (event.getSource().getEntity() instanceof Player player){
            player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store ->{
                if (store.getActivePotato().equals("universe_potato")&&event.getSource().is(DamageTypes.PLAYER_ATTACK)){
            event.getEntity().invulnerableTime = 20;
            event.getEntity().setNoActionTime(0);
            event.getEntity().walkAnimation.setSpeed(1.5F);
            event.getEntity().getCombatTracker().recordDamage(FastMakeEntityUtil.DamageSourceToCreat(player, DamageTypes.PLAYER_ATTACK), event.getAmount());
            event.getEntity().setHealth(event.getEntity().getHealth() - event.getAmount());
            event.getEntity().gameEvent(GameEvent.ENTITY_DAMAGE);
            // 设置受击动画持续时间
            event.getEntity().hurtDuration = 10;
            event.getEntity().hurtTime = event.getEntity().hurtDuration;
                }
            });
        }

    }

    private static void applyUniversePotato(Player player) {
        AttributeInstance armorAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
        if (armorAttr != null) {
            if (armorAttr.getModifier(UniversePotato_UUID) == null) {
            armorAttr.removeModifier(UniversePotato_UUID);
            armorAttr.addTransientModifier(new AttributeModifier(
                    UniversePotato_UUID,
                    "UniversePotato Potato Boost",
                    5,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            ));
            }
        }
        AttributeInstance healAttr = player.getAttribute(Attributes.MAX_HEALTH);
        if (healAttr != null) {
            if (healAttr.getModifier(UniversePotato_UUID) == null) {
            healAttr.removeModifier(UniversePotato_UUID);
            healAttr.addTransientModifier(new AttributeModifier(
                    UniversePotato_UUID,
                    "UniversePotato Potato Boost",
                    5,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            ));
            }
        }
    }


    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> toolTip, TooltipFlag tooltipFlag) {
        toolTip.add(PerpetualPotatoes.tag());
        toolTip.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpAvaPotato.UniversePotato,0)).withStyle(style -> style.withColor(16738373)));

        toolTip.add(PerpetualPotatoes.effectsHeader());
        if (Screen.hasShiftDown()) {
            // Shift显示
            toolTip.add(PerpetualPotatoes.body(CnLangUtil.FastMakeItemTooltip(SpAvaPotato.UniversePotato,7)));
            toolTip.add(PerpetualPotatoes.body(CnLangUtil.FastMakeItemTooltip(SpAvaPotato.UniversePotato,8)));
            toolTip.add(PerpetualPotatoes.body(CnLangUtil.FastMakeItemTooltip(SpAvaPotato.UniversePotato,9)));
            toolTip.add(PerpetualPotatoes.body(CnLangUtil.FastMakeItemTooltip(SpAvaPotato.UniversePotato,10)));
        } else {
            // 神秘小文本~~~
            toolTip.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpAvaPotato.UniversePotato,2),
                    AdvancedGradientTextHelper.createGradientText(
                            Component.translatable("tooltip.spudiversity.entropy_increase").getString(),
                            ColorPreset.LIGHT_RAINBOW,
                            AdvancedGradientTextHelper.GradientMode.FLOW_REVERSE
                    )
            ).withStyle(style -> style.withColor(4587434)));
            toolTip.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpAvaPotato.UniversePotato,3),
                    AdvancedGradientTextHelper.createGradientText(
                            Component.translatable("tooltip.spudiversity.suppress").getString(),
                            ColorPreset.LIGHT_RAINBOW,
                            AdvancedGradientTextHelper.GradientMode.FLOW_REVERSE
                    )).withStyle(style -> style.withColor(4575743)));
            toolTip.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpAvaPotato.UniversePotato,4),
                    AdvancedGradientTextHelper.createGradientText(
                            Component.translatable("tooltip.spudiversity.endless_judgment").getString(),
                            ColorPreset.LIGHT_RAINBOW,
                            AdvancedGradientTextHelper.GradientMode.FLOW_REVERSE
                    )).withStyle(style -> style.withColor(4539903)));
            toolTip.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpAvaPotato.UniversePotato,5),
                    AdvancedGradientTextHelper.createGradientText(
                            Component.translatable("tooltip.spudiversity.dominate").getString(),
                            ColorPreset.LIGHT_RAINBOW,
                            AdvancedGradientTextHelper.GradientMode.FLOW_REVERSE
                    )).withStyle(style -> style.withColor(11683327)));
            toolTip.add(PerpetualPotatoes.shiftValuesHint());
        }
        toolTip.add(Component.translatable(CnLangUtil.FastMakeItemTooltip(SpAvaPotato.UniversePotato,6)).withStyle(style -> style.withColor(16729575)));
        PerpetualPotatoes.appendActiveStatus(toolTip, false);
    }

    @Override
    public Component getName(ItemStack itemStack) {
        return  AdvancedGradientTextHelper.createGradientText(
                Component.translatable(this.getDescriptionId(itemStack)).getString(),
                ColorPreset.LIGHT_RAINBOW,
                AdvancedGradientTextHelper.GradientMode.FLOW_REVERSE
        );
    }

    @Override
    protected void applyCrystalEffect(Player player) {
    }
}
