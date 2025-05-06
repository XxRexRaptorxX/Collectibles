package xxrexraptorxx.collectibles.world;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;
import xxrexraptorxx.collectibles.main.Collectibles;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.registry.ModItems;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;
import xxrexraptorxx.collectibles.utils.Config;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Mod.EventBusSubscriber(modid = References.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Events {

    /** Update-Checker **/
    private static boolean hasShownUp = false;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent  event) {
        if (Config.UPDATE_CHECKER.get() != null && Config.UPDATE_CHECKER.get()) {
            if (!hasShownUp && Minecraft.getInstance().screen == null) {
                if (VersionChecker.getResult(ModList.get().getModContainerById(References.MODID).get().getModInfo()).status() == VersionChecker.Status.OUTDATED ||
                        VersionChecker.getResult(ModList.get().getModContainerById(References.MODID).get().getModInfo()).status() == VersionChecker.Status.BETA_OUTDATED ) {

                    MutableComponent url = Component.literal(ChatFormatting.GREEN + "Click here to update!");
                    url.withStyle(url.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, References.URL)));

                    Minecraft.getInstance().player.displayClientMessage(Component.literal(ChatFormatting.BLUE + "A newer version of " + ChatFormatting.YELLOW + References.NAME + ChatFormatting.BLUE + " is available!"), false);
                    Minecraft.getInstance().player.displayClientMessage(url, false);

                    hasShownUp = true;

                } else if (VersionChecker.getResult(ModList.get().getModContainerById(References.MODID).get().getModInfo()).status() == VersionChecker.Status.FAILED) {
                    Collectibles.LOGGER.error(References.NAME + "'s version checker failed!");
                    hasShownUp = true;
                }
            }
        }
    }


    /** Collectible Drop **/
    @SubscribeEvent
    public static void onBlockDestroyed(BlockEvent.BreakEvent event) {
        Random random = new Random();
        Level level = event.getPlayer().level();
        BlockState block = event.getState();
        BlockPos pos = event.getPos();

        if (!level.isClientSide) {

            if (block.is(BlockTags.BASE_STONE_OVERWORLD) || block.is(Tags.Blocks.STONE)) {
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

        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.GOLD_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.SILVER_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.PLATINUM_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.NETHERITE_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.COPPER_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.BRONZE_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.BRASS_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.IRON_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.STONE_COIN.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.DIAMOND_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.EMERALD_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.AMETHYST_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.RUBY_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.SAPPHIRE_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.TOPAZ_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.CRYSTAL_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.HEMATITE_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.TOURMALINE_FRAGMENT.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.OLD_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.NECRONOMICON_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.KNOWLEDGE_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.NOTCHS_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.DARKHOLD_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.MONSTER_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.GRIMOIRE_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.CURSED_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.HEROBRINES_BOOK.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.CLAW_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.LEG_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.AMMONITE_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.CRINOID_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.TRILOBITE_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.SKULL_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.SPINE_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.RIP_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.THORAX_FOSSIL.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.AMULET_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.HAIRPIN_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.BRACELET_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.BROOCH_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.EARRING_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.DIADEM_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.CROWN_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.CHAIN_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(ModItems.RING_JEWELRY.get()), reward, 1, 5, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(ModItems.LOOT_BAG.get()), 1, 10, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 60), new ItemStack(ModItems.EPIC_LOOT_BAG.get()), 1, 15, 0.05F)));
    }


    /**
     * Distributes the supporter rewards on first join.
     */
    @SubscribeEvent
    public static void SupporterRewards(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        Level level = player.level();

        if (Config.PATREON_REWARDS.get() != null && Config.PATREON_REWARDS.get()) {

            try {
                URL SUPPORTER_URL = new URL("https://raw.githubusercontent.com/XxRexRaptorxX/Patreons/main/Supporter");
                URL PREMIUM_SUPPORTER_URL = new URL("https://raw.githubusercontent.com/XxRexRaptorxX/Patreons/main/Premium%20Supporter");
                URL ELITE_URL = new URL("https://raw.githubusercontent.com/XxRexRaptorxX/Patreons/main/Elite");

                //test if a player already has rewards
                if (!player.getInventory().contains(new ItemStack(Items.PAPER))) {

                    ServerPlayer serverPlayer = (ServerPlayer) player;
                    //test if player joins the first time
                    if (serverPlayer.getStats().getValue(Stats.CUSTOM, Stats.PLAY_TIME) < 5) {

                        //test if player is supporter
                        if (SupporterCheck(SUPPORTER_URL, player)) {

                            ItemStack certificate = new ItemStack(Items.PAPER).setHoverName((Component.literal("Thank you for supporting me in my work!").withStyle(ChatFormatting.GOLD).append(Component.literal(" - XxRexRaptorxX").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GREEN))));

                            CompoundTag ownerNBT = new CompoundTag();
                            ItemStack reward = new ItemStack(Items.PLAYER_HEAD);
                            ownerNBT.putString("SkullOwner", player.getName().getString());
                            reward.setTag(ownerNBT);

                            level.playSound((Player) null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.15F + 0.8F);
                            player.addItem(reward);
                            player.addItem(certificate);
                        }

                        //test if player is premium supporter
                        if (SupporterCheck(PREMIUM_SUPPORTER_URL, player)) {
                            ItemStack reward = new ItemStack(Items.DIAMOND_SWORD, 1).setHoverName(Component.literal("Rex's Night Sword").withStyle(ChatFormatting.DARK_GRAY));
                            reward.enchant(Enchantments.MENDING, 1);
                            reward.enchant(Enchantments.SHARPNESS, 3);
                            player.addItem(reward);
                        }

                        //test if player is elite
                        if (SupporterCheck(ELITE_URL, player)) {
                            player.addItem(new ItemStack(Items.NETHER_STAR).setHoverName(Component.literal("Elite Star")));
                        }
                    }
                }
            } catch (Exception e) {
                Collectibles.LOGGER.error(e);
            }
        }
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
            Collectibles.LOGGER.error("Supporter list URL not found! >>" + url);

        } catch (Exception e) {
            Collectibles.LOGGER.error(e);
        }

        return false;
    }


    /**
     * @author XxRexRaptorxX (RexRaptor)
     *
     * When entering a new MC installation for the first time, a message appears informing about the risks of mod reposts.
     * Then generates a marker file in the configs folder with more details. Supports implementation in multiple mods.
     *
     * You are welcome to implement this method in your own mods!
     */
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        Path configDir = FMLPaths.CONFIGDIR.get();
        Path marker   = configDir.resolve("#STOP_MOD_REPOSTS.txt");

        try {
            if (Files.notExists(marker)) {
                String fileContent = """
                        Sites like 9minecraft.net, mc-mod.net, and many others, are known for reuploading mod files without permission from the authors. These sites will also contain a bunch of ads, to try to make money from mods they did not create.
                        
                        These sites will use various methods to appear higher in Google when you search for the mod name, so a lot of people will download mods from them instead of the proper place. If you were linked to this site, you're one of these people.
                        
                        FOR YOU, AS A PLAYER, THIS CAN MEAN ANY OF THE FOLLOWING:
                        > Getting versions of the mods advertised for the wrong Minecraft versions, which will 100% crash when you load them.
                        > Getting old, and broken, versions of the mods, possibly causing problems in your game.
                        > Getting modified versions of the mods, which may contain malware and viruses.
                        > Having your information stolen from malicious ads in the sites.
                        > Taking money and views away from the official authors, which may cause them to stop making new mods.

                        WHAT DO I DO NOW?
                        The most important thing to do now is to make sure you stop visiting these sites, and get the mods from official sources. We also recommend you do the following:
                        
                        > Delete all the mods you've downloaded from these sites.
                        > Install the StopModReposts plugin, which makes sure you never visit them again.
                        > Run a virus/malware scan. We recommend MalwareBytes.
                        > Check out the #StopModReposts campaign, that tries to put an end to these sites. (https://stopmodreposts.org/)
                        > Spread the word. If you have any friends that use these sites, inform them to keep them safe.
                        
                        WHERE DO I GET MODS NOW?
                        Here's a bunch of links to places where you can download official versions of mods, hosted by their real authors:
                        
                        > CurseForge, where most modders host their mods. If it exists, it's probably there.
                        > Modrinth, a new hosting platform for mods that's also legit and more popular by the day.
                        > OptiFine.net, the official OptiFine site.
                        > Neoforged.net, which you need for any other Neoforge mods.
                        > FabricMC.net, which you need for any other Fabric mods.
                        > MinecraftForge Files, which you need for any other Forge mods.
                        
                        This doesn't mean other sites aren't legit. In general, the first place to look for a mod is CurseForge and Modrinth, so look there first.
                        
                        FAQ
                        Q: What if I've never had problems before?
                        > Just because you've never had problems with these sites before doesn't mean they're good. You should still avoid them for all the reasons listed above.
                        
                        Q: Is there a list of these sites I can check out?
                        > Yes, however, due to these showing up all the time, it's possible to be incomplete. (https://github.com/StopModReposts/Illegal-Mod-Sites/blob/master/SITES.md)
                        
                        Q: Why can't you just take these sites down?
                        > Unfortunately, these sites are often hosted in countries like Russia or Vietnam, where doing so isn't as feasible.
                        
                        Q: What if it says "Official Download" on the sites?
                        > Sometimes they'll do that to trick you. If you're uncertain, you should verify with the StopModReposts list linked above.
                        
                        
                        Credits: XxRexRaptorxX, Vazkii, StopModReposts campaign
                    """;

                Files.writeString(marker, fileContent, StandardCharsets.UTF_8);
                String launcher = FMLLoader.getLauncherInfo().toLowerCase();

                if (!launcher.contains("curseforge") || !launcher.contains("modrinth") || !launcher.contains("prism")) {
                    player.sendSystemMessage(Component.literal("Important Information about mod reposts:\n").withStyle(ChatFormatting.UNDERLINE).withStyle(ChatFormatting.DARK_RED));
                    player.sendSystemMessage(Component.literal("Sites like 9minecraft.net, mc-mod.net, etc. are known for reuploading mod files without permissions. \nThese sites will also contain a bunch of ads, to try to make money from mods they did not create.\n").withStyle(ChatFormatting.RED));
                    player.sendSystemMessage(Component.literal("For you, this can mean any of the following:").withStyle(ChatFormatting.UNDERLINE).withStyle(ChatFormatting.RED));
                    player.sendSystemMessage(Component.literal("- Modified versions of mods, which may contain malware & viruses").withStyle(ChatFormatting.RED));
                    player.sendSystemMessage(Component.literal("- Having your information stolen from malicious ads").withStyle(ChatFormatting.RED));
                    player.sendSystemMessage(Component.literal("- Old and broken mod versions that can corrupt your world").withStyle(ChatFormatting.RED));
                    player.sendSystemMessage(Component.literal("- Taking money and views away from the real authors, which may cause them to stop making mods").withStyle(ChatFormatting.RED));

                    MutableComponent url = Component.literal(ChatFormatting.GOLD + "* Click here for more information *");
                    url.withStyle(url.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://vazkii.net/repost/")));
                    player.sendSystemMessage(url);
                }
            }
        } catch (IOException e) {
            Collectibles.LOGGER.error(e);
        }
    }
}
