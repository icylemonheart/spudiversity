package com.dinzeer.spudiversity.network;


import com.dinzeer.spudiversity.Spudiversity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class SpuNetwork {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Spudiversity.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    
    public static int packetId = 0;
    
    public static void register() {
        if (ModList.get().isLoaded("twilightforest")) {
            INSTANCE.registerMessage(packetId++, SpawnEnderPaket.class,
                    SpawnEnderPaket::encode,
                    SpawnEnderPaket::new,
                    SpawnEnderPaket::handle);
        }
        if (ModList.get().isLoaded("botania")) {
            INSTANCE.registerMessage(packetId++, SpawnTerraBladePaket.class,
                    SpawnTerraBladePaket::encode,
                    SpawnTerraBladePaket::new,
                    SpawnTerraBladePaket::handle);
        }
        INSTANCE.registerMessage(packetId++, PlayTotemAnimationPacket.class,
                PlayTotemAnimationPacket::encode,
                PlayTotemAnimationPacket::decode,
                PlayTotemAnimationPacket::handle);
    }
    
    public static void sendToServer(Object msg) {
        INSTANCE.sendToServer(msg);
    }
}