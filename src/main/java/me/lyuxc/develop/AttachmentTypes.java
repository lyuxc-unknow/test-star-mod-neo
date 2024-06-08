package me.lyuxc.develop;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class AttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Variables.MOD_ID);
    public static final Supplier<AttachmentType<Integer>> DIGGING_ATTACHMENT = ATTACHMENT_TYPES.register("digging_value", () -> AttachmentType.builder(() -> 0)
            .serialize(Codec.INT)
            .build());
    public static final Supplier<AttachmentType<Integer>> THROTTLED_TIMER_ATTACHMENT = ATTACHMENT_TYPES.register("throttled_timer_value", () -> AttachmentType.builder(() -> 0)
            .serialize(Codec.INT)
            .build());
    public static final Supplier<AttachmentType<Boolean>> THROTTLED_ATTACHMENT = ATTACHMENT_TYPES.register("throttled_value", () -> AttachmentType.builder(() -> false)
            .serialize(Codec.BOOL)
            .build());
    public static final Supplier<AttachmentType<Integer>> INF_ATTACHMENT = ATTACHMENT_TYPES.register("inf_value", () -> AttachmentType.builder(() -> 0)
            .serialize(Codec.INT)
            .copyOnDeath()
            .build());
}
