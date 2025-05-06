package xxrexraptorxx.collectibles.main;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
 * @projectPage <a href="https://www.curseforge.com/minecraft/mc-mods/collectibles">...</a>
 **/
@Mod(References.MODID)
public class Collectibles {

    public static final Logger LOGGER = LogManager.getLogger();

    public Collectibles() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
        modBus.addListener(this::setup);

        Config.init();
        ModItems.init();
        ModLootModifiers.init();
        CreativeModeTabs.init();
    }

    private void setup(final @NotNull FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.addListener(LootTableInjection::onChestLootLoad);
    }

}