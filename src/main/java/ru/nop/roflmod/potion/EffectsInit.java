package ru.nop.roflmod.potion;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.RegistryEvent;
import ru.nop.roflmod.RoflMod;

public class EffectsInit {

    public static Effect CURSE;


    public static void init(RegistryEvent.Register<Effect> event) {

        CURSE = new CurseEffect(EffectType.BENEFICIAL, 0x3C3232).setRegistryName(RoflMod.getResourceLocation("curse_effect"));

        event.getRegistry().register(CURSE);

    }



}
