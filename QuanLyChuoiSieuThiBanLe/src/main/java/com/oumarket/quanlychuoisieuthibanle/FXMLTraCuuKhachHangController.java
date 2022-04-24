/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.oumarket.quanlychuoisieuthibanle;

import com.oumarket.pojo.KhachHang;
import com.oumarket.services.KhachHangServices;
import com.oumarket.utils.Utils;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author anhtuan
 */
public class FXMLTraCuuKhachHangController implements Initializable {
    private static final KhachHangServices k = new KhachHangServices();
    
    @FXML private TableView<KhachHang> tbKhachHang;
    @FXML private TextField txtKeyword; 
    @FXML private TextField txtTenKH;
    @FXML private TextField txtSdt;
    @FXML private DatePicker dpNamSinh;
    @FXML private TextField txtEmail;
    @FXML private TextArea txtDiaChi;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.loadTableView();
        this.loadTableData(null);
        
        this.txtKeyword.textProperty().addListener((evt) -> {
            this.loadTableData(this.txtKeyword.getText());
        });
    } 
    
    private void loadTableData(String kw) {
        try {
            this.tbKhachHang.setItems(FXCollections.observableList(k.getKhachHangBySdt(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQuanLyHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTableView() {
        this.tbKhachHang.setEditable(true);
        
        TableColumn colMaKH = new TableColumn("Mã khách hàng");
        colMaKH.setCellValueFactory(new PropertyValueFactory("maKH"));
        colMaKH.setCellFactory(TextFieldTableCell.forTableColumn());
        
        TableColumn colTenKH = new TableColumn("Tên khách hàng");
        colTenKH.setCellValueFactory(new PropertyValueFactory("tenKH"));
        
        TableColumn colSDT = new TableColumn("SĐT");
        colSDT.setCellValueFactory(new PropertyValueFactory("sdt"));
        
        TableColumn colNamSinh = new TableColumn("Năm sinh");
        colNamSinh.setCellValueFactory(new PropertyValueFactory("namSinh"));
        
        this.tbKhachHang.getColumns().addAll(colMaKH, colTenKH, colSDT, colNamSinh);
    }
    
    public void themKhachHangHandler(ActionEvent event) {
        KhachHang c = new KhachHang(UUID.randomUUID().toString(), this.txtTenKH.getText(), this.txtSdt.getText(), null , null, null);
        
        KhachHangServices cs = new KhachHangServices();
        try {
            cs.themKhachHang(c);
            Utils.getBox("Thêm khách hàng thành công", Alert.AlertType.INFORMATION).show();
            this.loadTableData(null);
            this.txtSdt.setText(null);
            this.txtTenKH.setText(null);
        } catch (SQLException ex) {
            Utils.getBox("Thêm khách hàng không thành công", Alert.AlertType.WARNING).show();
        }
    }
}
