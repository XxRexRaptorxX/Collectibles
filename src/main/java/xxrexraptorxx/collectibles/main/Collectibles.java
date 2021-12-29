package xxrexraptorxx.collectibles.main;

import net.minecraftforge.common.MinecraftForge;
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

    public Collectibles() {
        Config.init();
        ModItems.init();

        MinecraftForge.EVENT_BUS.register(this);
    }
}