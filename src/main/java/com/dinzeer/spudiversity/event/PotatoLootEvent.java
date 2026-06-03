package com.dinzeer.spudiversity.event;

import com.dinzeer.legendreliclib.lib.compat.curios.CurioCompat;
import com.dinzeer.spudiversity.item.speaical.NoNamePotato;
import com.dinzeer.spudiversity.registry.SpItem;
import com.dinzeer.spudiversity.registry.SpPotato;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.Random;

@Mod.EventBusSubscriber
public class PotatoLootEvent {
    private static final String HAS_RECEIVED_FIRST_DIAMOND_TAG = "onPlayerLoggedInGetNonamePotato";
    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onPlayerLoggedInGetNonamePotato(PlayerInteractEvent.EntityInteract event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            CompoundTag persistentData = player.getPersistentData();
            CompoundTag playerData = persistentData.getCompound(ServerPlayer.PERSISTED_NBT_TAG);
            if (!playerData.getBoolean(HAS_RECEIVED_FIRST_DIAMOND_TAG)) {
                ItemStack potato = new ItemStack(SpPotato.NONAME_POTATO.get(), 1);
                if (player.getInventory().add(potato)) {
                    playerData.putBoolean(HAS_RECEIVED_FIRST_DIAMOND_TAG, true);
                    persistentData.put(ServerPlayer.PERSISTED_NBT_TAG, playerData);
                    player.playSound(net.minecraft.sounds.SoundEvents.ITEM_PICKUP,
                            1.0F, 1.0F);
                }else {
                    player.drop(potato, false);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickBed(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();

        Map<Item, Item> potatoConversionMap = Map.of(
                SpPotato.NetherStarPotato.get(), SpPotato.ZzzPotato.get(),
                SpPotato.SakuraPotato.get(), SpPotato.CrystalPotato.get(),
                SpPotato.DIAMOND_POTATO.get(), SpPotato.DiamondCrystalPotato.get()
        );

        Item resultItem = potatoConversionMap.get(heldItem.getItem());
        if (resultItem == null) {
            return;
        }

        boolean isBed = block instanceof BedBlock;
        boolean isBuddingAmethyst = block instanceof BuddingAmethystBlock;

        if (!isBed && !isBuddingAmethyst) {
            return;
        }

        if (level.isClientSide) {
            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
            return;
        }

        performConversion(player, level, pos, heldItem, resultItem, isBed);

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);
    }

    private static void performConversion(Player player, Level level, BlockPos pos,
                                          ItemStack heldItem, Item resultItem, boolean isBed) {
        if (!player.isCreative()) {
            heldItem.shrink(1);
        }

        ItemStack resultStack = new ItemStack(resultItem, 1);
        if (!player.getInventory().add(resultStack)) {
            player.drop(resultStack, false);
        }

        level.playSound(null, pos, SoundEvents.PLAYER_LEVELUP,
                SoundSource.PLAYERS, 0.5F, 1.0F);
        level.gameEvent(player, GameEvent.ENTITY_INTERACT, pos);

        if (isBed && resultItem == SpPotato.ZzzPotato.get() && player instanceof ServerPlayer serverPlayer) {
            serverPlayer.sendSystemMessage(Component.literal("Good Morning Rider!").withStyle(ChatFormatting.GOLD));
        }
    }








    // 苹果土豆：挖土豆时概率获取
    @SubscribeEvent
    public static void onHarvestPotato(BlockEvent.BreakEvent event) {
        if (event.getState().getBlock() == Blocks.POTATOES && event.getState().getValue(CropBlock.AGE) == 7) {
            if (RANDOM.nextFloat() < 0.05f) { // 5% 概率
                Level level = (Level) event.getLevel();
                if (!level.isClientSide()) {
                    level.addFreshEntity(new ItemEntity(level,
                            event.getPos().getX() + 0.5,
                            event.getPos().getY() + 0.5,
                            event.getPos().getZ() + 0.5,
                            new ItemStack(SpPotato.ApplePotato.get(), 1)));

                    // 播放获得音效
                    if (event.getPlayer() != null) {
                        event.getPlayer().playSound(net.minecraft.sounds.SoundEvents.ITEM_PICKUP, 0.5F, 1.0F);
                    }
                }
            }
        }
    }

    // 眼球土豆：村民/玩家死亡时概率掉落
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Villager || event.getEntity() instanceof Player) {
            if (RANDOM.nextFloat() < 0.1f) { // 10% 概率
                Level level = event.getEntity().level();
                if (!level.isClientSide()) {
                    level.addFreshEntity(new ItemEntity(level,
                            event.getEntity().getX(),
                            event.getEntity().getY(),
                            event.getEntity().getZ(),
                            new ItemStack(SpPotato.EyesPotato.get(), 1)));
                }
            }
        }
        if (event.getEntity() instanceof WitherBoss) {
            if (event.getSource().getEntity() instanceof Player player){
                if (player.getOffhandItem().getItem() instanceof NoNamePotato){
                    player.getOffhandItem().shrink(1);
                    player.getInventory().offhand.set(0, SpPotato.NoNamePotatoDark.get().getDefaultInstance());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityDeathByElderGuardian(LivingDeathEvent event) {
        if (event.getEntity() instanceof ElderGuardian) {

                Level level = event.getEntity().level();
                if (!level.isClientSide()) {
                    level.addFreshEntity(new ItemEntity(level,
                            event.getEntity().getX(),
                            event.getEntity().getY(),
                            event.getEntity().getZ(),
                            new ItemStack(SpItem.WaveCore.get(), 1)));
                }
            }
        if (event.getEntity() instanceof Rabbit) {
            if (event.getSource().is(DamageTypes.EXPLOSION)||event.getSource().is(DamageTypes.PLAYER_EXPLOSION)){
            Level level = event.getEntity().level();
            if (!level.isClientSide()) {
                level.addFreshEntity(new ItemEntity(level,
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        new ItemStack(SpItem.rabbit.get(), 1)));
            }
            }
        }
        if (event.getEntity() instanceof Turtle) {
            if (event.getSource().is(DamageTypes.EXPLOSION)||event.getSource().is(DamageTypes.PLAYER_EXPLOSION)){
                Level level = event.getEntity().level();
                if (!level.isClientSide()) {
                    level.addFreshEntity(new ItemEntity(level,
                            event.getEntity().getX(),
                            event.getEntity().getY(),
                            event.getEntity().getZ(),
                            new ItemStack(SpItem.tank.get(), 1)));
                }
            }
        }
    }


    // 樱吹雪土豆：在繁花森林对土豆苗使用骨粉有5%概率获得
    @SubscribeEvent
    public static void onBoneMealUsed(BonemealEvent event) {
        if (event.getBlock().getBlock() == Blocks.POTATOES &&
                event.getLevel().getBiome(event.getPos()).is(Biomes.FLOWER_FOREST)) {

            if (RANDOM.nextFloat() < 0.05f) { // 5% 概率
                Level level = (Level) event.getLevel();
                if (!level.isClientSide()) {
                    // 破坏土豆苗并掉落樱吹雪土豆
                    level.destroyBlock(event.getPos(), false);
                    level.addFreshEntity(new ItemEntity(level,
                            event.getPos().getX() + 0.5,
                            event.getPos().getY() + 0.5,
                            event.getPos().getZ() + 0.5,
                            new ItemStack(SpPotato.SakuraPotato.get(), RANDOM.nextInt(2) + 1))); // 1-2个

                    // 播放樱花粒子效果
                    spawnSakuraParticles(level, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());

                    if (event.getEntity() != null) {
                        event.getEntity().playSound(net.minecraft.sounds.SoundEvents.BEEHIVE_SHEAR, 0.5F, 1.0F);
                    }
                }
                event.setCanceled(true); // 阻止原版骨粉效果
            }
        }
    }

    // 冰冻土豆：用烤土豆右键雪傀儡加工获得
    @SubscribeEvent
    public static void onRightClickSnowGolem(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof SnowGolem &&
                event.getItemStack().getItem() == Items.BAKED_POTATO) {

            if (!event.getLevel().isClientSide()) {
                // 消耗烤土豆
                if (!event.getEntity().getAbilities().instabuild) {
                    event.getItemStack().shrink(1);
                }

                // 给予冰冻土豆
                ItemStack icePotato = new ItemStack(SpPotato.IcePotato.get(), 1);
                if (!event.getEntity().getInventory().add(icePotato)) {
                    event.getEntity().spawnAtLocation(icePotato, 1.0F);
                }

                // 播放冰冻音效和粒子
                event.getEntity().playSound(net.minecraft.sounds.SoundEvents.GLASS_BREAK, 1.0F, 1.0F);
                spawnIceParticles(event.getLevel(), event.getTarget().getX(), event.getTarget().getY(), event.getTarget().getZ());
            }
            event.setCanceled(true);
        }
    }




    // 竹筒土豆：对熊猫喂食竹子后，用苹果土豆右键熊猫
    @SubscribeEvent
    public static void onRightClickPanda(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof Panda panda &&
                event.getItemStack().getItem() == SpPotato.ApplePotato.get()) {

            // 检查熊猫是否刚刚被喂食过竹子（通过NBT标记）
            CompoundTag pandaData = panda.getPersistentData();
            if (pandaData.getBoolean("wasFedBamboo")) {
                if (!event.getLevel().isClientSide()) {
                    // 消耗苹果土豆
                    if (!event.getEntity().getAbilities().instabuild) {
                        event.getItemStack().shrink(1);
                    }

                    // 给予竹筒土豆
                    ItemStack bambooPotato = new ItemStack(SpPotato.BambooPotato.get(), 1);
                    if (!event.getEntity().getInventory().add(bambooPotato)) {
                        event.getEntity().spawnAtLocation(bambooPotato, 1.0F);
                    }

                    // 播放熊猫高兴音效
                    event.getEntity().playSound(net.minecraft.sounds.SoundEvents.PANDA_EAT, 1.0F, 1.0F);

                    // 重置标记
                    pandaData.putBoolean("wasFedBamboo", false);
                }
                event.setCanceled(true);
            }
        }

        // 标记喂食竹子的熊猫
        if (event.getTarget() instanceof Panda panda &&
                event.getItemStack().getItem() == Items.BAMBOO) {
            panda.getPersistentData().putBoolean("wasFedBamboo", true);
        }
    }

    // 疾风记忆体：凋零在200米高度击杀任意生物有30%概率掉落
    @SubscribeEvent
    public static void onWitherKillAtHeight(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof net.minecraft.world.entity.boss.wither.WitherBoss wither &&
                wither.getY() > 200 &&
                RANDOM.nextFloat() < 0.3f) {

            Level level = wither.level();
            if (!level.isClientSide()) {

                 level.addFreshEntity(new ItemEntity(level, wither.getX(), wither.getY(), wither.getZ(),
                     new ItemStack(SpItem.Cyclone.get(), 1)));


            }
        }
    }

    // 王牌记忆体：空手击杀100血以上生物概率掉落
    @SubscribeEvent
    public static void onBarehandKill(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player &&
                player.getMainHandItem().isEmpty() && // 空手
                event.getEntity().getMaxHealth() >= 100.0F &&
                RANDOM.nextFloat() < 0.4f) { // 40% 概率

            Level level = player.level();
            if (!level.isClientSide()) {

                 level.addFreshEntity(new ItemEntity(level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
                     new ItemStack(SpItem.Joker.get(), 1)));
            }
        }
    }

