package com.dinzeer.spudiversity.item.curios;

import com.dinzeer.legendreliclib.lib.key.LRModKeyMappings;
import com.dinzeer.legendreliclib.lib.key.SpecialTypeKeyEvent;
import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.spudiversity.registry.SpPotato;
import com.dinzeer.spudiversity.util.CurioUtil;
import com.dinzeer.spudiversity.util.lang.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

@Mod.EventBusSubscriber
public class SonicPotato extends Item implements ICurioItem {
    public SonicPotato(Properties properties) {
        super(properties.food((new FoodProperties.Builder())
                        .nutrition(6)
                        .saturationMod(1f)
                        .alwaysEat()
                        .build())
                .stacksTo(1));
    }

    @SubscribeEvent
    public static void OnKetDown(SpecialTypeKeyEvent.SpecialTypeKey3ReleasedEvent event){
        LivingEntity entity=event.getEntity();
       List<ItemStack> curios= CurioUtil.getCurioItems(event.getEntity());
       for (ItemStack item : curios){
           if (item.getItem() instanceof SonicPotato){
               var range = 20;
               Vec3 start = entity.getEyePosition();
               Vec3 end = start.add(entity.getForward().scale(range));


               AABB boundingBox = entity.getBoundingBox().expandTowards(end.subtract(start));

               List<LivingEntity> entities = entity.level().getEntitiesOfClass(LivingEntity.class, boundingBox);


               for (LivingEntity target : entities) {
                   if (target!=entity){
                   target.hurt( FastMakeEntityUtil.DamageSourceToCreat(entity, DamageTypes.MAGIC), (float) entity.getAttributeValue(Attributes.ATTACK_DAMAGE));
                   }
               }


               Vec3 vec3 = entity.getLookAngle().normalize();
               for (int i = 0; i < range; i++) {
                   var vec32 = vec3.scale(i).add(entity.getEyePosition());

                   spawnParticles(entity.level(), ParticleTypes.SONIC_BOOM, vec32.x, vec32.y, vec32.z, 1, 0, 0, 0, 0, false);
               }
           }
       }
    }
    public static void spawnParticles(Level level, ParticleOptions particle, double x, double y, double z, int count, double deltaX, double deltaY, double deltaZ, double speed, boolean force) {
        level.getServer().getPlayerList().getPlayers().forEach(player -> ((ServerLevel) level).sendParticles(player, particle, force, x, y, z, count, deltaX, deltaY, deltaZ, speed));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
        tooltipList.add(com.dinzeer.spudiversity.util.CharmPotatoes.flavor(
                LangUtil.FastMakeItemTooltip(SpPotato.SonicPotato, 0)));
        tooltipList.add(com.dinzeer.spudiversity.util.CharmPotatoes.effectsHeader());
        tooltipList.add(com.dinzeer.spudiversity.util.CharmPotatoes.body(
                LangUtil.FastMakeItemTooltip(SpPotato.SonicPotato, 1),
                Component.literal(LRModKeyMappings.SpecialTypeKey3.getKey().getDisplayName().getString())
                        .withStyle(ChatFormatting.GREEN)));
    }


}
