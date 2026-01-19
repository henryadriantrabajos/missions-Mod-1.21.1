package net.thebins.misionescomarca.common.data;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.thebins.misionescomarca.MisionesComarca;

import java.util.function.Supplier;

public class ModAttachments {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MisionesComarca.MOD_ID);

    public static final Supplier<AttachmentType<PlayerMissionData>> PLAYER_MISSIONS =
            ATTACHMENTS.register(
                    "player_missions",
                    () -> AttachmentType.serializable(PlayerMissionData::new).build()
            );
}
