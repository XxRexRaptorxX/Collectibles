package xxrexraptorxx.collectibles.world;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import xxrexraptorxx.collectibles.main.Collectibles;
import xxrexraptorxx.collectibles.main.References;
import xxrexraptorxx.collectibles.utils.mixins.LootTableAccessor;

import java.util.Map;
import java.util.Set;

/**
 * Handles loading mod loot tables as well as injecting pools into vanilla tables
 * Inspired (or almost copied) from @williewillus LootHandler for vazkii's Botania && teamLapen's Vampirism
 * https://github.com/williewillus/Botania/blob/07f68b37da9ad3a246b95c042cd6c10bd91698d1/src/main/java/vazkii/botania/common/core/loot/LootHandler.java
 * https://github.com/TeamLapen/Vampirism/blob/dev_1.20/src/main/java/de/teamlapen/vampirism/core/ModLootTables.java
 */
@Mod.EventBusSubscriber(modid = References.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootTableInjection {

    private static final Set<ResourceLocation> LOOT_TABLES = Sets.newHashSet();
    private static final Map<String, ResourceLocation> INJECTION_TABLES = Maps.newHashMap();
    //inject
    public static final ResourceLocation abandoned_mineshaft = registerInject("abandoned_mineshaft");
    public static final ResourceLocation jungle_temple = registerInject("jungle_temple");
    public static final ResourceLocation stronghold_library = registerInject("stronghold_library");
    public static final ResourceLocation bastion_treasure = registerInject("bastion_treasure");
    public static final ResourceLocation pillager_outpost = registerInject("pillager_outpost");
    public static final ResourceLocation shipwreck_treasure = registerInject("shipwreck_treasure");
    public static final ResourceLocation woodland_mansion = registerInject("woodland_mansion");
    public static final ResourceLocation buried_treasure = registerInject("buried_treasure");

    public static final ResourceLocation desert_pyramid = registerInject("desert_pyramid");
    public static final ResourceLocation desert_well = registerInject("desert_well");
    public static final ResourceLocation ocean_ruin_warm = registerInject("ocean_ruin_warm");
    public static final ResourceLocation ocean_ruin_cold = registerInject("ocean_ruin_cold");
    public static final ResourceLocation trail_ruins_common = registerInject("trail_ruins_common");
    public static final ResourceLocation trail_ruins_rare = registerInject("trail_ruins_rare");

    private static int injected = 0;

    static @NotNull ResourceLocation registerInject(String resourceName) {
        ResourceLocation registryName = register("inject/" + resourceName);
        INJECTION_TABLES.put(resourceName, registryName);
        return registryName;
    }

    static @NotNull ResourceLocation register(@NotNull String resourceName) {
        return register(new ResourceLocation(References.MODID, resourceName));
    }

    static @NotNull ResourceLocation register(@NotNull ResourceLocation resourceLocation) {
        LOOT_TABLES.add(resourceLocation);
        return resourceLocation;
    }

    public static @NotNull Set<ResourceLocation> getLootTables() {
        return ImmutableSet.copyOf(LOOT_TABLES);
    }


    @SubscribeEvent
    public static void onChestLootLoad(@NotNull LootTableLoadEvent event) {
        String prefix = "minecraft:chests/";
        String name = event.getName().toString();
        if (name.startsWith(prefix)) {
            String file = name.substring(name.indexOf(prefix) + prefix.length());
            if (INJECTION_TABLES.containsKey(file)) {
                try {
                    ((LootTableAccessor) event.getTable()).getPools().add(getInjectPool(file));
                    injected++;
                } catch (NullPointerException e) {
                    Collectibles.LOGGER.error("Loottable {} is broken by some other mod. Cannot add " + References.NAME + " loot to it. " + name);
                }
            }
        }
    }


    @SubscribeEvent
    public static void onArchaeologyLootLoad(@NotNull LootTableLoadEvent event) {
        String prefix = "minecraft:archaeology/";
        String name = event.getName().toString();
        if (name.startsWith(prefix)) {
            String file = name.substring(name.indexOf(prefix) + prefix.length());
            if (INJECTION_TABLES.containsKey(file)) {
                try {
                    ((LootTableAccessor) event.getTable()).getPools().add(getInjectPool(file));
                    injected++;
                } catch (NullPointerException e) {
                    Collectibles.LOGGER.error("Loottable {} is broken by some other mod. Cannot add " + References.NAME + " loot to it. " + name);
                }
            }
        }
    }


    private static @NotNull LootPool getInjectPool(String entryName) {
        LootPoolEntryContainer.Builder<?> entryBuilder = LootTableReference.lootTableReference(INJECTION_TABLES.get(entryName)).setWeight(1);
        return LootPool.lootPool().setBonusRolls(UniformGenerator.between(0, 1)).setRolls(ConstantValue.exactly(1)).add(entryBuilder).build();
    }

    /**
     * @return 0 if alright, or the count of not injected loottables
     */
    public static int checkAndResetInsertedAll() {
        int i = injected;
        injected = 0;
        return Math.max(0, INJECTION_TABLES.size() - i); //Sponge loads the loot tables for all worlds at start. Which makes this test not work anyway.
    }

}

//                              TEST command
//      /setblock ~ ~ ~ minecraft:chest{LootTable:"minecraft:chests/abandoned_mineshaft"}