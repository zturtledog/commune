package com.confusedparrotfish.commune.lib;

import java.util.Random;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class utils {
    public static final void ejectitem(World world, BlockPos pos, ItemStack item) {
        Random rand = world.rand;

        ItemEntity entityItem = new ItemEntity(world,
                pos.getX()+0.5, pos.getY(), pos.getZ()+0.5,
                item.copy());

        if (item.hasTag()) {
            entityItem.getItem().setTag(item.getTag().copy());
        }

        float factor = 0.005F;
        entityItem.setMotion(
            (rand.nextDouble()-0.5d) * factor,
            (rand.nextDouble()-0.5d) * factor + 0.7F,
            (rand.nextDouble()-0.5d) * factor);
        world.addEntity(entityItem);
    }

    public static final void dropitem(World world, BlockPos pos, ItemStack item) {
        ItemEntity entityItem = new ItemEntity(world,
                pos.getX()+0.5, pos.getY(), pos.getZ()+0.5,
                item.copy());

        if (item.hasTag()) {
            entityItem.getItem().setTag(item.getTag().copy());
        }

        world.addEntity(entityItem);
    }
}
