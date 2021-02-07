package ru.nop.roflmod.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.nop.roflmod.RoflMod;
import ru.nop.roflmod.potion.EffectsInit;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, RoflMod.MOD_ID);

    public static final RegistryObject<Item> KEY = ITEMS.register("noj",
            () -> new SwordItem(ItemTier.IRON, 12, 2F, new Item.Properties().group(RoflMod.RoflModItemGroup.instance)));

    public static final RegistryObject<Item> BARK = ITEMS.register("bark",
            () -> new Item(new Item.Properties().group(RoflMod.RoflModItemGroup.instance)) {
                @Override
                public int getBurnTime(ItemStack itemStack) {
                    return 90;
                }
            });

    public static final RegistryObject<Item> HUMAN_EFFIGY = ITEMS.register("human_effigy",
            () -> new Item(new Item.Properties().group(RoflMod.RoflModItemGroup.instance)
                    .food(new Food.Builder().fastToEat().setAlwaysEdible().build())) {

                @Override
                public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
                    if (!worldIn.isRemote) {
                        entityLiving.removePotionEffect(EffectsInit.CURSE);
                    }
                    return super.onItemUseFinish(stack, worldIn, entityLiving);
                }

            });


}
