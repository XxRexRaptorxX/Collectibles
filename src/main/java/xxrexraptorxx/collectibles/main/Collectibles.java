package xxrexraptorxx.collectibles.main;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xxrexraptorxx.collectibles.registry.CreativeModeTabs;
import xxrexraptorxx.collectibles.registry.ModItems;
import xxrexraptorxx.collectibles.registry.ModLootModifiers;
import xxrexraptorxx.collectibles.registry.ModStats;
import xxrexraptorxx.collectibles.utils.Config;
import xxrexraptorxx.magmacore.config.ConfigHelper;
import xxrexraptorxx.magmacore.main.ModRegistry;

/**
 * @author XxRexRaptorxX (RexRaptor)
 * @projectPage <a href="https://www.curseforge.com/minecraft/mc-mods/collectibles">...</a>
 **/
@Mod(References.MODID)
public class Collectibles {

    public static final Logger LOGGER = LogManager.getLogger();

    public Collectibles(IEventBus eventBus, ModContainer container) {
        ModItems.init(eventBus);
        ModStats.init(eventBus);
        ModLootModifiers.init(eventBus);
        CreativeModeTabs.init(eventBus);

        ConfigHelper.registerConfigs(container, References.MODID, false, Config.SERVER_CONFIG, null);
        ModRegistry.register(References.MODID, References.NAME, References.URL);
    }

    @Mod(value = References.MODID, dist = Dist.CLIENT)
    public static class CollectiblesClient {

        public CollectiblesClient(ModContainer container) {
            ConfigHelper.registerIngameConfig(container);
        }
    }
}
