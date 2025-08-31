package xxrexraptorxx.collectibles.integration;

import java.util.ArrayList;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;
import me.shedaniel.rei.plugin.client.BuiltinClientPlugin;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import xxrexraptorxx.collectibles.main.Collectibles;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.registry.ModItems;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;
import xxrexraptorxx.collectibles.utils.Config;
import xxrexraptorxx.magmacore.utils.FormattingHelper;

@REIPluginClient
public class REIIntegration implements REIClientPlugin {

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        BuiltinClientPlugin instance = BuiltinClientPlugin.getInstance();

        ArrayList<EntryStack<ItemStack>> treasures = new ArrayList<>();
        ArrayList<EntryStack<ItemStack>> epic_treasures = new ArrayList<>();

        for (String item : Config.getEpicLootBagRewards()) {
            try {
                epic_treasures.add(EntryStacks.of(CollectibleHelper.parseItemEntry(item)));
            } catch (Exception e) {
                Collectibles.LOGGER.error(
                        "Invalid item entry '{}' in epic_loot_bag_rewards config: {}", item, e.getMessage());
            }
        }
        for (String item : Config.getLootBagRewards()) {
            try {
                treasures.add(EntryStacks.of(CollectibleHelper.parseItemEntry(item)));
            } catch (Exception e) {
                Collectibles.LOGGER.error(
                        "Invalid item entry '{}' in epic_loot_bag_rewards config: {}", item, e.getMessage());
            }
        }

        instance.registerInformation(EntryIngredient.of(treasures), Component.empty(), list -> {
            list.add(FormattingHelper.setModLangComponent("message", References.MODID, "lootbag_entry_jei_desc"));
            return list;
        });
        instance.registerInformation(EntryIngredient.of(epic_treasures), Component.empty(), list -> {
            list.add(FormattingHelper.setModLangComponent("message", References.MODID, "epic_lootbag_entry_jei_desc"));
            return list;
        });
        instance.registerInformation(EntryStacks.of(ModItems.LOOT_BAG), Component.empty(), list -> {
            list.add(FormattingHelper.setModLangComponent("message", References.MODID, "lootbag_jei_desc"));
            return list;
        });
        instance.registerInformation(EntryStacks.of(ModItems.EPIC_LOOT_BAG), Component.empty(), list -> {
            list.add(FormattingHelper.setModLangComponent("message", References.MODID, "lootbag_jei_desc"));
            return list;
        });
    }
}