    // 粒子效果方法
    private static void spawnSakuraParticles(Level level, double x, double y, double z) {
        if (level.isClientSide()) return;

        for (int i = 0; i < 20; i++) {
            ((net.minecraft.server.level.ServerLevel) level).sendParticles(
                    net.minecraft.core.particles.ParticleTypes.CHERRY_LEAVES,
                    x + (RANDOM.nextDouble() - 0.5) * 2,
                    y + RANDOM.nextDouble() * 2,
                    z + (RANDOM.nextDouble() - 0.5) * 2,
                    1, 0, 0, 0, 0
            );
        }
    }

    private static void spawnIceParticles(Level level, double x, double y, double z) {
        if (level.isClientSide()) return;

        for (int i = 0; i < 15; i++) {
            ((net.minecraft.server.level.ServerLevel) level).sendParticles(
                    net.minecraft.core.particles.ParticleTypes.SNOWFLAKE,
                    x + (RANDOM.nextDouble() - 0.5) * 2,
                    y + RANDOM.nextDouble() * 2,
                    z + (RANDOM.nextDouble() - 0.5) * 2,
                    1, 0, 0, 0, 0
            );
        }
    }

    private static void spawnFireParticles(Level level, double x, double y, double z) {
        if (level.isClientSide()) return;

        for (int i = 0; i < 15; i++) {
            ((net.minecraft.server.level.ServerLevel) level).sendParticles(
                    net.minecraft.core.particles.ParticleTypes.FLAME,
                    x + (RANDOM.nextDouble() - 0.5) * 2,
                    y + RANDOM.nextDouble() * 2,
                    z + (RANDOM.nextDouble() - 0.5) * 2,
                    1, 0, 0, 0, 0
            );
        }
    }















    @SubscribeEvent
    public static void onTangxingPotatoEaten(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity().level().isClientSide) return;
        if (!(event.getEntity() instanceof Player player)) return;

        if (!event.getItem().is(SpPotato.TangxingPotato.get())) return;


        float GameTime=player.level().getGameTime();
        int requiredTicks = 99 * 3600;
        if (GameTime < requiredTicks) return;

        if (!CurioCompat.hasItemInCurio(player, SpPotato.CakeKingPotato.get())) return;

        ItemStack jijin = new ItemStack(SpPotato.JijinPotato.get());
        if (!player.addItem(jijin)) {
            player.drop(jijin, false);
        }

    }

    @SubscribeEvent
    public static void onStarPotatoEaten(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity().level().isClientSide) return;
        if (!(event.getEntity() instanceof Player player)) return;

        if (!event.getItem().is(SpPotato.StarPotato.get())) return;
        if(event.getEntity().level().dimension() == Level.NETHER){
            if(player.getOffhandItem().getItem() == Items.NETHER_STAR){
                ItemStack the_end_core = new ItemStack(SpItem.the_end_core.get());
                if (!player.addItem(the_end_core)) {
                    player.drop(the_end_core, false);
                }
            }
        }

    }






}