package xxrexraptorxx.collectibles.registry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.magmacore.content.TagHelper;

public class ModTags {

    public static final TagKey<Block> DROPS_COINS =
            TagHelper.createBlockTag(References.MODID, "drops_collectibles/coins");
    public static final TagKey<Block> DROPS_FRAGMENTS =
            TagHelper.createBlockTag(References.MODID, "drops_collectibles/fragments");
    public static final TagKey<Block> DROPS_FOSSILS =
            TagHelper.createBlockTag(References.MODID, "drops_collectibles/fossils");
}
