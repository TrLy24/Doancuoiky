package model.dao;

import Util.HibernateUtil;
import model.response.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.*;

public class NhanVienDao {
    private static EntityManager entityManager = HibernateUtil.getEntityManager();
    private static Session session = entityManager.unwrap(Session.class);
    private static SessionImplementor sessionImplementor = (SessionImplementor) session;
    private static Connection connection = sessionImplementor.connection();
    private static EntityTransaction transaction = entityManager.getTransaction();;

    // Get List nhan vien
    public ObservableList<NhanVien> getListNhanVien(String keyWord) throws SQLException {
        ObservableList<NhanVien> listData = FXCollections.observableArrayList();

        String query = "Select *, n.role_id AS roleId From nhan_vien n INNER JOIN role r ON n.role_id = r.role_id ";
        if (!keyWord.isEmpty()) {
            query = query + " WHERE (ma_nhan_vien like '%" + keyWord + "%'"
                    + " OR ten_nhan_vien like '%" + keyWord + "%')";
        }
        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            NhanVien nhanVien = new NhanVien();

            nhanVien.setId(queryResult.getInt("id"));
            nhanVien.setMaNhanVien(queryResult.getString("ma_nhan_vien"));
            nhanVien.setTenNhanVien(queryResult.getString("ten_nhan_vien"));
            nhanVien.setPassword(queryResult.getString("password"));
            nhanVien.setDiaChi(queryResult.getString("dia_chi"));
            nhanVien.setSoDienThoai(queryResult.getString("so_dien_thoai"));
            nhanVien.setGioiTinh(queryResult.getString("gioi_tinh"));
            nhanVien.setNgaySinh(queryResult.getDate("ngay_sinh"));
            nhanVien.setRoleName(queryResult.getString("name"));
            nhanVien.setRoleId(queryResult.getInt("roleId"));

            listData.add(nhanVien);

        }
        return listData;
    }

    // Get nhan vien by id
    public NhanVien getById(int id) throws SQLException {
        NhanVien nhanVien = new NhanVien();
        String query = "Select *, n.role_id AS roleId From nhan_vien n INNER JOIN role r ON n.role_id = r.role_id Where n.id = " + id + " ";

        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            nhanVien.setId(queryResult.getInt("id"));
            nhanVien.setMaNhanVien(queryResult.getString("ma_nhan_vien"));
            nhanVien.setTenNhanVien(queryResult.getString("ten_nhan_vien"));
            nhanVien.setPassword(queryResult.getString("password"));
            nhanVien.setDiaChi(queryResult.getString("dia_chi"));
            nhanVien.setSoDienThoai(queryResult.getString("so_dien_thoai"));
            nhanVien.setGioiTinh(queryResult.getString("gioi_tinh"));
            nhanVien.setNgaySinh(queryResult.getDate("ngay_sinh"));
            nhanVien.setRoleName(queryResult.getString("name"));
            nhanVien.setRoleId(queryResult.getInt("roleId"));

        }
        return nhanVien;
    }

    // Get nhan vien by name
    public static NhanVien getNhanVienByName(String maNhanVien) throws SQLException {
        NhanVien nhanVien = new NhanVien();
        String query = "Select *, n.role_id AS roleId From nhan_vien n INNER JOIN role r ON n.role_id = r.role_id Where ma_nhan_vien = '" + maNhanVien + "'";
        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            nhanVien.setId(queryResult.getInt("id"));
            nhanVien.setMaNhanVien(queryResult.getString("ma_nhan_vien"));
            nhanVien.setTenNhanVien(queryResult.getString("ten_nhan_vien"));
            nhanVien.setPassword(queryResult.getString("password"));
            nhanVien.setDiaChi(queryResult.getString("dia_chi"));
            nhanVien.setSoDienThoai(queryResult.getString("so_dien_thoai"));
            nhanVien.setGioiTinh(queryResult.getString("gioi_tinh"));
            nhanVien.setNgaySinh(queryResult.getDate("ngay_sinh"));
            nhanVien.setRoleName(queryResult.getString("name"));
            nhanVien.setRoleId(queryResult.getInt("roleId"));

        }
        return nhanVien;
    }

    // Get nhan vien by sdt
    public long getByMaNhanVien(String maNhanVien) throws SQLException {
        long count = 0;
        String query = "Select COUNT(*) AS total From nhan_vien Where ma_nhan_vien = '" + maNhanVien + "'";
        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            count = queryResult.getInt("total");
        }
        return count;
    }

    // Get nhan vien by sdt
    public long getNhanVienBySdt(String sdt) throws SQLException {
        long count = 0;
        String query = "Select COUNT(*) AS total From nhan_vien Where so_dien_thoai = '" + sdt + "'";
        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            count = queryResult.getInt("total");
        }
        return count;
    }

    // create
    public void Create(NhanVien nhanVien) throws SQLException {
        try {
            String query = "INSERT INTO nhan_vien (ma_nhan_vien, ten_nhan_vien, password, dia_chi, gioi_tinh, ngay_sinh, so_dien_thoai, role_id)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            transaction.begin();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nhanVien.getMaNhanVien());
            preparedStatement.setString(2, nhanVien.getTenNhanVien());
            preparedStatement.setString(3, nhanVien.getPassword());
            preparedStatement.setString(4, nhanVien.getDiaChi());
            preparedStatement.setString(5, nhanVien.getGioiTinh());
            preparedStatement.setDate(6, nhanVien.getNgaySinh());
            preparedStatement.setString(7, nhanVien.getSoDienThoai());
            preparedStatement.setInt(8, 0);
            preparedStatement.executeUpdate();

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback transaction in case of an error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Update
    public void update(NhanVien nhanVien) throws SQLException {
        try {
            String sql = "UPDATE nhan_vien SET "
                    + " ma_nhan_vien = ? , ten_nhan_vien = ? , "
                    + " password = ? , dia_chi = ? , "
                    + " gioi_tinh = ? , ngay_sinh = ? , "
                    + " so_dien_thoai = ?, role_id = ? "
                    + " WHERE id = ? ";

            transaction.begin();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nhanVien.getMaNhanVien());
            preparedStatement.setString(2, nhanVien.getTenNhanVien());
            preparedStatement.setString(3, nhanVien.getPassword());
            preparedStatement.setString(4, nhanVien.getDiaChi());
            preparedStatement.setString(5, nhanVien.getGioiTinh());
            preparedStatement.setDate(6, nhanVien.getNgaySinh());
            preparedStatement.setString(7, nhanVien.getSoDienThoai());
            preparedStatement.setInt(8, nhanVien.getRoleId());
            preparedStatement.setInt(9, nhanVien.getId());
            preparedStatement.executeUpdate();

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback transaction in case of an error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete nhan vien
    public void deleteNhanVien(int id) throws SQLException {
        try {
            String query = "DELETE FROM nhan_vien WHERE id = ?";

            transaction.begin();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback transaction in case of an error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // change password
    public void changePass(String pass, String maNhanVien) throws SQLException {
        try {
            String query = " UPDATE nhan_vien SET password = ? WHERE ma_nhan_vien = ? ";
            transaction.begin();

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, pass); // set input parameter 1
            pstmt.setString(2, maNhanVien); // set input parameter 2
            pstmt.executeUpdate(); // execute update statement

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback transaction in case of an error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

