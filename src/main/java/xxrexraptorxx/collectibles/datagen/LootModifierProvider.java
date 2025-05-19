package xxrexraptorxx.collectibles.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.registry.ModItems;
import xxrexraptorxx.collectibles.world.modifiers.ChestLootModifier;
import xxrexraptorxx.collectibles.world.modifiers.SuspiciousLootModifier;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class LootModifierProvider extends GlobalLootModifierProvider {

    public LootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, References.MODID);
    }

    ArrayList<Item> suspicious_blocks_loot = new ArrayList<Item>();
    private static final ResourceLocation DESERT_PYRAMID = ResourceLocation.withDefaultNamespace("archaeology/desert_pyramid");
    private static final ResourceLocation DESERT_WELL = ResourceLocation.withDefaultNamespace("archaeology/desert_well");
    private static final ResourceLocation OCEAN_RUIN_WARM = ResourceLocation.withDefaultNamespace("archaeology/ocean_ruin_warm");
    private static final ResourceLocation OCEAN_RUIN_COLD = ResourceLocation.withDefaultNamespace("archaeology/ocean_ruin_cold");
    private static final ResourceLocation TRAIL_RUINS_COMMON = ResourceLocation.withDefaultNamespace("archaeology/trail_ruins_common");
    private static final ResourceLocation TRAIL_RUINS_RARE = ResourceLocation.withDefaultNamespace("archaeology/trail_ruins_rare");

    ArrayList<Item> chest_loot = new ArrayList<Item>();
    private static final ResourceLocation MINESHAFT = ResourceLocation.withDefaultNamespace("chests/abandoned_mineshaft");
    private static final ResourceLocation BURIED_TREASURE = ResourceLocation.withDefaultNamespace("chests/buried_treasure");
    private static final ResourceLocation SHIPWRECK_TREASURE = ResourceLocation.withDefaultNamespace("chests/shipwreck_treasure");
    private static final ResourceLocation BASTION_TREASURE = ResourceLocation.withDefaultNamespace("chests/bastion_treasure");
    private static final ResourceLocation STRONGHOLD = ResourceLocation.withDefaultNamespace("chests/stronghold_library");
    private static final ResourceLocation JUNGLE_TEMPLE = ResourceLocation.withDefaultNamespace("chests/jungle_temple");
    private static final ResourceLocation DESERT_TEMPLE = ResourceLocation.withDefaultNamespace("chests/desert_pyramid");
    private static final ResourceLocation MANSION = ResourceLocation.withDefaultNamespace("chests/woodland_mansion");


    @Override
    protected void start() {
        suspicious_blocks_loot.add(ModItems.AMULET_JEWELRY.get());
        suspicious_blocks_loot.add(ModItems.HAIRPIN_JEWELRY.get());
        suspicious_blocks_loot.add(ModItems.BRACELET_JEWELRY.get());
        suspicious_blocks_loot.add(ModItems.BROOCH_JEWELRY.get());
        suspicious_blocks_loot.add(ModItems.EARRING_JEWELRY.get());
        suspicious_blocks_loot.add(ModItems.DIADEM_JEWELRY.get());
        suspicious_blocks_loot.add(ModItems.CROWN_JEWELRY.get());
        suspicious_blocks_loot.add(ModItems.CHAIN_JEWELRY.get());
        suspicious_blocks_loot.add(ModItems.RING_JEWELRY.get());

        chest_loot.add(ModItems.OLD_BOOK.get());
        chest_loot.add(ModItems.NECRONOMICON_BOOK.get());
        chest_loot.add(ModItems.KNOWLEDGE_BOOK.get());
        chest_loot.add(ModItems.NOTCHS_BOOK.get());
        chest_loot.add(ModItems.DARKHOLD_BOOK.get());
        chest_loot.add(ModItems.MONSTER_BOOK.get());
        chest_loot.add(ModItems.GRIMOIRE_BOOK.get());
        chest_loot.add(ModItems.CURSED_BOOK.get());
        chest_loot.add(ModItems.HEROBRINES_BOOK.get());

        for (Item item: suspicious_blocks_loot) {
            String name = item.toString().replace(References.MODID + ":", "");

            add(name + "_from_pyramid_suspicious_blocks", new SuspiciousLootModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(DESERT_PYRAMID).build(), LootItemRandomChanceCondition.randomChance(0.1f).build() },
                    item));

            add(name + "_from_well_suspicious_blocks", new SuspiciousLootModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(DESERT_WELL).build(), LootItemRandomChanceCondition.randomChance(0.1f).build() },
                    item));

            add(name + "_from_cold_ocean_ruin_suspicious_blocks", new SuspiciousLootModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(OCEAN_RUIN_COLD).build(), LootItemRandomChanceCondition.randomChance(0.1f).build() },
                    item));

            add(name + "_from_warm_ocean_ruin_suspicious_blocks", new SuspiciousLootModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(OCEAN_RUIN_WARM).build(), LootItemRandomChanceCondition.randomChance(0.1f).build() },
                    item));

            add(name + "_from_trail_ruins_suspicious_blocks", new SuspiciousLootModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(TRAIL_RUINS_RARE).build(), LootItemRandomChanceCondition.randomChance(0.1f).build() },
                    item));

            add(name + "_from_trail_ruins_c_suspicious_blocks", new SuspiciousLootModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(TRAIL_RUINS_COMMON).build(), LootItemRandomChanceCondition.randomChance(0.1f).build() },
                    item));
        }


        for (Item item: chest_loot) {
        String name = item.toString().replace(References.MODID + ":", "");

        add(name + "_from_mineshaft", new ChestLootModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(MINESHAFT).build(), LootItemRandomChanceCondition.randomChance(0.001f).build() },
                item));

        add(name + "_from_mansion", new ChestLootModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(MANSION).build(), LootItemRandomChanceCondition.randomChance(0.001f).build() },
                item));

        add(name + "_from_buried_treasure", new ChestLootModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(BURIED_TREASURE).build(), LootItemRandomChanceCondition.randomChance(0.001f).build() },
                item));

        add(name + "_from_shipwreck", new ChestLootModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(SHIPWRECK_TREASURE).build(), LootItemRandomChanceCondition.randomChance(0.001f).build() },
                item));

        add(name + "_from_bastion", new ChestLootModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(BASTION_TREASURE).build(), LootItemRandomChanceCondition.randomChance(0.001f).build() },
                item));

        add(name + "_from_stronghold", new ChestLootModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(STRONGHOLD).build(), LootItemRandomChanceCondition.randomChance(0.001f).build() },
                item));

        add(name + "_from_desert_pyramid", new ChestLootModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(DESERT_TEMPLE).build(), LootItemRandomChanceCondition.randomChance(0.001f).build() },
                item));

        add(name + "_from_jungle_temple", new ChestLootModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(JUNGLE_TEMPLE).build(), LootItemRandomChanceCondition.randomChance(0.001f).build()},
                item));
        }
    }
}