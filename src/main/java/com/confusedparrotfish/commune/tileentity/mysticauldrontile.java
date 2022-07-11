package com.confusedparrotfish.commune.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class mysticauldrontile extends TileEntity {
    public mysticauldrontile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public mysticauldrontile() {
        this(ModTileEntities.MYSTIC_CAULDRON_TILE.get());
    }
    
    //
}
