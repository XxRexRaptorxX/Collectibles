package xxrexraptorxx.collectibles.main;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xxrexraptorxx.collectibles.registry.CreativeModeTabs;
import xxrexraptorxx.collectibles.registry.ModItems;
import xxrexraptorxx.collectibles.registry.ModLootModifiers;
import xxrexraptorxx.collectibles.utils.Config;

/**
 * @author XxRexRaptorxX (RexRaptor)
 * @projectPage <a href="https://www.curseforge.com/minecraft/mc-mods/collectibles">...</a>
 **/
@Mod(References.MODID)
public class Collectibles {

    public static final Logger LOGGER = LogManager.getLogger();

    public Collectibles(IEventBus eventBus, ModContainer container) {
        Config.init(container);
        ModItems.init(eventBus);
        ModLootModifiers.init(eventBus);
        CreativeModeTabs.init(eventBus);
    }
}