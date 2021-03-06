package me.olliem5.past.api.util.client;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.olliem5.past.Past;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiWorldSelection;

public class DiscordUtil {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final DiscordRPC rpc = DiscordRPC.INSTANCE;
    public static DiscordRichPresence rp = new DiscordRichPresence();
    private static String details;
    private static String state;

    public static void startup() {
        Past.log("Discord RPC is starting up!");
        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        rpc.Discord_Initialize(Past.APP_ID, handlers, true, "");
        rp.startTimestamp = System.currentTimeMillis() / 1000L;
        rp.largeImageKey = "pastclient";
        rp.largeImageText = Past.NAME_VERSION;
        rpc.Discord_UpdatePresence(rp);

        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    details = "Main Menu";
                    state = "discord.gg/3DBphxC";

                    if (mc.isIntegratedServerRunning()) {
                        details = "Singleplayer | " + mc.getIntegratedServer().getWorldName();
                    } else if (mc.currentScreen instanceof GuiMultiplayer) {
                        details = "Multiplayer Menu";
                    } else if (mc.currentScreen instanceof GuiWorldSelection) {
                        details = "Singleplayer Menu";
                    } else if (mc.getCurrentServerData() != null) {
                        details = "Server | " + mc.getCurrentServerData().serverIP.toLowerCase();
                    }

                    rp.details = details;
                    rp.state = state;
                    rpc.Discord_UpdatePresence(rp);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }, "RPC-Callback-Handler").start();
    }

    public static void shutdown() {
        Past.log("Discord RPC is shutting down!");
        rpc.Discord_Shutdown();
    }
}
