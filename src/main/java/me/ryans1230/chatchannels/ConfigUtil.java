package me.ryans1230.chatchannels;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigUtil {
    private ChatChannels plugin;
    ConfigUtil(ChatChannels plugin) {this.plugin = plugin;}
    public static Configuration c;
    public static ConfigurationProvider provider = YamlConfiguration.getProvider(YamlConfiguration.class);
    public static File conf;

    synchronized void createConfig() {
        File f = plugin.getDataFolder();
        conf = new File(f, "config.yml");
        try {
            plugin.getDataFolder().mkdir();
            conf.createNewFile();
            Configuration config = provider.load(conf);
            c = config;
            if(config.getSection("toggles").getKeys().isEmpty()) {
                config.set("toggles.allowConsole", false);
                config.set("toggles.sendDisabledMessages", false);
                config.set("toggles.logMessages", true);

                config.set("toggles.owner", true);
                config.set("toggles.developer", true);
                config.set("toggles.admin", true);
                config.set("toggles.moderator", true);
                config.set("toggles.network", true);

                config.set("toggles.noCommand", false);
            }
            if(config.getSection("prefix").getKeys().isEmpty()) {
                config.set("prefix.owner", "&r[&cOwner Chat&r] &c{author}&r: &c{message}");
                config.set("prefix.developer", "&r[&aDeveloper Chat&r] &a{author}&r: &a{message}");
                config.set("prefix.admin", "&r[&5Admin Chat&r] &5{author}&r: &5{message}");
                config.set("prefix.moderator", "&r[&6Moderator Chat&r] &6{author}&r: &6{message}");
                config.set("prefix.network", "&r[&7Network&r] &7{author}&r: &7{message}");
            }
            provider.save(config, conf);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}