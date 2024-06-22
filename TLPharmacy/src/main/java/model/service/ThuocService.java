package model.service;

import model.dao.ThuocDao;
import model.response.Thuoc;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ThuocService {
    private ThuocDao thuocDao = new ThuocDao();

    // list thuoc
    public ObservableList<Thuoc> getList(String keyWord) throws SQLException {
        return thuocDao.getListThuoc(keyWord);
    }

    // get thuoc by id
    public Thuoc getByIdThuoc(int id) throws SQLException {
        return thuocDao.getByIdThuoc(id);
    }

    // get thuoc by ma thuoc
    public boolean getByMaThuoc(String maThuoc) throws SQLException {
        boolean isCheck = false;
        long count = 0;
        count = thuocDao.getByMaThuoc(maThuoc);
        if (count == 0){
            isCheck = true;
        }
        return isCheck;
    }

    public Thuoc getThuocByMaThuoc(String maThuoc) throws SQLException {
        return thuocDao.getThuocByMaThuoc(maThuoc);
    }

    // create
    public void Create(Thuoc thuoc) throws SQLException {
        thuocDao.Create(thuoc);
    }

    // update
    public void Update(Thuoc thuoc) throws SQLException {
        thuocDao.update(thuoc);
    }

    // Delete
    public void Delete(int id) throws SQLException {
        thuocDao.delete(id);
    }

}
