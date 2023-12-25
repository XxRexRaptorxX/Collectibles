package xxrexraptorxx.collectibles.integration;

//import mezz.jei.api.IModPlugin;
//import mezz.jei.api.JeiPlugin;
//import mezz.jei.api.constants.VanillaTypes;
//import mezz.jei.api.registration.IRecipeRegistration;
//import mezz.jei.api.runtime.IIngredientManager;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//import net.minecraftforge.registries.ForgeRegistries;
//import xxrexraptorxx.collectibles.main.Collectibles;
//import xxrexraptorxx.collectibles.registry.ModItems;
//import xxrexraptorxx.collectibles.main.References;
//import xxrexraptorxx.collectibles.utils.Config;
//
//import java.util.ArrayList;
//
//@JeiPlugin        TODO!
//public class JEIIntegration implements IModPlugin {
//
//    private static final ResourceLocation ID = new ResourceLocation(References.MODID, "jei_plugin");
//
//    @Override
//    public ResourceLocation getPluginUid() {
//        return ID;
//    }
//
//    @Override
//    public void registerRecipes(IRecipeRegistration registry) {
//        IIngredientManager ingredientManager = registry.getIngredientManager();
//
//        ArrayList<ItemStack> treasures = new ArrayList<ItemStack>();
//        ArrayList<ItemStack> epic_treasures = new ArrayList<ItemStack>();
//
//        for (String item : Config.LOOT_BAG_REWARDS.get()) {
//            try {
//                treasures.add(new ItemStack(ForgeRegistries.ITEMS.getValue(
//                        //                                          get the mod prefix              |        get the item registry name      |         get the item amount
//                        new ResourceLocation(item.substring(item.indexOf('*') + 1, item.indexOf(':')), item.substring(item.indexOf(':') + 1))), Integer.parseInt(item.substring(0, item.indexOf('*')))));
//
//            } catch (Exception e) {
//                Collectibles.LOGGER.error("Invalid item entry in the Collectibles 'epic_loot_bag_rewards' config option!");
//            }
//        }
//
//        for (String item : Config.EPIC_LOOT_BAG_REWARDS.get()) {
//            try {
//                epic_treasures.add(new ItemStack(ForgeRegistries.ITEMS.getValue(
//                        //                                          get the mod prefix              |        get the item registry name      |         get the item amount
//                        new ResourceLocation(item.substring(item.indexOf('*') + 1, item.indexOf(':')), item.substring(item.indexOf(':') + 1))), Integer.parseInt(item.substring(0, item.indexOf('*')))));
//
//            } catch (Exception e) {
//                Collectibles.LOGGER.error("Invalid item entry in the Collectibles 'epic_loot_bag_rewards' config option!");
//            }
//        }
//
//        registry.addIngredientInfo(treasures, VanillaTypes.ITEM_STACK, Component.translatable("message.collectibles.lootbag_entry_jei_desc"));
//        registry.addIngredientInfo(epic_treasures, VanillaTypes.ITEM_STACK, Component.translatable("message.collectibles.epic_lootbag_entry_jei_desc"));
//        registry.addIngredientInfo(new ItemStack(ModItems.LOOT_BAG.get()), VanillaTypes.ITEM_STACK, Component.translatable("message.collectibles.lootbag_jei_desc"));
//
//    }
//}