/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.tester01;

import com.oumarket.pojo.HangHoa;
import com.oumarket.services.HangHoaServices;
import com.oumarket.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author anhtuan
 */
public class HangHoaTestSuite {
    private static Connection conn;
    private static HangHoaServices s;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(HangHoaTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        s = new HangHoaServices();
    }
    
    @AfterAll
    public static void afterAll() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HangHoaTestSuite.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void testSearchSuccessful() throws SQLException {
        String kw = "you";
        List<HangHoa> questions = s.getHangHoas(kw);
        
        for (HangHoa q: questions)
            Assertions.assertTrue(q.getTenHang().toLowerCase().contains(kw.toLowerCase()));
    }
    
    @Test
    public void testSearchInvalid() throws SQLException {
        String kw = "youuuuuuuuuuuuuuuuuuuuuuuuuuuu";
        List<HangHoa> questions = s.getHangHoas(kw);
        
        Assertions.assertEquals(questions.size(), 0);
    }
    
    @Test
    public void testSearchUnscure() throws SQLException {
        String kw = "1 OR 1=1";
        List<HangHoa> questions = s.getHangHoas(kw);
        
        Assertions.assertEquals(questions.size(), 0);
    }
    
    @Test
    public void deleteFail() throws SQLException {
        String id = "499999670eb24-3985-40f9-bab6-a86de52a5c34";
        Assertions.assertFalse(s.deleteHangHoa(id));
    }
    
    @Test
    public void deleteSuccess() throws SQLException {
        String id = "5042d658-80a0-4ee9-ae0d-dde895dab341";
        Assertions.assertTrue(s.deleteHangHoa(id));
        Assertions.assertNull(s.getHangHoaById(id));
    }
}
