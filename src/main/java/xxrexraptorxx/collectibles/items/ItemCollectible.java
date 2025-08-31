package xxrexraptorxx.collectibles.items;

import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;
import xxrexraptorxx.magmacore.utils.FormattingHelper;

public class ItemCollectible extends Item {

    public ItemCollectible(Properties properties) {
        super(properties.rarity(Rarity.RARE));
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            TooltipDisplay display,
            Consumer<Component> list,
            TooltipFlag flag) {
        list.accept(FormattingHelper.setModLangComponent("message", References.MODID, "collectible")
                .withStyle(ChatFormatting.GOLD));
        if (this.getDescriptionId().contains(References.MODID)
                && this.getDescriptionId().contains("fragment")) {
            list.accept(FormattingHelper.setModLangComponent("message", References.MODID, "set_fragments")
                    .withStyle(ChatFormatting.YELLOW));

        } else if (this.getDescriptionId().contains(References.MODID)
                && this.getDescriptionId().contains("coin")) {
            list.accept(FormattingHelper.setModLangComponent("message", References.MODID, "set_coins")
                    .withStyle(ChatFormatting.YELLOW));

        } else if (this.getDescriptionId().contains(References.MODID)
                && this.getDescriptionId().contains("book")) {
            list.accept(FormattingHelper.setModLangComponent("message", References.MODID, "set_books")
                    .withStyle(ChatFormatting.YELLOW));

        } else if (this.getDescriptionId().contains(References.MODID)
                && this.getDescriptionId().contains("fossil")) {
            list.accept(FormattingHelper.setModLangComponent("message", References.MODID, "set_fossils")
                    .withStyle(ChatFormatting.YELLOW));

        } else if (this.getDescriptionId().contains(References.MODID)
                && this.getDescriptionId().contains("jewelry")) {
            list.accept(FormattingHelper.setModLangComponent("message", References.MODID, "set_jewelry")
                    .withStyle(ChatFormatting.YELLOW));

        } else if (this.getDescriptionId().contains(References.MODID)
                && this.getDescriptionId().contains("leave")) {
            list.accept(FormattingHelper.setModLangComponent("message", References.MODID, "set_leaves")
                    .withStyle(ChatFormatting.YELLOW));
        }

        list.accept(Component.literal(ChatFormatting.YELLOW + "#" + CollectibleHelper.getCollectibleNumber(stack)));
    }
}
