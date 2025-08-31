package xxrexraptorxx.collectibles.integration;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.resources.ResourceLocation;
import xxrexraptorxx.collectibles.main.Collectibles;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.registry.ModItems;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;
import xxrexraptorxx.collectibles.utils.Config;
import xxrexraptorxx.magmacore.utils.FormattingHelper;

@EmiEntrypoint
public class EMIIntegration implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        ArrayList<EmiIngredient> treasures = new ArrayList<>();
        ArrayList<EmiIngredient> epic_treasures = new ArrayList<>();

        for (String item : Config.getEpicLootBagRewards()) {
            try {
                epic_treasures.add(EmiStack.of(CollectibleHelper.parseItemEntry(item)));
            } catch (Exception e) {
                Collectibles.LOGGER.error(
                        "Invalid item entry '{}' in epic_loot_bag_rewards config: {}", item, e.getMessage());
            }
        }
        for (String item : Config.getLootBagRewards()) {
            try {
                epic_treasures.add(EmiStack.of(CollectibleHelper.parseItemEntry(item)));
            } catch (Exception e) {
                Collectibles.LOGGER.error(
                        "Invalid item entry '{}' in epic_loot_bag_rewards config: {}", item, e.getMessage());
            }
        }

        registry.addRecipe(new EmiInfoRecipe(
                treasures,
                List.of(FormattingHelper.setModLangComponent("message", References.MODID, "lootbag_jei_desc")),
                ResourceLocation.fromNamespaceAndPath(References.MODID, "info/lootbag_treasures")));
        registry.addRecipe(new EmiInfoRecipe(
                epic_treasures,
                List.of(FormattingHelper.setModLangComponent("message", References.MODID, "epic_lootbag_jei_desc")),
                ResourceLocation.fromNamespaceAndPath(References.MODID, "info/epic_lootbag_treasures")));
        registry.addRecipe(new EmiInfoRecipe(
                List.of(EmiStack.of(ModItems.LOOT_BAG), EmiStack.of(ModItems.EPIC_LOOT_BAG)),
                List.of(FormattingHelper.setModLangComponent("message", References.MODID, "lootbag_jei_desc")),
                ResourceLocation.fromNamespaceAndPath(References.MODID, "info/bag")));
    }
}
