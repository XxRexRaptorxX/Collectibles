package xxrexraptorxx.collectibles.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;

import java.util.List;

public class ItemCollectible extends Item {

    public ItemCollectible() {
        super(new Properties()
                .rarity(Rarity.RARE)
        );
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("message." + References.MODID + ".collectible").withStyle(ChatFormatting.GOLD));

        if (this.getDescriptionId().contains(References.MODID + ":") && this.getDescriptionId().contains("fragment")) {
            list.add(Component.translatable("message." + References.MODID + ".set_fragments").withStyle(ChatFormatting.YELLOW));

        } else if (this.getDescriptionId().contains(References.MODID + ":") && this.getDescriptionId().contains("coin")) {
            list.add(Component.translatable("message." + References.MODID + ".set_coins").withStyle(ChatFormatting.YELLOW));

        } else if (this.getDescriptionId().contains(References.MODID + ":") && this.getDescriptionId().contains("book")) {
            list.add(Component.translatable("message." + References.MODID + ".set_books").withStyle(ChatFormatting.YELLOW));

        } else if (this.getDescriptionId().contains(References.MODID + ":") && this.getDescriptionId().contains("fossils")) {
            list.add(Component.translatable("message." + References.MODID + ".set_fossils").withStyle(ChatFormatting.YELLOW));

        } else if (this.getDescriptionId().contains(References.MODID + ":") && this.getDescriptionId().contains("jewelry")) {
            list.add(Component.translatable("message." + References.MODID + ".set_jewelry").withStyle(ChatFormatting.YELLOW));
        }

        list.add(Component.literal(ChatFormatting.YELLOW + "#" + CollectibleHelper.getCollectibleNumber(stack)));
    }
}
