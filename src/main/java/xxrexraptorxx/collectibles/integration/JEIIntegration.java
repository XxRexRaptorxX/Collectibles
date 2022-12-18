package xxrexraptorxx.collectibles.integration;
/**
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import xxrexraptorxx.collectibles.main.ModItems;
import xxrexraptorxx.collectibles.main.References;

import java.util.ArrayList;

@JeiPlugin
public class JEIIntegration implements IModPlugin {

    private static final ResourceLocation ID = new ResourceLocation(References.MODID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        IIngredientManager ingredientManager = registry.getIngredientManager();

        ArrayList<ItemStack> treasures = new ArrayList<ItemStack>();
        treasures.add(new ItemStack(Items.NETHERITE_INGOT));
        treasures.add(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
        treasures.add(new ItemStack(Items.NETHER_STAR));

        registry.addIngredientInfo(treasures, VanillaTypes.ITEM_STACK, Component.translatable("message.collectibles.lootbag_entry_jei_desc"));

        registry.addIngredientInfo(new ItemStack(ModItems.LOOT_BAG.get()), VanillaTypes.ITEM_STACK, Component.translatable("message.collectibles.lootbag_jei_desc"));

    }
}**/