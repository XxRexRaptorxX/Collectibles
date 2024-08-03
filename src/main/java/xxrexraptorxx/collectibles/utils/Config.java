package xxrexraptorxx.collectibles.utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {

    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_COLLECTIBLES = "collectibles";
    public static final String CATEGORY_LOOT_BAG = "loot_bag";

    public static ModConfigSpec SERVER_CONFIG;
    public static ModConfigSpec CLIENT_CONFIG;

    public static ModConfigSpec.BooleanValue UPDATE_CHECKER;
    public static ModConfigSpec.IntValue LOOT_BAG_XP;
    public static ModConfigSpec.IntValue EPIC_LOOT_BAG_XP;
    public static ModConfigSpec.ConfigValue<List<String>> LOOT_BAG_REWARDS;
    public static ModConfigSpec.ConfigValue<List<String>> EPIC_LOOT_BAG_REWARDS;
    public static ModConfigSpec.IntValue LOOT_BAG_ITEM_AMOUNT;
    public static ModConfigSpec.IntValue EPIC_LOOT_BAG_ITEM_AMOUNT;
    public static ModConfigSpec.IntValue COLLECTIBLES_XP;
    public static ModConfigSpec.IntValue FRAGMENT_COLLECTIBLE_RARITY;
    public static ModConfigSpec.IntValue FOSSIL_COLLECTIBLE_RARITY;
    public static ModConfigSpec.IntValue COIN_COLLECTIBLE_RARITY;
    public static ModConfigSpec.BooleanValue PATREON_REWARDS;

    public static void init(ModContainer container) {
        initServer();
        initClient();

        container.registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG);
        container.registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG);
    }


    public static void initClient() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.comment("General").push(CATEGORY_GENERAL);
        UPDATE_CHECKER = builder.comment("Activate the Update-Checker").define("update-checker", true);
        builder.pop();

        CLIENT_CONFIG = builder.build();
    }


    public static void initServer() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.comment("General").push(CATEGORY_GENERAL);
        PATREON_REWARDS = builder.comment("Enables ingame rewards on first spawn for Patreons").define("patreon_rewards", true);
        builder.pop();

        builder.comment("Collectibles").push(CATEGORY_COLLECTIBLES);
        COLLECTIBLES_XP = builder.comment("How much xp a player should get when he finds a collectible").defineInRange("collectible_xp", 30, 0, 1000);
        COIN_COLLECTIBLE_RARITY = builder.comment("How rarely should coin collectibles drop. higher = rarer [1:X]").defineInRange("coin_collectibles_rarity", 1000, 1, 100000);
        FRAGMENT_COLLECTIBLE_RARITY = builder.comment("How rarely should fragment collectibles drop. higher = rarer [1:X]").defineInRange("fragment_collectibles_rarity", 1000, 1, 100000);
        FOSSIL_COLLECTIBLE_RARITY = builder.comment("How rarely should fossil collectibles drop. higher = rarer [1:X]").defineInRange("fossil_collectibles_rarity", 1000, 1, 100000);
        builder.pop();


        builder.comment("Loot Bag").push(CATEGORY_LOOT_BAG);
        EPIC_LOOT_BAG_XP = builder.comment("How much xp a player should get when he uses a loot bag").defineInRange("epic_loot_bag_xp", 100, 0, 1000);
        EPIC_LOOT_BAG_REWARDS = builder.comment("A list with all epic loot bag rewards [amount*modid:item]").define("epic_loot_bag_rewards", new ArrayList<>(Arrays.asList(
                "1*" + BuiltInRegistries.ITEM.getKey(Items.NETHERITE_INGOT).toString(),
                "1*" + BuiltInRegistries.ITEM.getKey(Items.NETHER_STAR).toString(),
                "1*" + BuiltInRegistries.ITEM.getKey(Items.END_CRYSTAL).toString(),
                "1*" + BuiltInRegistries.ITEM.getKey(Items.ELYTRA).toString(),
                "1*" + BuiltInRegistries.ITEM.getKey(Items.TOTEM_OF_UNDYING).toString(),
                "1*" + BuiltInRegistries.ITEM.getKey(Items.ENCHANTED_GOLDEN_APPLE).toString(),
                "5*" + BuiltInRegistries.ITEM.getKey(Items.DRAGON_BREATH).toString(),
                "10*" + BuiltInRegistries.ITEM.getKey(Items.DIAMOND).toString()
        )));
        EPIC_LOOT_BAG_ITEM_AMOUNT = builder.comment("How many item entries the epic loot bag gives the player").defineInRange("epic_loot_bag_item_amount", 1, 1, 10);
        LOOT_BAG_REWARDS = builder.comment("A list with all loot bag rewards [amount*modid:item]").define("loot_bag_rewards", new ArrayList<>(Arrays.asList(
                "3*" + BuiltInRegistries.ITEM.getKey(Items.EMERALD).toString(),
                "3*" + BuiltInRegistries.ITEM.getKey(Items.DIAMOND).toString(),
                "1*" + BuiltInRegistries.ITEM.getKey(Items.GOLDEN_APPLE).toString(),
                "1*" + BuiltInRegistries.ITEM.getKey(Items.ENDER_EYE).toString()
        )));
        LOOT_BAG_XP = builder.comment("How much xp a player should get when he uses a loot bag").defineInRange("loot_bag_xp", 50, 0, 1000);
        LOOT_BAG_ITEM_AMOUNT = builder.comment("How many item entries the loot bag gives the player").defineInRange("loot_bag_item_amount", 1, 1, 10);
        builder.pop();

        SERVER_CONFIG = builder.build();
    }

}