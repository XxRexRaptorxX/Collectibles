package xxrexraptorxx.collectibles.utils;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import xxrexraptorxx.collectibles.main.ModItems;
import xxrexraptorxx.collectibles.main.References;

public class CreativeTab {

    public static CreativeModeTab MOD_TAB = new CreativeModeTab(References.MODID + "_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.LOOT_BAG.get());
        }
    };
}
