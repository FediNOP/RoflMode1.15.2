package ru.nop.roflmod;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
import ru.nop.roflmod.items.ItemInit;
import ru.nop.roflmod.potion.EffectsInit;

import java.util.Objects;


@Mod(RoflMod.MOD_ID)
@Mod.EventBusSubscriber(modid = RoflMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RoflMod {


    public static final String MOD_ID = "roflmod";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final int max_curse_effect_amplifierIn = 10;
    public static int curse_effect_amplifierIn = 1;

    public RoflMod() {
        final IEventBus modeEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modeEventBus.addListener(this::setup);

        BiomeInit.BIOMES.register(modeEventBus);
        ItemInit.ITEMS.register(modeEventBus);
        BlockInit.BLOCKS.register(modeEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
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

    @SubscribeEvent
    public static void registerPotions(final RegistryEvent.Register<Potion> event) {

        RoflMod.LOGGER.debug("Registered Potions");
    }

    @SubscribeEvent
    public static void registerEffects(final RegistryEvent.Register<Effect> event) {
        EffectsInit.init(event);
        RoflMod.LOGGER.debug("Registered Effects");
    }

    public static ResourceLocation getResourceLocation(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        RoflMod.LOGGER.debug("Player Death event");
    }

    @SubscribeEvent
    public void playerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        event.getPlayer().addPotionEffect(new EffectInstance(EffectsInit.CURSE, 3600, curse_effect_amplifierIn));
        if (curse_effect_amplifierIn < max_curse_effect_amplifierIn)
            curse_effect_amplifierIn++;

        RoflMod.LOGGER.debug("Player Respawn event");
    }

    @SubscribeEvent
    public void potionRemoveEvent(PotionEvent.PotionRemoveEvent event) {
        if (Objects.requireNonNull(event.getPotionEffect()).getEffectName().equals(EffectsInit.CURSE.getName())) {
            event.setCanceled(true);
        }
        RoflMod.LOGGER.debug("Player PotionRemoveEvent event");
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
