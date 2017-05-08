package me.ryans1230.chatchannels;

import me.ryans1230.chatchannels.commands.*;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static me.ryans1230.chatchannels.ChatChannels.Channel.*;
import static me.ryans1230.chatchannels.ChatChannels.Settings.*;

public final class ChatChannels extends Plugin {
    public Map<Enum, Boolean> settings = new HashMap<>();

    @Override
    public void onEnable() {
        ConfigUtil configUtil = new ConfigUtil(this);
        getLogger().info("Loading Chat Channels v" + getDescription().getVersion() + ". . . .");
            //Create config file if not exist
        configUtil.createConfig();
            //Store the settings to be toggled later
        settings.put(ALLOW_CONSOLE, ConfigUtil.c.getBoolean("toggles.allowConsole"));
        settings.put(SEND_DISABLED, ConfigUtil.c.getBoolean("toggles.sendDisabledMessages"));
        settings.put(LOG_MESSAGES, ConfigUtil.c.getBoolean("toggles.logMessages"));
        settings.put(OWNER, ConfigUtil.c.getBoolean("toggles.owner"));
        settings.put(DEVELOPER, ConfigUtil.c.getBoolean("toggles.developer"));
        settings.put(ADMIN, ConfigUtil.c.getBoolean("toggles.admin"));
        settings.put(MODERATOR, ConfigUtil.c.getBoolean("toggles.moderator"));
        settings.put(NETWORK, ConfigUtil.c.getBoolean("toggles.network"));

            //Register commands
        getProxy().getPluginManager().registerCommand(this, new OwnerChatCommand(this));
        getProxy().getPluginManager().registerCommand(this, new DeveloperChatCommand(this));
        getProxy().getPluginManager().registerCommand(this, new AdminChatCommand(this));
        getProxy().getPluginManager().registerCommand(this, new ModeratorChatCommand(this));
        getProxy().getPluginManager().registerCommand(this, new NetworkChatCommand(this));
        getProxy().getPluginManager().registerCommand(this, new SettingsCommand(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling Chat Channels. . . .");
            //Save the settings!
        try {
            Configuration config = ConfigUtil.provider.load(ConfigUtil.conf);
            config.set("toggles.allowConsole", settings.get(ALLOW_CONSOLE));
            config.set("toggles.sendDisabledMessages", settings.get(SEND_DISABLED));
            config.set("toggles.log_Messages", settings.get(LOG_MESSAGES));
            config.set("toggles.owner", settings.get(OWNER));
            config.set("toggles.developer", settings.get(DEVELOPER));
            config.set("toggles.admin", settings.get(ADMIN));
            config.set("toggles.moderator", settings.get(MODERATOR));
            config.set("toggles.network", settings.get(NETWORK));
            ConfigUtil.provider.save(config, ConfigUtil.conf);
        } catch (IOException e) {
            getLogger().info("Error saving the configuration!");
            e.printStackTrace();
        }
    }
    public enum Channel {OWNER,DEVELOPER,ADMIN,MODERATOR,NETWORK}
    public enum Settings {ALLOW_CONSOLE, SEND_DISABLED, LOG_MESSAGES}
    public void logger(Channel channel, String author, String message) {
        Date now = new Date();
        SimpleDateFormat fileDate = new SimpleDateFormat("MM-dd-yyyy");
        File saveTo = null;
        File dataFolder;
        try {
            switch (channel) {
                case OWNER:
                    dataFolder = new File(this.getDataFolder(), "logs/owner/");
                    if(!dataFolder.exists()) {dataFolder.mkdirs();}
                    if(!(saveTo = new File(dataFolder, String.valueOf(fileDate.format(now)) + ".log")).exists()) {
                        saveTo.createNewFile();
                    }
                    break;
                case DEVELOPER:
                    dataFolder = new File(this.getDataFolder(), "logs/developer/");
                    if(!dataFolder.exists()) {dataFolder.mkdirs();}
                    if(!(saveTo = new File(dataFolder, String.valueOf(fileDate.format(now)) + ".log")).exists()) {
                        saveTo.createNewFile();
                    }
                    break;
                case ADMIN:
                    dataFolder = new File(this.getDataFolder(), "logs/admin/");
                    if(!dataFolder.exists()) {dataFolder.mkdirs();}
                    if(!(saveTo = new File(dataFolder, String.valueOf(fileDate.format(now)) + ".log")).exists()) {
                        saveTo.createNewFile();
                    }
                    break;
                case MODERATOR:
                    dataFolder = new File(this.getDataFolder(), "logs/moderator/");
                    if(!dataFolder.exists()) {dataFolder.mkdirs();}
                    if(!(saveTo = new File(dataFolder, String.valueOf(fileDate.format(now)) + ".log")).exists()) {
                        saveTo.createNewFile();
                    }
                    break;
                case NETWORK:
                    dataFolder = new File(this.getDataFolder(), "logs/network/");
                    if(!dataFolder.exists()) {dataFolder.mkdirs();}
                    if(!(saveTo = new File(dataFolder, String.valueOf(fileDate.format(now)) + ".log")).exists()) {
                        saveTo.createNewFile();
                    }
                    break;
            }
            FileWriter fw = new FileWriter(saveTo, true);
            PrintWriter pw = new PrintWriter(fw);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
            pw.println("[" + dateFormat.format(now) + "] " + author + ": " + message);
            pw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
