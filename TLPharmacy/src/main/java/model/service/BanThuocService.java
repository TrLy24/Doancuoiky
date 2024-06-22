package model.service;

import model.dao.BanThuocDao;
import model.response.BanThuoc;
import model.response.NhanVien;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class BanThuocService {
    private BanThuocDao banThuocDao = new BanThuocDao();

    public ObservableList<BanThuoc> getListBanThuoc(NhanVien nhanVien) throws SQLException {
       return banThuocDao.getListBanThuoc(nhanVien);
    }

    public ObservableList<BanThuoc> getListBanThuocDoanhThu(Integer month, Integer year) throws SQLException {
        return banThuocDao.getListBanThuocDoanhThu(month, year);
    }

    public void Create(BanThuoc banThuoc) throws SQLException {
        banThuocDao.Create(banThuoc);
    }

    public void Update(String maDonThuoc) throws SQLException {
        banThuocDao.Update(maDonThuoc);
    }
    public ObservableList<BanThuoc> getListHoaDon(String maDonThuoc) throws SQLException {
        return banThuocDao.getListHoaDon(maDonThuoc);
    }
    // delete
    public void Delete(int id) throws SQLException {
        banThuocDao.deleteBanThuoc(id);
    }
}
