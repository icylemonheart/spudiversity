package com.dinzeer.spudiversity.item.ex;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.legendreliclib.effect.LReffect;
import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
@EventBusSubscriber
public class LootKingPotato extends Item implements ICurioItem {
    private static final ResourceLocation LOOT_KING_POTATO_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "loot_king_potato");

    public LootKingPotato(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder())
                .nutrition(9)
                .saturationModifier(1f)
                .build()));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        if (slotContext.entity() instanceof ServerPlayer player){
            if (!player.hasEffect(LReffect.SUPER_STRENGTH)){
            FastMakeEntityUtil.FastEffect(player, LReffect.SUPER_STRENGTH,1);
            }
            if (!player.hasEffect(MobEffects.ABSORPTION)){
                FastMakeEntityUtil.FastEffect(player,MobEffects.ABSORPTION,2);
            }
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = LinkedHashMultimap.create();
        modifiers.put(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                        LOOT_KING_POTATO_ID,
                        0.1,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        );
        modifiers.put(
                Attributes.ARMOR,
                new AttributeModifier(
                        LOOT_KING_POTATO_ID,
                        0.1,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        );
        modifiers.put(
                Attributes.LUCK,
                new AttributeModifier(
                        LOOT_KING_POTATO_ID,
                        9,
                        AttributeModifier.Operation.ADD_VALUE
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
            var lootTableId = entity.getLootTable();
            if (lootTableId == null) return;

            // 2. 创建掉落参数
            LootParams.Builder builder = new LootParams.Builder((ServerLevel) entity.level())
                    .withParameter(LootContextParams.THIS_ENTITY, entity)
                    .withParameter(LootContextParams.ORIGIN, entity.position())
                    .withParameter(LootContextParams.DAMAGE_SOURCE, entity.damageSources().playerAttack(killer))
                    .withParameter(LootContextParams.ATTACKING_ENTITY, killer)
                    .withParameter(LootContextParams.LAST_DAMAGE_PLAYER, killer)
                   ;

            // 3. 应用抢夺附魔
            if (killer.getMainHandItem().isEnchanted()) {
                builder.withLuck(killer.getLuck());
            }

            LootParams params = builder.create(LootContextParamSets.ENTITY);

            // 4. 获取服务器的掉落表
            LootTable lootTable = entity.level().getServer().reloadableRegistries().getLootTable(lootTableId);

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
