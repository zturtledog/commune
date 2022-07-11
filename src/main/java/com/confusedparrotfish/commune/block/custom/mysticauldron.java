package com.confusedparrotfish.commune.block.custom;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class mysticauldron extends Block {
    public mysticauldron(Properties properties) {
        super(properties);
        //TODO Auto-generated constructor stub
    }

    public static final VoxelShape SHAPE = Stream.of(
        Block.makeCuboidShape(0, 1.5, 0, 5, 3, 5),
        Block.makeCuboidShape(11, 1.5, 0, 16, 3, 5),
        Block.makeCuboidShape(11, 1.5, 11, 16, 3, 16),
        Block.makeCuboidShape(0, 1.5, 11, 5, 3, 16),
        Block.makeCuboidShape(0, 0, 14, 2, 1.5, 16),
        Block.makeCuboidShape(0, 0, 0, 2, 1.5, 2),
        Block.makeCuboidShape(14, 0, 0, 16, 1.5, 2),
        Block.makeCuboidShape(14, 0, 14, 16, 1.5, 16),
        Block.makeCuboidShape(0, 3, 0, 16, 4, 16),
        Block.makeCuboidShape(0, 4, 0, 1, 17, 16),
        Block.makeCuboidShape(15, 4, 0, 16, 17, 16),
        Block.makeCuboidShape(1, 4, 15, 15, 17, 16),
        Block.makeCuboidShape(1, 4, 0, 15, 17, 1)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
