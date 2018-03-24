package de.gedankenleid.multilocations;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    public static File dir = new File("plugins//MultiLocations");
    public static File file = new File("plugins//MultiLocations//config.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    public static Main instance;
    public static String prefix;
    public static String tp;
    public static String waiting;

    public void onEnable()
    {
        checkCFG();
        prefix = cfg.getString("Messages.prefix");
        waiting = cfg.getString("Messages.waiting");
        tp = cfg.getString("Messages.tp");
        instance = this;
        Bukkit.getConsoleSender().sendMessage("§aMultiLocations aktiviert!");
        getCommand("multilocations").setExecutor(new CommandClass());
        try
        {
            MySQL.connect();
            MySQL.update("CREATE TABLE IF NOT EXISTS Pos(UUID varchar(64), X double, Y double, Z double, Yaw float, Pitch float);");
            Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        }
        catch (Exception e)
        {
            Bukkit.getConsoleSender().sendMessage("§c§lFehler beim verbinden mit der MySQL Datenbank!");
            Bukkit.getConsoleSender().sendMessage("§7------------------------------------------------");
            Bukkit.getConsoleSender().sendMessage("§eBitte ueberpruefe deine MySQL Daten!");
            e.printStackTrace();
        }
    }

    public void onDisable()
    {
        try
        {

        }
        catch (Exception localException) {}
    }

    public static void checkCFG()
    {
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            try
            {
                file.createNewFile();
                cfg.set("MySQL.host", "localhost");
                cfg.set("MySQL.database", "localhost");
                cfg.set("MySQL.username", "root");
                cfg.set("MySQL.password", "abc123");
                cfg.set("MySQL.port", Integer.valueOf(3306));
                cfg.set("Messages.prefix", "&8| &3Positionen &8|");
                cfg.set("Messages.waiting", "&8Frage letzte Position ab...");
                cfg.set("Messages.tp", "&8Du wurdest zu deiner letzten Position teleportiert!");
                cfg.save(file);
            }
            catch (IOException e)
            {
                checkCFG();
            }
        }
    }

}
