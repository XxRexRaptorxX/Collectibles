package xxrexraptorxx.collectibles.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.registry.ModItems;

public class ItemModelGen extends ItemModelProvider {

    public ItemModelGen(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, References.MODID, existingFileHelper);
    }


    @Override
    protected void registerModels() {

        itemGenerated(ModItems.LOOT_BAG);
        itemGenerated(ModItems.EPIC_LOOT_BAG);

        itemGenerated(ModItems.GOLD_COIN);
        itemGenerated(ModItems.SILVER_COIN);
        itemGenerated(ModItems.COPPER_COIN);
        itemGenerated(ModItems.PLATINUM_COIN);
        itemGenerated(ModItems.BRONZE_COIN);
        itemGenerated(ModItems.IRON_COIN);
        itemGenerated(ModItems.STONE_COIN);
        itemGenerated(ModItems.NETHERITE_COIN);
        itemGenerated(ModItems.BRASS_COIN);

        itemGenerated(ModItems.DIAMOND_FRAGMENT);
        itemGenerated(ModItems.EMERALD_FRAGMENT);
        itemGenerated(ModItems.AMETHYST_FRAGMENT);
        itemGenerated(ModItems.RUBY_FRAGMENT);
        itemGenerated(ModItems.SAPPHIRE_FRAGMENT);
        itemGenerated(ModItems.TOPAZ_FRAGMENT);
        itemGenerated(ModItems.CRYSTAL_FRAGMENT);
        itemGenerated(ModItems.HEMATITE_FRAGMENT);
        itemGenerated(ModItems.TOURMALINE_FRAGMENT);

        itemGenerated(ModItems.OLD_BOOK);
        itemGenerated(ModItems.DARKHOLD_BOOK);
        itemGenerated(ModItems.NOTCHS_BOOK);
        itemGenerated(ModItems.HEROBRINES_BOOK);
        itemGenerated(ModItems.GRIMOIRE_BOOK);
        itemGenerated(ModItems.MONSTER_BOOK);
        itemGenerated(ModItems.KNOWLEDGE_BOOK);
        itemGenerated(ModItems.CURSED_BOOK);
        itemGenerated(ModItems.NECRONOMICON_BOOK);

        itemGenerated(ModItems.CLAW_FOSSIL);
        itemGenerated(ModItems.LEG_FOSSIL);
        itemGenerated(ModItems.AMMONITE_FOSSIL);
        itemGenerated(ModItems.CRINOID_FOSSIL);
        itemGenerated(ModItems.TRILOBITE_FOSSIL);
        itemGenerated(ModItems.SKULL_FOSSIL);
        itemGenerated(ModItems.SPINE_FOSSIL);
        itemGenerated(ModItems.RIP_FOSSIL);
        itemGenerated(ModItems.THORAX_FOSSIL);

        itemGenerated(ModItems.AMULET_JEWELRY);
        itemGenerated(ModItems.HAIRPIN_JEWELRY);
        itemGenerated(ModItems.BRACELET_JEWELRY);
        itemGenerated(ModItems.BROOCH_JEWELRY);
        itemGenerated(ModItems.EARRING_JEWELRY);
        itemGenerated(ModItems.DIADEM_JEWELRY);
        itemGenerated(ModItems.CROWN_JEWELRY);
        itemGenerated(ModItems.CHAIN_JEWELRY);
        itemGenerated(ModItems.RING_JEWELRY);
    }



    private void itemGenerated(DeferredItem item) {
        singleTexture(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/generated"),"layer0", ResourceLocation.fromNamespaceAndPath(References.MODID, "item/" + item.getId().getPath()));
    }

    private void itemGenerated(DeferredBlock item) {
        singleTexture(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/generated"),"layer0", ResourceLocation.fromNamespaceAndPath(References.MODID, "item/" + item.getId().getPath()));
    }

    private void itemHandheld(DeferredItem item) {
        singleTexture(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/handheld"),"layer0", ResourceLocation.fromNamespaceAndPath(References.MODID, "item/" + item.getId().getPath()));
    }

    private void itemBlock(DeferredBlock item) {
        withExistingParent(item.getId().getPath(), modLoc( "block/" + item.getId().getPath()));
    }

}
