package xxrexraptorxx.collectibles.utils;

import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "general";

    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.BooleanValue UPDATE_CHECKER;
    public static ForgeConfigSpec.IntValue LOOT_BAG_XP;
    public static ForgeConfigSpec.IntValue EPIC_LOOT_BAG_XP;
    public static ForgeConfigSpec.ConfigValue<List<String>> LOOT_BAG_REWARDS;
    public static ForgeConfigSpec.ConfigValue<List<String>> EPIC_LOOT_BAG_REWARDS;
    public static ForgeConfigSpec.IntValue LOOT_BAG_ITEM_AMOUNT;
    public static ForgeConfigSpec.IntValue EPIC_LOOT_BAG_ITEM_AMOUNT;
    public static ForgeConfigSpec.IntValue COLLECTIBLES_XP;
    public static ForgeConfigSpec.IntValue FRAGMENT_COLLECTIBLE_RARITY;
    public static ForgeConfigSpec.IntValue COIN_COLLECTIBLE_RARITY;

    public static void init() {
        initServer();
        initClient();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG);
    }


    public static void initClient() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("General").push(CATEGORY_GENERAL);
        UPDATE_CHECKER = builder.comment("Activate the Update-Checker").define("update-checker", true);
        builder.pop();

        CLIENT_CONFIG = builder.build();
    }


    public static void initServer() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("General").push(CATEGORY_GENERAL);
        COLLECTIBLES_XP = builder.comment("How much xp a player should get when he finds a collectible").defineInRange("collectible_xp", 30, 0, 1000);
        EPIC_LOOT_BAG_XP = builder.comment("How much xp a player should get when he uses a loot bag").defineInRange("epic_loot_bag_xp", 100, 0, 1000);
        EPIC_LOOT_BAG_REWARDS = builder.comment("A list with all epic loot bag rewards [amount*modid:item]").define("epic_loot_bag_rewards", new ArrayList<>(Arrays.asList(
                "1*" + Items.NETHERITE_INGOT.getRegistryName().toString(),
                "1*" + Items.NETHER_STAR.getRegistryName().toString(),
                "1*" + Items.END_CRYSTAL.getRegistryName().toString(),
                "1*" + Items.ELYTRA.getRegistryName().toString(),
                "1*" + Items.TOTEM_OF_UNDYING.getRegistryName().toString(),
                "1*" + Items.ENCHANTED_GOLDEN_APPLE.getRegistryName().toString(),
                "5*" + Items.DRAGON_BREATH.getRegistryName().toString(),
                "10*" + Items.DIAMOND.getRegistryName().toString()
                )));
        EPIC_LOOT_BAG_ITEM_AMOUNT = builder.comment("How many item entries the epic loot bag gives the player").defineInRange("epic_loot_bag_item_amount", 1, 1, 10);
        LOOT_BAG_REWARDS = builder.comment("A list with all loot bag rewards [amount*modid:item]").define("loot_bag_rewards", new ArrayList<>(Arrays.asList(
                "3*" + Items.EMERALD.getRegistryName().toString(),
                "3*" + Items.DIAMOND.getRegistryName().toString(),
                "1*" + Items.GOLDEN_APPLE.getRegistryName().toString(),
                "1*" + Items.ENDER_EYE.getRegistryName().toString()
        )));
        LOOT_BAG_XP = builder.comment("How much xp a player should get when he uses a loot bag").defineInRange("loot_bag_xp", 50, 0, 1000);
        LOOT_BAG_ITEM_AMOUNT = builder.comment("How many item entries the loot bag gives the player").defineInRange("loot_bag_item_amount", 1, 1, 10);
        COIN_COLLECTIBLE_RARITY = builder.comment("How rarely should coin collectibles drop. higher = rarer [1:X]").defineInRange("coin_collectibles_rarity", 1000, 1, 100000);
        FRAGMENT_COLLECTIBLE_RARITY = builder.comment("How rarely should fragment collectibles drop. higher = rarer [1:X]").defineInRange("fragment_collectibles_rarity", 1000, 1, 100000);
        builder.pop();

        SERVER_CONFIG = builder.build();
    }

}