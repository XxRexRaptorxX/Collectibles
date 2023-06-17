package xxrexraptorxx.collectibles.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import xxrexraptorxx.collectibles.main.References;

public class CreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, References.MODID);

    public static void init() { CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus()); }


    public static final RegistryObject<CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register(References.MODID, () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + References.MODID + "_tab"))
            .icon(() -> ModItems.EPIC_LOOT_BAG.get().getDefaultInstance())
            .displayItems((params, output) -> {
                output.accept(ModItems.GOLD_COIN.get());
                output.accept(ModItems.SILVER_COIN.get());
                output.accept(ModItems.PLATINUM_COIN.get());
                output.accept(ModItems.NETHERITE_COIN.get());
                output.accept(ModItems.COPPER_COIN.get());
                output.accept(ModItems.BRONZE_COIN.get());
                output.accept(ModItems.BRASS_COIN.get());
                output.accept(ModItems.IRON_COIN.get());
                output.accept(ModItems.STONE_COIN.get());

                output.accept(ModItems.DIAMOND_FRAGMENT.get());
                output.accept(ModItems.EMERALD_FRAGMENT.get());
                output.accept(ModItems.AMETHYST_FRAGMENT.get());
                output.accept(ModItems.RUBY_FRAGMENT.get());
                output.accept(ModItems.SAPPHIRE_FRAGMENT.get());
                output.accept(ModItems.TOPAZ_FRAGMENT.get());
                output.accept(ModItems.CRYSTAL_FRAGMENT.get());
                output.accept(ModItems.HEMATITE_FRAGMENT.get());
                output.accept(ModItems.TOURMALINE_FRAGMENT.get());

                output.accept(ModItems.OLD_BOOK.get());
                output.accept(ModItems.NECRONOMICON_BOOK.get());
                output.accept(ModItems.KNOWLEDGE_BOOK.get());
                output.accept(ModItems.NOTCHS_BOOK.get());
                output.accept(ModItems.DARKHOLD_BOOK.get());
                output.accept(ModItems.MONSTER_BOOK.get());
                output.accept(ModItems.GRIMOIRE_BOOK.get());
                output.accept(ModItems.CURSED_BOOK.get());
                output.accept(ModItems.HEROBRINES_BOOK.get());

                output.accept(ModItems.CLAW_FOSSIL.get());
                output.accept(ModItems.LEG_FOSSIL.get());
                output.accept(ModItems.AMMONITE_FOSSIL.get());
                output.accept(ModItems.CRINOID_FOSSIL.get());
                output.accept(ModItems.TRILOBITE_FOSSIL.get());
                output.accept(ModItems.SKULL_FOSSIL.get());
                output.accept(ModItems.SPINE_FOSSIL.get());
                output.accept(ModItems.RIP_FOSSIL.get());
                output.accept(ModItems.THORAX_FOSSIL.get());

                output.accept(ModItems.AMULET_JEWELRY.get());
                output.accept(ModItems.HAIRPIN_JEWELRY.get());
                output.accept(ModItems.BRACELET_JEWELRY.get());
                output.accept(ModItems.BROOCH_JEWELRY.get());
                output.accept(ModItems.EARRING_JEWELRY.get());
                output.accept(ModItems.DIADEM_JEWELRY.get());
                output.accept(ModItems.CROWN_JEWELRY.get());
                output.accept(ModItems.CHAIN_JEWELRY.get());
                output.accept(ModItems.RING_JEWELRY.get());

                output.accept(ModItems.LOOT_BAG.get());
                output.accept(ModItems.EPIC_LOOT_BAG.get());
            }).build());
}
