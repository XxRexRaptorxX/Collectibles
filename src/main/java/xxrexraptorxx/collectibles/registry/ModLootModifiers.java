package xxrexraptorxx.collectibles.registry;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.world.modifiers.ChestLootModifier;
import xxrexraptorxx.collectibles.world.modifiers.SuspiciousLootModifier;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, References.MODID);
    public static void init() { LOOT_MODIFIER_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus()); }

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM = LOOT_MODIFIER_SERIALIZERS.register("add_item", ChestLootModifier.CODEC); //UNUSED
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_SUSPICIOUS_ITEM = LOOT_MODIFIER_SERIALIZERS.register("add_suspicious_item", SuspiciousLootModifier.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}