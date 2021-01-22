package ru.nop.roflmod.dimensions;

import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.nop.roflmod.RoflMod;

public class DimensionInit {

    public static final DeferredRegister<ModDimension> MOD_DIMENSION = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, RoflMod.MOD_ID);
    public static final RegistryObject<ModDimension> TEST_DIMENSION = MOD_DIMENSION.register("test_dimension", () -> new TestModDimension());


}
