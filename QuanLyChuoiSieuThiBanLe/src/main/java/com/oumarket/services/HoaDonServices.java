/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.services;

import com.oumarket.pojo.HangHoa;
import com.oumarket.pojo.HoaDon;
import com.oumarket.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author anhtuan
 */
public class HoaDonServices {
    public void themHoaDon(HoaDon h, List<HangHoa> gioHang) throws SQLException {
        String q1 = "INSERT INTO hoadon(MaHoaDon, MaNV, MaKH, NgayHD) VALUES(?, ?, ?, ?)";
        String q2 = "INSERT INTO chitiethoadon(MaHoaDon, MaHang, SoLuong, DonGia, ThanhTien) VALUES(?, ?, ?, ?, ?)";
        
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            PreparedStatement stm1 = conn.prepareStatement(q1);
            stm1.setString(1, h.getMaHoaDon());
            stm1.setString(2, h.getMaNV());
            stm1.setString(3, h.getMaKH());
            stm1.setDate(4, h.getNgayHD());
            
            if (stm1.executeUpdate() > 0) {
                for(HangHoa hs: gioHang) {
                    PreparedStatement stm2 = conn.prepareStatement(q2);
                    stm2.setString(1, h.getMaHoaDon());
                    stm2.setString(2, hs.getMaHang());
                    stm2.setInt(3, hs.getSelectedCount());
                    stm2.setFloat(4, hs.getDonGia());
                    stm2.setFloat(5, hs.getDonGia() * hs.getSelectedCount());
                    
                    stm2.executeUpdate();
                }
            }
            conn.commit();
        }
    }
}
