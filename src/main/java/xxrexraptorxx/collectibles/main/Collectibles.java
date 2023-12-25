package xxrexraptorxx.collectibles.main;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import xxrexraptorxx.collectibles.registry.CreativeModeTabs;
import xxrexraptorxx.collectibles.registry.ModItems;
import xxrexraptorxx.collectibles.registry.ModLootModifiers;
import xxrexraptorxx.collectibles.utils.Config;
import xxrexraptorxx.collectibles.world.LootTableInjection;

/**
 * @author XxRexRaptorxX (RexRaptor)
 * @projectPage https://www.curseforge.com/minecraft/mc-mods/collectibles
 **/
@Mod(References.MODID)
public class Collectibles {

    public static final Logger LOGGER = LogManager.getLogger();

    public Collectibles() {
        IEventBus forgeBus = NeoForge.EVENT_BUS;
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.addListener(this::setup);

        Config.init();
        ModItems.init();
        ModLootModifiers.init();
        CreativeModeTabs.init();
    }

    private void setup(final @NotNull FMLCommonSetupEvent event) {
        NeoForge.EVENT_BUS.addListener(LootTableInjection::onChestLootLoad);
    }

}