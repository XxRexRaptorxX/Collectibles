package xxrexraptorxx.collectibles.datagen;

import java.util.function.BiConsumer;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import xxrexraptorxx.collectibles.registry.ModItems;

public class ItemModelGen extends ItemModelGenerators {

    public ItemModelGen(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    @Override
    public void run() {

        this.generateFlatItem(ModItems.LOOT_BAG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.EPIC_LOOT_BAG.get(), ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.GOLD_COIN.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.SILVER_COIN.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.COPPER_COIN.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.PLATINUM_COIN.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.BRONZE_COIN.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.IRON_COIN.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.STONE_COIN.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.NETHERITE_COIN.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.BRASS_COIN.get(), ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.DIAMOND_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.EMERALD_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.AMETHYST_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.RUBY_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.SAPPHIRE_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.TOPAZ_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.CRYSTAL_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.HEMATITE_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.TOURMALINE_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.OLD_BOOK.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.DARKHOLD_BOOK.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.NOTCHS_BOOK.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.HEROBRINES_BOOK.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.GRIMOIRE_BOOK.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.MONSTER_BOOK.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.KNOWLEDGE_BOOK.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.CURSED_BOOK.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.NECRONOMICON_BOOK.get(), ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.CLAW_FOSSIL.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.LEG_FOSSIL.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.AMMONITE_FOSSIL.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.CRINOID_FOSSIL.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.TRILOBITE_FOSSIL.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.SKULL_FOSSIL.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.SPINE_FOSSIL.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.RIP_FOSSIL.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.THORAX_FOSSIL.get(), ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.AMULET_JEWELRY.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.HAIRPIN_JEWELRY.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.BRACELET_JEWELRY.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.BROOCH_JEWELRY.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.EARRING_JEWELRY.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.DIADEM_JEWELRY.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.CROWN_JEWELRY.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.CHAIN_JEWELRY.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.RING_JEWELRY.get(), ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.DELICIOSA_LEAVE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.AUREA_LEAVE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.OBLIQUA_LEAVE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.SUBPINNATA_LEAVE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.FRYDEK_LEAVE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.JACKLYN_LEAVE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.SABRINA_LEAVE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.FLORIDA_LEAVE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.BILLIETIAE_LEAVE.get(), ModelTemplates.FLAT_ITEM);
    }
}
