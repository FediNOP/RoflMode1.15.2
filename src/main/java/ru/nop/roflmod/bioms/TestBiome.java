package ru.nop.roflmod.bioms;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class TestBiome extends Biome {

    protected TestBiome() {
        super((new Biome.Builder())
                .surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
                .precipitation(RainType.SNOW)
                .category(Category.PLAINS)
                .depth(0.12F)
                .scale(1.2F)
                .temperature(0.7F)
                .downfall(0.8F)
                .waterColor(9044739)
                .waterFogColor(9044739)
                .parent((String)null));

        this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.12313423f)));
        this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.HELL_CAVE, new ProbabilityConfig(0.02f)));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CHICKEN, 12, 25, 60));

        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addOres(this);

    }

}
