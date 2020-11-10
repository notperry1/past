package me.olliem5.past.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.TextComponentString;

public class MessageUtil {
    private static final EntityPlayerSP player = Minecraft.getMinecraft().player;

    public static String prefix = ColourUtil.gray + "[" + ColourUtil.red + "Past Client"  + ColourUtil.gray + "]";
    public static String entityalertPrefix = ColourUtil.gray + "[" + ColourUtil.darkRed + "EntityAlert" + ColourUtil.gray + "]";
    public static String weaknessAlertPrefix = ColourUtil.gray + "[" + ColourUtil.darkRed + "WeaknessDetect" + ColourUtil.gray + "]";
    public static String bedAuraPrefix = ColourUtil.gray + "[" + ColourUtil.darkRed + "BedAura" + ColourUtil.gray + "]";
    public static String surroundPrefix = ColourUtil.gray + "[" + ColourUtil.darkRed + "Surround" + ColourUtil.gray + "]";
    public static String autoBuilderPrefix = ColourUtil.gray + "[" + ColourUtil.darkRed + "AutoBuilder" + ColourUtil.gray + "]";

    public static void sendRawMessage(String message) { player.sendMessage(new TextComponentString(message)); }
    public static void sendMessagePrefix(String message) { sendRawMessage(prefix + " " + message); }
    public static void sendEntityAlertMessage(String message) { sendMessagePrefix(entityalertPrefix + " " + message); }
    public static void sendWeaknessAlertMessage(String message) { sendMessagePrefix(weaknessAlertPrefix + " " + message); }
    public static void sendBedAuraMessage(String message) { sendMessagePrefix(bedAuraPrefix + " " + message); }
    public static void sendSurroundMessage(String message) { sendMessagePrefix(surroundPrefix + " " + message); }
    public static void sendAutoBuilderMessage(String message) { sendMessagePrefix(autoBuilderPrefix + " " + message); }
}