package model.response;

import java.sql.Date;

public class NhanVien {
    
    private Integer id;
    private String maNhanVien;
    private String tenNhanVien;
    private String gioiTinh;
    private Date ngaySinh;
    private String soDienThoai;
    private String diaChi;
    private String password;
    private Integer roleId;
    private String roleName;

    public Integer getId() {return id;}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaNhanVien() { return maNhanVien;}

    public void setMaNhanVien(String maNhanVien) { this.maNhanVien = maNhanVien;}

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {return ngaySinh;}

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {return soDienThoai;}

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() { return diaChi; }

    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getPassword() { return password;}

    public void setPassword(String password) { this.password = password;}

    public Integer getRoleId() {return roleId;}

    public void setRoleId(Integer roleId) {this.roleId = roleId;}

    public String getRoleName() { return roleName;}

    public void setRoleName(String roleName) {  this.roleName = roleName;}

    public NhanVien() {
        super();
    }
    public NhanVien(Integer id, String tenNhanVien, String gioiTinh, Date ngaySinh,
                    String soDienThoai, String diaChi, String password, String roleName, String maNhanVien, Integer roleId) {
        this.id = id;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.password = password;
        this.roleName = roleName;
        this.roleId = roleId;
    }

}
