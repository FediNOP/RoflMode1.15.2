package ru.nop.roflmod.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class CurseEffect extends Effect {

    public CurseEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
        addAttributesModifier(SharedMonsterAttributes.MAX_HEALTH, "91AEAA56-376B-4498-935B-2F7F68070635", -1.0D, AttributeModifier.Operation.ADDITION);
    }


}
