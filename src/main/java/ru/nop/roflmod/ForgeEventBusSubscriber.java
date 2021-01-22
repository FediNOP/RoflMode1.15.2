package ru.nop.roflmod;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.nop.roflmod.dimensions.DimensionInit;

@Mod.EventBusSubscriber(modid = RoflMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventBusSubscriber {

    @SubscribeEvent
    public static void registerDimensions(final RegisterDimensionsEvent event){
        if(DimensionType.byName(RoflMod.TEST_DIM_TYPE) == null){
            DimensionManager.registerDimension(RoflMod.TEST_DIM_TYPE, DimensionInit.TEST_DIMENSION.get(),null, true);
        }
    }

}
