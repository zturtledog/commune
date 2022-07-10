package com.confusedparrotfish.commune.item;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.confusedparrotfish.commune.Comune;

public class ModItems {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, Comune.modid);

    public static final RegistryObject<Item> TESTITEM = items.register("name", 
        () -> new Item(new Item.Properties().group(ModItemGroup.communetab)));

    public static void register(IEventBus eventBus) {
        items.register(eventBus);
    }
}
