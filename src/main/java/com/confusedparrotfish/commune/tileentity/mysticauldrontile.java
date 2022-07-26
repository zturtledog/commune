package com.confusedparrotfish.commune.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class mysticauldrontile extends TileEntity implements ITickableTileEntity {
    public int mode = 0;

    public mysticauldrontile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public mysticauldrontile() {
        this(ModTileEntities.MYSTIC_CAULDRON_TILE.get());
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("mode", mode);
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        mode = nbt.getInt("mode");
    }

    @Override
    public void tick() {
        // do stuff here

        if (mode == 1) {
            //check conf for boil
        }
    }

    public boolean itemrecipie(ItemStack currentItem) {
        return false;
    }

    public void sour(World world, BlockPos pos, PlayerEntity player) {
    }
}

/**\,odes
 * 0 empty      :0-
 * 1 water      :1-
 * 2 boil       :1-0
 * 3 selected   :1-0
 * 4 soured     :1-0
 * 5 sucseed    :1-
 *
 * \,ffects
 * 0 cloud
 * 1 eject
 * 2 poof
 * 
 * \,tates
 * 0 empty
 * 1 filled
 */
