package com.confusedparrotfish.commune.block;

import java.util.function.Supplier;

import com.confusedparrotfish.commune.Comune;
import com.confusedparrotfish.commune.block.custom.mysticauldron;
import com.confusedparrotfish.commune.item.ModItemGroup;
import com.confusedparrotfish.commune.item.ModItems;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, Comune.modid);

    //blocks 

    public static final RegistryObject<Block> TESTBLOCK = registerblock("blockname", ()-> new Block(stone_prop(3)));

    //voidstone
    public static final RegistryObject<Block> VOIDSTONE = registerblock("voidstone", ()-> new Block(stone_prop(3)));

    public static final RegistryObject<Block> VOIDSTONE_POLISHED = registerblock("voidstonepolished", ()-> new Block(stone_prop(3)));

    public static final RegistryObject<Block> VOIDSTONE_BRICKS = registerblock("voidstonebricks", 
        ()-> new Block(stone_prop(3)));

    public static final RegistryObject<Block> VOIDSTONE_BRICKS_SMALL = registerblock("voidstonebricksmall", 
        ()-> new Block(stone_prop(3)));

    public static final RegistryObject<Block> VOIDSTONE_PILLAR_CAP = registerblock("voidstonepillarcap", 
        ()-> new Block(stone_prop(3)));
    
    public static final RegistryObject<Block> VOIDSTONE_PILLAR = registerblock("voidstonepillar", 
        ()-> new Block(stone_prop(3)));

    public static final RegistryObject<Block> VOIDSTONE_CHISELED = registerblock("voidstonechiseled", 
        ()-> new Block(stone_prop(3)));

    //--stairs

    public static final RegistryObject<Block> VOIDSTONE_BRICK_STAIRS = registerblock("voidstonebrickstairs", 
        ()-> new StairsBlock(()-> VOIDSTONE_BRICKS.get().getDefaultState(), stone_prop(3)));   //voidstonebrickstairsmall 

    public static final RegistryObject<Block> VOIDSTONE_BRICK_STAIRS_SMALL = registerblock("voidstonebrickstairsmall", 
        ()-> new StairsBlock(()-> VOIDSTONE_BRICKS.get().getDefaultState(), stone_prop(3)));

    //--custom

    public static final RegistryObject<Block> MYSTIC_CAULDRON = registerblock("mysticauldron", 
        ()-> new mysticauldron(metal_prop(3).notSolid()));

    //end of blocks

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

    //properties default

    private static Properties stone_prop(int lvl) {
        return AbstractBlock.Properties.create(Material.ROCK)
            .harvestLevel(lvl).harvestTool(ToolType.PICKAXE)
            .setRequiresTool().hardnessAndResistance(lvl+2);
    }

    private static Properties wood_prop(int lvl) {
        return AbstractBlock.Properties.create(Material.WOOD)
            .harvestLevel(lvl).harvestTool(ToolType.AXE)
            .setRequiresTool().hardnessAndResistance(lvl+1);
    }

    private static Properties metal_prop(int lvl) {
        return AbstractBlock.Properties.create(Material.IRON)
            .harvestLevel(lvl).harvestTool(ToolType.PICKAXE)
            .setRequiresTool().hardnessAndResistance(lvl+1);
    }
}
