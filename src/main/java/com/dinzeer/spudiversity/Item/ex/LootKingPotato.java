package com.dinzeer.spudiversity.Item.ex;

import com.dinzeer.legendreliclib.effect.LReffect;
import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.entity.DiamondSwordEntity;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
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
            FastMakeEntityUtil.FastEffect(player, LReffect.SUPER_STRENGTH.get(),1);
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
                        9,
                        AttributeModifier.Operation.ADDITION
                )
        );
        return modifiers;
    }







    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {

                    LivingEntity killedEntity = event.getEntity();
                    if (CurioCompat.hasItemInCurio(player, SpPotato.LootKingPotato.get())){
                        generateExtraLoot(killedEntity, player);
                    }

        }
    }

    private static void generateExtraLoot(LivingEntity entity, Player killer) {
        if (!entity.level().isClientSide){
        try {
            // 1. 获取实体的掉落表
            ResourceLocation lootTableId = entity.getLootTable();
            if (lootTableId == null) return;

            // 2. 创建掉落参数
            LootParams.Builder builder = new LootParams.Builder((ServerLevel) entity.level())
                    .withParameter(LootContextParams.THIS_ENTITY, entity)
                    .withParameter(LootContextParams.ORIGIN, entity.position())
                    .withParameter(LootContextParams.DAMAGE_SOURCE, entity.damageSources().playerAttack(killer))
                    .withParameter(LootContextParams.KILLER_ENTITY, killer)
                    .withParameter(LootContextParams.LAST_DAMAGE_PLAYER, killer)
                   ;

            // 3. 应用抢夺附魔
            if (killer.getMainHandItem().isEnchanted()) {
                builder.withLuck(killer.getLuck());
            }

            LootParams params = builder.create(LootContextParamSets.ENTITY);

            // 4. 获取服务器的掉落表
            LootTable lootTable = entity.level().getServer().getLootData().getLootTable(lootTableId);

            // 5. 生成掉落物
            List<ItemStack> drops = lootTable.getRandomItems(params);

            // 6. 生成额外的掉落物到世界
            for (ItemStack stack : drops) {
                if (!stack.isEmpty()) {
                    // 随机位置偏移，让掉落物分散
                    double offsetX = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * 0.5;
                    double offsetY = entity.getY() + 0.5;
                    double offsetZ = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * 0.5;

                    ItemEntity itemEntity = new ItemEntity(
                            entity.level(),
                            offsetX, offsetY, offsetZ,
                            stack.copy() // 使用副本，避免修改原物品
                    );

                    // 设置随机运动
                    itemEntity.setDeltaMovement(
                            (entity.getRandom().nextDouble() - 0.5) * 0.2,
                            0.2,
                            (entity.getRandom().nextDouble() - 0.5) * 0.2
                    );

                    // 设置拾取延迟
                    itemEntity.setPickUpDelay(10);

                    // 添加到世界
                    entity.level().addFreshEntity(itemEntity);
                }
            }

        } catch (Exception e) {
            // 防止崩溃
            System.err.println("Failed to generate extra loot: " + e.getMessage());
        }
        }
    }


}
