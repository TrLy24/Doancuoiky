package model.dao;

import Util.HibernateUtil;
import model.response.BanThuoc;
import model.response.NhanVien;
import model.response.Thuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.*;

public class BanThuocDao {

    private static EntityManager entityManager = HibernateUtil.getEntityManager();
    private static Session session = entityManager.unwrap(Session.class);
    private static SessionImplementor sessionImplementor = (SessionImplementor) session;
    private static  Connection connection = sessionImplementor.connection();
    private  static EntityTransaction transaction = entityManager.getTransaction();;

    public ObservableList<BanThuoc> getListBanThuoc(NhanVien data) throws SQLException {
        ObservableList<BanThuoc> listData = FXCollections.observableArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT *, n.role_id AS roleId, bt.id AS id_ban_thuoc, t.id AS id_thuoc, n.id AS id_nhan_vien, ");
        sql.append(" bt.so_luong_ban*t.don_gia AS tongtien ");
        sql.append(" FROM ban_thuoc bt ");
        sql.append(" INNER JOIN thuoc t ON bt.ma_thuoc =  t.ma_thuoc ");
        sql.append(" INNER JOIN nhan_vien n ON bt.ma_nhan_vien = n.ma_nhan_vien ");
        sql.append(" INNER JOIN role r ON n.role_id = r.role_id ");
        if(!data.getTenNhanVien().equals("admin")){
            sql.append(" WHERE in_hoa_don = 0");
            sql.append(" AND bt.ma_nhan_vien = ").append(data.getMaNhanVien());
        }
        sql.append(" ORDER BY bt.ngay_ban_thuoc DESC, bt.ma_don_thuoc ASC ");

        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(sql.toString());

        while (queryResult.next()){
            BanThuoc banThuoc = new BanThuoc();
            // ban thuoc
            banThuoc.setId(queryResult.getInt("id_ban_thuoc"));
            banThuoc.setMaDonThuoc(queryResult.getString("ma_don_thuoc"));
            banThuoc.setMaThuoc(queryResult.getString("ma_thuoc"));
            banThuoc.setSoLuongBan(queryResult.getInt("so_luong_ban"));
            banThuoc.setTongTien(queryResult.getDouble("tongtien"));
            banThuoc.setNgayBan(queryResult.getDate("ngay_ban_thuoc"));
            String inHoaDon= "Đã In";
            if(queryResult.getInt("in_hoa_don") == 0){
                inHoaDon = "Chưa In";
            }
            banThuoc.setInHoaDon(inHoaDon);

            // Nhan vien
            NhanVien nhanVien = new NhanVien();
            nhanVien.setId(queryResult.getInt("id_nhan_vien"));
            nhanVien.setMaNhanVien(queryResult.getString("ma_nhan_vien"));
            nhanVien.setTenNhanVien(queryResult.getString("ten_nhan_vien"));
            nhanVien.setPassword(queryResult.getString("password"));
            nhanVien.setDiaChi(queryResult.getString("dia_chi"));
            nhanVien.setSoDienThoai(queryResult.getString("so_dien_thoai"));
            nhanVien.setGioiTinh(queryResult.getString("gioi_tinh"));
            nhanVien.setNgaySinh(queryResult.getDate("ngay_sinh"));
            nhanVien.setRoleName(queryResult.getString("name"));
            nhanVien.setRoleId(queryResult.getInt("roleId"));
            banThuoc.setNhanVien(nhanVien);

            // thuoc
            Thuoc thuoc = new Thuoc();
            thuoc.setId(queryResult.getInt("id_thuoc"));
            thuoc.setMaThuoc(queryResult.getString("ma_thuoc"));
            thuoc.setTenThuoc(queryResult.getString("ten_thuoc"));
            thuoc.setNgaySanXuat(queryResult.getDate("ngay_san_xuat"));
            thuoc.setHanDung(queryResult.getDate("han_dung"));
            thuoc.setDonGia(queryResult.getDouble("don_gia"));
            thuoc.setSoLuong(queryResult.getInt("so_luong"));
            thuoc.setTongGia(queryResult.getDouble("tong_gia_nhap"));
            thuoc.setNhaCungCap(queryResult.getString("nha_cung_cap"));
            thuoc.setDonViTinh(queryResult.getString("don_vi_tinh"));
            banThuoc.setThuoc(thuoc);

            listData.add(banThuoc);
        }

        return listData;
    }

    public ObservableList<BanThuoc> getListBanThuocDoanhThu(Integer month, Integer year) throws SQLException {
        ObservableList<BanThuoc> listData = FXCollections.observableArrayList();
        String query = "SELECT bt.ngay_ban_thuoc, SUM(bt.tong_tien) AS tongtien, "
                + " DAY(bt.ngay_ban_thuoc) AS day,"
                + " MONTH(bt.ngay_ban_thuoc) AS month,"
                + " YEAR(bt.ngay_ban_thuoc) AS year "
                + " FROM ban_thuoc bt "
                + " INNER JOIN thuoc t ON bt.ma_thuoc =  t.ma_thuoc "
                + " INNER JOIN nhan_vien n ON bt.ma_nhan_vien = n.ma_nhan_vien "
                + " INNER JOIN role r ON n.role_id = r.role_id "
                + " WHERE MONTH(bt.ngay_ban_thuoc) = " + month + " "
                + " AND YEAR(bt.ngay_ban_thuoc) = " + year + " "
                + " GROUP BY bt.ngay_ban_thuoc "
                + " ORDER BY bt.ngay_ban_thuoc DESC, bt.ma_don_thuoc ASC ";

        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()){
            BanThuoc banThuoc = new BanThuoc();
            // ban thuoc
            banThuoc.setTongTien(queryResult.getDouble("tongtien"));
            banThuoc.setNgayBan(queryResult.getDate("ngay_ban_thuoc"));
            banThuoc.setDay(queryResult.getInt("day"));
            banThuoc.setMonth(queryResult.getInt("month"));
            banThuoc.setYears(queryResult.getInt("year"));
            listData.add(banThuoc);
        }

