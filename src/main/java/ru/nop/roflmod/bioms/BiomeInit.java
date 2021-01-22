package ru.nop.roflmod.bioms;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.nop.roflmod.RoflMod;

public class BiomeInit {

    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, RoflMod.MOD_ID);
    public static final RegistryObject<Biome> TEST_BIOME = BIOMES.register("testbiome", TestBiome::new);

    public static void registerBiomes(){
        registerBiome(TEST_BIOME.get(), BiomeDictionary.Type.PLAINS);
    }

    private static void registerBiome(Biome biome, BiomeDictionary.Type... types) {
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);
    }

}
