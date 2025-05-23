package xxrexraptorxx.collectibles.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import xxrexraptorxx.collectibles.main.Collectibles;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.registry.ModItems;
import xxrexraptorxx.collectibles.utils.Config;

import java.util.ArrayList;

@JeiPlugin
public class JEIIntegration implements IModPlugin {

    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(References.MODID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        IIngredientManager ingredientManager = registry.getIngredientManager();

        ArrayList<ItemStack> treasures = new ArrayList<ItemStack>();
        ArrayList<ItemStack> epic_treasures = new ArrayList<ItemStack>();

        for (String item : Config.getLootBagRewards()) {
            try {
                treasures.add(new ItemStack(BuiltInRegistries.ITEM.getValue(
                        //                                          get the mod prefix              |        get the item registry name      |         get the item amount
                        ResourceLocation.fromNamespaceAndPath(item.substring(item.indexOf('*') + 1, item.indexOf(':')), item.substring(item.indexOf(':') + 1))), Integer.parseInt(item.substring(0, item.indexOf('*')))));

            } catch (Exception e) {
                Collectibles.LOGGER.error("Invalid item entry in the Collectibles 'loot_bag_rewards' config option!");
            }
        }

        for (String item : Config.getEpicLootBagRewards()) {
            try {
                epic_treasures.add(new ItemStack(BuiltInRegistries.ITEM.getValue(
                        //                                          get the mod prefix              |        get the item registry name      |         get the item amount
                        ResourceLocation.fromNamespaceAndPath(item.substring(item.indexOf('*') + 1, item.indexOf(':')), item.substring(item.indexOf(':') + 1))), Integer.parseInt(item.substring(0, item.indexOf('*')))));

            } catch (Exception e) {
                Collectibles.LOGGER.error("Invalid item entry in the Collectibles 'epic_loot_bag_rewards' config option!");
            }
        }

        registry.addIngredientInfo(treasures, VanillaTypes.ITEM_STACK, Component.translatable("message." + References.MODID + ".lootbag_entry_jei_desc"));
        registry.addIngredientInfo(epic_treasures, VanillaTypes.ITEM_STACK, Component.translatable("message." + References.MODID + ".epic_lootbag_entry_jei_desc"));
        registry.addIngredientInfo(new ItemStack(ModItems.LOOT_BAG.get()), VanillaTypes.ITEM_STACK, Component.translatable("message." + References.MODID + ".lootbag_jei_desc"));

    }
}