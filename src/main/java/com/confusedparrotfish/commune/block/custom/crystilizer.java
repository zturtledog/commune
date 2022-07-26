package com.confusedparrotfish.commune.block.custom;

import java.util.Random;
import java.util.stream.Stream;

import com.confusedparrotfish.commune.item.ModItems;
import com.confusedparrotfish.commune.lib.partikle.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class crystilizer extends Block {
    public static final IntegerProperty LEVEL = IntegerProperty.create("mode", 0, 4);

    public crystilizer(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
            Hand hand, BlockRayTraceResult hit) {

        if (!world.isRemote()) {
            if (state.get(LEVEL) == 0) {
                if (player.inventory.getCurrentItem().getItem() == ModItems.VOID_BOTTLE.get()) {
                    world.setBlockState(pos, state.with(LEVEL, 1), 2);
                    if (!player.abilities.isCreativeMode) {
                        player.inventory.getCurrentItem().shrink(1);
                        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
                    }
                    world.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                } else {
                    ItemStack fall = player.inventory.getCurrentItem().copy();
                    fall.setCount(1);
                    player.inventory.getCurrentItem().shrink(1);
                    utils.ejectitem(world, pos, fall, 0.6d);
                    // particle.eject(world, pos, RedstoneParticleData.REDSTONE_DUST, 0.5625D, 4);
                    // play eject
                }
            }
            if (state.get(LEVEL) > 1) {
                // player.inventory.getCurrentItem().shrink(1);
                utils.ejectitem(world, pos, new ItemStack(
                    state.get(LEVEL) == 2 ? ModItems.LOSIL_FRAGMENT.get() :
                    (state.get(LEVEL) == 3 ? ModItems.SILOS_FRAGMENT.get() : ModItems.FALIS_FRAGMENT.get())
                ),0.6d);
                
                world.setBlockState(pos, state.with(LEVEL, 0), 2);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(LEVEL) > 1) {
            utils.dropitem(world, pos, new ItemStack(
                state.get(LEVEL) == 2 ? ModItems.LOSIL_FRAGMENT.get() :
                (state.get(LEVEL) == 3 ? ModItems.SILOS_FRAGMENT.get() : ModItems.FALIS_FRAGMENT.get())
            ));
        }

        super.onBlockHarvested(world, pos, state, player);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int lvl = 2+random.nextInt(3);
        // System.out.println(lvl+"::"+state.get(LEVEL));
        if (state.get(LEVEL) == 1) {
            world.setBlockState(pos, state.with(LEVEL, lvl), 2);
        }
    }

    public static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 7, 16),
            Block.makeCuboidShape(1, 7, 1, 15, 8, 15),
            Block.makeCuboidShape(2, 8, 5, 5, 9, 11),
            Block.makeCuboidShape(11, 8, 5, 14, 9, 11),
            Block.makeCuboidShape(5, 8, 2, 11, 9, 3),
            Block.makeCuboidShape(5, 8, 13, 11, 9, 14),
            Block.makeCuboidShape(3, 8, 3, 13, 9, 5),
            Block.makeCuboidShape(3, 8, 11, 13, 9, 13),
            Block.makeCuboidShape(5, 8, 10, 6, 9, 11),
            Block.makeCuboidShape(5, 8, 5, 6, 9, 6),
            Block.makeCuboidShape(10, 8, 5, 11, 9, 6),
            Block.makeCuboidShape(10, 8, 10, 11, 9, 11))
            .reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    // public void fillWithRain(World worldIn, BlockPos pos) {
    // if (worldIn.rand.nextInt(20) == 1) {
    // float f = worldIn.getBiome(pos).getTemperature(pos);
    // if (!(f < 0.15F)) {
    // BlockState blockstate = worldIn.getBlockState(pos);
    // worldIn.setBlockState(pos, state.with(LEVEL, Integer.valueOf(0)), 2);
    // }
    // }
    // }// worldIn.setBlockState(pos, state.with(LEVEL, Integer.valueOf(0)), 2);

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }
}
