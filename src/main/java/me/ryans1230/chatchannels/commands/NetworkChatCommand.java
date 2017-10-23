package me.ryans1230.chatchannels.commands;

import me.ryans1230.chatchannels.ChatChannels;
import me.ryans1230.chatchannels.ConfigUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static me.ryans1230.chatchannels.ChatChannels.Channel.NETWORK;
import static me.ryans1230.chatchannels.ChatChannels.Settings.ALLOW_CONSOLE;
import static me.ryans1230.chatchannels.ChatChannels.Settings.SEND_DISABLED;

public class NetworkChatCommand extends Command {
    private ChatChannels plugin;

    public NetworkChatCommand(ChatChannels plugin) {
        super("networkchat", "chatchannels.network", "netchat", "nc", "nchat");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (plugin.settings.get(NETWORK)) {
            String username;
            if (sender instanceof ProxiedPlayer) {
                username = ((ProxiedPlayer) sender).getDisplayName();
                if(args.length == 0) {
                    if(plugin.player_storage.containsKey(((ProxiedPlayer) sender).getUniqueId())) {
                        if(plugin.player_storage.get(((ProxiedPlayer) sender).getUniqueId()).equals(NETWORK)) {
                            plugin.player_storage.remove(((ProxiedPlayer) sender).getUniqueId());
                            sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&6You have toggled Network chat off. You're message will now be sent to the server chat.")));
                        }
                    }
                    sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&4You have toggled Network chat on. You're message will now be sent to the Admin chat.")));
                    plugin.player_storage.put(((ProxiedPlayer) sender).getUniqueId(), ChatChannels.Channel.NETWORK);
                }
            } else {
                if (plugin.settings.get(ALLOW_CONSOLE)) {
                    username = "CONSOLE";
                } else {
                    sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes(
                            '&', "&You must be a player to use this command!")));
                    return;
                }
            }
            StringBuilder builder = new StringBuilder();
            for (String arg : args) {
                builder.append(arg).append(" ");
            }
            plugin.logger(NETWORK, username, builder.toString().trim());
            for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                p.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes(
                        '&', ConfigUtil.c.getString("prefix.network").replace("{author}", username)
                                .replace("{message}", builder.toString()))));
            }
        } else {
            if (plugin.settings.get(SEND_DISABLED)) {
                sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes(
                        '&', "&4This chat channel is disabled!")));
            }
        }
    }
}