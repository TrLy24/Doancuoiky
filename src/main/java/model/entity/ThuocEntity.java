package model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "thuoc")
public class ThuocEntity implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="ma_thuoc")
    private String maThuoc;

    @Column(name="ten_thuoc")
    private String tenThuoc;

    @Column(name="ngay_san_xuat")
    private Date ngaySanXuat;

    @Column(name="han_dung")
    private Date hanDung;

    @Column(name="don_gia")
    private Double donGia;

    @Column(name="so_luong")
    private int soLuong;

    @Column(name="tong_gia_nhap")
    private double tongGia;

    @Column(name="nha_cung_cap")
    private String nhaCungCap;

    @Column(name="don_vi_tinh")
    private String donViTinh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(Date ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public Date getHanDung() {
        return hanDung;
    }

    public void setHanDung(Date hanDung) {
        this.hanDung = hanDung;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongGia() {
        return tongGia;
    }

    public void setTongGia(double tongGia) {
        this.tongGia = tongGia;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public ThuocEntity(int id, String maThuoc, String tenThuoc, Date ngaySanXuat, Date hanDung, Double donGia, int soLuong, double tongGia, String nhaCungCap, String donViTinh) {
        this.id = id;
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.ngaySanXuat = ngaySanXuat;
        this.hanDung = hanDung;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.tongGia = tongGia;
        this.nhaCungCap = nhaCungCap;
        this.donViTinh = donViTinh;
    }

    public ThuocEntity() {
        super();
    }
}
