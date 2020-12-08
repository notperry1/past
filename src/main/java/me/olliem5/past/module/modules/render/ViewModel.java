package me.olliem5.past.module.modules.render;

import me.olliem5.past.Past;
import me.olliem5.past.module.Category;
import me.olliem5.past.module.Module;
import me.olliem5.past.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraftforge.client.event.EntityViewRenderEvent;

public class ViewModel extends Module {
    public ViewModel() {
        super("ViewModel", "Changes the way your player looks in first person", Category.RENDER);
    }

    Setting itemfov;
    Setting armpitch;
    Setting mainhand;
    Setting offhand;

    @Override
    public void setup() {
        Past.settingsManager.registerSetting(itemfov = new Setting("Item FOV", "ViewModelItemFOV", 110, 130, 170, this));
        Past.settingsManager.registerSetting(armpitch = new Setting("Arm Pitch", "ViewModelArmPitch", -360, 90, 360, this));
        Past.settingsManager.registerSetting(mainhand = new Setting("Main Hand", "ViewModelMainHand", 0.0, 1.0, 1.0, this));
        Past.settingsManager.registerSetting(offhand = new Setting("Off Hand", "ViewModelOffHand", 0.0, 1.0, 1.0, this));
    }

    public void onUpdate() {
        if (nullCheck()) return;

        mc.player.renderArmPitch = armpitch.getValueInt();
        mc.entityRenderer.itemRenderer.equippedProgressMainHand = (float) mainhand.getValueDouble();
        mc.entityRenderer.itemRenderer.equippedProgressOffHand = (float) offhand.getValueDouble();
    }

    @EventHandler
    public Listener<EntityViewRenderEvent.FOVModifier> listener = new Listener<>(event -> {
        event.setFOV(itemfov.getValueInt());
    });
}
