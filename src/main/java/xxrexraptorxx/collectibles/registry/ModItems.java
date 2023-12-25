package xxrexraptorxx.collectibles.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import xxrexraptorxx.collectibles.items.ItemCollectible;
import xxrexraptorxx.collectibles.items.ItemLootbag;
import xxrexraptorxx.collectibles.main.References;

public class ModItems {

    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(References.MODID);


    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final DeferredItem<ItemCollectible> GOLD_COIN = ITEMS.register("gold_coin", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> SILVER_COIN = ITEMS.register("silver_coin", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> PLATINUM_COIN = ITEMS.register("platinum_coin", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> NETHERITE_COIN = ITEMS.register("netherite_coin", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> COPPER_COIN = ITEMS.register("copper_coin", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> BRONZE_COIN = ITEMS.register("bronze_coin", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> BRASS_COIN = ITEMS.register("brass_coin", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> IRON_COIN = ITEMS.register("iron_coin", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> STONE_COIN = ITEMS.register("stone_coin", ItemCollectible::new);

    public static final DeferredItem<ItemCollectible> DIAMOND_FRAGMENT = ITEMS.register("diamond_fragment", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> EMERALD_FRAGMENT = ITEMS.register("emerald_fragment", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> AMETHYST_FRAGMENT = ITEMS.register("amethyst_fragment", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> RUBY_FRAGMENT = ITEMS.register("ruby_fragment", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> SAPPHIRE_FRAGMENT = ITEMS.register("sapphire_fragment", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> TOPAZ_FRAGMENT = ITEMS.register("topaz_fragment", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> CRYSTAL_FRAGMENT = ITEMS.register("crystal_fragment", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> HEMATITE_FRAGMENT = ITEMS.register("hematite_fragment", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> TOURMALINE_FRAGMENT = ITEMS.register("tourmaline_fragment", ItemCollectible::new);

    public static final DeferredItem<ItemCollectible> OLD_BOOK = ITEMS.register("old_book", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> NECRONOMICON_BOOK = ITEMS.register("necronomicon_book", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> KNOWLEDGE_BOOK = ITEMS.register("knowledge_book", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> NOTCHS_BOOK = ITEMS.register("notchs_book", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> DARKHOLD_BOOK = ITEMS.register("darkhold_book", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> MONSTER_BOOK = ITEMS.register("monster_book", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> GRIMOIRE_BOOK = ITEMS.register("grimoire_book", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> CURSED_BOOK = ITEMS.register("cursed_book", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> HEROBRINES_BOOK = ITEMS.register("herobrines_book", ItemCollectible::new);

    public static final DeferredItem<ItemCollectible> CLAW_FOSSIL = ITEMS.register("claw_fossil", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> LEG_FOSSIL = ITEMS.register("leg_fossil", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> AMMONITE_FOSSIL = ITEMS.register("ammonite_fossil", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> CRINOID_FOSSIL = ITEMS.register("crinoid_fossil", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> TRILOBITE_FOSSIL = ITEMS.register("trilobite_fossil", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> SKULL_FOSSIL = ITEMS.register("skull_fossil", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> SPINE_FOSSIL = ITEMS.register("spine_fossil", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> RIP_FOSSIL = ITEMS.register("rip_fossil", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> THORAX_FOSSIL = ITEMS.register("thorax_fossil", ItemCollectible::new);

    public static final DeferredItem<ItemCollectible> AMULET_JEWELRY = ITEMS.register("amulet_jewelry", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> HAIRPIN_JEWELRY = ITEMS.register("hairpin_jewelry", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> BRACELET_JEWELRY = ITEMS.register("bracelet_jewelry", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> BROOCH_JEWELRY = ITEMS.register("brooch_jewelry", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> EARRING_JEWELRY = ITEMS.register("earring_jewelry", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> DIADEM_JEWELRY = ITEMS.register("diadem_jewelry", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> CROWN_JEWELRY = ITEMS.register("crown_jewelry", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> CHAIN_JEWELRY = ITEMS.register("chain_jewelry", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> RING_JEWELRY = ITEMS.register("ring_jewelry", ItemCollectible::new);
/*
    public static final DeferredItem<ItemCollectible> ARROW_LEFTOVER = ITEMS.register("arrow_leftover", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> HOE_LEFTOVER = ITEMS.register("hoe_leftover", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> NAME_TAG_LEFTOVER = ITEMS.register("nametag_leftover", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> PICKAXE_LEFTOVER = ITEMS.register("pickaxe_leftover", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> SHOVEL_LEFTOVER = ITEMS.register("shovel_leftover", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> SPECTRAL_ARROW_LEFTOVER = ITEMS.register("spectral_leftover", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> SWORD_LEFTOVER = ITEMS.register("sword_leftover", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> TRIDENT_LEFTOVER = ITEMS.register("trident_leftover", ItemCollectible::new);
    public static final DeferredItem<ItemCollectible> _LEFTOVER = ITEMS.register("_leftover", ItemCollectible::new);
*/

    public static final DeferredItem<ItemLootbag> LOOT_BAG = ITEMS.register("loot_bag", () -> new ItemLootbag(new Item.Properties().rarity(Rarity.RARE).stacksTo(16)));
    public static final DeferredItem<ItemLootbag> EPIC_LOOT_BAG = ITEMS.register("epic_loot_bag", () -> new ItemLootbag(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16)));


}