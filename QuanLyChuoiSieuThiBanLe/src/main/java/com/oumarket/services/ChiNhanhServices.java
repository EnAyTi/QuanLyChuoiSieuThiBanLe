/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.services;

import com.oumarket.pojo.ChiNhanh;
import com.oumarket.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anhtuan
 */
public class ChiNhanhServices {
    public void themChiNhanh(ChiNhanh c) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO chinhanh(MaChiNhanh, SoNha, Duong, Phuong, Quan, ThanhPho) VALUES(?, ?, ?, ?, ?, ?)");
            stm.setString(1, c.getMaChiNhanh());
            stm.setString(2, c.getSoNha());
            stm.setString(3, c.getDuong());
            stm.setString(4, c.getPhuong());
            stm.setString(5, c.getQuan());
            stm.setString(6, c.getThanhPho());
            
            stm.executeUpdate();
        }
    }
    
    public boolean deleteChiNhanh(String maChiNhanh) throws SQLException {
       try (Connection conn = JdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("DELETE FROM chinhanh WHERE MaChiNhanh=?");
           stm.setString(1, maChiNhanh);
           
           return stm.executeUpdate() > 0;
       }
    }
    
    public void editChiNhanh(ChiNhanh c) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("UPDATE chinhanh SET "+"SoNha=?,"+"Duong=?,"+"Phuong=?,"+"Quan=?,"+"ThanhPho=? WHERE MaChiNhanh=?");
            stm.setString(1, c.getSoNha());
            stm.setString(2, c.getDuong());
            stm.setString(3, c.getPhuong());
            stm.setString(4, c.getQuan());
            stm.setString(5, c.getThanhPho());
            stm.setString(6, c.getMaChiNhanh());
            
            stm.executeUpdate();
        }
    }
    
    public List<ChiNhanh> getChiNhanhs(String kw) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM chinhanh WHERE ThanhPho like concat('%', '%', '%', '%', '%', ?)");
        
           if (kw == null)
               kw = "";
           stm.setString(1, kw);
           
           ResultSet rs = stm.executeQuery();
           
           List<ChiNhanh> chinhanhs = new ArrayList<>();
           
           while (rs.next()) {
               String maChiNhanh = rs.getString("maChiNhanh");
               String soNha = rs.getString("soNha");
               String duong = rs.getString("duong");
               String phuong = rs.getString("phuong");
               String quan = rs.getString("quan");
               String thanhPho = rs.getString("thanhPho");
               
               chinhanhs.add(new ChiNhanh(maChiNhanh, soNha, duong, phuong, quan, thanhPho));
           }
           return chinhanhs;
        }
    }
}
