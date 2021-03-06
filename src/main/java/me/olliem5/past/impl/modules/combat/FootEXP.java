package me.olliem5.past.impl.modules.combat;

import me.olliem5.past.Past;
import me.olliem5.past.api.module.ModuleInfo;
import me.olliem5.past.impl.events.PacketEvent;
import me.olliem5.past.api.module.Category;
import me.olliem5.past.api.module.Module;
import me.olliem5.past.api.setting.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;

@ModuleInfo(name = "FootEXP", description = "Makes you look down server side while using EXP bottles", category = Category.COMBAT)
public class FootEXP extends Module {

    Setting usecustompitch;
    Setting custompitch;

    @Override
    public void setup() {
        Past.settingsManager.registerSetting(usecustompitch = new Setting("Use Custom Pitch", "FootEXPUseCustomPitch", false, this));
        Past.settingsManager.registerSetting(custompitch = new Setting("Custom Pitch", "FootEXPCustomPitch", -90, 90, 90, this));
    }

    @EventHandler
    public Listener<PacketEvent.Send> listener = new Listener<>(event -> {
        if (event.getPacket() instanceof CPacketPlayerTryUseItem && mc.player.getHeldItemMainhand().getItem() instanceof ItemExpBottle) {
            if (usecustompitch.getValBoolean()) {
                mc.player.connection.sendPacket(new CPacketPlayer.Rotation(mc.player.rotationYaw, custompitch.getValueInt(), mc.player.onGround));
            } else {
                mc.player.connection.sendPacket(new CPacketPlayer.Rotation(mc.player.rotationYaw, 90.0f, mc.player.onGround));
            }
        }
    });
}
