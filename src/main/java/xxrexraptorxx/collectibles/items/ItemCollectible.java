package xxrexraptorxx.collectibles.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;
import xxrexraptorxx.collectibles.utils.CreativeTab;

import java.util.List;

public class ItemCollectible extends Item {

    public ItemCollectible() {
        super(new Properties()
                .tab(CreativeTab.MOD_TAB)
                .rarity(Rarity.RARE)
        );
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag isAdvanced) {
        list.add(new TranslatableComponent("message.collectibles.collectible").withStyle(ChatFormatting.GOLD));

        if (this.getRegistryName().toString().contains("collectibles:") && this.getRegistryName().toString().contains("fragment")) {
            list.add(new TranslatableComponent("message.collectibles.set_fragments").withStyle(ChatFormatting.YELLOW));

        } else if (this.getRegistryName().toString().contains("collectibles:") && this.getRegistryName().toString().contains("coin")) {
            list.add(new TranslatableComponent("message.collectibles.set_coins").withStyle(ChatFormatting.YELLOW));

        } else if (this.getRegistryName().toString().contains("collectibles:") && this.getRegistryName().toString().contains("book")) {
            list.add(new TranslatableComponent("message.collectibles.set_books").withStyle(ChatFormatting.YELLOW));
        }

        list.add(new TextComponent(ChatFormatting.YELLOW + "#" + CollectibleHelper.getCollectibleNumber(stack)));
    }
}
