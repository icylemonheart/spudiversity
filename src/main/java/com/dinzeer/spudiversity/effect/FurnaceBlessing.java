package com.dinzeer.spudiversity.effect;

import com.dinzeer.spudiversity.capability.PlayerPermanentAbilities;
import com.ibm.icu.impl.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.dinzeer.spudiversity.regsitry.SpEffect;

import java.util.*;

/**
 * 熔炉祝福效果类，提供自动熔炼物品的功能
 * 当玩家带有此效果时，拾取的物品会自动转化为对应的熔炼产物
 */
@Mod.EventBusSubscriber
public class FurnaceBlessing extends MobEffect {

    public static ThreadLocal<Stack<Pair<ServerPlayer, BlockState>>> PLAYER = ThreadLocal.withInitial(Stack::new);
    public FurnaceBlessing() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFF00); // 黄色效果粒子
    }
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        var players = PLAYER.get();
        if (players.isEmpty()) return;
        ServerPlayer player = players.peek().first;
        if (!(event.getEntity() instanceof ItemEntity e)) return;
        if (player.hasEffect(SpEffect.FurnaceBlessing.get())) {
            ItemStack input = e.getItem().copy();
            SimpleContainer cont = new SimpleContainer(input);
            var opt = event.getLevel().getRecipeManager()
                    .getRecipeFor(RecipeType.SMELTING, cont, event.getLevel());
            if (opt.isPresent()) {
                ItemStack ans = opt.get().assemble(cont, event.getLevel().registryAccess());
                int count = ans.getCount() * input.getCount();
                ans.setCount(count);
                e.setItem(ans);
            }
        }
        player.getCapability(PlayerPermanentAbilities.CAPABILITY).ifPresent(store -> {
            if (store.getActivePotato().equals("fire_dragon_potato")) {
                ItemStack input = e.getItem().copy();
                SimpleContainer cont = new SimpleContainer(input);
                var opt = event.getLevel().getRecipeManager()
                        .getRecipeFor(RecipeType.SMELTING, cont, event.getLevel());
                if (opt.isPresent()) {
                    ItemStack ans = opt.get().assemble(cont, event.getLevel().registryAccess());
                    int count = ans.getCount() * input.getCount();
                    ans.setCount(count);
                    e.setItem(ans);
                }


            }


        }


        );
    }
    public static void pushPlayer(ServerPlayer player, BlockPos pos) {
        PLAYER.get().push(Pair.of(player, player.level().getBlockState(pos)));
    }


    public static void popPlayer(ServerPlayer player) {
        if (PLAYER.get().peek().first == player)
            PLAYER.get().pop();
    }

}