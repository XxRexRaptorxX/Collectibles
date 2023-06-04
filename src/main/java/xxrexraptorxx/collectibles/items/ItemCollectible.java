package xxrexraptorxx.collectibles.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;

import java.util.List;

public class ItemCollectible extends Item {

    public ItemCollectible() {
        super(new Properties()
                .rarity(Rarity.RARE)
        );
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag isAdvanced) {
        list.add(Component.translatable("message.collectibles.collectible").withStyle(ChatFormatting.GOLD));

        if (this.getDescriptionId().contains("collectibles:") && this.getDescriptionId().contains("fragment")) {
            list.add(Component.translatable("message.collectibles.set_fragments").withStyle(ChatFormatting.YELLOW));

        } else if (this.getDescriptionId().contains("collectibles:") && this.getDescriptionId().contains("coin")) {
            list.add(Component.translatable("message.collectibles.set_coins").withStyle(ChatFormatting.YELLOW));

        } else if (this.getDescriptionId().contains("collectibles:") && this.getDescriptionId().contains("book")) {
            list.add(Component.translatable("message.collectibles.set_books").withStyle(ChatFormatting.YELLOW));

        } else if (this.getDescriptionId().contains("collectibles:") && this.getDescriptionId().contains("fossils")) {
            list.add(Component.translatable("message.collectibles.set_fossils").withStyle(ChatFormatting.YELLOW));

        } else if (this.getDescriptionId().contains("collectibles:") && this.getDescriptionId().contains("jewelry")) {
            list.add(Component.translatable("message.collectibles.set_jewelry").withStyle(ChatFormatting.YELLOW));
        }

        list.add(Component.literal(ChatFormatting.YELLOW + "#" + CollectibleHelper.getCollectibleNumber(stack)));
    }
}
