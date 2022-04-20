/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.oumarket.quanlychuoisieuthibanle;

import com.oumarket.pojo.HangHoa;
import com.oumarket.services.HangHoaServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anhtuan
 */
public class FXMLOUMarketController implements Initializable {
    private static final HangHoaServices s = new HangHoaServices();
    
    @FXML private TableView<HangHoa> tbHangHoa;
    @FXML private TextField txtKeyword;
    
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
            this.tbHangHoa.setItems(FXCollections.observableList(s.getHangHoasById(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQuanLyHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTableView() {
        TableColumn colMaHang = new TableColumn("Mã hàng");
        colMaHang.setCellValueFactory(new PropertyValueFactory("maHang"));
        
        TableColumn colTenHang = new TableColumn("Tên hàng");
        colTenHang.setCellValueFactory(new PropertyValueFactory("tenHang"));
        
        TableColumn colNguonGoc = new TableColumn("Nguồn gốc");
        colNguonGoc.setCellValueFactory(new PropertyValueFactory("nguonGoc"));
        
        TableColumn colDonGia = new TableColumn("Đơn giá");
        colDonGia.setCellValueFactory(new PropertyValueFactory("donGia"));
        
        TableColumn colAdd = new TableColumn();
        colAdd.setCellFactory((p) -> {
            Button btn = new Button("Chọn");
            
            btn.setOnAction((evt) -> {
                TableCell c = (TableCell)((Button)evt.getSource()).getParent();
                HangHoa h = (HangHoa) c.getTableRow().getItem();
                
                
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        
        this.tbHangHoa.getColumns().addAll(colMaHang, colTenHang, colNguonGoc, colDonGia, colAdd);
    }
    
    public void traCuuKhachHangHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLTraCuuKhachHang.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tra cứu khách hàng");
        stage.show();
    }
    
    public void quanLyChiNhanhHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLQuanLyChiNhanh.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quản lý chi nhánh");
        stage.show();
    }
    
    public void quanLyNhanVienHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLQuanLyNhanVien.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quản lý nhân viên");
        stage.show();
    }
    
    public void quanLyHangHoaHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLQuanLyHangHoa.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quản lý hàng hoá");
        stage.show();
    }
}
