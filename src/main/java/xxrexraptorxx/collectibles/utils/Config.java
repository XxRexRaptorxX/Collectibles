package xxrexraptorxx.collectibles.utils;

import java.util.Arrays;
import java.util.List;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.ModConfigSpec;
import xxrexraptorxx.magmacore.config.ConfigHelper;
import xxrexraptorxx.magmacore.config.ConfigListHelper;

public class Config {

    private static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec SERVER_CONFIG;

    private static final ModConfigSpec.IntValue LOOT_BAG_XP;
    private static final ModConfigSpec.IntValue EPIC_LOOT_BAG_XP;
    private static ModConfigSpec.ConfigValue<List<? extends String>> LOOT_BAG_REWARDS;
    private static ModConfigSpec.ConfigValue<List<? extends String>> EPIC_LOOT_BAG_REWARDS;
    private static final ModConfigSpec.IntValue LOOT_BAG_ITEM_AMOUNT;
    private static final ModConfigSpec.IntValue EPIC_LOOT_BAG_ITEM_AMOUNT;
    private static final ModConfigSpec.IntValue COLLECTIBLES_XP;
    private static final ModConfigSpec.IntValue FRAGMENT_COLLECTIBLE_RARITY;
    private static final ModConfigSpec.IntValue FOSSIL_COLLECTIBLE_RARITY;
    private static final ModConfigSpec.IntValue COIN_COLLECTIBLE_RARITY;
    private static final ModConfigSpec.BooleanValue LUCK_FOR_COLLECTIBLES;
    private static final ModConfigSpec.BooleanValue COLLECTIBLES_DIRECTLY_INTO_INVENTORY;

