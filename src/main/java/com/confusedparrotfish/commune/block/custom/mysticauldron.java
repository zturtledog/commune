package com.confusedparrotfish.commune.block.custom;

import java.util.stream.Stream;

import com.confusedparrotfish.commune.lib.utils;
import com.confusedparrotfish.commune.tileentity.ModTileEntities;
import com.confusedparrotfish.commune.tileentity.mysticauldrontile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
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

public class mysticauldron extends Block {
    public static final IntegerProperty LEVEL = IntegerProperty.create("mode", 0, 5);

    public mysticauldron(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.MYSTIC_CAULDRON_TILE.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
            Hand hand, BlockRayTraceResult hit) {

        // System.out.println(Blocks.ACACIA_BUTTON);

        // System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa");

        if (!world.isRemote()) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof mysticauldrontile) {
                ((mysticauldrontile)tile).mode = state.get(LEVEL);

                ItemStack main = player.inventory.getCurrentItem();

                if (main.equals(new ItemStack(Items.WATER_BUCKET), true) && ((mysticauldrontile) tile).mode == 0) {
                    ((mysticauldrontile) tile).mode = 1;
                    world.setBlockState(pos, state.with(LEVEL,1), 1);
                    if (!player.abilities.isCreativeMode) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem,
                                new ItemStack(Items.BUCKET));
                    }
                    world.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F,
                            1.0F);
                } else if (((mysticauldrontile) tile).mode == 0
                        && ((mysticauldrontile) tile).itemrecipie(player.inventory.getCurrentItem())) {
                    // play splash
                } else if (((mysticauldrontile) tile).mode > 0) {
                    ItemStack fall = main.copy();
                    fall.setCount(1);
                    main.shrink(1);
                    utils.ejectitem(world, pos, fall);
                    if (((mysticauldrontile) tile).mode > 2) {
                        ((mysticauldrontile) tile).sour(world, pos, player);
                    }
                    // play eject
                }

                // world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(),
                // SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.PLAYERS, 1.0F, 1.0F);
                return ActionResultType.SUCCESS;
            }
        }

        return ActionResultType.SUCCESS;
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
            Block.makeCuboidShape(1, 4, 0, 15, 17, 1))
            .reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    // public void fillWithRain(World worldIn, BlockPos pos) {
    //     if (worldIn.rand.nextInt(20) == 1) {
    //         float f = worldIn.getBiome(pos).getTemperature(pos);
    //         if (!(f < 0.15F)) {
    //             BlockState blockstate = worldIn.getBlockState(pos);
    //             worldIn.setBlockState(pos, state.with(LEVEL, Integer.valueOf(0)), 2);
    //         }
    //     }
    // }// worldIn.setBlockState(pos, state.with(LEVEL, Integer.valueOf(0)), 2);

    @Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(LEVEL);
	}
}
