package com.dinzeer.spudiversity.Item.curios;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.text.AdvancedGradientTextHelper;
import com.dinzeer.legendreliclib.lib.util.text.ColorPreset;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class MadeInPotato extends Item {
    public MadeInPotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(20)
                .saturationMod(1f)
                        .alwaysEat()
                .build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        LivingEntity player=entity;
        if (player!=null) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 5));




            if (player instanceof Player player1){
                if (CurioCompat.hasItemInCurio(player, SpPotato.CakeKingPotato.get())
                &&CurioCompat.hasItemInCurio(player, SpPotato.MysteriousPotato.get())
                  &&player1.getOffhandItem().is(SpPotato.OceanPotato.get())
                ){
                    player1.addItem(SpPotato.MadeInPotatoMadeInHaven.get().getDefaultInstance());
                }
            }




        }
        return super.finishUsingItem(stack, world, entity);
    }
    @SubscribeEvent
    public static void OnPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.level();

        if (CurioCompat.hasItemInCurio(player, SpPotato.MadeInPotato.get())) {
            // 恢复手持物品耐久
            restoreItemDurability(player);

            // 加速作物生长
            accelerateCropGrowth(player, level);

            // 加速动物成长和繁育冷却
            accelerateAnimalGrowth(player, level);

            // 提升玩家自身移动速度和攻击速度
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 3)); // 移动速度提升
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20, 3)); // 攻击速度提升

            // 削减敌对生物无敌帧
            reduceEnemyInvincibilityFrames(player, level);
        }
    }

    private static void restoreItemDurability(Player player) {
        // 恢复主手和副手物品耐久
        for (ItemStack stack : List.of(player.getMainHandItem(), player.getOffhandItem())) {
            if (!stack.isEmpty() && stack.isDamageableItem() && stack.getDamageValue() > 0) {
                stack.setDamageValue(stack.getDamageValue() - 1); // 减少耐久损耗
            }
        }
    }

    private static void accelerateCropGrowth(Player player, Level level) {
        // 遍历玩家周围3x3范围内的作物方块
        BlockPos.betweenClosedStream(player.blockPosition().offset(-1, -1, -1), player.blockPosition().offset(1, 1, 1))
                .forEach(pos -> {
                    BlockState state = level.getBlockState(pos);

                        if (level instanceof ServerLevel serverLevel) {
                            state.randomTick(serverLevel, pos, level.getRandom()); // 触发随机生长
                            state.randomTick(serverLevel, pos, level.getRandom());
                            state.randomTick(serverLevel, pos, level.getRandom());
                            state.randomTick(serverLevel, pos, level.getRandom());
                            state.randomTick(serverLevel, pos, level.getRandom());
                        }

                });
    }

    private static void accelerateAnimalGrowth(Player player, Level level) {
        // 遍历玩家周围10格内的动物实体
        level.getEntitiesOfClass(Animal.class, player.getBoundingBox().inflate(10))
                .forEach(animal -> {
                    if (animal.isBaby()) {
                        animal.ageUp(10); // 加速成长
                    }
                    if (animal.getAge() == 0 && animal.canFallInLove()) {
                        animal.setInLoveTime(animal.getInLoveTime() - 10); // 减少繁育冷却
                    }
                });
    }

    private static void reduceEnemyInvincibilityFrames(Player player, Level level) {
        level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(10))
                .stream()
                .filter(entity -> entity instanceof LivingEntity && !(entity instanceof Animal || entity instanceof Player))
                .forEach(enemy -> {
                    if (enemy.invulnerableTime > 0) {
                        enemy.invulnerableTime -= 5; // 削减无敌帧
                    }
                });
    }






    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip").withStyle(ChatFormatting.WHITE));
        if (Screen.hasAltDown()){
        tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_1").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_2").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_3").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_4").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_5").withStyle(ChatFormatting.LIGHT_PURPLE));
        }else {
            tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_6").withStyle(ChatFormatting.GOLD));
            tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_7").withStyle(ChatFormatting.GOLD));
            tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_8").withStyle(ChatFormatting.GOLD));
            tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_9").withStyle(ChatFormatting.GOLD));
            tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_10").withStyle(ChatFormatting.GOLD));
            tooltipList.add(Component.translatable("item.spudiversity.made_in_potato.tooltip_alt",
                    Component.literal("Alt").withStyle(ChatFormatting.LIGHT_PURPLE)).withStyle(ChatFormatting.GOLD));
        }
    }


}