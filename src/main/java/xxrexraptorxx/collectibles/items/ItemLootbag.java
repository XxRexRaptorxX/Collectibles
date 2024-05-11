package xxrexraptorxx.collectibles.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import xxrexraptorxx.collectibles.main.ModItems;
import xxrexraptorxx.collectibles.utils.Config;
import xxrexraptorxx.collectibles.utils.CreativeTab;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;

import java.util.List;

public class ItemLootbag extends Item {

    public ItemLootbag(Properties properties) {
        super(properties);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag isAdvanced) {
        list.add(new TranslatableComponent("message.collectibles.open_bag").withStyle(ChatFormatting.BLUE));
    }


    @Override
    public boolean isFoil(ItemStack pStack) {
        if (this == ModItems.EPIC_LOOT_BAG.get()) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if(this == ModItems.LOOT_BAG.get() || this == ModItems.EPIC_LOOT_BAG.get()) {
            level.playSound((Player) null, player.getOnPos(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.15F + 0.0F);
            stack.shrink(1);

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

            if (level.isClientSide) player.awardStat(Stats.ITEM_USED.get(this));
        }

        return InteractionResultHolder.success(stack);
    }
}
