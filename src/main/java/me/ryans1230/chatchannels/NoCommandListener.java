package me.ryans1230.chatchannels;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import static net.md_5.bungee.event.EventPriority.HIGHEST;

public class NoCommandListener implements Listener {
    private ChatChannels plugin;
    NoCommandListener(ChatChannels plugin) {this.plugin = plugin;}
    @EventHandler (priority = HIGHEST)
    public void onChat(ChatEvent e) {
        if (!plugin.settings.get(ChatChannels.Settings.NO_CMD)) { return; }
        if(e.isCommand()) { return; }
        ProxiedPlayer p = (ProxiedPlayer) e.getSender();
        if (p == null) { return; }
        if (!plugin.player_storage.containsKey(p.getUniqueId())) { return; }
        e.setCancelled(true);
        switch (plugin.player_storage.get(p.getUniqueId())) {
            case OWNER:
                plugin.getProxy().getPluginManager().dispatchCommand(p, "oc " + e.getMessage());
                break;
            case DEVELOPER:
                plugin.getProxy().getPluginManager().dispatchCommand(p, "dc " + e.getMessage());
                break;
            case ADMIN:
                plugin.getProxy().getPluginManager().dispatchCommand(p, "ac " + e.getMessage());
                break;
            case MODERATOR:
                plugin.getProxy().getPluginManager().dispatchCommand(p, "sc " + e.getMessage());
                break;
            case NETWORK:
                plugin.getProxy().getPluginManager().dispatchCommand(p, "nc " + e.getMessage());
                break;
        }
    }
}