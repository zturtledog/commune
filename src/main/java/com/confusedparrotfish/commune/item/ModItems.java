package com.confusedparrotfish.commune.item;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.confusedparrotfish.commune.Comune;

public class ModItems {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, Comune.modid);

    public static final RegistryObject<Item> TESTITEM = items.register("name", 
        () -> new Item(prop()));

    public static final RegistryObject<Item> VOID_BOTTLE = items.register("voidbottle", 
        () -> new Item(prop().maxStackSize(1)));

    //--elements

    public static final RegistryObject<Item> LOSIL_FRAGMENT = items.register("losilfragment", 
        () -> new Item(prop()));
        
    public static final RegistryObject<Item> SILOS_FRAGMENT = items.register("silosfragment", 
        () -> new Item(prop()));
        
    public static final RegistryObject<Item> FALIS_FRAGMENT = items.register("falisfragment", 
        () -> new Item(prop()));

    public static final RegistryObject<Item> SOUR_INGREDIENT = items.register("souringredient", 
        () -> new Item(prop()));

    public static void register(IEventBus eventBus) {
        items.register(eventBus);
    }

    //properties

    private static Properties prop() {
        return new Item.Properties().group(ModItemGroup.communetab);
    }
}
