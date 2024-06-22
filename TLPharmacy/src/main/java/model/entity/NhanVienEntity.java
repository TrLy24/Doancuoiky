package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "nhan_vien")
public class NhanVienEntity implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="ma_nhan_vien")
    private String maNhanVien;

    @Column(name="ten_nhan_vien")
    private String tenNhanVien;

    @Column(name="password")
    private String password;

    @Column(name="so_dien_thoai")
    private String soDienThoai;

    @Column(name="dia_chi")
    private String diaChi;

    @Column(name="ngay_sinh")
    private String ngaySinh;

    @Column(name="gioi_tinh")
    private String gioiTinh;

    @Column(name="role_id")
    private String roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public NhanVienEntity(int id, String maNhanVien, String tenNhanVien, String password, String soDienThoai, String diaChi, String ngaySinh, String gioiTinh, String roleId) {
        this.id = id;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.password = password;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.roleId = roleId;
    }

    public NhanVienEntity() {
        super();
    }
}
