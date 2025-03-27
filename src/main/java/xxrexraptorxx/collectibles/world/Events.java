package xxrexraptorxx.collectibles.world;

import com.mojang.authlib.GameProfile;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.VersionChecker;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import xxrexraptorxx.collectibles.main.Collectibles;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.registry.ModItems;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;
import xxrexraptorxx.collectibles.utils.Config;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = References.MODID, bus = EventBusSubscriber.Bus.GAME)
public class Events {

    /** Update Checker **/
    private static boolean hasShownUp = false;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Pre event) {
        if (Config.UPDATE_CHECKER != null && Config.UPDATE_CHECKER.get()) {

            if (!hasShownUp && Minecraft.getInstance().screen == null) {
                var player = Minecraft.getInstance().player;
                if (player == null) return;

                var modContainer = ModList.get().getModContainerById(References.MODID).orElse(null);

                if (modContainer != null) {
                    var versionCheckResult = VersionChecker.getResult(modContainer.getModInfo());

                    if (versionCheckResult.status() == VersionChecker.Status.OUTDATED || versionCheckResult.status() == VersionChecker.Status.BETA_OUTDATED) {
                        MutableComponent url = Component.literal(ChatFormatting.GREEN + "Click here to update!")
                                .withStyle(style -> style.withClickEvent(new ClickEvent.OpenUrl(URI.create(References.URL))));

                        player.displayClientMessage(Component.literal(ChatFormatting.BLUE + "A newer version of " + ChatFormatting.YELLOW + References.NAME + ChatFormatting.BLUE + " is available!"), false);
                        player.displayClientMessage(url, false);

                        hasShownUp = true;

                    } else if (versionCheckResult.status() == VersionChecker.Status.FAILED) {
                        Collectibles.LOGGER.error(References.NAME + "'s version checker failed!");
                        hasShownUp = true;
                    }
                }
            }
        }
    }


    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    /**
     * Distributes supporter rewards on first login.
     */
    @SubscribeEvent
    public static void SupporterRewards(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        Level level = player.level();

        if (Config.PATREON_REWARDS.get()) {
            // Check if the player already has rewards
            if (!player.getInventory().contains(new ItemStack(Items.PAPER))) {
                if (player instanceof ServerPlayer serverPlayer) { // Ensure the player is a ServerPlayer
                    // Check if the player is logging in for the first time
                    if (serverPlayer.getStats().getValue(Stats.CUSTOM, Stats.PLAY_TIME) < 5) {

                        // Perform supporter checks asynchronously
                        CompletableFuture.runAsync(() -> {
                            if (SupporterCheck(URI.create("https://raw.githubusercontent.com/XxRexRaptorxX/Patreons/main/Supporter"), player)) {
                                giveSupporterReward(player, level);
                            }
                            if (SupporterCheck(URI.create("https://raw.githubusercontent.com/XxRexRaptorxX/Patreons/main/Premium%20Supporter"), player)) {
                                givePremiumSupporterReward(player, level);
                            }
                            if (SupporterCheck(URI.create("https://raw.githubusercontent.com/XxRexRaptorxX/Patreons/main/Elite"), player)) {
                                giveEliteReward(player);
                            }
                        });
                    }
                }
            }
        }
    }


    /**
     * Checks if the player is in the supporter list from the given URI.
     *
     * @param uri URI to a file containing supporter names
     * @param player The in-game player
     * @return true if the player is a supporter, otherwise false
     */
    private static boolean SupporterCheck(URI uri, Player player) {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse supporter list
            List<String> supporterList = List.of(response.body().split("\\R")); // Split lines
            return supporterList.contains(player.getName().getString());

        } catch (Exception e) {
            Collectibles.LOGGER.error("Failed to fetch or process supporter list from URI: {}", uri, e);
            return false;
        }
    }


    private static void giveSupporterReward(Player player, Level level) {
        ItemStack certificate = new ItemStack(Items.PAPER);
        certificate.set(DataComponents.CUSTOM_NAME, Component.literal("Thank you for supporting me in my work!").withStyle(ChatFormatting.GOLD)
                .append(Component.literal(" - XxRexRaptorxX").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GREEN)));

        ItemStack reward = new ItemStack(Items.PLAYER_HEAD);
        var profile = new GameProfile(player.getUUID(), player.getName().getString());
        reward.set(DataComponents.PROFILE, new ResolvableProfile(profile));

        level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.15F + 0.8F);
        player.getInventory().add(reward);
        player.getInventory().add(certificate);
    }

    private static void givePremiumSupporterReward(Player player, Level level) {
        ItemStack reward = new ItemStack(Items.DIAMOND_SWORD, 1);
        Registry<Enchantment> enchantmentsRegistry = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);

        reward.enchant(enchantmentsRegistry.getOrThrow(Enchantments.MENDING), 1);
        reward.enchant(enchantmentsRegistry.getOrThrow(Enchantments.SHARPNESS), 3);
        reward.set(DataComponents.ENCHANTMENTS, reward.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY));
        player.getInventory().add(reward);
    }

    private static void giveEliteReward(Player player) {
        ItemStack star = new ItemStack(Items.NETHER_STAR);

        star.set(DataComponents.CUSTOM_NAME, Component.literal("Elite Star"));
        player.getInventory().add(star);
    }


    /**
     * Tests if a player is a supporter
     *
     * @param url url to a file that contains the supporter names
     * @param player ingame player
     * @return true/false
     */
    private static boolean SupporterCheck(URL url, Player player) {
        try {
            Scanner scanner = new Scanner(url.openStream());
            List<String> supporterList = scanner.tokens().toList();

            for (String name: supporterList) {
                //test if player is in supporter list
                if (player.getName().getString().equals(name)) {
                    return true;
                }
            }

            scanner.close();

        } catch (MalformedURLException e) {
            Collectibles.LOGGER.error("Supporter list URL not found! >>{}", url);

        } catch (Exception e) {
            Collectibles.LOGGER.error("An unexpected error occurred while checking supporter list", e);
        }

        return false;
    }


    /** Collectible Drop **/
    @SubscribeEvent
    public static void onBlockDestroyed(BlockEvent.BreakEvent event) {
        Random random = new Random();
        Level level = event.getPlayer().level();
        BlockState block = event.getState();
        BlockPos pos = event.getPos();

        if (!level.isClientSide) {

            if (block.is(BlockTags.BASE_STONE_OVERWORLD) || block.is(Tags.Blocks.STONES)) {
                if (random.nextInt(Config.FRAGMENT_COLLECTIBLE_RARITY.get()) == 1) {
                    level.playSound((Player) null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.15F + 0.F);

                    ItemEntity drop = new ItemEntity(level, (double) pos.getX() + 0.5D, (double) pos.getY() + 1.5D, (double) pos.getZ() + 0.5D, CollectibleHelper.getRandomFragment());
                    level.addFreshEntity(drop);
                }


            } else if (block.is(BlockTags.DIRT) || block.is(BlockTags.SAND)) {
                if (random.nextInt(Config.COIN_COLLECTIBLE_RARITY.get()) == 1) {
                    level.playSound((Player) null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.15F + 0.F);

                    ItemEntity drop = new ItemEntity(level, (double) pos.getX() + 0.5D, (double) pos.getY() + 1.5D, (double) pos.getZ() + 0.5D, CollectibleHelper.getRandomCoin());
                    level.addFreshEntity(drop);
                }


            } else if (block.is(BlockTags.BASE_STONE_NETHER)) {
                if (random.nextInt(Config.FOSSIL_COLLECTIBLE_RARITY.get()) == 1) {
                    level.playSound((Player) null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.15F + 0.F);

                    ItemEntity drop = new ItemEntity(level, (double) pos.getX() + 0.5D, (double) pos.getY() + 1.5D, (double) pos.getZ() + 0.5D, CollectibleHelper.getRandomFossil());
                    level.addFreshEntity(drop);
                }
            }
        }
    }


    /** Wandering Trader trades **/
    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> trades = event.getRareTrades();
        ItemStack reward = new ItemStack(Items.EMERALD, 30);

        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.GOLD_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.SILVER_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.PLATINUM_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.NETHERITE_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.COPPER_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.BRONZE_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.BRASS_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.IRON_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.STONE_COIN.get()), reward, 1, 5, 0.05F)));

        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.DIAMOND_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.EMERALD_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.AMETHYST_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.RUBY_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.SAPPHIRE_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.TOPAZ_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.CRYSTAL_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.HEMATITE_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.TOURMALINE_FRAGMENT.get()), reward, 1, 5, 0.05F)));

        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.OLD_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.NECRONOMICON_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.KNOWLEDGE_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.NOTCHS_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.DARKHOLD_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.MONSTER_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.GRIMOIRE_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.CURSED_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.HEROBRINES_BOOK.get()), reward, 1, 5, 0.05F)));

        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.CLAW_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.LEG_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.AMMONITE_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.CRINOID_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.TRILOBITE_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.SKULL_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.SPINE_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.RIP_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.THORAX_FOSSIL.get()), reward, 1, 5, 0.05F)));

        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.AMULET_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.HAIRPIN_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.BRACELET_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.BROOCH_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.EARRING_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.DIADEM_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.CROWN_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.CHAIN_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(ModItems.RING_JEWELRY.get()), reward, 1, 5, 0.05F)));

        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 10), new ItemStack(ModItems.LOOT_BAG.get()), 1, 10, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 60), new ItemStack(ModItems.EPIC_LOOT_BAG.get()), 1, 15, 0.05F)));
    }

}
