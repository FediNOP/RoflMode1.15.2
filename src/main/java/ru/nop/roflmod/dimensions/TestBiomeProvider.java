package ru.nop.roflmod.dimensions;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import ru.nop.roflmod.bioms.BiomeInit;

import java.util.Set;

public class TestBiomeProvider extends BiomeProvider {

    public TestBiomeProvider(){
        super(biomeList);
    }

    private static final Set<Biome> biomeList = ImmutableSet.of(BiomeInit.TEST_BIOME.get());

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return BiomeInit.TEST_BIOME.get();
    }
}
