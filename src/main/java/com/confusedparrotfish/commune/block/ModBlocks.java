package com.confusedparrotfish.commune.block;

import java.util.function.Supplier;

import com.confusedparrotfish.commune.Comune;
import com.confusedparrotfish.commune.block.custom.crystilizer;
import com.confusedparrotfish.commune.block.custom.mysticauldron;
import com.confusedparrotfish.commune.item.ModItemGroup;
import com.confusedparrotfish.commune.item.ModItems;
import com.confusedparrotfish.commune.lib.partikle.props;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
// import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, Comune.modid);

    // blocks

    public static final RegistryObject<Block> TESTBLOCK = registerblock("blockname",
            () -> new Block(props.stone_prop(3)));

    // voidstone
    public static final RegistryObject<Block> VOIDSTONE = registerblock("voidstone",
            () -> new Block(props.stone_prop(3)));

    public static final RegistryObject<Block> VOIDSTONE_POLISHED = registerblock("voidstonepolished",
            () -> new Block(props.stone_prop(3)));

    public static final RegistryObject<Block> VOIDSTONE_BRICKS = registerblock("voidstonebricks",
            () -> new Block(props.stone_prop(3)));

    public static final RegistryObject<Block> VOIDSTONE_BRICKS_SMALL = registerblock("voidstonebricksmall",
            () -> new Block(props.stone_prop(3)));

    public static final RegistryObject<Block> VOIDSTONE_PILLAR_CAP = registerblock("voidstonepillarcap",
            () -> new Block(props.stone_prop(3)));

    public static final RegistryObject<Block> VOIDSTONE_PILLAR = registerblock("voidstonepillar",
            () -> new Block(props.stone_prop(3)));

    public static final RegistryObject<Block> VOIDSTONE_CHISELED = registerblock("voidstonechiseled",
            () -> new Block(props.stone_prop(3)));

    // --stairs

    public static final RegistryObject<Block> VOIDSTONE_BRICK_STAIRS = registerblock("voidstonebrickstairs",
            () -> new StairsBlock(() -> VOIDSTONE_BRICKS.get().getDefaultState(), props.stone_prop(3))); // voidstonebrickstairsmall

    public static final RegistryObject<Block> VOIDSTONE_BRICK_STAIRS_SMALL = registerblock("voidstonebrickstairsmall",
            () -> new StairsBlock(() -> VOIDSTONE_BRICKS.get().getDefaultState(), props.stone_prop(3)));

    // --custom

    public static final RegistryObject<Block> MYSTIC_CAULDRON = registerblock("mysticauldron",
            () -> new mysticauldron(props.metal_prop(3).notSolid()));

    public static final RegistryObject<Block> CRYSTALIZER = registerblock("crystalizer",
            () -> new crystilizer(props.stone_prop(3).notSolid().tickRandomly()));

    // --glass

    public static final RegistryObject<Block> SMOOTH_GLASS = registerblock("smoothglass",
            () -> new GlassBlock(props.glass_prop(3)));

    // end of blocks

    public static <T extends Block> RegistryObject<T> registerblock(String name, Supplier<T> block) {
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

    // properties default

    public static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    public static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return (boolean) false;
    }
}
