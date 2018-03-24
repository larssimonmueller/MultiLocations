package de.gedankenleid.multilocations;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners
        implements org.bukkit.event.Listener
{
    @EventHandler
    public void onJoin(final PlayerJoinEvent e)
    {
        if (Methods.playerExists(e.getPlayer().getUniqueId().toString()))
        {
            final String prefix = ChatColor.translateAlternateColorCodes('&', Main.prefix);
            String waiting = ChatColor.translateAlternateColorCodes('&', Main.waiting);
            if(!Main.waiting.equalsIgnoreCase("")){
                e.getPlayer().sendMessage(prefix + " " + waiting);
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
            {
                public void run()
                {
                    Methods.tpPlayer(e.getPlayer());
                    String tp = ChatColor.translateAlternateColorCodes('&', Main.tp);
                    if(!Main.tp.equalsIgnoreCase("")){
                        e.getPlayer().sendMessage(prefix + " " + tp);
                    }
                }
            }, 20L);
        }
        else
        {
            Methods.createPlayer(e.getPlayer().getUniqueId().toString());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        Methods.setPos(e.getPlayer());
    }
}
