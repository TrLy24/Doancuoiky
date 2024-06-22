package model.service;

import model.response.NhanVien;
import model.dao.NhanVienDao;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class NhanVienSevice {

    private NhanVienDao nhanVienDao = new NhanVienDao();

    // Login
    public NhanVien login(String maNhanVien) throws Exception {
        return NhanVienDao.getNhanVienByName(maNhanVien);
    }

    // check sdt in chang pass
    public boolean checkSdt(String sdt) throws Exception {
        boolean isCheck = false;
        long count = 0;
        count = nhanVienDao.getNhanVienBySdt(sdt);
        if (count == 0){
            isCheck = true;
        }

        return isCheck;
    }
    // list nhan vien
    public ObservableList<NhanVien> getList(String keyWord) throws SQLException {
        return nhanVienDao.getListNhanVien(keyWord);
    }

    // get nhan vien by id
    public NhanVien getById(int id) throws SQLException {
        return nhanVienDao.getById(id);
    }

    // get nhan vien by ma nhan vien
    public boolean getByMaNhanVien(String maNhanVien) throws SQLException {
        boolean isCheck = false;
        long count = 0;
        count = nhanVienDao.getByMaNhanVien(maNhanVien);
        if (count == 0){
            isCheck = true;
        }
        return isCheck;
    }

    // create
    public void Create(NhanVien nhanVien) throws SQLException {
        nhanVienDao.Create(nhanVien);
    }

    // update
    public void Update(NhanVien nhanVien) throws SQLException {
        nhanVienDao.update(nhanVien);
    }

    // delete
    public void Delete(int id) throws SQLException {
        nhanVienDao.deleteNhanVien(id);
    }

    // change password
    public void ChangePass(String pass, String maNhanVien) throws SQLException {
        nhanVienDao.changePass(pass, maNhanVien);
    }
}
