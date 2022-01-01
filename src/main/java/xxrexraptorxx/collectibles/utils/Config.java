package xxrexraptorxx.collectibles.utils;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "general";

    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.BooleanValue UPDATE_CHECKER;
    public static ForgeConfigSpec.IntValue LOOT_BAG_XP;
    public static ForgeConfigSpec.IntValue LOOT_BAG_ITEM_AMOUNT;
    public static ForgeConfigSpec.IntValue COLLECTIBLES_XP;
    public static ForgeConfigSpec.IntValue COLLECTIBLE_RARITY;

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
        LOOT_BAG_XP = builder.comment("How much xp a player should get when he uses a loot bag").defineInRange("loot_bag_xp", 100, 0, 1000);
        LOOT_BAG_ITEM_AMOUNT = builder.comment("How many items the loot bag gives the player ").defineInRange("loot_bag_item_amount", 1, 1, 10);
        COLLECTIBLE_RARITY = builder.comment("How rarely should collectibles drop. higher = rarer [1:X]").defineInRange("collectibles_rarity", 300, 0, 1000);
        builder.pop();

        SERVER_CONFIG = builder.build();
    }

}