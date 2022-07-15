package com.confusedparrotfish.commune.lib;

import java.util.Random;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class utils {
    public static final void ejectitem(World world, BlockPos pos, ItemStack item) {
        Random rand = new Random();

        ItemEntity entityItem = new ItemEntity(world,
                pos.getX()+0.5, pos.getY(), pos.getZ()+0.5,
                item.copy());

        if (item.hasTag()) {
            entityItem.getItem().setTag(item.getTag().copy());
        }

        float factor = 0.05F;
        entityItem.setMotion(
                rand.nextGaussian()// * factor
                // 0
                ,
                rand.nextGaussian() * 1.7F,
                rand.nextGaussian()// * factor
                // 0
                );
        world.addEntity(entityItem);
    }
}
