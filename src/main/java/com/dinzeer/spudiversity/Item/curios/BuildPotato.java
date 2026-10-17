package com.dinzeer.spudiversity.item.curios;

import net.neoforged.fml.common.EventBusSubscriber;

import com.dinzeer.legendreliclib.effect.LReffect;
import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.legendreliclib.lib.util.WaitingTick;
import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.item.speaical.TangxingPotato;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
@EventBusSubscriber
public class BuildPotato extends Item implements ICurioItem {
    private static final ResourceLocation BUILD_POTATO_ID = ResourceLocation.fromNamespaceAndPath(Spudiversity.MODID, "build_potato");

    public BuildPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(9)
                .saturationModifier(1f)
                .build()));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        if (slotContext.entity() instanceof ServerPlayer player){
            FastMakeEntityUtil.FastEffect(player, LReffect.SuperSpeed,1);
            if (!player.hasEffect(LReffect.SuperJump)){
            FastMakeEntityUtil.FastEffect(player, LReffect.SuperJump,1);
            }
            if (!player.hasEffect(MobEffects.REGENERATION)){
            FastMakeEntityUtil.FastEffect(player, MobEffects.REGENERATION,1);
            }
            FastMakeEntityUtil.FastEffect(player, MobEffects.DAMAGE_RESISTANCE,2);
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = LinkedHashMultimap.create();
        modifiers.put(
                Attributes.KNOCKBACK_RESISTANCE,
                new AttributeModifier(
                        BUILD_POTATO_ID,
                        1,
                        AttributeModifier.Operation.ADD_VALUE
                )
        );
        modifiers.put(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(
                        BUILD_POTATO_ID,
                        -0.2,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        );

        return modifiers;
    }

    public static int getThreshold(LivingEntity user){
        if (user instanceof Player player){
            PlayerPermanentAbilities store = player.getCapability(PlayerPermanentAbilities.CAPABILITY);
            if (store != null
                    && store.getActivePotato().equals("for_love")
                    && store.getActivePotatoOff().equals(TangxingPotato.CRYSTAL_TYPE)) {
                return -1;
            }
        }
        return 70;
    }

    @SubscribeEvent
    public static void OnLivingHurt(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity user){
            if (!event.getSource().is(DamageTypes.PLAYER_ATTACK))return;
            if (CurioCompat.hasItemInCurio(user, SpPotato.BuildPotato.get())){
            List<LivingEntity> entityList =user.level().getEntitiesOfClass(LivingEntity.class, user.getBoundingBox().inflate(7));
            int i=0;

                int threshold=getThreshold(user);

            for (LivingEntity entity : entityList){
                i++;

                WaitingTick.schedule(i, () -> {
                    if (entity.getRandom().nextInt(100)>threshold){
                        if (entity==user){
                            return;
                        }

                        FastMakeEntityUtil.FastHurt(entity, user, event.getAmount()*0.01f, DamageTypes.PLAYER_EXPLOSION);
                        if (entity.level() instanceof ServerLevel serverLevel) {
                            // 爆炸粒子
                            serverLevel.sendParticles(
                                    ParticleTypes.EXPLOSION,
                                    entity.getX(),
                                    entity.getY(),
                                    entity.getZ(),
                                    3, // 数量
                                    0.1, 0.1, 0.1, // 偏移
                                    0.0 // 速度
                            );
                            serverLevel.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                                    SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0F);
                        }

                    }


                });
            }
            i=0;
            }

        }

    }
}
