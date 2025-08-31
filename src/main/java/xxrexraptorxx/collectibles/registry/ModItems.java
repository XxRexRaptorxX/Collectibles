package xxrexraptorxx.collectibles.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import xxrexraptorxx.collectibles.items.ItemCollectible;
import xxrexraptorxx.collectibles.items.ItemLootbag;
import xxrexraptorxx.collectibles.main.References;

public class ModItems {

    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(References.MODID);

    public static void init(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final DeferredItem<ItemCollectible> GOLD_COIN =
            ITEMS.register("gold_coin", () -> new ItemCollectible(new Item.Properties().setId(itemId("gold_coin"))));
    public static final DeferredItem<ItemCollectible> SILVER_COIN = ITEMS.register(
            "silver_coin", () -> new ItemCollectible(new Item.Properties().setId(itemId("silver_coin"))));
    public static final DeferredItem<ItemCollectible> PLATINUM_COIN = ITEMS.register(
            "platinum_coin", () -> new ItemCollectible(new Item.Properties().setId(itemId("platinum_coin"))));
    public static final DeferredItem<ItemCollectible> NETHERITE_COIN = ITEMS.register(
            "netherite_coin", () -> new ItemCollectible(new Item.Properties().setId(itemId("netherite_coin"))));
    public static final DeferredItem<ItemCollectible> COPPER_COIN = ITEMS.register(
            "copper_coin", () -> new ItemCollectible(new Item.Properties().setId(itemId("copper_coin"))));
    public static final DeferredItem<ItemCollectible> BRONZE_COIN = ITEMS.register(
            "bronze_coin", () -> new ItemCollectible(new Item.Properties().setId(itemId("bronze_coin"))));
    public static final DeferredItem<ItemCollectible> BRASS_COIN =
            ITEMS.register("brass_coin", () -> new ItemCollectible(new Item.Properties().setId(itemId("brass_coin"))));
    public static final DeferredItem<ItemCollectible> IRON_COIN =
            ITEMS.register("iron_coin", () -> new ItemCollectible(new Item.Properties().setId(itemId("iron_coin"))));
    public static final DeferredItem<ItemCollectible> STONE_COIN =
            ITEMS.register("stone_coin", () -> new ItemCollectible(new Item.Properties().setId(itemId("stone_coin"))));

    public static final DeferredItem<ItemCollectible> DIAMOND_FRAGMENT = ITEMS.register(
            "diamond_fragment", () -> new ItemCollectible(new Item.Properties().setId(itemId("diamond_fragment"))));
    public static final DeferredItem<ItemCollectible> EMERALD_FRAGMENT = ITEMS.register(
            "emerald_fragment", () -> new ItemCollectible(new Item.Properties().setId(itemId("emerald_fragment"))));
    public static final DeferredItem<ItemCollectible> AMETHYST_FRAGMENT = ITEMS.register(
            "amethyst_fragment", () -> new ItemCollectible(new Item.Properties().setId(itemId("amethyst_fragment"))));
    public static final DeferredItem<ItemCollectible> RUBY_FRAGMENT = ITEMS.register(
            "ruby_fragment", () -> new ItemCollectible(new Item.Properties().setId(itemId("ruby_fragment"))));
    public static final DeferredItem<ItemCollectible> SAPPHIRE_FRAGMENT = ITEMS.register(
            "sapphire_fragment", () -> new ItemCollectible(new Item.Properties().setId(itemId("sapphire_fragment"))));
    public static final DeferredItem<ItemCollectible> TOPAZ_FRAGMENT = ITEMS.register(
            "topaz_fragment", () -> new ItemCollectible(new Item.Properties().setId(itemId("topaz_fragment"))));
    public static final DeferredItem<ItemCollectible> CRYSTAL_FRAGMENT = ITEMS.register(
            "crystal_fragment", () -> new ItemCollectible(new Item.Properties().setId(itemId("crystal_fragment"))));
    public static final DeferredItem<ItemCollectible> HEMATITE_FRAGMENT = ITEMS.register(
            "hematite_fragment", () -> new ItemCollectible(new Item.Properties().setId(itemId("hematite_fragment"))));
    public static final DeferredItem<ItemCollectible> TOURMALINE_FRAGMENT = ITEMS.register(
            "tourmaline_fragment",
            () -> new ItemCollectible(new Item.Properties().setId(itemId("tourmaline_fragment"))));

    public static final DeferredItem<ItemCollectible> OLD_BOOK =
            ITEMS.register("old_book", () -> new ItemCollectible(new Item.Properties().setId(itemId("old_book"))));
    public static final DeferredItem<ItemCollectible> NECRONOMICON_BOOK = ITEMS.register(
            "necronomicon_book", () -> new ItemCollectible(new Item.Properties().setId(itemId("necronomicon_book"))));
    public static final DeferredItem<ItemCollectible> KNOWLEDGE_BOOK = ITEMS.register(
            "knowledge_book", () -> new ItemCollectible(new Item.Properties().setId(itemId("knowledge_book"))));
    public static final DeferredItem<ItemCollectible> NOTCHS_BOOK = ITEMS.register(
            "notchs_book", () -> new ItemCollectible(new Item.Properties().setId(itemId("notchs_book"))));
    public static final DeferredItem<ItemCollectible> DARKHOLD_BOOK = ITEMS.register(
            "darkhold_book", () -> new ItemCollectible(new Item.Properties().setId(itemId("darkhold_book"))));
    public static final DeferredItem<ItemCollectible> MONSTER_BOOK = ITEMS.register(
            "monster_book", () -> new ItemCollectible(new Item.Properties().setId(itemId("monster_book"))));
    public static final DeferredItem<ItemCollectible> GRIMOIRE_BOOK = ITEMS.register(
            "grimoire_book", () -> new ItemCollectible(new Item.Properties().setId(itemId("grimoire_book"))));
    public static final DeferredItem<ItemCollectible> CURSED_BOOK = ITEMS.register(
            "cursed_book", () -> new ItemCollectible(new Item.Properties().setId(itemId("cursed_book"))));
    public static final DeferredItem<ItemCollectible> HEROBRINES_BOOK = ITEMS.register(
            "herobrines_book", () -> new ItemCollectible(new Item.Properties().setId(itemId("herobrines_book"))));

    public static final DeferredItem<ItemCollectible> CLAW_FOSSIL = ITEMS.register(
            "claw_fossil", () -> new ItemCollectible(new Item.Properties().setId(itemId("claw_fossil"))));
    public static final DeferredItem<ItemCollectible> LEG_FOSSIL =
            ITEMS.register("leg_fossil", () -> new ItemCollectible(new Item.Properties().setId(itemId("leg_fossil"))));
    public static final DeferredItem<ItemCollectible> AMMONITE_FOSSIL = ITEMS.register(
            "ammonite_fossil", () -> new ItemCollectible(new Item.Properties().setId(itemId("ammonite_fossil"))));
    public static final DeferredItem<ItemCollectible> CRINOID_FOSSIL = ITEMS.register(
            "crinoid_fossil", () -> new ItemCollectible(new Item.Properties().setId(itemId("crinoid_fossil"))));
    public static final DeferredItem<ItemCollectible> TRILOBITE_FOSSIL = ITEMS.register(
            "trilobite_fossil", () -> new ItemCollectible(new Item.Properties().setId(itemId("trilobite_fossil"))));
    public static final DeferredItem<ItemCollectible> SKULL_FOSSIL = ITEMS.register(
            "skull_fossil", () -> new ItemCollectible(new Item.Properties().setId(itemId("skull_fossil"))));
    public static final DeferredItem<ItemCollectible> SPINE_FOSSIL = ITEMS.register(
            "spine_fossil", () -> new ItemCollectible(new Item.Properties().setId(itemId("spine_fossil"))));
    public static final DeferredItem<ItemCollectible> RIP_FOSSIL =
            ITEMS.register("rip_fossil", () -> new ItemCollectible(new Item.Properties().setId(itemId("rip_fossil"))));
    public static final DeferredItem<ItemCollectible> THORAX_FOSSIL = ITEMS.register(
            "thorax_fossil", () -> new ItemCollectible(new Item.Properties().setId(itemId("thorax_fossil"))));

    public static final DeferredItem<ItemCollectible> AMULET_JEWELRY = ITEMS.register(
            "amulet_jewelry", () -> new ItemCollectible(new Item.Properties().setId(itemId("amulet_jewelry"))));
    public static final DeferredItem<ItemCollectible> HAIRPIN_JEWELRY = ITEMS.register(
            "hairpin_jewelry", () -> new ItemCollectible(new Item.Properties().setId(itemId("hairpin_jewelry"))));
    public static final DeferredItem<ItemCollectible> BRACELET_JEWELRY = ITEMS.register(
            "bracelet_jewelry", () -> new ItemCollectible(new Item.Properties().setId(itemId("bracelet_jewelry"))));
    public static final DeferredItem<ItemCollectible> BROOCH_JEWELRY = ITEMS.register(
            "brooch_jewelry", () -> new ItemCollectible(new Item.Properties().setId(itemId("brooch_jewelry"))));
    public static final DeferredItem<ItemCollectible> EARRING_JEWELRY = ITEMS.register(
            "earring_jewelry", () -> new ItemCollectible(new Item.Properties().setId(itemId("earring_jewelry"))));
    public static final DeferredItem<ItemCollectible> DIADEM_JEWELRY = ITEMS.register(
            "diadem_jewelry", () -> new ItemCollectible(new Item.Properties().setId(itemId("diadem_jewelry"))));
    public static final DeferredItem<ItemCollectible> CROWN_JEWELRY = ITEMS.register(
            "crown_jewelry", () -> new ItemCollectible(new Item.Properties().setId(itemId("crown_jewelry"))));
    public static final DeferredItem<ItemCollectible> CHAIN_JEWELRY = ITEMS.register(
            "chain_jewelry", () -> new ItemCollectible(new Item.Properties().setId(itemId("chain_jewelry"))));
    public static final DeferredItem<ItemCollectible> RING_JEWELRY = ITEMS.register(
            "ring_jewelry", () -> new ItemCollectible(new Item.Properties().setId(itemId("ring_jewelry"))));

    public static final DeferredItem<ItemLootbag> LOOT_BAG = ITEMS.register(
            "loot_bag",
            () -> new ItemLootbag(new Item.Properties()
                    .setId(itemId("loot_bag"))
                    .rarity(Rarity.RARE)
                    .stacksTo(16)));
    public static final DeferredItem<ItemLootbag> EPIC_LOOT_BAG = ITEMS.register(
            "epic_loot_bag",
            () -> new ItemLootbag(new Item.Properties()
                    .setId(itemId("epic_loot_bag"))
                    .rarity(Rarity.EPIC)
                    .stacksTo(16)));

    public static ResourceKey<Item> itemId(String name) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(References.MODID, name));
    }
}
