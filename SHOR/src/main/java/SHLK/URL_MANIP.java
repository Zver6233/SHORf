package SHLK; /**
 * Created by Петро on 20.06.2016.
 */

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Петро on 18.06.2016.
 */
public class URL_MANIP {
    //set original data
    static final String DB_URL = "jdbc:mysql://localhost/links?useSSL=false";
    static  final String user="root";
    static final String pass="root";
    Connection connection=null;
    Statement stmt=null;
    public static Map<String,String> HereIsShortAndLongLinks = new HashMap<String,String>(); // store of links







    public void coppy()
    {
        String sql="SELECT  Long_url,Short_url from key_shorturl";
        try {
            connection = DriverManager.getConnection(DB_URL, user, pass);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String first = rs.getString("Long_url");
                String last = rs.getString("Short_url");
                HereIsShortAndLongLinks.put(rs.getString("Long_url"), rs.getString("Short_url"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public boolean ifExistLongURL(String Long_URL) // проверка длинной ссылки на наличия
    {
        return HereIsShortAndLongLinks.containsKey(Long_URL);

    }
    public boolean ifExistTinyURL(String shortURL) // проверка длинной ссылки на наличия
    {
        return HereIsShortAndLongLinks.containsValue(shortURL);

    }
    public boolean IfLinkIsOriginalPutIt(String Long_url,String Tiny_url)
    {
        if(HereIsShortAndLongLinks.containsValue(Tiny_url))
            return false;
        else {
            addLinks(Long_url, Tiny_url);
            return true;
        }
    }
    public void addLinks(String url,  String shorturl)
    {


        String sql="INSERT INTO key_shorturl "+"VALUE ('"+url+"','"+shorturl+"')";

        try {
            connection=DriverManager.getConnection(DB_URL, user, pass);
            stmt=connection.createStatement();
            stmt.executeUpdate(sql);
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  String getLinks(String shorturl)
    {
        String redirectURL="";

        String sql="SELECT  Long_url from key_shorturl where Short_url = '"+shorturl+"'";
        try {
            connection=DriverManager.getConnection(DB_URL, user, pass);
            stmt=connection.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next())
                redirectURL=rs.getString("Long_url");

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return redirectURL;

    }

}

