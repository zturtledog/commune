package com.confusedparrotfish.commune.block;

import java.util.function.Supplier;

import com.confusedparrotfish.commune.Comune;
import com.confusedparrotfish.commune.item.ModItemGroup;
import com.confusedparrotfish.commune.item.ModItems;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, Comune.modid);

    public static final RegistryObject<Block> TESTBLOCK = registerblock("blockname", 
        ()-> new Block(AbstractBlock.Properties.create(Material.WOOL)
            .harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
            .hardnessAndResistance(5f)));

    public static <T extends Block>RegistryObject<T> registerblock(String name, Supplier<T> block) {
        RegistryObject<T> retval = blocks.register(name, block);

        registerblockitem(name, retval);

        return retval;
    }

    public static <T extends Block> void registerblockitem(String name, RegistryObject<T> block) {
        ModItems.items.register(name, () -> new BlockItem(block.get(),
            new Item.Properties().group(ModItemGroup.communetab)));
    }

    public static void register(IEventBus eventBus) {
        blocks.register(eventBus);
    }
}
