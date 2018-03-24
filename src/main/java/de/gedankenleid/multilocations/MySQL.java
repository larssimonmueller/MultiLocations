package de.gedankenleid.multilocations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.bukkit.configuration.file.FileConfiguration;

public class MySQL {

    static FileConfiguration cfg = Main.cfg;
    public static String HOST = cfg.getString("MySQL.host");
    public static String DATABASE = cfg.getString("MySQL.database");
    public static String USER = cfg.getString("MySQL.username");
    public static String PASSWORD = cfg.getString("MySQL.password");
    public static int port = cfg.getInt("MySQL.port");
    public static Connection con;

    public static void connect()
    {
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + port + "/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
            System.out.println("[MySQL] Die Verbindung zur MySQL wurde hergestellt!");
        }
        catch (SQLException e)
        {
            System.out.println("[MySQL] Die Verbindung zur MySQL ist fehlgeschlagen! Fehler: " + e.getMessage());
        }
    }

    public static void close()
    {
        try
        {
            if (con != null)
            {
                con.close();
                System.out.println("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
            }
        }
        catch (SQLException e)
        {
            System.out.println("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
        }
    }

    public static void update(String qry)
    {
        try
        {
            Statement st = con.createStatement();
            st.executeUpdate(qry);
            st.close();
        }
        catch (SQLException e)
        {
            connect();
            System.err.println(e);
        }
    }

    public static ResultSet query(String qry)
    {
        ResultSet rs = null;
        try
        {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        }
        catch (SQLException e)
        {
            connect();
            System.err.println(e);
        }
        return rs;
    }

}
