package de.gedankenleid.multilocations;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Methods
{
    public static void tpPlayer(Player p)
    {
        final Player a = p;
        Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.instance, new Runnable()
        {
            public void run()
            {
                a.getPlayer().teleport(Methods.getPos(a));
            }
        }, 10L);
    }

    public static boolean playerExists(String uuid)
    {
        try
        {
            ResultSet rs = MySQL.query("SELECT * FROM Pos WHERE UUID= '" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
            return false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(String uuid)
    {
        if (!playerExists(uuid)) {
            MySQL.update("INSERT INTO Pos(UUID, X, Y, Z, Yaw, Pitch) VALUES ('" + uuid + "', '165', '98', '-342', '0.0', '0.0');");
        }
    }

    public static Location getPos(Player p)
    {
        String uuid = p.getUniqueId().toString();
        Location loc = null;

        loc = new Location(p.getWorld(), getX(uuid).doubleValue(), getY(uuid).doubleValue(), getZ(uuid).doubleValue(), getYaw(uuid), getPitch(uuid));

        return loc;
    }

    public static void setPos(Player p)
    {
        if (playerExists(p.getUniqueId().toString()))
        {
            Double yd = Double.valueOf(p.getLocation().getY());
            Integer y = Integer.valueOf(yd.intValue() + 1);
            MySQL.update("UPDATE Pos SET X= '" + p.getLocation().getX() + "', Y= '" + y + "', Z='" + p.getLocation().getZ() + "', Yaw='" + p.getLocation().getYaw() + "', Pitch='" + p.getLocation().getPitch() + "' WHERE UUID= '" + p.getUniqueId().toString() + "';");
        }
        else
        {
            createPlayer(p.getUniqueId().toString());
            setPos(p);
        }
    }

    public static Double getX(String uuid)
    {
        Double i = Double.valueOf(0.0D);
        if (playerExists(uuid))
        {
            try
            {
                ResultSet rs = MySQL.query("SELECT * FROM Pos WHERE UUID= '" + uuid + "'");
                if ((rs.next()) && (Double.valueOf(rs.getDouble("X")) == null)) {}
                i = Double.valueOf(rs.getDouble("X"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            createPlayer(uuid);
            getX(uuid);
        }
        return i;
    }

    public static Double getY(String uuid)
    {
        Double i = Double.valueOf(0.0D);
        if (playerExists(uuid))
        {
            try
            {
                ResultSet rs = MySQL.query("SELECT * FROM Pos WHERE UUID= '" + uuid + "'");
                if ((rs.next()) && (Double.valueOf(rs.getDouble("Y")) == null)) {}
                i = Double.valueOf(rs.getDouble("Y"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            createPlayer(uuid);
            getY(uuid);
        }
        return i;
    }

    public static Double getZ(String uuid)
    {
        Double i = Double.valueOf(0.0D);
        if (playerExists(uuid))
        {
            try
            {
                ResultSet rs = MySQL.query("SELECT * FROM Pos WHERE UUID= '" + uuid + "'");
                if ((rs.next()) && (Double.valueOf(rs.getDouble("Z")) == null)) {}
                i = Double.valueOf(rs.getDouble("Z"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            createPlayer(uuid);
            getZ(uuid);
        }
        return i;
    }

    public static float getYaw(String uuid)
    {
        float i = 0.0F;
        if (playerExists(uuid))
        {
            try
            {
                ResultSet rs = MySQL.query("SELECT * FROM Pos WHERE UUID= '" + uuid + "'");
                if ((rs.next()) && (Float.valueOf(rs.getFloat("Yaw")) == null)) {}
                i = rs.getInt("Yaw");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            createPlayer(uuid);
            getYaw(uuid);
        }
        return i;
    }

    public static float getPitch(String uuid)
    {
        float i = 0.0F;
        if (playerExists(uuid))
        {
            try
            {
                ResultSet rs = MySQL.query("SELECT * FROM Pos WHERE UUID= '" + uuid + "'");
                if ((rs.next()) && (Float.valueOf(rs.getFloat("Pitch")) == null)) {}
                i = rs.getInt("Pitch");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            createPlayer(uuid);
            getPitch(uuid);
        }
        return i;
    }
}