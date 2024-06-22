package model.dao;

import Util.HibernateUtil;
import model.response.Thuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.*;

public class ThuocDao {
    private static EntityManager entityManager = HibernateUtil.getEntityManager();
    private static Session session = entityManager.unwrap(Session.class);
    private static SessionImplementor sessionImplementor = (SessionImplementor) session;
    private static  Connection connection = sessionImplementor.connection();
    private  static  EntityTransaction transaction = entityManager.getTransaction();

    // list thuoc
    public ObservableList<Thuoc> getListThuoc(String keyWord) throws SQLException {
        ObservableList<Thuoc> listData = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM thuoc ";
            if (!keyWord.isEmpty()) {
                query = query +" where (ma_thuoc like '%" + keyWord+"%'"
                        +" OR ten_thuoc like '%" + keyWord+"%'"
                        +" OR don_gia like '%" + keyWord+"%'"
                        +" OR so_luong like '%" + keyWord+"%'"
                        +" OR tong_gia_nhap like '%" + keyWord+"%'"
                        +" OR nha_cung_cap like '%" + keyWord+"%'"
                        +" OR don_vi_tinh like '%" + keyWord+"%')";
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet queryResult =  preparedStatement.executeQuery();

            while (queryResult.next()) {
                Thuoc item = new Thuoc();
                item.setId(queryResult.getInt("id"));
                item.setMaThuoc(queryResult.getString("ma_thuoc"));
                item.setTenThuoc(queryResult.getString("ten_thuoc"));
                item.setNgaySanXuat(queryResult.getDate("ngay_san_xuat"));
                item.setHanDung(queryResult.getDate("han_dung"));
                item.setDonGia(queryResult.getDouble("don_gia"));
                item.setSoLuong(queryResult.getInt("so_luong"));
                item.setTongGia(queryResult.getDouble("tong_gia_nhap"));
                item.setNhaCungCap(queryResult.getString("nha_cung_cap"));
                item.setDonViTinh(queryResult.getString("don_vi_tinh"));
                listData.add(item);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    // Get thuoc by id
    public Thuoc getByIdThuoc(int id) throws SQLException {
        Thuoc thuoc = new Thuoc();
        String query = "Select * From thuoc  Where id = " + id + "";

        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            thuoc.setId(queryResult.getInt("id"));
            thuoc.setMaThuoc(queryResult.getString("ma_thuoc"));
            thuoc.setTenThuoc(queryResult.getString("ten_thuoc"));
            thuoc.setNgaySanXuat(queryResult.getDate("ngay_san_xuat"));
            thuoc.setHanDung(queryResult.getDate("han_dung"));
            thuoc.setDonGia(queryResult.getDouble("don_gia"));
            thuoc.setSoLuong(queryResult.getInt("so_luong"));
            thuoc.setTongGia(queryResult.getDouble("tong_gia_nhap"));
            thuoc.setNhaCungCap(queryResult.getString("nha_cung_cap"));
            thuoc.setDonViTinh(queryResult.getString("don_vi_tinh"));
        }
        return thuoc;
    }

    // Get By MaThuoc
    public long getByMaThuoc(String maThuoc) throws SQLException {
        long count = 0;
        String query = "Select COUNT(*) AS total From thuoc Where ma_thuoc = '"+maThuoc+"'";
        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while(queryResult.next()){
            count = queryResult.getInt("total");
        }
        return count;
    }

    // Get thuoc By Ma Thuoc
    public Thuoc getThuocByMaThuoc(String maThuoc) throws SQLException {
        Thuoc thuoc = new Thuoc();
        String query = "Select * From thuoc Where ma_thuoc = '"+maThuoc+"'";
        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while(queryResult.next()){
            thuoc.setId(queryResult.getInt("id"));
            thuoc.setMaThuoc(queryResult.getString("ma_thuoc"));
            thuoc.setTenThuoc(queryResult.getString("ten_thuoc"));
            thuoc.setNgaySanXuat(queryResult.getDate("ngay_san_xuat"));
            thuoc.setHanDung(queryResult.getDate("han_dung"));
            thuoc.setDonGia(queryResult.getDouble("don_gia"));
            thuoc.setSoLuong(queryResult.getInt("so_luong"));
            thuoc.setTongGia(queryResult.getDouble("tong_gia_nhap"));
            thuoc.setNhaCungCap(queryResult.getString("nha_cung_cap"));
            thuoc.setDonViTinh(queryResult.getString("don_vi_tinh"));
        }
        return thuoc;
    }

    // create thuoc
    public void Create (Thuoc thuoc) throws SQLException {
        try {
            String query = "INSERT INTO thuoc (ma_thuoc, ten_thuoc, ngay_san_xuat, han_dung, don_gia, so_luong, tong_gia_nhap, nha_cung_cap, don_vi_tinh) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            transaction.begin();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, thuoc.getMaThuoc());
            preparedStatement.setString(2, thuoc.getTenThuoc());
            preparedStatement.setDate(3, thuoc.getNgaySanXuat());
            preparedStatement.setDate(4, thuoc.getHanDung());
            preparedStatement.setDouble(5, thuoc.getDonGia());
            preparedStatement.setInt(6, thuoc.getSoLuong());
            preparedStatement.setDouble(7, thuoc.getTongGia());
            preparedStatement.setString(8, thuoc.getNhaCungCap());
            preparedStatement.setString(9, thuoc.getDonViTinh());
            preparedStatement.executeUpdate();

            // Commit transaction
            transaction.commit();
        }catch (Exception e) {
            // Rollback transaction in case of an error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Update thuoc
    public void update (Thuoc thuoc) throws SQLException {
        try{
            String sql = "UPDATE thuoc set ma_thuoc = ?, ten_thuoc = ?, ngay_san_xuat = ?, han_dung =? "
                    + ", don_gia = ?, so_luong = ?, tong_gia_nhap = ?, nha_cung_cap = ?, don_vi_tinh = ? WHERE id = ?" ;

            transaction.begin();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, thuoc.getMaThuoc());
            preparedStatement.setString(2, thuoc.getTenThuoc());
            preparedStatement.setDate(3, thuoc.getNgaySanXuat());
            preparedStatement.setDate(4, thuoc.getHanDung());
            preparedStatement.setDouble(5, thuoc.getDonGia());
            preparedStatement.setInt(6, thuoc.getSoLuong());
            preparedStatement.setDouble(7, thuoc.getTongGia());
            preparedStatement.setString(8, thuoc.getNhaCungCap());
            preparedStatement.setString(9, thuoc.getDonViTinh());
            preparedStatement.setInt(10, thuoc.getId());
            preparedStatement.executeUpdate();

            // Commit transaction
            transaction.commit();
        } catch (Exception e){
            // Rollback transaction in case of an error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete thuoc
    public void delete(int id)  throws SQLException {
        try {
            String query = "DELETE FROM thuoc WHERE id = ?";
            transaction.begin();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            // Commit transaction
            transaction.commit();
        } catch (Exception e){
            // Rollback transaction in case of an error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
