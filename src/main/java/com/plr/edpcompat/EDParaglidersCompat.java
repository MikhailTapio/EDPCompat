package com.plr.edpcompat;

import com.plr.edpcompat.network.handler.NetworkHandler;
import net.minecraftforge.fml.common.Mod;

@Mod(EDParaglidersCompat.MODID)
public class EDParaglidersCompat {
    public static final String MODID = "edpcompat";

    public EDParaglidersCompat() {
        NetworkHandler.registerMessage();
    }
}
