package xxrexraptorxx.collectibles.utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.ModConfigSpec;
import xxrexraptorxx.magmacore.config.ConfigHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {

    private static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec SERVER_CONFIG;

    private static ModConfigSpec.IntValue LOOT_BAG_XP;
    private static ModConfigSpec.IntValue EPIC_LOOT_BAG_XP;
    private static ModConfigSpec.ConfigValue<List<String>> LOOT_BAG_REWARDS;
    private static ModConfigSpec.ConfigValue<List<String>> EPIC_LOOT_BAG_REWARDS;
    private static ModConfigSpec.IntValue LOOT_BAG_ITEM_AMOUNT;
    private static ModConfigSpec.IntValue EPIC_LOOT_BAG_ITEM_AMOUNT;
    private static ModConfigSpec.IntValue COLLECTIBLES_XP;
    private static ModConfigSpec.IntValue FRAGMENT_COLLECTIBLE_RARITY;
    private static ModConfigSpec.IntValue FOSSIL_COLLECTIBLE_RARITY;
    private static ModConfigSpec.IntValue COIN_COLLECTIBLE_RARITY;


    static {
        ConfigHelper.setCategory(SERVER_BUILDER, "collectibles");
        COLLECTIBLES_XP = SERVER_BUILDER.comment("How much XP a player should get when finding a collectible").defineInRange("collectible_xp", 30, 0, 1000);
        COIN_COLLECTIBLE_RARITY = SERVER_BUILDER.comment("How rarely coin collectibles drop. Higher = rarer [1:X]").defineInRange("coin_collectibles_rarity", 1000, 1, 100000);
        FRAGMENT_COLLECTIBLE_RARITY = SERVER_BUILDER.comment("How rarely fragment collectibles drop. Higher = rarer [1:X]").defineInRange("fragment_collectibles_rarity", 1000, 1, 100000);
        FOSSIL_COLLECTIBLE_RARITY = SERVER_BUILDER.comment("How rarely fossil collectibles drop. Higher = rarer [1:X]").defineInRange("fossil_collectibles_rarity", 1000, 1, 100000);
        SERVER_BUILDER.pop();

        ConfigHelper.setCategory(SERVER_BUILDER, "loot_bags");
        EPIC_LOOT_BAG_XP = SERVER_BUILDER.comment("How much XP a player gets from an epic loot bag").defineInRange("epic_loot_bag_xp", 100, 0, 1000);
        EPIC_LOOT_BAG_ITEM_AMOUNT = SERVER_BUILDER.comment("How many item entries the epic loot bag gives").defineInRange("epic_loot_bag_item_amount", 3, 1, 10);
        EPIC_LOOT_BAG_REWARDS = SERVER_BUILDER.comment("Epic loot bag rewards [amount*modid:item]").define("epic_loot_bag_rewards", new ArrayList<>(Arrays.asList(
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
                "10*" + BuiltInRegistries.ITEM.getKey(Items.DIAMOND)
        )));
        LOOT_BAG_XP = SERVER_BUILDER.comment("How much XP a player gets from a loot bag").defineInRange("loot_bag_xp", 50, 0, 1000);
        LOOT_BAG_ITEM_AMOUNT = SERVER_BUILDER.comment("How many item entries the loot bag gives").defineInRange("loot_bag_item_amount", 1, 1, 10);
        LOOT_BAG_REWARDS = SERVER_BUILDER.comment("Loot bag rewards [amount*modid:item]").define("loot_bag_rewards", new ArrayList<>(Arrays.asList(
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
                "1*" + BuiltInRegistries.ITEM.getKey(Items.TRIAL_KEY)
        )));
        SERVER_BUILDER.pop();

        SERVER_CONFIG = SERVER_BUILDER.build();
    }


    public static int getCollectiblesXp()                  { return COLLECTIBLES_XP.get(); }
    public static int getCoinCollectibleRarity()           { return COIN_COLLECTIBLE_RARITY.get(); }
    public static int getFragmentCollectibleRarity()       { return FRAGMENT_COLLECTIBLE_RARITY.get(); }
    public static int getFossilCollectibleRarity()         { return FOSSIL_COLLECTIBLE_RARITY.get(); }
    public static int getLootBagXp()                       { return LOOT_BAG_XP.get(); }
    public static int getEpicLootBagXp()                   { return EPIC_LOOT_BAG_XP.get(); }
    public static int getLootBagItemAmount()               { return LOOT_BAG_ITEM_AMOUNT.get(); }
    public static int getEpicLootBagItemAmount()           { return EPIC_LOOT_BAG_ITEM_AMOUNT.get(); }
    public static List<String> getLootBagRewards()         { return LOOT_BAG_REWARDS.get(); }
    public static List<String> getEpicLootBagRewards()     { return EPIC_LOOT_BAG_REWARDS.get(); }
}