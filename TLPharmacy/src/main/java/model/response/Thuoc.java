package model.response;

import java.sql.Date;

public class Thuoc {
    private int id;
    private String maThuoc;
    private String tenThuoc;
    private Date ngaySanXuat;
    private Date hanDung;
    private Double donGia;
    private int soLuong;
    private double tongGia;
    private String nhaCungCap;
    private String donViTinh;

    public int getId() {
        return id;
    }
    public int getSoLuong() {
        return soLuong;
    }
    public String getMaThuoc() {
        return maThuoc;
    }
    public String getTenThuoc() {
        return tenThuoc;
    }
    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }
    public Date getHanDung() {
        return hanDung;
    }
    public Double getDonGia() {
        return donGia;
    }
    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public String getDonViTinh() {
        return donViTinh;
    }
    public Double getTongGia() {
        return tongGia;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }
    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }
    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
    public void setHanDung(Date hanDung) {
        this.hanDung = hanDung;
    }
    public void setNgaySanXuat(Date ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }
    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public void setTongGia(double tongGia) {
        this.tongGia = tongGia;
    }
}
