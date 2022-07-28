package com.confusedparrotfish.commune.lib.effect;

import java.util.HashMap;
import java.util.Map;

public class EffectRegistry {
    Map<String, EffectRegistryObject> map = new HashMap<String, EffectRegistryObject>();

    public EffectRegistry register(String id, effect eff) {
        // EffectRegistryObject[] ell = (EffectRegistryObject[])map.values().toArray();

        // for (EffectRegistryObject effl : ell) {
        //     if (effl.idx == id) {
        //         effl.level++;
        //         return this;
        //     }
        // }

        map.put(id, new EffectRegistryObject(id, eff));

        return this;
    }

    public EffectRegistryObject raw(String id) {
        return map.get(id);
    }

    public effect get(String id) {
        return map.get(id).efect;
    }

    // class

    public class EffectRegistryObject {
        public String idx;
        // public String keyx;
        public effect efect;
        // public int level = 0;
    
        public EffectRegistryObject(String id, effect eff) {
            idx = id;
            efect = eff;
        }
    }
}