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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class mysticauldron extends Block {
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

        // System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa");
        
        if (!world.isRemote()){
            TileEntity tile = world.getTileEntity(pos);
            // IItemHandler inventory = new PlayerMainInvWrapper(player.inventory);
            if (tile instanceof mysticauldrontile){
                ItemStack main = player.inventory.getCurrentItem();

                if (main.equals(new ItemStack(Items.WATER_BUCKET), true)) {
                    ((mysticauldrontile)tile).mode = 1;
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.BUCKET));
                    // System.out.println("mode == 1 from 0, in slot: "+);
                }else if (((mysticauldrontile)tile).itemrecipie(player.inventory.getCurrentItem())) {
                    //
                } else {
                    ItemStack fall = main.copy();
                    fall.setCount(1);
                    main.shrink(1);
                    utils.ejectitem(world, pos, fall);
                }

                // world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.PLAYERS, 1.0F, 1.0F);
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
        Block.makeCuboidShape(1, 4, 0, 15, 17, 1)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
