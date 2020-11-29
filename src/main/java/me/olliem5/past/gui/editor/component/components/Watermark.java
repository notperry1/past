package me.olliem5.past.gui.editor.component.components;

import me.olliem5.past.Past;
import me.olliem5.past.gui.editor.component.HudComponent;
import me.olliem5.past.settings.Setting;
import me.olliem5.past.util.colour.ColourUtil;
import me.olliem5.past.util.text.StringUtil;

public class Watermark extends HudComponent {
    public Watermark() {
        super("Watermark");
        setWidth(boxWidth);
        setHeight(9);
    }

    Setting lowercase;
    Setting rainbow;
    Setting customfont;

    @Override
    public void setup() {
        Past.settingsManager.registerSetting(lowercase = new Setting("Lowercase", "WatermarkLowercase", false, this));
        Past.settingsManager.registerSetting(rainbow = new Setting("Rainbow", "WatermarkRainbow", true, this));
        Past.settingsManager.registerSetting(customfont = new Setting("Custom Font", "WatermarkCustomFont", true, this));
    }

    private static String renderText = Past.nameversion;

    private static int boxWidth = StringUtil.getStringWidth(renderText);

    public void render(float ticks) {
        if (!lowercase.getValBoolean()) {
            if (customfont.getValBoolean()) {
                Past.customFontRenderer.drawStringWithShadow(renderText, getX(), getY(), rainbow.getValBoolean() ? ColourUtil.getMultiColour().getRGB() : -1);
            } else {
                mc.fontRenderer.drawStringWithShadow(renderText, getX(), getY(), rainbow.getValBoolean() ? ColourUtil.getMultiColour().getRGB() : -1);
            }
        } else {
            if (customfont.getValBoolean()) {
                Past.customFontRenderer.drawStringWithShadow(renderText.toLowerCase(), getX(), getY(), rainbow.getValBoolean() ? ColourUtil.getMultiColour().getRGB() : -1);
            } else {
                mc.fontRenderer.drawStringWithShadow(renderText.toLowerCase(), getX(), getY(), rainbow.getValBoolean() ? ColourUtil.getMultiColour().getRGB() : -1);
            }
        }
    }
}
