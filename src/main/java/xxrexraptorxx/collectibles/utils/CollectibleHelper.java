package xxrexraptorxx.collectibles.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import xxrexraptorxx.collectibles.main.Collectibles;
import xxrexraptorxx.collectibles.registry.ModItems;

import java.util.ArrayList;
import java.util.Random;

public class CollectibleHelper {

    public static ItemStack getRandomTreasure() {
        ArrayList<ItemStack> rewards = new ArrayList<>();
        Random random = new Random();

            for (String item : Config.getLootBagRewards()) {
                try {
                    rewards.add(new ItemStack(BuiltInRegistries.ITEM.getValue(
                            //                                          get the mod prefix              |        get the item registry name      |         get the item amount
                            ResourceLocation.fromNamespaceAndPath(item.substring(item.indexOf('*') + 1, item.indexOf(':')), item.substring(item.indexOf(':') + 1))), Integer.parseInt(item.substring(0, item.indexOf('*')))));

                } catch (Exception e) {
                    Collectibles.LOGGER.error("Invalid item entry in the Collectibles 'loot_bag_rewards' config option!");
                }
            }

            return rewards.get(random.nextInt(rewards.size()));
    }


    public static ItemStack getRandomEpicTreasure() {
        ArrayList<ItemStack> rewards = new ArrayList<>();
        Random random = new Random();

        for (String item : Config.getEpicLootBagRewards()) {
            try {
                rewards.add(new ItemStack(BuiltInRegistries.ITEM.getValue(
                        //                                          get the mod prefix              |        get the item registry name      |         get the item amount
                        ResourceLocation.fromNamespaceAndPath(item.substring(item.indexOf('*') + 1, item.indexOf(':')), item.substring(item.indexOf(':') + 1))), Integer.parseInt(item.substring(0, item.indexOf('*')))));

            } catch (Exception e) {
                Collectibles.LOGGER.error("Invalid item entry in the Collectibles 'epic_loot_bag_rewards' config option!");
            }
        }

        return rewards.get(random.nextInt(rewards.size()));
    }


    public static ItemStack getRandomCollectible(CollectibleSet set) {
        switch (set) {
            case COIN_SET:
                getRandomCoin();
            case FRAGMENT_SET:
                getRandomFragment();
            case FOSSIL_SET:
                getRandomFossil();
            default:
                Collectibles.LOGGER.error("Unknown Collectible Set!");
                return new ItemStack(Items.AIR);
        }
    }


    public static ItemStack getRandomCoin() {
        Random random = new Random();

        switch (random.nextInt(9)) {
            case 0:
                return new ItemStack(ModItems.COPPER_COIN.get());
            case 1:
                return new ItemStack(ModItems.NETHERITE_COIN.get());
            case 2:
                return new ItemStack(ModItems.SILVER_COIN.get());
            case 3:
                return new ItemStack(ModItems.IRON_COIN.get());
            case 4:
                return new ItemStack(ModItems.BRASS_COIN.get());
            case 5:
                return new ItemStack(ModItems.BRONZE_COIN.get());
            case 6:
                return new ItemStack(ModItems.GOLD_COIN.get());
            case 7:
                return new ItemStack(ModItems.PLATINUM_COIN.get());
            case 8:
                return new ItemStack(ModItems.STONE_COIN.get());
            default:
                return new ItemStack(Items.AIR);
        }
    }


    public static ItemStack getRandomFragment() {
        Random random = new Random();

        switch (random.nextInt(9)) {
            case 0:
                return new ItemStack(ModItems.AMETHYST_FRAGMENT.get());
            case 1:
                return new ItemStack(ModItems.DIAMOND_FRAGMENT.get());
            case 2:
                return new ItemStack(ModItems.EMERALD_FRAGMENT.get());
            case 3:
                return new ItemStack(ModItems.RUBY_FRAGMENT.get());
            case 4:
                return new ItemStack(ModItems.SAPPHIRE_FRAGMENT.get());
            case 5:
                return new ItemStack(ModItems.CRYSTAL_FRAGMENT.get());
            case 6:
                return new ItemStack(ModItems.TOPAZ_FRAGMENT.get());
            case 7:
                return new ItemStack(ModItems.HEMATITE_FRAGMENT.get());
            case 8:
                return new ItemStack(ModItems.TOURMALINE_FRAGMENT.get());
            default:
                return new ItemStack(Items.AIR);
        }
    }


