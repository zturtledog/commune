package com.confusedparrotfish.commune.lib.effect;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
// import net.minecraft.entity.player.PlayerEntity;
// import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
// import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class effect {
    //overrides

    /**
    * item only, called as the item is being used by an entity.
    */
    public void use(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count, int level) {}

    /**
     * block only, executes when entities walk on the block
     * 
     * @param worldIn
     * @param pos
     * @param entityIn
     */
    public void entitywalk(World worldIn, BlockPos pos, Entity entityIn, int level) {}

    /**
     * item only, called when a item is hit
     * 
     * @param stack
     * @param target
     * @param attacker
     */
    public void hitentity(ItemStack stack, LivingEntity target, LivingEntity attacker, int level) {}

    // public void rightclick(World worldIn, PlayerEntity playerIn, Hand handIn, int level) {}

    /**
     * item only, called every tick that the item is in the inventory
     * 
     * @param stack
     * @param worldIn
     * @param entityIn
     * @param itemSlot
     * @param isSelected
     */
    public void inventorytick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected, int level) {}

    /**
     * item only, called when a block is destroyed with the item
     * 
     * @param stack
     * @param worldIn
     * @param state
     * @param pos
     * @param entityLiving
     * @param level
     */
    public void blockdestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving, int level) {}

    //--properties
    
    /**
     * types include:
     *  none- works as an anything that isn't other types,
     *  block- can be aplied in voidic expungeres,
     *  item- can be aplied to effected items,
     *  any- can be used by any item,
     * 
     * @return
     */
    public String type() {
        return "none";
    }

    // public IEffectProps properties = props();
    
    // public IEffectProps props() {
    //     return new IEffectProps().recipie(new IRecipieWrapper() {
    //         //Autofill
    //     });
    // }
}
