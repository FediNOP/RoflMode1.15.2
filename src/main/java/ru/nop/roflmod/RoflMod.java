package ru.nop.roflmod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.nop.roflmod.bioms.BiomeInit;
import ru.nop.roflmod.dimensions.DimensionInit;


@Mod(RoflMod.MOD_ID)
@Mod.EventBusSubscriber(modid = RoflMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RoflMod {


    public static final String MOD_ID = "roflmod";
    public static final ResourceLocation TEST_DIM_TYPE = new ResourceLocation(MOD_ID, "test");
    // private static final Logger LOGGER = LogManager.getLogger();

    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, RoflMod.MOD_ID);
    public static final RegistryObject<Item> KEY = ITEMS.register("noj", () ->
            new SwordItem(ItemTier.IRON, 12,2F, new Item.Properties().group(ItemGroup.COMBAT)));

    public RoflMod() {
        final IEventBus modeEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modeEventBus.addListener(this::setup);
        // TODO: Сделать отдельный класс для прдеметов
        ITEMS.register(modeEventBus);
        BiomeInit.BIOMES.register(modeEventBus);
        DimensionInit.MOD_DIMENSION.register(modeEventBus);

    }

    @SubscribeEvent
    public static void onRegisterBiome(final RegistryEvent.Register<Biome> event){
        BiomeInit.registerBiomes();
    }



    private void setup(final FMLCommonSetupEvent event){

    }

}
