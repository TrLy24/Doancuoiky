package model.response;

import java.sql.Date;

public class BanThuoc {
    private int id;
    private String maDonThuoc;
    private String maThuoc;
    private int soLuongBan;
    private String maNhanVien;
    private Double tongTien;
    private Date ngayBan;
    private model.response.NhanVien NhanVien;
    private model.response.Thuoc Thuoc;
    private Integer day;
    private Integer month;
    private Integer years;
    private String inHoaDon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaDonThuoc() {return maDonThuoc;}

    public void setMaDonThuoc(String maDonThuoc) {this.maDonThuoc = maDonThuoc;}

    public String getMaNhanVien() {return maNhanVien;}

    public void setMaNhanVien(String maNhanVien) {this.maNhanVien = maNhanVien;}

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {this.soLuongBan = soLuongBan;}

    public Double getTongTien() {return tongTien;}

    public void setTongTien(Double tongTien) {this.tongTien = tongTien;}

    public Date getNgayBan() {return ngayBan;}

    public void setNgayBan(Date ngayBan) {this.ngayBan = ngayBan;}

    public model.response.NhanVien getNhanVien() {return NhanVien;}

    public void setNhanVien(model.response.NhanVien nhanVien) {NhanVien = nhanVien;}

    public model.response.Thuoc getThuoc() {return Thuoc;}

    public void setThuoc(model.response.Thuoc thuoc) {Thuoc = thuoc;}

    public BanThuoc() {super();}

    public Integer getDay() {return day;}

    public void setDay(Integer day) {this.day = day;}

    public Integer getMonth() {return month;}

    public Integer getYears() {return years;}

    public void setMonth(Integer month) {this.month = month;}

    public void setYears(Integer years) {this.years = years;}

    public String getInHoaDon() {return inHoaDon;}

    public void setInHoaDon(String inHoaDon) {this.inHoaDon = inHoaDon;}

    public BanThuoc(int id, String maDonThuoc, String maThuoc, int soLuongBan, String maNhanVien, Double tongTien,
                    Date ngayBan, model.response.NhanVien nhanVien, model.response.Thuoc thuoc, String inHoaDon) {
        this.id = id;
        this.maDonThuoc = maDonThuoc;
        this.maThuoc = maThuoc;
        this.soLuongBan = soLuongBan;
        this.maNhanVien = maNhanVien;
        this.tongTien = tongTien;
        this.ngayBan = ngayBan;
        NhanVien = nhanVien;
        Thuoc = thuoc;
        this.inHoaDon = inHoaDon;
    }

    public BanThuoc(Double tongTien, Date ngayBan, Integer day, Integer month, Integer years) {
        this.tongTien = tongTien;
        this.ngayBan = ngayBan;
        this.day = day;
        this.month = month;
        this.years = years;
    }

    public BanThuoc(int id, String maDonThuoc) {
        this.id = id;
        this.maDonThuoc = maDonThuoc;
    }
}
