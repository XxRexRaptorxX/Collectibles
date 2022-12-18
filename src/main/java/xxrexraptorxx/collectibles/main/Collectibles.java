package xxrexraptorxx.collectibles.main;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xxrexraptorxx.collectibles.utils.Config;

/**
 * @author XxRexRaptorxX (RexRaptor)
 * @projectPage https://www.curseforge.com/minecraft/mc-mods/collectibles
 **/
@Mod(References.MODID)
public class Collectibles {

    public static final Logger LOGGER = LogManager.getLogger();
    private static final ResourceLocation CREATIVE_TAB = new ResourceLocation(References.MODID, "tab");

    public Collectibles() {
        Mod.EventBusSubscriber.Bus.MOD.bus().get().register(Collectibles.class);

        Config.init();
        ModItems.init();

        MinecraftForge.EVENT_BUS.register(this);
    }


    //Creative Tab
    @SubscribeEvent
    public static void registerTabs(final CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(CREATIVE_TAB, (cf) -> cf.icon(() -> new ItemStack(ModItems.EPIC_LOOT_BAG.get()))
                .title(Component.translatable("itemGroup." + References.MODID + "_tab")).displayItems((flagSet, output, ifSth) -> {
                    output.accept(ModItems.GOLD_COIN.get());
                    output.accept(ModItems.SILVER_COIN.get());
                    output.accept(ModItems.PLATINUM_COIN.get());
                    output.accept(ModItems.NETHERITE_COIN.get());
                    output.accept(ModItems.COPPER_COIN.get());
                    output.accept(ModItems.BRONZE_COIN.get());
                    output.accept(ModItems.BRASS_COIN.get());
                    output.accept(ModItems.IRON_COIN.get());
                    output.accept(ModItems.STONE_COIN.get());
                    output.accept(ModItems.DIAMOND_FRAGMENT.get());
                    output.accept(ModItems.EMERALD_FRAGMENT.get());
                    output.accept(ModItems.AMETHYST_FRAGMENT.get());
                    output.accept(ModItems.RUBY_FRAGMENT.get());
                    output.accept(ModItems.SAPPHIRE_FRAGMENT.get());
                    output.accept(ModItems.TOPAZ_FRAGMENT.get());
                    output.accept(ModItems.CRYSTAL_FRAGMENT.get());
                    output.accept(ModItems.HEMATITE_FRAGMENT.get());
                    output.accept(ModItems.TOURMALINE_FRAGMENT.get());
                    output.accept(ModItems.OLD_BOOK.get());
                    output.accept(ModItems.NECRONOMICON_BOOK.get());
                    output.accept(ModItems.KNOWLEDGE_BOOK.get());
                    output.accept(ModItems.NOTCHS_BOOK.get());
                    output.accept(ModItems.DARKHOLD_BOOK.get());
                    output.accept(ModItems.MONSTER_BOOK.get());
                    output.accept(ModItems.GRIMOIRE_BOOK.get());
                    output.accept(ModItems.CURSED_BOOK.get());
                    output.accept(ModItems.HEROBRINES_BOOK.get());
                    output.accept(ModItems.LOOT_BAG.get());
                    output.accept(ModItems.EPIC_LOOT_BAG.get());
                })
        );
    }
}