    static {
        ConfigHelper.setCategory(SERVER_BUILDER, "collectibles");
        COLLECTIBLES_XP = SERVER_BUILDER
                .comment("How much XP a player should get when finding a collectible")
                .defineInRange("collectible_xp", 30, 0, 1000);
        LUCK_FOR_COLLECTIBLES = SERVER_BUILDER
                .comment("Should you get the Luck effect when you find a collectible?")
                .define("luck_for_collectibles", true);
        COLLECTIBLES_DIRECTLY_INTO_INVENTORY = SERVER_BUILDER
                .comment(
                        "Should you get the collectible directly into your inventory when you find it instead of it dropping?")
                .define("collectibles_directly_into_inventory", false);
        COIN_COLLECTIBLE_RARITY = SERVER_BUILDER
                .comment("How rarely coin collectibles drop. Higher = rarer [1:X]")
                .defineInRange("coin_collectibles_rarity", 1000, 1, 100000);
        FRAGMENT_COLLECTIBLE_RARITY = SERVER_BUILDER
                .comment("How rarely fragment collectibles drop. Higher = rarer [1:X]")
                .defineInRange("fragment_collectibles_rarity", 1000, 1, 100000);
        FOSSIL_COLLECTIBLE_RARITY = SERVER_BUILDER
                .comment("How rarely fossil collectibles drop. Higher = rarer [1:X]")
                .defineInRange("fossil_collectibles_rarity", 1000, 1, 100000);
        SERVER_BUILDER.pop();

        ConfigHelper.setCategory(SERVER_BUILDER, "loot_bags");
        EPIC_LOOT_BAG_XP = SERVER_BUILDER
                .comment("How much XP a player gets from an epic loot bag")
                .defineInRange("epic_loot_bag_xp", 100, 0, 1000);
        EPIC_LOOT_BAG_ITEM_AMOUNT = SERVER_BUILDER
                .comment("How many item entries the epic loot bag gives")
                .defineInRange("epic_loot_bag_item_amount", 3, 1, 10);
        EPIC_LOOT_BAG_REWARDS = SERVER_BUILDER
                .comment("Epic loot bag rewards [amount*modid:item]")
                .defineListAllowEmpty(
                        "epic_loot_bag_rewards",
                        Arrays.asList(
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.NETHERITE_INGOT),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.NETHER_STAR),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.END_CRYSTAL),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.ELYTRA),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.TOTEM_OF_UNDYING),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.ENCHANTED_GOLDEN_APPLE),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.HEAVY_CORE),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.HEART_OF_THE_SEA),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.OMINOUS_TRIAL_KEY),
                                "3*" + BuiltInRegistries.ITEM.getKey(Items.NETHERITE_SCRAP),
                                "4*" + BuiltInRegistries.ITEM.getKey(Items.ECHO_SHARD),
                                "5*" + BuiltInRegistries.ITEM.getKey(Items.DRAGON_BREATH),
                                "10*" + BuiltInRegistries.ITEM.getKey(Items.DIAMOND)),
                        () -> "amount*modid:item",
                        obj -> obj instanceof String string && isValidLootEntry(string));
        LOOT_BAG_XP = SERVER_BUILDER
                .comment("How much XP a player gets from a loot bag")
                .defineInRange("loot_bag_xp", 50, 0, 1000);
        LOOT_BAG_ITEM_AMOUNT = SERVER_BUILDER
                .comment("How many item entries the loot bag gives")
                .defineInRange("loot_bag_item_amount", 1, 1, 10);
        LOOT_BAG_REWARDS = SERVER_BUILDER
                .comment("Loot bag rewards [amount*modid:item]")
                .defineListAllowEmpty(
                        "loot_bag_rewards",
                        Arrays.asList(
                                "3*" + BuiltInRegistries.ITEM.getKey(Items.EMERALD),
                                "3*" + BuiltInRegistries.ITEM.getKey(Items.DIAMOND),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.GOLDEN_APPLE),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.ENDER_EYE),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.ECHO_SHARD),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.BREEZE_ROD),
                                "3*" + BuiltInRegistries.ITEM.getKey(Items.WIND_CHARGE),
                                "3*" + BuiltInRegistries.ITEM.getKey(Items.FIRE_CHARGE),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.BLAZE_ROD),
                                "3*" + BuiltInRegistries.ITEM.getKey(Items.EXPERIENCE_BOTTLE),
                                "1*" + BuiltInRegistries.ITEM.getKey(Items.TRIAL_KEY)),
                        () -> "amount*modid:item",
                        obj -> obj instanceof String string && isValidLootEntry(string));
        SERVER_BUILDER.pop();

        SERVER_CONFIG = SERVER_BUILDER.build();
    }

    public static int getCollectiblesXp() {
        return COLLECTIBLES_XP.get();
    }

    public static int getCoinCollectibleRarity() {
        return COIN_COLLECTIBLE_RARITY.get();
    }

    public static int getFragmentCollectibleRarity() {
        return FRAGMENT_COLLECTIBLE_RARITY.get();
    }

    public static int getFossilCollectibleRarity() {
        return FOSSIL_COLLECTIBLE_RARITY.get();
    }

    public static int getLootBagXp() {
        return LOOT_BAG_XP.get();
    }

    public static int getEpicLootBagXp() {
        return EPIC_LOOT_BAG_XP.get();
    }

    public static int getLootBagItemAmount() {
        return LOOT_BAG_ITEM_AMOUNT.get();
    }

    public static int getEpicLootBagItemAmount() {
        return EPIC_LOOT_BAG_ITEM_AMOUNT.get();
    }

    public static boolean getLuckForCollectibles() {
        return LUCK_FOR_COLLECTIBLES.get();
    }

    public static boolean getCollectiblesDirectlyIntoInventory() {
        return COLLECTIBLES_DIRECTLY_INTO_INVENTORY.get();
    }

    public static List<String> getLootBagRewards() {
        return (List<String>) LOOT_BAG_REWARDS.get();
    }

    public static List<String> getEpicLootBagRewards() {
        return (List<String>) EPIC_LOOT_BAG_REWARDS.get();
    }

    /**
     * Validates loot entry format: "amount*namespace:item"
     *
     * @param lootString The loot string to validate (e.g. "3*minecraft:diamond")
     * @return true if valid, false otherwise
     */
    public static boolean isValidLootEntry(String lootString) {
        if (lootString == null || lootString.trim().isEmpty() || !lootString.contains("*")) {
            return false;
        }

        try {
            String trimmed = lootString.trim();
            int starIndex = trimmed.indexOf("*");

            if (starIndex <= 0 || starIndex >= trimmed.length() - 1) {
                return false;
            }

            String amountPart = trimmed.substring(0, starIndex);
            String itemPart = trimmed.substring(starIndex + 1);

            // Validate amount is positive integer
            int amount = Integer.parseInt(amountPart);
            if (amount <= 0) {
                return false;
            }

            // Validate item exists
            return ConfigListHelper.isValidItem(itemPart);

        } catch (Exception e) {
            return false;
        }
    }
}
