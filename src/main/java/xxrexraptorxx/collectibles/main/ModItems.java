package xxrexraptorxx.collectibles.main;

import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xxrexraptorxx.collectibles.items.ItemCollectible;
import xxrexraptorxx.collectibles.items.ItemLootbag;

public class ModItems {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, References.MODID);


    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<ItemCollectible> GOLD_COIN = ITEMS.register("gold_coin", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> SILVER_COIN = ITEMS.register("silver_coin", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> PLATINUM_COIN = ITEMS.register("platinum_coin", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> NETHERITE_COIN = ITEMS.register("netherite_coin", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> COPPER_COIN = ITEMS.register("copper_coin", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> BRONZE_COIN = ITEMS.register("bronze_coin", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> BRASS_COIN = ITEMS.register("brass_coin", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> IRON_COIN = ITEMS.register("iron_coin", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> STONE_COIN = ITEMS.register("stone_coin", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> DIAMOND_FRAGMENT = ITEMS.register("diamond_fragment", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> EMERALD_FRAGMENT = ITEMS.register("emerald_fragment", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> AMETHYST_FRAGMENT = ITEMS.register("amethyst_fragment", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> RUBY_FRAGMENT = ITEMS.register("ruby_fragment", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> SAPPHIRE_FRAGMENT = ITEMS.register("sapphire_fragment", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> TOPAZ_FRAGMENT = ITEMS.register("topaz_fragment", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> CRYSTAL_FRAGMENT = ITEMS.register("crystal_fragment", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> HEMATITE_FRAGMENT = ITEMS.register("hematite_fragment", ItemCollectible::new);
    public static final RegistryObject<ItemCollectible> TOURMALINE_FRAGMENT = ITEMS.register("tourmaline_fragment", ItemCollectible::new);
    public static final RegistryObject<ItemLootbag> LOOT_BAG = ITEMS.register("loot_bag", ItemLootbag::new);


}