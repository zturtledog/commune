package com.confusedparrotfish.commune.tileentity;

import com.confusedparrotfish.commune.Comune;
import com.confusedparrotfish.commune.block.ModBlocks;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;;;

public class ModTileEntities {
    public static DeferredRegister<TileEntityType<?>> tiles =
        DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Comune.modid);

    //entities

    public static RegistryObject<TileEntityType<mysticauldrontile>> MYSTIC_CAULDRON_TILE =
        tiles.register("mysticauldrontile", ()-> TileEntityType.Builder.create(
            mysticauldrontile::new , ModBlocks.MYSTIC_CAULDRON.get()).build(null));

    //end of entities

    public static void register(IEventBus eventBus) {
        tiles.register(eventBus);
    }
}
