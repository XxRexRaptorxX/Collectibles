package xxrexraptorxx.collectibles.registry;

import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.world.modifiers.ChestLootModifier;
import xxrexraptorxx.collectibles.world.modifiers.SuspiciousLootModifier;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, References.MODID);

    public static void init(IEventBus eventBus) { LOOT_MODIFIER_SERIALIZERS.register(eventBus); }


    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<ChestLootModifier>> ADD_ITEM = LOOT_MODIFIER_SERIALIZERS.register("add_item", ChestLootModifier.CODEC);
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<? extends IGlobalLootModifier>> ADD_SUSPICIOUS_ITEM = LOOT_MODIFIER_SERIALIZERS.register("add_suspicious_item", SuspiciousLootModifier.CODEC);


    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}