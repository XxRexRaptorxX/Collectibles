package xxrexraptorxx.collectibles.world;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.registry.ModItems;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;
import xxrexraptorxx.collectibles.utils.Config;

import java.util.List;
import java.util.Random;

@EventBusSubscriber(modid = References.MODID, bus = EventBusSubscriber.Bus.GAME)
public class Events {

    /** Collectible Drop **/
    @SubscribeEvent
    public static void onBlockDestroyed(BlockEvent.BreakEvent event) {
        Random random = new Random();
        Level level = event.getPlayer().level();
        BlockState block = event.getState();
        BlockPos pos = event.getPos();

        if (!level.isClientSide) {

            if (block.is(BlockTags.BASE_STONE_OVERWORLD) || block.is(Tags.Blocks.STONES)) {
                if (random.nextInt(Config.getFragmentCollectibleRarity()) == 1) {
                    CollectibleHelper.dropCollectible(level, pos, CollectibleHelper.getRandomFragment());
                }


            } else if (block.is(BlockTags.DIRT) || block.is(BlockTags.SAND)) {
                if (random.nextInt(Config.getCoinCollectibleRarity()) == 1) {
                    CollectibleHelper.dropCollectible(level, pos, CollectibleHelper.getRandomCoin());
                }


            } else if (block.is(BlockTags.BASE_STONE_NETHER)) {
                if (random.nextInt(Config.getFossilCollectibleRarity()) == 1) {
                    CollectibleHelper.dropCollectible(level, pos, CollectibleHelper.getRandomFossil());
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

        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 30), new ItemStack(ModItems.LOOT_BAG.get()), 1, 10, 0.05F)));
        trades.add(((trader, random) -> new MerchantOffer(new ItemCost(Items.EMERALD_BLOCK, 10), new ItemStack(ModItems.EPIC_LOOT_BAG.get()), 1, 15, 0.05F)));
    }

}
