package xxrexraptorxx.collectibles.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.registry.ModItems;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;
import xxrexraptorxx.collectibles.utils.Config;

import java.util.function.Consumer;

public class ItemLootbag extends Item {

    public ItemLootbag(Properties properties) {
        super(properties);
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> list, TooltipFlag flag) {
        list.accept(Component.translatable("message." + References.MODID + ".open_bag").withStyle(ChatFormatting.BLUE));
    }


    @Override
    public boolean isFoil(ItemStack stack) {
        if (this == ModItems.EPIC_LOOT_BAG.get()) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if(this == ModItems.LOOT_BAG.get() || this == ModItems.EPIC_LOOT_BAG.get()) {
            level.playSound((Player) null, player.getOnPos(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.15F + 0.0F);

            if (!level.isClientSide) {
                if (this == ModItems.EPIC_LOOT_BAG.get()) {
                    player.giveExperiencePoints(Config.EPIC_LOOT_BAG_XP.get());

                    for (int i = 0; i < Config.EPIC_LOOT_BAG_ITEM_AMOUNT.get(); i++ ) {
                        player.addItem(CollectibleHelper.getRandomEpicTreasure());
                    }

                } else {
                    player.giveExperiencePoints(Config.LOOT_BAG_XP.get());

                    for (int i = 0; i < Config.LOOT_BAG_ITEM_AMOUNT.get(); i++ ) {
                        player.addItem(CollectibleHelper.getRandomTreasure());
                    }
                }

            }

            stack.shrink(1);
            if (level.isClientSide) player.awardStat(Stats.ITEM_USED.get(this));
        }

        return InteractionResult.CONSUME;
    }
}
