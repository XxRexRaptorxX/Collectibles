package xxrexraptorxx.collectibles.integration;

import java.util.ArrayList;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import xxrexraptorxx.collectibles.main.Collectibles;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.registry.ModItems;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;
import xxrexraptorxx.collectibles.utils.Config;
import xxrexraptorxx.magmacore.utils.FormattingHelper;

@JeiPlugin
public class JEIIntegration implements IModPlugin {

    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(References.MODID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        ArrayList<ItemStack> treasures = new ArrayList<ItemStack>();
        ArrayList<ItemStack> epic_treasures = new ArrayList<ItemStack>();

        for (String item : Config.getEpicLootBagRewards()) {
            try {
                epic_treasures.add(CollectibleHelper.parseItemEntry(item));
            } catch (Exception e) {
                Collectibles.LOGGER.error(
                        "Invalid item entry '{}' in epic_loot_bag_rewards config: {}", item, e.getMessage());
            }
        }
        for (String item : Config.getLootBagRewards()) {
            try {
                treasures.add(CollectibleHelper.parseItemEntry(item));
            } catch (Exception e) {
                Collectibles.LOGGER.error(
                        "Invalid item entry '{}' in epic_loot_bag_rewards config: {}", item, e.getMessage());
            }
        }

        registry.addIngredientInfo(
                treasures,
                VanillaTypes.ITEM_STACK,
                FormattingHelper.setModLangComponent("message", References.MODID, "lootbag_entry_jei_desc"));
        registry.addIngredientInfo(
                epic_treasures,
                VanillaTypes.ITEM_STACK,
                FormattingHelper.setModLangComponent("message", References.MODID, "epic_lootbag_entry_jei_desc"));
        registry.addIngredientInfo(
                new ItemStack(ModItems.LOOT_BAG.get()),
                VanillaTypes.ITEM_STACK,
                FormattingHelper.setModLangComponent("message", References.MODID, "lootbag_jei_desc"));
        registry.addIngredientInfo(
                new ItemStack(ModItems.EPIC_LOOT_BAG.get()),
                VanillaTypes.ITEM_STACK,
                FormattingHelper.setModLangComponent("message", References.MODID, "lootbag_jei_desc"));
    }
}
