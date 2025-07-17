package xxrexraptorxx.collectibles.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import xxrexraptorxx.collectibles.main.References;

import java.util.function.Supplier;

public class ModStats {

    public static final DeferredRegister<ResourceLocation> CUSTOM_STATS = DeferredRegister.create(BuiltInRegistries.CUSTOM_STAT, References.MODID);

    public static final Supplier<ResourceLocation> COLLECTIBLES_FOUND = CUSTOM_STATS.register("found_collectibles", () -> ResourceLocation.fromNamespaceAndPath(References.MODID, "found_collectibles"));

    public static void init(IEventBus eventBus) {
        CUSTOM_STATS.register(eventBus);
    }
}
