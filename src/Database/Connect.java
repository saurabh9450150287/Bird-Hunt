/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;

/**
 *
 * @author Saurabh
 */
public class Connect 
{
   private static Connection conn=null;
    private static String exe=null;
    private static PreparedStatement pstmt=null,pstmt2=null;
    public  Connect() throws ClassNotFoundException 
    {
    try 
        { System.out.println("Entered constructors try");
            Class.forName("org.sqlite.JDBC");
            System.out.println("DONE1");
            conn = DriverManager.getConnection("jdbc:sqlite:src\\Database\\Scores.db");
            System.out.println("DONE2");
        }
        catch (SQLException e) 
        {
            System.out.println("Catch"+e);
        } 
    }
    public void putData(String time,int score) throws SQLException
    {
        exe="insert into HighScores(DateTime,Score) values(?,?)";
    try 
        {
            System.out.println("Entered try block");
            pstmt=conn.prepareStatement(exe);
       
            System.out.println("0");
            pstmt.setString(1,time);
            System.out.println("this1");
            //pstmt.setString(2,time);
            pstmt.setInt(2,score);
            System.out.println("2");
            pstmt.execute();
            System.out.println("INSERTED");
        } 
        catch (SQLException e) 
        {
            System.out.println("Exception in putting data"+e);
        }
  //  conn.close();
    }
    
    
    
    public void putData2(String time,int score) throws SQLException
    {
        exe="insert into HighScores2(Date,Score) values(?,?)";
    try 
        {
            System.out.println("Entered try block");
            pstmt=conn.prepareStatement(exe);
       
            System.out.println("0");
            pstmt.setString(1,time);
            System.out.println("1");
            //pstmt.setString(2,time);
            pstmt.setInt(2,score);
            System.out.println("2");
            pstmt.execute();
            System.out.println("INSERTED");
        } 
        catch (SQLException e) 
        {
            System.out.println("Exception in putting data2"+e);
        }
  //  conn.close();
    }
    public  ResultSet getHighScores() throws SQLException
    {
        ResultSet rs=null;
        String query="SELECT * FROM [HighScores] order by Score DESC;";
        try
        {
            pstmt2=conn.prepareStatement(query);
            System.out.println("1");
            rs=pstmt2.executeQuery();
            System.out.println("2");
        }
        catch(Exception e)
        {
            System.out.println("Error in getAllData in DbConnectivity class"+e);
        }
        //System.out.println("HERE"+rs.getString(1));
        //conn.close();
        return rs;
    }
    
    
    
    public  ResultSet getHighScores2() throws SQLException
    {
        ResultSet rs=null;
        String query="SELECT * FROM [HighScores2] order by Score DESC;";
        try
        {
            pstmt=conn.prepareStatement(query);
            System.out.println("1");
            rs=pstmt.executeQuery();
            System.out.println("2");
        }
        catch(Exception e)
        {
            System.out.println("Error in getAllData in DbConnectivity class2"+e);
        }
        //conn.close();
        return rs;
    }
    public void Close() throws SQLException
    {
        conn.close();
    }
}

