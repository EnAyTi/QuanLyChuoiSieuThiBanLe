/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.oumarket.quanlychuoisieuthibanle;

import com.oumarket.pojo.HangHoa;
import com.oumarket.pojo.HoaDon;
import com.oumarket.pojo.NhanVien;
import com.oumarket.services.HoaDonServices;
import com.oumarket.services.NhanVienServices;
import com.oumarket.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anhtuan
 */
public class FXMLHoaDonController implements Initializable {
    public List<HangHoa> gioHang = new ArrayList<>();
    
    @FXML private ComboBox<NhanVien> cbNhanVien;
    @FXML private TextField txtTienKhachDua;
    @FXML private TextField txtMaKH;
    @FXML private Label lbTongTien;
    @FXML private Label lbTienThua;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        NhanVienServices nv = new NhanVienServices();
        try {
            this.cbNhanVien.setItems(FXCollections.observableList(nv.getNhanVien()));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.txtTienKhachDua.textProperty().addListener((evt) -> {
            this.lbTienThua.setText(String.valueOf(Float.parseFloat(this.txtTienKhachDua.getText()) - Float.parseFloat(this.lbTongTien.getText())));
        });
    } 
    
    public void setHoaDon(List<HangHoa> giohang){
        this.gioHang = giohang;
        
        Float tongTien = 0.0f;
        for (HangHoa s : gioHang) {
            tongTien += s.getDonGia() * s.getSelectedCount();
        }
        
        this.lbTongTien.setText(String.valueOf(tongTien));
    }
    
    public void traCuuKhachHangHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLTraCuuKhachHang.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tra cứu khách hàng");
        stage.show();
    }
    
    public void quayLai(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLOUMarket.fxml"));
        Parent ouMarketParent = loader.load();
        Scene scene = new Scene(ouMarketParent);
        stage.setScene(scene);
    }
    
    public void themHoaDonHandler(ActionEvent event) throws IOException {
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        if (cbNhanVien.getSelectionModel().isEmpty() || txtMaKH.getText().trim().equals(""))
            Utils.getBox("Xin hãy nhập đẩy đủ thông tin", Alert.AlertType.WARNING).show();
        else {
            HoaDon h = new HoaDon(UUID.randomUUID().toString(), this.cbNhanVien.getSelectionModel().getSelectedItem().getMaNV(), 
                            this.txtMaKH.getText(), date);
        
            HoaDonServices hd = new HoaDonServices();

            try {
                hd.themHoaDon(h, gioHang);
                Utils.getBox("Thêm hoá đơn thành công", Alert.AlertType.INFORMATION).show();

                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("FXMLOUMarket.fxml"));
                Parent ouMarketParent = loader.load();
                Scene scene = new Scene(ouMarketParent);
                stage.setScene(scene);
            } catch (SQLException ex) {
                Utils.getBox("Thêm hoá đơn không thành công", Alert.AlertType.WARNING).show();
            }
        }
    }
}
