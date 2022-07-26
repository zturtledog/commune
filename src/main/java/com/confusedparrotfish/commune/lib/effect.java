package com.confusedparrotfish.commune.lib;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class effect {
    public void use(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {}

    public void rightclick(World worldIn, PlayerEntity playerIn, Hand handIn) {}

    public void blockdestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {}
}
