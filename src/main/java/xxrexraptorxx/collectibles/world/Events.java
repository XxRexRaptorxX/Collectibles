package xxrexraptorxx.collectibles.world;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.VillagerTradingManager;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.utils.CollectibleHelper;
import xxrexraptorxx.collectibles.utils.Config;

import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = References.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Events {


    /** Update-Checker **/
    private static boolean hasShownUp = false;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (Config.UPDATE_CHECKER.get()) {
            if (!hasShownUp && Minecraft.getInstance().screen == null) {
                if (VersionChecker.getResult(ModList.get().getModContainerById(References.MODID).get().getModInfo()).status() == VersionChecker.Status.OUTDATED ||
                        VersionChecker.getResult(ModList.get().getModContainerById(References.MODID).get().getModInfo()).status() == VersionChecker.Status.BETA_OUTDATED) {

                    TextComponent clickevent = new TextComponent(ChatFormatting.RED + "Click here to update!");
                    clickevent.withStyle(clickevent.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, References.URL)));

                    Minecraft.getInstance().player.sendMessage(new TextComponent(ChatFormatting.BLUE + "A newer version of " + ChatFormatting.YELLOW + References.NAME + ChatFormatting.BLUE + " is available!"), UUID.randomUUID());
                    Minecraft.getInstance().player.sendMessage(clickevent, UUID.randomUUID());

                    hasShownUp = true;
                } else if (VersionChecker.getResult(ModList.get().getModContainerById(References.MODID).get().getModInfo()).status() == VersionChecker.Status.FAILED) {
                    System.err.println(References.NAME + "'s version checker failed!");
                    hasShownUp = true;
                }
            }
        }
    }


    /** Collectible Drop **/
    @SubscribeEvent
    public static void onBlockDestroyed(BlockEvent.BreakEvent event) {
        Random random = new Random();
        Level level = event.getPlayer().level;
        Block block = event.getState().getBlock();
        BlockPos pos = event.getPos();

        if (!level.isClientSide) {

            if (block.getTags().contains(BlockTags.BASE_STONE_OVERWORLD.getName()) || block.getTags().contains(Tags.Blocks.STONE.getName())) {

                if (random.nextInt(Config.COLLECTIBLE_RARITY.get()) == 1) {
                    level.playSound((Player) null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.15F + 0.F);

                    ItemEntity drop = new ItemEntity(level, (double) pos.getX() + 0.5D, (double) pos.getY() + 1.5D, (double) pos.getZ() + 0.5D, CollectibleHelper.getRandomFragment());
                    level.addFreshEntity(drop);
                }


            } else if (block.getTags().contains(BlockTags.DIRT.getName()) || block.getTags().contains(BlockTags.SAND.getName())) {
                if (random.nextInt(Config.COLLECTIBLE_RARITY.get()) == 1) {
                    level.playSound((Player) null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.15F + 0.F);

                    ItemEntity drop = new ItemEntity(level, (double) pos.getX() + 0.5D, (double) pos.getY() + 1.5D, (double) pos.getZ() + 0.5D, CollectibleHelper.getRandomCoin());
                    level.addFreshEntity(drop);
                }
            }
        }
    }


}
