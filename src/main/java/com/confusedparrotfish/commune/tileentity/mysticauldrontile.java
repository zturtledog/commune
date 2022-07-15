package com.confusedparrotfish.commune.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class mysticauldrontile extends TileEntity implements ITickableTileEntity {
    public int mode = 0;

    public mysticauldrontile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public mysticauldrontile() {
        this(ModTileEntities.MYSTIC_CAULDRON_TILE.get());
    }

    @Override
    public void tick() {
        // do stuff here
    }

    public boolean itemrecipie(ItemStack currentItem) {
        return false;
    }
}
