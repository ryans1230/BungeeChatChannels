package me.ryans1230.chatchannels.commands;

import me.ryans1230.chatchannels.ChatChannels;
import me.ryans1230.chatchannels.ConfigUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

import java.io.IOException;
import java.util.Map;

import static me.ryans1230.chatchannels.ChatChannels.Channel.*;
import static me.ryans1230.chatchannels.ChatChannels.Settings.*;

public class SettingsCommand extends Command {
    private ChatChannels plugin;
    public SettingsCommand(ChatChannels plugin) {
        super("chatchannelsettings", "chatchannels.owner", "ccsettings");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            help(sender);
            sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&6Here are all the current settings:")));
            for(Map.Entry<Enum, Boolean> e1 : plugin.settings.entrySet()) {
                sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&6" + e1.getKey().toString() + " : " + e1.getValue())));
            }
        } else if(args.length == 1) {
            String setting = args[0];
            boolean value = false;
            boolean sent = true;
            switch (setting) {
                case "owner":
                    value = plugin.settings.get(OWNER);
                    break;
                case "developer":
                    value = plugin.settings.get(DEVELOPER);
                    break;
                case "admin":
                    value = plugin.settings.get(ADMIN);
                    break;
                case "moderator":
                    value = plugin.settings.get(MODERATOR);
                    break;
                case "network":
                    value = plugin.settings.get(NETWORK);
                    break;
                case "console":
                    value = plugin.settings.get(ALLOW_CONSOLE);
                    break;
                case "log":
                    value = plugin.settings.get(LOG_MESSAGES);
                    break;
                case "sendDisabled":
                    value = plugin.settings.get(SEND_DISABLED);
                    break;
                case "sendDisabledMessages":
                    value = plugin.settings.get(SEND_DISABLED);
                    break;
                case "noCommand":
                    value = plugin.settings.get(NO_CMD);
                default:
                    help(sender);
                    sent = false;
                    break;
            }
            if(sent) {
                sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&6" + setting + "'s current value is: " + value + "!")));
            }
        } else if(args.length == 2) {
            String setting = args[0].toLowerCase();
            String value = args[1];
            if(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("on") || value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("enabled")) {
                boolean sent = true;
                Configuration con = ConfigUtil.c;
                switch (setting) {
                    case "owner":
                        plugin.settings.put(OWNER, true);
                        con.set("toggles.owner", true);
                        break;
                    case "developer":
                        plugin.settings.put(DEVELOPER, true);
                        con.set("toggles.developer", true);
                        break;
                    case "admin":
                        plugin.settings.put(ADMIN, true);
                        con.set("toggles.admin", true);
                        break;
                    case "moderator":
                        plugin.settings.put(MODERATOR, true);
                        con.set("toggles.moderator", true);
                        break;
                    case "network":
                        plugin.settings.put(NETWORK, true);
                        con.set("toggles.network", true);
                        break;
                    case "console":
                        plugin.settings.put(ALLOW_CONSOLE, true);
                        con.set("toggles.allowConsle", true);
                        break;
                    case "log":
                        plugin.settings.put(LOG_MESSAGES, true);
                        con.set("toggles.logMessages", true);
                        break;
                    case "sendDisabled":
                        plugin.settings.put(SEND_DISABLED, true);
                        con.set("toggles.sendDisabledMessages", true);
                        break;
                    case "sendDisabledMessages":
                        plugin.settings.put(SEND_DISABLED, true);
                        con.set("toggles.sendDisabledMessages", true);
                        break;
                    case "noCommand":
                        plugin.settings.put(NO_CMD, true);
                        con.set("toggles.noCommand", true);
                        break;
                    default:
                        help(sender);
                        sent = false;
                        break;

                }
                if(sent) {
                    try {
                        ConfigUtil.provider.save(con, ConfigUtil.conf);
                        sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&6" + setting + " has been updated to true!")));
                    } catch (IOException e) {
                        sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&cAn internal error occurred!")));
                        e.printStackTrace();
                    }
                }
            } else if(value.equalsIgnoreCase("false") || value.equalsIgnoreCase("off") || value.equalsIgnoreCase("no") || value.equalsIgnoreCase("disabled")) {
                boolean sent = true;
                Configuration con = ConfigUtil.c;
                switch (setting) {
                    case "owner":
                        plugin.settings.put(OWNER, false);
                        con.set("toggles.owner", false);
                        break;
                    case "developer":
                        plugin.settings.put(DEVELOPER, false);
                        con.set("toggles.developer", false);
                        break;
                    case "admin":
                        plugin.settings.put(ADMIN, false);
                        con.set("toggles.admin", false);
                        break;
                    case "moderator":
                        plugin.settings.put(MODERATOR, false);
                        con.set("toggles.moderator", false);
                        break;
                    case "network":
                        plugin.settings.put(NETWORK, false);
                        con.set("toggles.network", false);
                        break;
                    case "console":
                        plugin.settings.put(ALLOW_CONSOLE, false);
                        con.set("toggles.allowConsole", false);
                        break;
                    case "log":
                        plugin.settings.put(LOG_MESSAGES, false);
                        con.set("toggles.logMessages", false);
                        break;
                    case "sendDisabled":
                        plugin.settings.put(SEND_DISABLED, false);
                        con.set("toggles.sendDisabledMessages", false);
                        break;
                    case "sendDisabledMessages":
                        plugin.settings.put(SEND_DISABLED, false);
                        con.set("toggles.sendDisabledMessages", false);
                        break;
                    case "noCommand":
                        plugin.settings.put(NO_CMD, false);
                        con.set("toggles.noCommand", false);
                        break;
                    default:
                        help(sender);
                        sent = false;
                        break;
                }
                if(sent) {
                    try {
                        ConfigUtil.provider.save(con, ConfigUtil.conf);
                        sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&6" + setting + " has been updated to false!")));
                    } catch (IOException e) {
                        sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&cAn internal error occurred!")));
                        e.printStackTrace();
                    }
                }
            } else {help(sender);}
        } else {help(sender);}
    }
    private void help(CommandSender sender) {
        sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes(
                '&', "&cUsage: /<command> <setting> true/false")));
        sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes(
                '&', "&cSettings: owner | developer | admin | moderator | network | console | log | sendDisabled")));
    }
}
