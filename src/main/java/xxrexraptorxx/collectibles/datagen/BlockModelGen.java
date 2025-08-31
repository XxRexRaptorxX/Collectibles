package xxrexraptorxx.collectibles.datagen;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.resources.ResourceLocation;

public class BlockModelGen extends BlockModelGenerators {

    public BlockModelGen(
            Consumer<BlockModelDefinitionGenerator> blockstateOutput,
            ItemModelOutput itemModelOutput,
            BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(blockstateOutput, itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        //
    }
}
