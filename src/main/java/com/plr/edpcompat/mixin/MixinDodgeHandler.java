package com.plr.edpcompat.mixin;

import com.elenai.elenaidodge2.util.DodgeHandler;
import com.plr.edpcompat.network.handler.NetworkHandler;
import com.plr.edpcompat.network.msg.EDPDodgeMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tictim.paraglider.api.stamina.Stamina;

import java.util.Objects;

@Mixin(DodgeHandler.class)
public abstract class MixinDodgeHandler {
    @Redirect(method = "handleDodge", at = @At(value = "INVOKE", target = "Lcom/elenai/feathers/api/FeathersHelper;spendFeathers(I)Z", remap = false), remap = false)
    private static boolean redirect$handleDodge(int amount) {
        final Player player = Objects.requireNonNull(Minecraft.getInstance().player);
        final int cost = amount * 300;
        final Stamina stamina = Stamina.get(player);
        final boolean success = stamina.stamina() >= cost && stamina.takeStamina(cost, false, false) == cost;
        if (success) NetworkHandler.INSTANCE.sendToServer(new EDPDodgeMessage(cost));
        return success;
    }
}
