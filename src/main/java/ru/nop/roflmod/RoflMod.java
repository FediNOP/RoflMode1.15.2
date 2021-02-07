package ru.nop.roflmod;

import net.minecraft.item.*;
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
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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


@Mod(RoflMod.MOD_ID)
@Mod.EventBusSubscriber(modid = RoflMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RoflMod {


    public static final String MOD_ID = "roflmod";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final float max_curse_effect_amplifierIn = 10;
    public static int amplifierIn = 0;
    public static boolean isUsedMilk;

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
        EffectInstance effectInstance = event.getEntityLiving().getActivePotionEffect(EffectsInit.CURSE);
        if (effectInstance != null)
            amplifierIn = effectInstance.getAmplifier();
        else
            amplifierIn = 0;
        RoflMod.LOGGER.debug("Player Death event");
    }

    @SubscribeEvent
    public void playerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        if (max_curse_effect_amplifierIn >= amplifierIn)
            amplifierIn++;
        RoflMod.LOGGER.debug(amplifierIn);
        if (amplifierIn > 0)
            event.getPlayer().addPotionEffect(new EffectInstance(EffectsInit.CURSE, 99999, amplifierIn));

        RoflMod.LOGGER.debug("Player Respawn event");
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event) {
        isUsedMilk = event.getItemStack().getItem().equals(Items.MILK_BUCKET);
        RoflMod.LOGGER.debug("PlayerInteractEvent event");
    }

    @SubscribeEvent
    public void potionRemoveEvent(PotionEvent.PotionRemoveEvent event) {
        if (event.getPotion().equals(EffectsInit.CURSE) && isUsedMilk)
            event.setCanceled(true);

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
