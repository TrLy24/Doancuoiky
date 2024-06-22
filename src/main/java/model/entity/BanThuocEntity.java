package model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "ban_thuoc")
public class BanThuocEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="ma_don_thuoc")
    private String maDonThuoc;

    @Column(name="ma_thuoc")
    private String maThuoc;

    @Column(name="so_luong_ban")
    private int soLuongBan;

    @Column(name="ma_nhan_vien")
    private String maNhanVien;

    @Column(name="tong_tien")
    private Double tongTien;

    @Column(name="ngay_ban_thuoc")
    private Date ngayBan;

    @Column(name="in_hoa_don")
    private String inHoaDon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaDonThuoc() {
        return maDonThuoc;
    }

    public void setMaDonThuoc(String maDonThuoc) {
        this.maDonThuoc = maDonThuoc;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public String getInHoaDon() {
        return inHoaDon;
    }

    public void setInHoaDon(String inHoaDon) {
        this.inHoaDon = inHoaDon;
    }

    public BanThuocEntity(int id, String maDonThuoc, String maThuoc, int soLuongBan, String maNhanVien, Double tongTien, Date ngayBan, String inHoaDon) {
        this.id = id;
        this.maDonThuoc = maDonThuoc;
        this.maThuoc = maThuoc;
        this.soLuongBan = soLuongBan;
        this.maNhanVien = maNhanVien;
        this.tongTien = tongTien;
        this.ngayBan = ngayBan;
        this.inHoaDon = inHoaDon;
    }

    public BanThuocEntity() {
        super();
    }
}
