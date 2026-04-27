package com.dinzeer.spudiversity.Item.curios;

import com.dinzeer.legendreliclib.effect.LReffect;
import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.legendreliclib.lib.util.WaitingTick;
import com.dinzeer.spudiversity.Item.speaical.TangxingPotato;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import com.dinzeer.spudiversity.regsitry.SpPotato;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import mods.flammpfeil.slashblade.registry.ModAttributes;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;
@Mod.EventBusSubscriber
public class BuildPotato extends Item implements ICurioItem {
    private static final UUID BuildPotato_UUID = UUID.nameUUIDFromBytes("BuildPotatoPotao".getBytes());
    public BuildPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                .nutrition(9)
                .saturationMod(1f)
                .build()));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        if (slotContext.entity() instanceof ServerPlayer player){
            FastMakeEntityUtil.FastEffect(player, LReffect.SuperSpeed.get(),1);
            if (!player.hasEffect(LReffect.SuperJump.get())){
            FastMakeEntityUtil.FastEffect(player, LReffect.SuperJump.get(),1);
            }
            if (!player.hasEffect(MobEffects.REGENERATION)){
            FastMakeEntityUtil.FastEffect(player, MobEffects.REGENERATION,1);
            }
            FastMakeEntityUtil.FastEffect(player, MobEffects.DAMAGE_RESISTANCE,2);
        }
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();
        modifiers.put(
                Attributes.KNOCKBACK_RESISTANCE,
                new AttributeModifier(
                        BuildPotato_UUID,
                        "LootKingPotao Boost",
                        1,
                        AttributeModifier.Operation.ADDITION
                )
        );
        modifiers.put(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(
                        BuildPotato_UUID,
                        "LootKingPotao Boost",
                        -0.2,
                        AttributeModifier.Operation.MULTIPLY_TOTAL
                )
        );

        return modifiers;
    }
   public static int getThreshold(LivingEntity user){
       if (user instanceof Player player){
           player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store -> {
               if (store.getActivePotato().equals("for_love")&&store.getActivePotatoOff().equals(TangxingPotato.CRYSTAL_TYPE)) {
                   return;
               }
           });
       }
       return 70;
   }

    @SubscribeEvent
    public static void OnLivingHurt(LivingHurtEvent event) {
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
                            // 横扫之刃粒子
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
