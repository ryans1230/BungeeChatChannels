package me.ryans1230.chatchannels.commands;

import me.ryans1230.chatchannels.ChatChannels;
import me.ryans1230.chatchannels.ConfigUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static me.ryans1230.chatchannels.ChatChannels.Channel.ADMIN;
import static me.ryans1230.chatchannels.ChatChannels.Settings.ALLOW_CONSOLE;
import static me.ryans1230.chatchannels.ChatChannels.Settings.SEND_DISABLED;

public class AdminChatCommand extends Command {
    private ChatChannels plugin;
    public AdminChatCommand(ChatChannels plugin) {
        super("adminchat", "chatchannels.admin", "ac", "adminc");
        this.plugin = plugin;
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(plugin.settings.get(ADMIN)) {
            String username;
            if(sender instanceof ProxiedPlayer) {
                username = ((ProxiedPlayer) sender).getDisplayName();
                if(args.length == 0) {
                    plugin.player_storage.put(((ProxiedPlayer) sender).getUniqueId(), ChatChannels.Channel.ADMIN);
                }
            } else {
                if(plugin.settings.get(ALLOW_CONSOLE)) {
                    username = "CONSOLE";
                } else {
                    sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes(
                            '&', "&You must be a player to use this command!")));
                    return;
                }
            }
            StringBuilder builder = new StringBuilder();
            for(String arg : args) {
                builder.append(arg).append(" ");
            }
            plugin.logger(ADMIN, username, builder.toString().trim());
            for(ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                if(p.hasPermission("chatchannels.admin")) {
                    p.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes(
                            '&', ConfigUtil.c.getString("prefix.admin").replace("{author}", username)
                                    .replace("{message}", builder.toString()))));
                }
            }
        } else {
            if(plugin.settings.get(SEND_DISABLED)) {
                sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes(
                        '&', "&4This chat channel is disabled!")));
            }
        }
    }
}