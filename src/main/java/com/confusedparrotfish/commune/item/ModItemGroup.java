package com.confusedparrotfish.commune.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup communetab = new ItemGroup("Commune") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.TESTITEM.get());
        }
    };
}
