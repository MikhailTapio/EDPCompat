package com.plr.edpcompat.network.msg;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import tictim.paraglider.api.stamina.Stamina;

import java.util.function.Supplier;

public class EDPDodgeMessage {
    private final int cost;

    public EDPDodgeMessage(FriendlyByteBuf buffer) {
        cost = buffer.readInt();
    }

    public EDPDodgeMessage(int cost) {
        this.cost = cost;
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeInt(cost);
    }

    public void handler(Supplier<NetworkEvent.Context> sup) {
        final NetworkEvent.Context ctx = sup.get();
        ctx.enqueueWork(() -> {
            final ServerPlayer player = ctx.getSender();
            if (player == null) return;
            Stamina.get(player).takeStamina(cost, false, false);
        });
        ctx.setPacketHandled(true);
    }
}