    public static ItemStack getRandomFossil() {
        Random random = new Random();

        switch (random.nextInt(9)) {
            case 0:
                return new ItemStack(ModItems.CLAW_FOSSIL.get());
            case 1:
                return new ItemStack(ModItems.LEG_FOSSIL.get());
            case 2:
                return new ItemStack(ModItems.AMMONITE_FOSSIL.get());
            case 3:
                return new ItemStack(ModItems.CRINOID_FOSSIL.get());
            case 4:
                return new ItemStack(ModItems.TRILOBITE_FOSSIL.get());
            case 5:
                return new ItemStack(ModItems.SKULL_FOSSIL.get());
            case 6:
                return new ItemStack(ModItems.SPINE_FOSSIL.get());
            case 7:
                return new ItemStack(ModItems.RIP_FOSSIL.get());
            case 8:
                return new ItemStack(ModItems.THORAX_FOSSIL.get());
            default:
                return new ItemStack(Items.AIR);
        }
    }


    public static Integer getCollectibleNumber(ItemStack collectible) {
        Item item = collectible.getItem();
        if        (item == ModItems.GOLD_COIN.get() || item == ModItems.DIAMOND_FRAGMENT.get() || item == ModItems.OLD_BOOK.get() || item == ModItems.CLAW_FOSSIL.get() || item == ModItems.AMULET_JEWELRY.get()) {
            return 1;
        } else if (item == ModItems.SILVER_COIN.get() || item == ModItems.EMERALD_FRAGMENT.get() || item == ModItems.NECRONOMICON_BOOK.get() || item == ModItems.LEG_FOSSIL.get() || item == ModItems.HAIRPIN_JEWELRY.get()) {
            return 2;
        } else if (item == ModItems.PLATINUM_COIN.get() || item == ModItems.AMETHYST_FRAGMENT.get() || item == ModItems.KNOWLEDGE_BOOK.get() || item == ModItems.AMMONITE_FOSSIL.get() || item == ModItems.BRACELET_JEWELRY.get()) {
            return 3;
        } else if (item == ModItems.NETHERITE_COIN.get() || item == ModItems.RUBY_FRAGMENT.get() || item == ModItems.NOTCHS_BOOK.get() || item == ModItems.CRINOID_FOSSIL.get() || item == ModItems.BROOCH_JEWELRY.get()) {
            return 4;
        } else if (item == ModItems.COPPER_COIN.get() || item == ModItems.SAPPHIRE_FRAGMENT.get() || item == ModItems.DARKHOLD_BOOK.get() || item == ModItems.TRILOBITE_FOSSIL.get() || item == ModItems.EARRING_JEWELRY.get()) {
            return 5;
        } else if (item == ModItems.BRONZE_COIN.get() || item == ModItems.TOPAZ_FRAGMENT.get() || item == ModItems.MONSTER_BOOK.get() || item == ModItems.SKULL_FOSSIL.get() || item == ModItems.DIADEM_JEWELRY.get()) {
            return 6;
        } else if (item == ModItems.BRASS_COIN.get() || item == ModItems.CRYSTAL_FRAGMENT.get() || item == ModItems.GRIMOIRE_BOOK.get() || item == ModItems.SPINE_FOSSIL.get() || item == ModItems.CROWN_JEWELRY.get()) {
            return 7;
        } else if (item == ModItems.IRON_COIN.get() || item == ModItems.HEMATITE_FRAGMENT.get() || item == ModItems.CURSED_BOOK.get() || item == ModItems.RIP_FOSSIL.get() || item == ModItems.CHAIN_JEWELRY.get()) {
            return 8;
        } else if (item == ModItems.STONE_COIN.get() || item == ModItems.TOURMALINE_FRAGMENT.get() || item == ModItems.HEROBRINES_BOOK.get() || item == ModItems.THORAX_FOSSIL.get() || item == ModItems.RING_JEWELRY.get()) {
            return 9;
        } else {
            Collectibles.LOGGER.error("Unknown Collectible!");
            return 0;
        }
    }


    public static void dropCollectible(Level level, BlockPos pos, ItemStack collectible) {
        level.playSound((Player) null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.15F + 0.F);
        ExperienceOrb.award((ServerLevel)level, pos.getCenter(), Config.getCollectiblesXp());

        ItemEntity drop = new ItemEntity(level, (double) pos.getX() + 0.5D, (double) pos.getY() + 1.5D, (double) pos.getZ() + 0.5D, collectible);
        level.addFreshEntity(drop);
    }


    public static ItemStack parseItemEntry(String entry) {
        String[] parts = entry.split("\\*", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid format: " + entry);
        }

        int amount = Integer.parseInt(parts[0]);
        String itemId = parts[1];

        ResourceLocation location = ResourceLocation.parse(itemId);
        Item item = BuiltInRegistries.ITEM.getValue(location);

        if (item == null) {
            throw new IllegalArgumentException("Unknown item: " + itemId);
        }

        return new ItemStack(item, amount);
    }


}
