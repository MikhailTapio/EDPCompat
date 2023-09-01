package com.plr.edpcompat.network.handler;

import com.plr.edpcompat.EDParaglidersCompat;
import com.plr.edpcompat.network.msg.EDPDodgeMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1.0";
    private static int id = 0;

    public static int nextId() {
        return id++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(EDParaglidersCompat.MODID, "dodge"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        INSTANCE.messageBuilder(EDPDodgeMessage.class, nextId())
                .encoder(EDPDodgeMessage::toBytes)
                .decoder(EDPDodgeMessage::new)
                .consumerMainThread(EDPDodgeMessage::handler)
                .add();
    }
}
