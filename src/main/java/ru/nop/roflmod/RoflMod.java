package ru.nop.roflmod;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nop.roflmod.bioms.BiomeInit;
import ru.nop.roflmod.block.BlockInit;
import ru.nop.roflmod.dimensions.DimensionInit;
import ru.nop.roflmod.items.ItemInit;


@Mod(RoflMod.MOD_ID)
@Mod.EventBusSubscriber(modid = RoflMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RoflMod {


    public static final String MOD_ID = "roflmod";
    public static final ResourceLocation TEST_DIM_TYPE = new ResourceLocation(MOD_ID, "test");
    public static final Logger LOGGER = LogManager.getLogger();

    public RoflMod() {
        final IEventBus modeEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modeEventBus.addListener(this::setup);

        BiomeInit.BIOMES.register(modeEventBus);
        DimensionInit.MOD_DIMENSION.register(modeEventBus);
        ItemInit.ITEMS.register(modeEventBus);
        BlockInit.BLOCKS.register(modeEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void ononRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .forEach(block -> {
                    final Item.Properties properties = new Item.Properties().group(RoflModItemGroup.instance);
                    final BlockItem blockItem = new BlockItem(block, properties);
                    blockItem.setRegistryName(block.getRegistryName());
                    registry.register(blockItem);
                });

        LOGGER.debug("Registered BlockItems!");
    }

    @SubscribeEvent
    public static void onRegisterBiome(final RegistryEvent.Register<Biome> event) {
        BiomeInit.registerBiomes();
    }


    private void setup(final FMLCommonSetupEvent event) {

    }

    public static class RoflModItemGroup extends ItemGroup {

        public static final RoflModItemGroup instance = new RoflModItemGroup(ItemGroup.GROUPS.length, "roflmod");

        private RoflModItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockInit.WOOD_BEAM.get());
        }
    }

}
