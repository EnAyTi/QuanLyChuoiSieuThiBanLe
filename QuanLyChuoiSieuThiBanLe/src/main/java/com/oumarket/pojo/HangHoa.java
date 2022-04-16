/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.pojo;

/**
 *
 * @author anhtuan
 */
public class HangHoa {
    private String maHang;
    private String tenHang;
    private int soLuong;
    private float donGia;
    private String nguonGoc;
    private String phanLoai;
    private String maGiamGia;

    public HangHoa() {
    }

    public HangHoa(String maHang, String tenHang, int soLuong, float donGia, String nguonGoc, String phanLoai, String maGiamGia) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.nguonGoc = nguonGoc;
        this.phanLoai = phanLoai;
        this.maGiamGia = maGiamGia;
    }
    
    

    /**
     * @return the maHang
     */
    public String getMaHang() {
        return maHang;
    }

    /**
     * @param maHang the maHang to set
     */
    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    /**
     * @return the tenHang
     */
    public String getTenHang() {
        return tenHang;
    }

    /**
     * @param tenHang the tenHang to set
     */
    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the donGia
     */
    public float getDonGia() {
        return donGia;
    }

    /**
     * @param donGia the donGia to set
     */
    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    /**
     * @return the nguonGoc
     */
    public String getNguonGoc() {
        return nguonGoc;
    }

    /**
     * @param nguonGoc the nguonGoc to set
     */
    public void setNguonGoc(String nguonGoc) {
        this.nguonGoc = nguonGoc;
    }

    /**
     * @return the phanLoai
     */
    public String getPhanLoai() {
        return phanLoai;
    }

    /**
     * @param phanLoai the phanLoai to set
     */
    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    /**
     * @return the maGiamGia
     */
    public String getMaGiamGia() {
        return maGiamGia;
    }

    /**
     * @param maGiamGia the maGiamGia to set
     */
    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }
}
