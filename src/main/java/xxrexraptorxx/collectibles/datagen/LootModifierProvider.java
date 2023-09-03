package xxrexraptorxx.collectibles.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.registry.ModItems;
import xxrexraptorxx.collectibles.world.modifiers.SuspiciousLootModifier;

import java.util.ArrayList;

public class LootModifierProvider extends GlobalLootModifierProvider {
    public LootModifierProvider(PackOutput output) {
        super(output, References.MODID);
    }

    ArrayList<Item> suspicious_blocks_loot = new ArrayList<Item>();
    private static final ResourceLocation desert_pyramid = new ResourceLocation("archaeology/desert_pyramid");
    private static final ResourceLocation desert_well = new ResourceLocation("archaeology/desert_well");
    private static final ResourceLocation ocean_ruin_warm = new ResourceLocation("archaeology/ocean_ruin_warm");
    private static final ResourceLocation ocean_ruin_cold = new ResourceLocation("archaeology/ocean_ruin_cold");
    private static final ResourceLocation trail_ruins_common = new ResourceLocation("archaeology/trail_ruins_common");
    private static final ResourceLocation trail_ruins_rare = new ResourceLocation("archaeology/trail_ruins_rare");

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

        for (Item item: suspicious_blocks_loot) {
            String name = item.toString();

            add(name + "_from_pyramid_suspicious_blocks", new SuspiciousLootModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(desert_pyramid).build() },
                    item));

            add(name + "_from_well_suspicious_blocks", new SuspiciousLootModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(desert_well).build() },
                    item));
    

            add(name + "_from_cold_ocean_ruin_suspicious_blocks", new SuspiciousLootModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(ocean_ruin_cold).build() },
                    item));

            add(name + "_from_warm_ocean_ruin_suspicious_blocks", new SuspiciousLootModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(ocean_ruin_warm).build() },
                    item));

            add(name + "_from_trail_ruins_suspicious_blocks", new SuspiciousLootModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(trail_ruins_rare).build() },
                    item));

            add(name + "_from_trail_ruins_c_suspicious_blocks", new SuspiciousLootModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(trail_ruins_common).build() },
                    item));
        }
    }
}