package com.dinzeer.spudiversity.capability;

import com.dinzeer.spudiversity.Spudiversity;
import com.dinzeer.spudiversity.capability.impl.PlayerKillBossAbilities;
import com.dinzeer.spudiversity.capability.impl.PlayerPermanentAbilities;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class SpAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Spudiversity.MODID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<PlayerPermanentAbilities>> PERMANENT_ABILITIES =
            ATTACHMENT_TYPES.register("permanent_abilities",
                    () -> AttachmentType.serializable(PlayerPermanentAbilities::new).copyOnDeath().build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<PlayerKillBossAbilities>> KILL_BOSS_ABILITIES =
            ATTACHMENT_TYPES.register("kill_bosses_abilities",
                    () -> AttachmentType.serializable(PlayerKillBossAbilities::new).copyOnDeath().build());
}