        return listData;
    }

    public ObservableList<BanThuoc> getListHoaDon(String maDonThuoc) throws SQLException {
        ObservableList<BanThuoc> listData = FXCollections.observableArrayList();
        String query = "SELECT *, n.role_id AS roleId, bt.id AS id_ban_thuoc, t.id AS id_thuoc, n.id AS id_nhan_vien, "
                + " SUM(bt.so_luong_ban) AS so_luong_ban_new, "
                + " SUM(bt.so_luong_ban) *t.don_gia AS tongtien "
                + " FROM ban_thuoc bt "
                + " INNER JOIN thuoc t ON bt.ma_thuoc =  t.ma_thuoc "
                + " INNER JOIN nhan_vien n ON bt.ma_nhan_vien = n.ma_nhan_vien "
                + " INNER JOIN role r ON n.role_id = r.role_id "
                + " WHERE bt.ma_don_thuoc = "+ maDonThuoc + ""
                + " AND in_hoa_don = 0"
                + " GROUP BY bt.ma_thuoc "
                + " ORDER BY bt.ngay_ban_thuoc DESC, bt.ma_don_thuoc ASC ";

        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()){
            BanThuoc banThuoc = new BanThuoc();
            // ban thuoc
            banThuoc.setId(queryResult.getInt("id_ban_thuoc"));
            banThuoc.setMaDonThuoc(queryResult.getString("ma_don_thuoc"));
            banThuoc.setMaThuoc(queryResult.getString("ma_thuoc"));
            banThuoc.setSoLuongBan(queryResult.getInt("so_luong_ban_new"));
            banThuoc.setTongTien(queryResult.getDouble("tongtien"));
            banThuoc.setNgayBan(queryResult.getDate("ngay_ban_thuoc"));

            // Nhan vien
            NhanVien nhanVien = new NhanVien();
            nhanVien.setId(queryResult.getInt("id_nhan_vien"));
            nhanVien.setMaNhanVien(queryResult.getString("ma_nhan_vien"));
            nhanVien.setTenNhanVien(queryResult.getString("ten_nhan_vien"));
            nhanVien.setPassword(queryResult.getString("password"));
            nhanVien.setDiaChi(queryResult.getString("dia_chi"));
            nhanVien.setSoDienThoai(queryResult.getString("so_dien_thoai"));
            nhanVien.setGioiTinh(queryResult.getString("gioi_tinh"));
            nhanVien.setNgaySinh(queryResult.getDate("ngay_sinh"));
            nhanVien.setRoleName(queryResult.getString("name"));
            nhanVien.setRoleId(queryResult.getInt("roleId"));
            banThuoc.setNhanVien(nhanVien);

            // thuoc
            Thuoc thuoc = new Thuoc();
            thuoc.setId(queryResult.getInt("id_thuoc"));
            thuoc.setMaThuoc(queryResult.getString("ma_thuoc"));
            thuoc.setTenThuoc(queryResult.getString("ten_thuoc"));
            thuoc.setNgaySanXuat(queryResult.getDate("ngay_san_xuat"));
            thuoc.setHanDung(queryResult.getDate("han_dung"));
            thuoc.setDonGia(queryResult.getDouble("don_gia"));
            thuoc.setSoLuong(queryResult.getInt("so_luong"));
            thuoc.setTongGia(queryResult.getDouble("tong_gia_nhap"));
            thuoc.setNhaCungCap(queryResult.getString("nha_cung_cap"));
            thuoc.setDonViTinh(queryResult.getString("don_vi_tinh"));
            banThuoc.setThuoc(thuoc);

            listData.add(banThuoc);
        }

        return listData;
    }

    // create
    public void Create(BanThuoc banThuoc) throws SQLException {
        try{
            String query = "INSERT INTO ban_thuoc (ma_don_thuoc, ma_thuoc, so_luong_ban, ma_nhan_vien, tong_tien, ngay_ban_thuoc)"
                    + "VALUES (?, ?, ?, ?, ?, NOW())";

            transaction.begin();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, banThuoc.getMaDonThuoc());
            preparedStatement.setString(2, banThuoc.getMaThuoc());
            preparedStatement.setInt(3, banThuoc.getSoLuongBan());
            preparedStatement.setString(4, banThuoc.getMaNhanVien());
            preparedStatement.setDouble(5, banThuoc.getTongTien());
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

    // create
    public void Update(String maDonThuoc) throws SQLException {
        try{
            String query = "UPDATE ban_thuoc SET "
                    + " in_hoa_don = 1 "
                    + " WHERE ma_don_thuoc = ? ";

            transaction.begin();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, maDonThuoc);
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

    // Delete ban thuoc
    public void deleteBanThuoc(int id)  throws SQLException {
        try{
            String query = "DELETE FROM ban_thuoc WHERE id = ?";

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
}

