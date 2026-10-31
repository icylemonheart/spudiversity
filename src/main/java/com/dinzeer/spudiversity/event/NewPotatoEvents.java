package com.dinzeer.spudiversity.event;

import com.dinzeer.spudiversity.registry.SpPotato;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber
public class NewPotatoEvents {
    private static final Map<ResourceKey<Level>, Map<BlockPos, Integer>> TNT_USES = new HashMap<>();

    @SubscribeEvent
    public static void onTntPotatoConversion(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockState state = level.getBlockState(event.getPos());
        if (!state.is(Blocks.TNT) || !event.getItemStack().is(Items.BAKED_POTATO)) {
            return;
        }

        if (level.isClientSide) {
            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
            return;
        }
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        Player player = event.getEntity();
        ItemStack heldItem = event.getItemStack();
        if (!player.isCreative()) {
            heldItem.shrink(1);
        }
        give(player, SpPotato.TntPotato.get());

        Map<BlockPos, Integer> uses = TNT_USES.computeIfAbsent(level.dimension(), key -> new HashMap<>());
        BlockPos pos = event.getPos();
        int used = uses.getOrDefault(pos, 0) + 1;
        if (used >= 3) {
            uses.remove(pos);
            if (uses.isEmpty()) {
                TNT_USES.remove(level.dimension());
            }
            igniteTnt(serverLevel, pos);
        } else {
            uses.put(pos, used);
        }

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onChargedCreeperPotatoConversion(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Creeper creeper) || !creeper.isPowered()) {
            return;
        }
        if (!event.getItemStack().is(SpPotato.CreeperPotato.get())) {
            return;
        }

        if (event.getLevel().isClientSide) {
            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
            return;
        }

        Player player = event.getEntity();
        ItemStack heldItem = event.getItemStack();
        if (!player.isCreative()) {
            heldItem.shrink(1);
        }
        give(player, SpPotato.ChargedCreeperPotato.get());

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onCreeperDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Creeper creeper)) {
            return;
        }
        if (!(event.getSource().getEntity() instanceof Player)) {
            return;
        }

        Level level = creeper.level();
        if (level.isClientSide || level.random.nextFloat() >= 0.01F) {
            return;
        }

        Item drop = creeper.isPowered()
                ? SpPotato.ChargedCreeperPotato.get()
                : SpPotato.CreeperPotato.get();
        level.addFreshEntity(new ItemEntity(level, creeper.getX(), creeper.getY(), creeper.getZ(),
                new ItemStack(drop)));
    }

    @SubscribeEvent
    public static void onTntBreak(BlockEvent.BreakEvent event) {
        if (!event.getState().is(Blocks.TNT) || !(event.getLevel() instanceof Level level)) {
            return;
        }
        Map<BlockPos, Integer> uses = TNT_USES.get(level.dimension());
        if (uses == null) {
            return;
        }
        uses.remove(event.getPos());
        if (uses.isEmpty()) {
            TNT_USES.remove(level.dimension());
        }
    }

    private static void give(Player player, Item item) {
        ItemStack stack = new ItemStack(item);
        if (!player.getInventory().add(stack)) {
            player.drop(stack, false);
        }
    }

    private static void igniteTnt(ServerLevel level, BlockPos pos) {
        level.removeBlock(pos, false);
        PrimedTnt tnt = new PrimedTnt(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, null);
        tnt.setFuse(80);
        level.addFreshEntity(tnt);
    }
}
