//kingdom of nouns

package com.confusedparrotfish.commune.lib.effect;

import java.util.HashMap;
import java.util.Map;

import com.confusedparrotfish.commune.Comune;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EffectManager extends effect {
    Map<String, Integer> map = new HashMap<String, Integer>();
    
    public EffectManager add(String id) {
        if (map.containsKey(id)) {
            map.put(id,map.get(id)+1);
            return this;
        }

        map.put(id, 0);

        return this;
    }

    @Override
    public void blockdestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos,
            LivingEntity entityLiving, int level) {
        map.forEach((k,v)->{
            Comune.effects.get(k).blockdestroyed(stack, worldIn, state, pos, entityLiving, v+level);;
        });
    }

    @Override
    public void entitywalk(World worldIn, BlockPos pos, Entity entityIn, int level) {
        map.forEach((k,v)->{
            Comune.effects.get(k).entitywalk(worldIn, pos, entityIn, v+level);
        });
    }

    @Override
    public void hitentity(ItemStack stack, LivingEntity target, LivingEntity attacker, int level) {
        map.forEach((k,v)->{
            Comune.effects.get(k).hitentity(stack, target, attacker, v+level);
        });
    }

    @Override
    public void inventorytick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected,
            int level) {
        map.forEach((k,v)->{
            Comune.effects.get(k).inventorytick(stack, worldIn, entityIn, itemSlot, isSelected, v+level);
        });
    }

    @Override
    public String type() {
        return "none";
    }

    @Override
    public void use(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count, int level) {
        map.forEach((k,v)->{
            Comune.effects.get(k).use(worldIn, livingEntityIn, stack, count, v+level);
        });
    } 
}
