/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author HP
 */
public class Account {
    private int id;
    private String username;
    private int userid;
    private int accountnumber;
    private String currency;
    private double balance;

    public Account(String username, int userid, int accountnumber, String currency, double balance) {
        this.username = username;
        this.userid = userid;
        this.accountnumber = accountnumber;
        this.currency = currency;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(int accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
     public int save() throws SQLException, ClassNotFoundException{
        Connection g = DB.getInstance().getConnection();
        PreparedStatement p = null;
        int recordCounter =0;
          String sql = "INSERT INTO ACCOUNTS (ID,USERID,ACCOUNTNUMBER,USERNAME,CURRENCY,BALANCE) VALUES (?, ?, ?, ?, ?, ?)";
        p = g.prepareStatement(sql);
        p.setInt(1, this.getId());
        p.setInt(2,this.getUserid());
        p.setInt(3, this.getAccountnumber());
        p.setString(4,this.getUsername());
        p.setString(5, this.getCurrency());
        p.setDouble(6, this.getBalance());
        recordCounter = p.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUsername()
                    +" ACCOUNT was added successfully!");
        }
        if (p != null){
            p.close();
        }
        g.close();
        return recordCounter;  
    }
    
       public static ArrayList<Account> getAllACCOUNTS() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> users = new ArrayList<>();
        String sql = "SELECT * FROM ACCOUNTS ";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()){
            Account account = new Account(rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getDouble(6));
            account.setId(rs.getInt(1));
            users.add(account);
            
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return users;
    }
      public int update() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement p = null;
        int recordCounter =0;
        String sql = "UPDATE ACCOUNTS SET USERID=?, ACCOUNTNUMBER=?, USERNAME=?, CURRENCY=?,BALANCE=? WHERE ID=?";
        p = c.prepareStatement(sql);     
        p.setInt(1, this.getUserid());
        p.setInt(2, this.getAccountnumber());
        p.setString(3,this.getUsername());
        p.setString(4, this.getCurrency());
        p.setDouble(5, this.getBalance());
        p.setInt(6, this.getId());
        recordCounter = p.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Account with id : "+this.getId()+" was updated successfully!");
        }
        if (p != null){
            p.close();
        }
        c.close();
        return recordCounter;  
    }
    
    //delete an user from users table 
    public int delete() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "DELETE FROM ACCOUNTS WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The user with id : "+this.getId()+" was deleted successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;  
    }
}
