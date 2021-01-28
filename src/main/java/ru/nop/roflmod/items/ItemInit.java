package ru.nop.roflmod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.nop.roflmod.RoflMod;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, RoflMod.MOD_ID);

    public static final RegistryObject<Item> KEY = ITEMS.register("noj",
            () -> new SwordItem(ItemTier.IRON, 12,2F, new Item.Properties().group(RoflMod.RoflModItemGroup.instance)));

    public static final RegistryObject<Item> BARK = ITEMS.register("bark",
            () -> new Item(new Item.Properties().group(RoflMod.RoflModItemGroup.instance)){
                @Override
                public int getBurnTime(ItemStack itemStack) {
                    return 90;
                }
            });



}
