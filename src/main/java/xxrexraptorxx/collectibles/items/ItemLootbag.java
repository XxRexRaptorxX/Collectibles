package xxrexraptorxx.collectibles.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import xxrexraptorxx.collectibles.main.ModItems;
import xxrexraptorxx.collectibles.utils.Config;
import xxrexraptorxx.collectibles.utils.CreativeTab;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;

import java.util.List;

public class ItemLootbag extends Item {

    public ItemLootbag() {
        super(new Properties()
                .tab(CreativeTab.MOD_TAB)
                .rarity(Rarity.EPIC)
                .stacksTo(16)
        );
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag isAdvanced) {
        list.add(new TranslatableComponent("message.collectibles.open_bag").withStyle(ChatFormatting.BLUE));
    }


    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();

        if(this == ModItems.LOOT_BAG.get()) {
            level.playSound((Player) null, context.getClickedPos(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.15F + 0.0F);
            context.getItemInHand().shrink(1);

            if (!level.isClientSide) {
                player.giveExperiencePoints(Config.LOOT_BAG_XP.get());

                for (int i = 0; i < Config.LOOT_BAG_ITEM_AMOUNT.get(); i++ ) {
                    player.addItem(CollectibleHelper.getRandomTreasure());
                }
            }
        }

        return InteractionResult.SUCCESS;
    }
}
