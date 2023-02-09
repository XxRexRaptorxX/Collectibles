package xxrexraptorxx.collectibles.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import xxrexraptorxx.collectibles.main.ModItems;
import xxrexraptorxx.collectibles.main.References;

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
    }



    private void itemGenerated(RegistryObject item) {
        singleTexture(item.getId().getPath(), new ResourceLocation("item/generated"),"layer0", new ResourceLocation(References.MODID, "item/" + item.getId().toString().substring(References.MODID.length() + 1)));
    }


    private void itemHandheld(RegistryObject item) {
        singleTexture(item.getId().getPath(), new ResourceLocation("item/handheld"),"layer0", new ResourceLocation(References.MODID, "item/" + item.getId().toString().substring(References.MODID.length() + 1)));
    }


    private void itemBlock(RegistryObject item) {
        withExistingParent(item.getId().getPath(), new ResourceLocation(References.MODID, "block/" + item.getId().toString().substring(References.MODID.length() + 1)));
    }

}