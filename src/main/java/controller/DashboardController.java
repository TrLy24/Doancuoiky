package controller;

import model.response.BanThuoc;
import model.response.NhanVien;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.response.Thuoc;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.mindrot.jbcrypt.BCrypt;
import model.service.BanThuocService;
import model.service.NhanVienSevice;
import model.service.ThuocService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DashboardController implements  Initializable {

    private ThuocService thuocService = new ThuocService();
    private NhanVienSevice nhanVienSevice = new NhanVienSevice();
    private BanThuocService banThuocService = new BanThuocService();

    @FXML
    private AnchorPane FormNhanVien;

    @FXML
    private AnchorPane FormThuoc;

    @FXML
    private AnchorPane FormBanThuoc;

    @FXML
    private AnchorPane FormDoanhThu;
    @FXML
    private AnchorPane FormTrangChu;

    @FXML
    private Button btnTrangChu;
    @FXML
    private Button btnThuoc;

    @FXML
    private Button btnNhanVien;

    @FXML
    private Button btnBanThuoc;

    @FXML
    private Button btnDoanhThu;

    @FXML
    private Label lblTenNhanVien;

    @FXML
    private Button btnExit;
    @FXML
    private Button btnSua;
    @FXML
    private Button btnXoa;
    @FXML
    private Button btnThem;
    @FXML
    private Button btnThoat;
    @FXML
    private TextField maThuoc;
    @FXML
    private TextField tenThuoc;
    @FXML
    private TextField gia;
    @FXML
    private TextField soLuong;
    @FXML
    private TextField tongGia;
    @FXML
    private ComboBox nhaCungCap;
    @FXML
    private DatePicker ngaySanXuat;
    @FXML
    private DatePicker ngayHetHan;
    @FXML
    private ComboBox donViTinh;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Thuoc, String> tbMaThuoc;
    @FXML
    private TableColumn<Thuoc, String> tbTenThuoc;
    @FXML
    private TableColumn<Thuoc, String> tbGia;
    @FXML
    private TableColumn<Thuoc, Integer> tbSoLuong;
    @FXML
    private TableColumn<Thuoc, String> tbDonViTinh;
    @FXML
    private TableColumn<Thuoc, String> tbTongGia;
    @FXML
    private TableColumn<Thuoc, String> tbNgaySanXuat;
    @FXML
    private TableColumn<Thuoc, String> tbNgayHetHan;
    @FXML
    private TableColumn<Thuoc, String> tbNhaCungCap;
    @FXML
    private TextField valueSelect;
    private Thuoc data;

    // Nhan Vien
    @FXML
    private Button btnThemNhanVien;
    @FXML
    private Button btnSuaNhanVIen;
    @FXML
    private Button btnXoaNhanVien;
    @FXML
    private Button btnThoatNhanVien;
    @FXML
    private TextField maNhanVien;
    @FXML
    private TextField tenNhanVien;
    @FXML
    private ComboBox gioiTinh;
    @FXML
    private DatePicker ngaySinh;
    @FXML
    private TextField soDienThoai;
    @FXML
    private TextField diaChi;
    @FXML
    private TableColumn<NhanVien, String> tbMaNhanVien;
    @FXML
    private TableColumn<NhanVien, String> tbTenNhanVien;
    @FXML
    private TableColumn<NhanVien, String> tbGioiTinh;
    @FXML
    private TableColumn<NhanVien, String> tbNgaySinh;
    @FXML
    private TableColumn<NhanVien, String> tbSdt;
    @FXML
    private TableColumn<NhanVien, String> tbDiaChi;
    @FXML
    private TableView tableViewNhanVien;
    @FXML
    private TextField valueSelectNhanVien;
    private NhanVien dataNhanVien;

    private NhanVien dataLogin;

    // Don thuoc
    @FXML
    private Button btnGioHang;
    @FXML
    private Button btnIn;
    @FXML
    private Label lblErrorHoaDon;
    @FXML
    private Button btnXoaDonThuoc;
    @FXML
    private Button btnChonDonThuoc;
    @FXML
    private Button btnNhapDonThuoc;
    @FXML
    private ComboBox chonMaDonthuoc;
    @FXML
    private ComboBox cbMaThuoc;
    @FXML
    private TextField nhapMaDonThuoc;
    @FXML
    private TextField tenThuocBT;
    @FXML
    private TextField soLuongBT;
    @FXML
    private TextField donViTinhBT;
    @FXML
    private TextField giaBanBT;
    @FXML
    private TextField tongTienBT;
    @FXML
    private TableView tableViewBanThuoc;
    @FXML
    private ComboBox cmThang;

    @FXML
    private TableColumn<BanThuoc, String> tbMaDonThuocBT;
    @FXML
    private TableColumn<BanThuoc, String> tbMaThuocBT;
    @FXML
    private TableColumn<BanThuoc, String> tbTenThuocBT;
    @FXML
    private TableColumn<BanThuoc, String> tbDonViTinhBT;
    @FXML
    private TableColumn<BanThuoc, String> tbGiaBanBT;
    @FXML
    private TableColumn<BanThuoc, Integer> tbSoLuongBan;
    @FXML
    private TableColumn<BanThuoc, String> tbTongTienBT;
    @FXML
    private TableColumn<BanThuoc, String> tbNgayBan;
    @FXML
    private TableColumn<BanThuoc, String> tbTenNhanVienBT;
    @FXML
    private TableColumn<BanThuoc, String> tbIn;

    private boolean isCheck = false;
    private String maDonthuocValue = "";
    private int idBanThuoc;

    // Doanh thu
    @FXML
    private AreaChart chart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FormTrangChu.setVisible(true);
        FormThuoc.setVisible(false);
        FormNhanVien.setVisible(false);
        FormBanThuoc.setVisible(false);
        FormDoanhThu.setVisible(false);
        //thuoc
        nhaCungCap.setItems(FXCollections.observableArrayList("", "Nhà Cung Cấp 1", "Nhà Cung Cấp 2", "Nhà Cung Cấp 3"));
        donViTinh.setItems(FXCollections.observableArrayList("", "Viên", "Vỉ", "Hộp"));
        nhaCungCap.getSelectionModel().select(0);
        donViTinh.getSelectionModel().select(0);
        btnThem.setDisable(true);
        btnSua.setDisable(true);
        btnXoa.setDisable(true);
        btnThoat.setDisable(true);
        //banthuoc
        tbMaThuocBT.setCellValueFactory(new PropertyValueFactory<BanThuoc, String>("maThuocBT"));
        tbTenThuocBT.setCellValueFactory(new PropertyValueFactory<BanThuoc, String>("tenThuocBT"));
        tbDonViTinhBT.setCellValueFactory(new PropertyValueFactory<BanThuoc, String>("donViTinhBT"));
        tbGiaBanBT.setCellValueFactory(new PropertyValueFactory<BanThuoc, String>("giaBanBT"));
        tbSoLuongBan.setCellValueFactory(new PropertyValueFactory<BanThuoc,Integer>("soLuongBan"));
        tbNgayBan.setCellValueFactory(new PropertyValueFactory<BanThuoc, String>("ngayBan"));
        tbTenNhanVienBT.setCellValueFactory(new PropertyValueFactory<BanThuoc, String>("tenNhanVienBT"));
        tbTongTienBT.setCellValueFactory(new PropertyValueFactory<BanThuoc, String>("tongTienBT"));
        fillData();
    }

    // Init data phan quyen admin
    public void initData(NhanVien nhanVien) {
        dataLogin = nhanVien;
        lblTenNhanVien.setText(nhanVien.getTenNhanVien());
        if ( "admin".equalsIgnoreCase(nhanVien.getRoleName())) {
            btnTrangChu.setVisible(true);
            btnThuoc.setVisible(true);
            btnNhanVien.setVisible(true);
            btnBanThuoc.setVisible(true);
            btnDoanhThu.setVisible(true);
            DisableHoaDon();
        } else {
            btnTrangChu.setVisible(true);
            btnDoanhThu.setVisible(false);
            btnNhanVien.setVisible(false);
            btnBanThuoc.setVisible(true);
            btnThuoc.setVisible(true);
            enableHoaDon();
        }
    }

    public void DisableHoaDon(){
        btnGioHang.setDisable(true);
        nhapMaDonThuoc.setDisable(true);
        btnChonDonThuoc.setDisable(true);
        cbMaThuoc.setDisable(true);
        soLuongBT.setDisable(true);
        tenThuocBT.setDisable(true);
        donViTinhBT.setDisable(true);
        giaBanBT.setDisable(true);
        tongTienBT.setDisable(true);
    }

    public void enableHoaDon(){
        btnGioHang.setDisable(true);
        nhapMaDonThuoc.setDisable(false);
        btnChonDonThuoc.setDisable(false);
        cbMaThuoc.setDisable(false);
        soLuongBT.setDisable(false);
        tenThuocBT.setDisable(false);
        donViTinhBT.setDisable(false);
        giaBanBT.setDisable(false);
        tongTienBT.setDisable(false);
    }

    // Show Form
    public void show(MouseEvent event) throws Exception {
        try {
            String form = ((Control) event.getSource()).getId();
            if (form.equals("btnTrangChu")) {
                FormTrangChu.setVisible(true);
                FormThuoc.setVisible(false);
                FormBanThuoc.setVisible(false);
                FormDoanhThu.setVisible(false);
                FormNhanVien.setVisible(false);
            } else if (form.equals("btnThuoc")) {
                FormTrangChu.setVisible(false);
                FormThuoc.setVisible(true);
                FormBanThuoc.setVisible(false);
                FormDoanhThu.setVisible(false);
                FormNhanVien.setVisible(false);
                fillData();
                setValueDefault();
            } else if (form.equals("btnNhanVien")) {
                FormTrangChu.setVisible(false);
                FormThuoc.setVisible(false);
                FormBanThuoc.setVisible(false);
                FormDoanhThu.setVisible(false);
                FormNhanVien.setVisible(true);
                // nhan vien
                gioiTinh.setItems(FXCollections.observableArrayList("Nam", "Nữ"));
                gioiTinh.getSelectionModel().select(0);
                btnThemNhanVien.setDisable(true);
                btnSuaNhanVIen.setDisable(true);
                btnXoaNhanVien.setDisable(true);
                btnThoatNhanVien.setDisable(true);
                fillDataNhanVien();
                clearDataBanThuoc();
                valueDefaultNhanVien();
            } else if (form.equals("btnBanThuoc")) {
                FormTrangChu.setVisible(false);
                FormThuoc.setVisible(false);
                FormBanThuoc.setVisible(true);
                FormDoanhThu.setVisible(false);
                FormNhanVien.setVisible(false);
                btnXoaDonThuoc.setDisable(true);
                btnIn.setDisable(true);
                fillDataBanThuoc();
                comboBoxMaDonThuoc();
                clearDataBanThuoc();
            } else if (form.equals("btnDoanhThu")) {
                FormTrangChu.setVisible(false);
                FormThuoc.setVisible(false);
                FormBanThuoc.setVisible(false);
                FormDoanhThu.setVisible(true);
                FormNhanVien.setVisible(false);
                cmThang.setItems(FXCollections.observableArrayList("Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4",
                        "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"));
                LocalDate now = LocalDate.now();
                cmThang.getSelectionModel().select(now.getMonth().getValue()-1);
                AreaChart();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Lỗi Không Kết Nối Vui Lòng Liên Hệ Với Admin");
            alert.getDialogPane().setPrefSize(400, 100);
            alert.showAndWait();
        }

    }

    // Quan ly thuoc
    // save thuoc
    public void save() {
        if (!validate()) {
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                Thuoc thuoc = new Thuoc();
                thuoc.setMaThuoc(maThuoc.getText());
                thuoc.setTenThuoc(tenThuoc.getText());
                thuoc.setNgaySanXuat(Date.valueOf(ngaySanXuat.getValue()));
                thuoc.setHanDung(Date.valueOf(ngayHetHan.getValue()));
                thuoc.setDonGia(Double.parseDouble(gia.getText()));
                thuoc.setSoLuong(Integer.parseInt(soLuong.getText()));
                thuoc.setTongGia(Double.parseDouble(tongGia.getText()));
                thuoc.setNhaCungCap(nhaCungCap.getSelectionModel().getSelectedItem().toString());
                thuoc.setDonViTinh(donViTinh.getSelectionModel().getSelectedItem().toString());

                if (data != null) {
                    thuoc.setId(data.getId());
                    thuocService.Update(thuoc);
                    alert.setHeaderText("Sửa thành công");
                    getId();
                    setValueEdit(data);
                } else {
                    thuocService.Create(thuoc);
                    alert.setTitle("Message");
                    alert.setHeaderText("Thêm thuốc thành công");
                }

                alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                alert.showAndWait();
                // set default value
                if (data == null) {
                    setValueDefault();
                }
                fillData();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message");
                alert.setHeaderText("Thêm thuốc thất bại");
                if (data != null) {
                    alert.setHeaderText("Sửa thất bại");
                }
                alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                alert.showAndWait();
            }
        }
    }

    // Validate thuoc
    public boolean validate() {
        boolean isCheck = false;
        if (maThuoc.getText().isBlank() || tenThuoc.getText().isBlank() ||
                gia.getText().isBlank() || soLuong.getText().isBlank() ||
                tongGia.getText().isBlank() || ngaySanXuat.getValue() == null ||
                ngayHetHan.getValue() == null ||
                nhaCungCap.getSelectionModel().getSelectedIndex() == 0 ||
                donViTinh.getSelectionModel().getSelectedIndex() == 0) {
            isCheck = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Vui lòng nhập đủ thông tin các trường");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        } else {
            if (data == null) {
                isCheck = checkMaThuoc();
            }
        }
        return isCheck;
    }

    // edit data thuoc
    public void editData() {
        data = (Thuoc) tableView.getSelectionModel().getSelectedItem();
        btnThoat.setDisable(false);
        btnSua.setDisable(false);
        setValueEdit(data);
    }

    // Set value thuoc
    public void setValueEdit(Thuoc item) {
        maThuoc.setText(item.getMaThuoc());
        tenThuoc.setText(item.getTenThuoc());
        ngaySanXuat.setValue(item.getNgaySanXuat().toLocalDate());
        ngayHetHan.setValue(item.getHanDung().toLocalDate());
        gia.setText(String.valueOf(item.getDonGia()));
        soLuong.setText(String.valueOf(item.getSoLuong()));
        tongGia.setText(String.valueOf(item.getTongGia()));
        nhaCungCap.getSelectionModel().select(item.getNhaCungCap());
        donViTinh.getSelectionModel().select(item.getDonViTinh());
        btnXoa.setDisable(false);
        btnThem.setDisable(true);
        btnSua.setDisable(false);
    }

    // Check value thuoc
    public void checkValue() {
        if (!maThuoc.getText().isBlank() || !tenThuoc.getText().isBlank() ||
                !gia.getText().isBlank() || !soLuong.getText().isBlank() ||
                !tongGia.getText().isBlank() || ngaySanXuat.getValue() != null ||
                ngayHetHan.getValue() != null ||
                nhaCungCap.getSelectionModel().getSelectedIndex() != 0 ||
                donViTinh.getSelectionModel().getSelectedIndex() != 0) {

            if (data == null) {
                btnThem.setDisable(false);
                btnSua.setDisable(true);
            }
            btnThoat.setDisable(false);
            return;
        } else {
            btnThem.setDisable(true);
            btnThoat.setDisable(true);
        }
    }

    // Nummber only thuoc
    public void checkInput(KeyEvent event) {
        char inputChar = event.getCharacter().charAt(0);
        TextField textField = (TextField) event.getSource();
        String textFieldId = textField.getId();

        // Allow only numeric input (0-9)
        if (!(Character.isDigit(inputChar) || inputChar == '\b' || inputChar == '\u0000')) {
            if (textFieldId.equals("gia")) {
                gia.setText("0");
            } else if (textFieldId.equals("soLuong")) {
                soLuong.setText("1");
            } else if (textFieldId.equals("tongGia")) {
                tongGia.setText("0");
            } else if (textFieldId.equals("soDienThoai")) {
                soDienThoai.setText("0");
            }
            event.consume();
        }
    }

    // set value thuoc
    public void setValueDefault() {
        maThuoc.setText("");
        tenThuoc.setText("");
        ngaySanXuat.setValue(null);
        ngayHetHan.setValue(null);
        gia.setText("");
        soLuong.setText("");
        tongGia.setText("");
        nhaCungCap.getSelectionModel().select(0);
        donViTinh.getSelectionModel().select(0);
        btnXoa.setDisable(true);
        btnThem.setDisable(true);
        btnSua.setDisable(true);
        if (data != null) {
            data = null;
        }
    }

    // Check ma thuoc
    public boolean checkMaThuoc() {
        boolean isCheck = false;
        if (!maThuoc.getText().isBlank()) {
            try {
                isCheck = thuocService.getByMaThuoc(maThuoc.getText());
                if (!isCheck) {
                    maThuoc.requestFocus();
                    isCheck = true;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Message");
                    alert.setHeaderText("Mã thuốc tồn tại");
                    alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                    alert.showAndWait();
                } else {
                    isCheck = false;
                }
            } catch (Exception e) {
                isCheck = true;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message");
                alert.setHeaderText("Lỗi không xác định");
                alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                alert.showAndWait();
            }
        }
        return isCheck;
    }

    // Delete thuoc
    public void btnXoaData() {
        try {
            if (data != null) {
                thuocService.Delete(data.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("Xóa thành công");
                alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                alert.showAndWait();
                fillData();
                data = null;
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Không xoá được");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }
        setValueDefault();
    }

    // Fill data
    public void fillData() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat formatNumber = new DecimalFormat("###,##0.00");
        tableView.setItems(getDataFromDatabase());
        tbMaThuoc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaThuoc()));
        tbTenThuoc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTenThuoc()));
        tbGia.setCellValueFactory(cellData -> new SimpleStringProperty(formatNumber.format(cellData.getValue().getDonGia())));
        tbSoLuong.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSoLuong()).asObject());
        tbTongGia.setCellValueFactory(cellData -> new SimpleStringProperty(formatNumber.format(cellData.getValue().getTongGia())));
        tbNgaySanXuat.setCellValueFactory(cellData -> new SimpleStringProperty(dateFormat.format(cellData.getValue().getNgaySanXuat())));
        tbNgayHetHan.setCellValueFactory(cellData -> new SimpleStringProperty(dateFormat.format(cellData.getValue().getHanDung())));
        tbNhaCungCap.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNhaCungCap()));
        tbDonViTinh.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDonViTinh()));
    }

    // Get id edit
    public void getId() {
        try {
            data = thuocService.getByIdThuoc(data.getId());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Lỗi không xác định");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }
    }

    // Get list thuoc
    public ObservableList<Thuoc> getDataFromDatabase() {
        ObservableList<Thuoc> listData = FXCollections.observableArrayList();
        try {
            listData = thuocService.getList(valueSelect.getText());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Lỗi không xác định");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }
        return listData;
    }


    // Nhan Vien
    // Check ma nhan vien
    public void checkValueNhanVien() {
        if (!maNhanVien.getText().isBlank() || !tenNhanVien.getText().isBlank() && !soDienThoai.getText().isBlank() || !diaChi.getText().isBlank() ||
                ngaySinh.getValue() != null || gioiTinh.getSelectionModel().getSelectedIndex() != 0) {
            btnThoatNhanVien.setDisable(false);
        } else {
            btnThoatNhanVien.setDisable(true);
        }

        if (!maNhanVien.getText().isBlank() && !tenNhanVien.getText().isBlank() &&
                !soDienThoai.getText().isBlank() && !diaChi.getText().isBlank() ||
                gioiTinh.getSelectionModel().getSelectedIndex() != 0) {

            if (dataNhanVien == null) {
                btnThemNhanVien.setDisable(false);
                btnSuaNhanVIen.setDisable(true);
            }

            return;
        } else {
            btnThemNhanVien.setDisable(true);
        }
    }

    // Set default value nhan vien
    public void valueDefaultNhanVien() {
        maNhanVien.setText("");
        tenNhanVien.setText("");
        ngaySinh.setValue(null);
        soDienThoai.setText("");
        diaChi.setText("");
        gioiTinh.getSelectionModel().select(0);
        btnXoaNhanVien.setDisable(true);
        btnThemNhanVien.setDisable(true);
        btnSuaNhanVIen.setDisable(true);
        if (dataNhanVien != null) {
            dataNhanVien = null;
        }
    }

    // save nhanvien
    public void saveNhanVien() {
        if (!validateNhanVien()) {
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                // Set nhan vien
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNhanVien(maNhanVien.getText());
                nhanVien.setTenNhanVien(tenNhanVien.getText());
                nhanVien.setSoDienThoai(soDienThoai.getText());
                nhanVien.setGioiTinh(gioiTinh.getSelectionModel().getSelectedItem().toString());
                nhanVien.setNgaySinh(Date.valueOf(ngaySinh.getValue()));
                nhanVien.setDiaChi(diaChi.getText());

                if (dataNhanVien != null) {
                    nhanVien.setId(dataNhanVien.getId());
                    nhanVien.setPassword(dataNhanVien.getPassword());
                    nhanVien.setRoleId(dataNhanVien.getRoleId());
                    nhanVienSevice.Update(nhanVien);
                    alert.setHeaderText("Sửa thành công");
                    getIdNhanVien();
                    setValueEditNhanVien(dataNhanVien);

                } else {
                    // random pass
                    String chars = "abcdefghijklmnopqrstuvwxyz";
                    String NUMS = "1234567890";
                    String SPEC = "@#$%^&+=";

                    int index = 0;
                    StringBuilder pass = new StringBuilder();

                    Random rnd = new Random();
                    index = (int) (rnd.nextFloat() * chars.length());
                    pass.append(chars.charAt(index));
                    index = (int) (rnd.nextFloat() * NUMS.length());
                    pass.append(NUMS.charAt(index));
                    index = (int) (rnd.nextFloat() * SPEC.length());
                    pass.append(SPEC.charAt(index));

                    nhanVien.setPassword(BCrypt.hashpw(pass.toString(), BCrypt.gensalt()));
                    nhanVien.setRoleId(0);
                    nhanVienSevice.Create(nhanVien);

                    alert.setTitle("Message");
                    alert.setHeaderText("Thêm nhân viên thành công");
                }

                alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                alert.showAndWait();

                // set default value
                if (dataNhanVien == null) {
                    valueDefaultNhanVien();
                }
                fillDataNhanVien();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message");
                alert.setHeaderText("Thêm nhân viên thất bại");
                if (dataNhanVien != null) {
                    alert.setHeaderText("Sửa thất bại");
                }
                alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                alert.showAndWait();
            }
        }
    }

    // Validate Nhan Vien
    public boolean validateNhanVien() {
        boolean isCheck = false;
        if (maNhanVien.getText().isBlank() || tenNhanVien.getText().isBlank() ||
                soDienThoai.getText().isBlank() || ngaySinh.getValue() == null) {
            isCheck = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Vui lòng nhập đủ thông tin các trường");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        } else {
            if (dataNhanVien == null) {
                isCheck = checkMaNhanVienVaSdt();
            }
        }
        return isCheck;
    }

    // Set value nhan vien
    public void setValueEditNhanVien(NhanVien item) {
        maNhanVien.setText(item.getMaNhanVien());
        tenNhanVien.setText(item.getTenNhanVien());
        gioiTinh.getSelectionModel().select(item.getGioiTinh());
        ngaySinh.setValue(item.getNgaySinh().toLocalDate());
        soDienThoai.setText(String.valueOf(item.getSoDienThoai()));
        diaChi.setText(item.getDiaChi());
        btnXoaNhanVien.setDisable(false);
        btnThemNhanVien.setDisable(true);
        btnSuaNhanVIen.setDisable(false);
    }

    // edit data nhan vien
    public void editDataNhanVien() {
        dataNhanVien = (NhanVien) tableViewNhanVien.getSelectionModel().getSelectedItem();
        btnThoatNhanVien.setDisable(false);
        btnSuaNhanVIen.setDisable(false);
        setValueEditNhanVien(dataNhanVien);
    }

    // Get id nhan vien edit
    public void getIdNhanVien() {
        try {
            dataNhanVien = nhanVienSevice.getById(dataNhanVien.getId());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Lỗi không xác định");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }
    }

    // Check ma nhan vien va sdt
    public boolean checkMaNhanVienVaSdt() {
        boolean isCheck = false;
        if (!maNhanVien.getText().isBlank()) {
            try {
                isCheck = nhanVienSevice.getByMaNhanVien(maNhanVien.getText());
                if (!isCheck) {
                    maNhanVien.requestFocus();
                    isCheck = true;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Message");
                    alert.setHeaderText("Mã Nhân Viên tồn tại");
                    alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                    alert.showAndWait();
                } else {
                    isCheck = nhanVienSevice.checkSdt(soDienThoai.getText());
                    if (!isCheck) {
                        soDienThoai.requestFocus();
                        isCheck = true;
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Message");
                        alert.setHeaderText("Số điện thoại đã tồn tại");
                        alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                        alert.showAndWait();
                    } else {
                        isCheck = false;
                    }
                }
            } catch (Exception e) {
                isCheck = true;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message");
                alert.setHeaderText("Lỗi không xác định");
                alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                alert.showAndWait();
            }
        }
        return isCheck;
    }

    // Get list nhan vien
    public ObservableList<NhanVien> getDataNhanVienFromDatabase() {
        ObservableList<NhanVien> listData = FXCollections.observableArrayList();
        try {
            listData = nhanVienSevice.getList(valueSelectNhanVien.getText());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Lỗi không xác định");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }
        return listData;
    }

    // Fill data nhan vien
    public void fillDataNhanVien() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        tableViewNhanVien.setItems(getDataNhanVienFromDatabase());
        tbMaNhanVien.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaNhanVien()));
        tbTenNhanVien.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTenNhanVien()));
        tbGioiTinh.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGioiTinh()));
        tbNgaySinh.setCellValueFactory(cellData -> new SimpleStringProperty(dateFormat.format(cellData.getValue().getNgaySinh())));
        tbSdt.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSoDienThoai()));
        tbDiaChi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiaChi()));
    }

    // Delete nhan vien
    public void btnXoaDataNhanVien() {
        try {
            if (dataNhanVien != null) {
                nhanVienSevice.Delete(dataNhanVien.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("Xóa thành công");
                alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                alert.showAndWait();
                fillDataNhanVien();
                dataNhanVien = null;
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Không xoá được");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }
        valueDefaultNhanVien();
    }

    // Don Thuốc
    public void ChonMaDonThuoc(MouseEvent event) throws Exception {
        String btn = ((Control) event.getSource()).getId();
        if (btn.equals("btnChonDonThuoc")) {
            btnChonDonThuoc.setVisible(false);
            nhapMaDonThuoc.setVisible(false);
            nhapMaDonThuoc.setText("");
            btnNhapDonThuoc.setVisible(true);
            chonMaDonthuoc.setVisible(true);
            btnIn.setDisable(true);
            btnXoaDonThuoc.setDisable(true);
            chonMaDonthuoc.requestFocus();
        } else {
            btnChonDonThuoc.setVisible(true);
            nhapMaDonThuoc.setVisible(true);
            btnNhapDonThuoc.setVisible(false);
            chonMaDonthuoc.setVisible(false);
            chonMaDonthuoc.getSelectionModel().select(0);
            btnIn.setDisable(true);
            btnXoaDonThuoc.setDisable(true);
            nhapMaDonThuoc.requestFocus();
        }
    }

    // Get Combobox
    public void comboBoxMaDonThuoc() throws Exception {
        try {
            // Ma don hang
            ObservableList<BanThuoc> listBanThuoc = FXCollections.observableArrayList();
            ObservableList<String> comboBoxDonThuoc = FXCollections.observableArrayList();
            listBanThuoc = getDataBanThuocFromDatabase();

            String maDonThuoc = "";
            comboBoxDonThuoc.add("Chọn");

            for (int i = 0; i < listBanThuoc.size(); i++) {
                if (!maDonThuoc.equals(listBanThuoc.get(i).getMaDonThuoc())) {
                    comboBoxDonThuoc.add(listBanThuoc.get(i).getMaDonThuoc());
                }
                maDonThuoc = listBanThuoc.get(i).getMaDonThuoc();
            }
            chonMaDonthuoc.setItems(comboBoxDonThuoc);

            // ma thuoc
            ObservableList<Thuoc> listThuoc = FXCollections.observableArrayList();
            ObservableList<String> comboBoxThuoc = FXCollections.observableArrayList();
            listThuoc = thuocService.getList("");
            comboBoxThuoc.add("Chọn");
            for (int i = 0; i < listThuoc.size(); i++) {
                comboBoxThuoc.add(listThuoc.get(i).getMaThuoc());
            }
            cbMaThuoc.setItems(comboBoxThuoc);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Lỗi không lấy được dữ liệu");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }
    }

    // Fill data Bán thuoc
    public void fillDataBanThuoc() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat formatNumber = new DecimalFormat("###,##0.00");
        tableViewBanThuoc.setItems(getDataBanThuocFromDatabase());
        tbMaDonThuocBT.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaDonThuoc()));
        tbMaThuocBT.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaThuoc()));
        tbTenThuocBT.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getThuoc().getTenThuoc()));
        tbDonViTinhBT.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getThuoc().getDonViTinh()));
        tbGiaBanBT.setCellValueFactory(cellData -> new SimpleStringProperty(formatNumber.format(cellData.getValue().getThuoc().getDonGia())));
        tbSoLuongBan.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSoLuongBan()).asObject());
        tbNgayBan.setCellValueFactory(cellData -> new SimpleStringProperty(dateFormat.format(cellData.getValue().getNgayBan())));
        tbTenNhanVienBT.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNhanVien().getTenNhanVien()));
        tbTongTienBT.setCellValueFactory(cellData -> new SimpleStringProperty(formatNumber.format(cellData.getValue().getTongTien())));
        tbIn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInHoaDon()));


    }

    // Get list ban thuoc
    public ObservableList<BanThuoc> getDataBanThuocFromDatabase() {
        ObservableList<BanThuoc> listBanThuoc = FXCollections.observableArrayList();
        try {
            listBanThuoc = banThuocService.getListBanThuoc(dataLogin);
            if(listBanThuoc.isEmpty()){
               isCheck =  true;
            } else{
                isCheck =  false;
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Lỗi không xác định");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }
        return listBanThuoc;
    }

    // fill ten thuoc
    public void comboxMaThuoc() throws SQLException {
        try {
            DecimalFormat formatNumber = new DecimalFormat("###,##0.00");
            Thuoc thuoc = new Thuoc();
            Double tongTien = 0D;
            int index = cbMaThuoc.getSelectionModel().getSelectedIndex();
            if (index > 0) {
                thuoc = thuocService.getThuocByMaThuoc(cbMaThuoc.getSelectionModel().getSelectedItem().toString());
                if (thuoc.getId() > 0) {
                    tenThuocBT.setText(thuoc.getTenThuoc());
                    donViTinhBT.setText(thuoc.getDonViTinh());
                    giaBanBT.setText(formatNumber.format(thuoc.getDonGia()));

                    if (soLuongBT.getText() != null && !soLuongBT.getText().isEmpty()) {
                        if (Integer.parseInt(soLuongBT.getText()) > thuoc.getSoLuong()){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Message");
                            alert.setHeaderText("Vượt quá số lương thuốc trong kho");
                            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                            alert.showAndWait();
                            soLuongBT.setText(null);
                        }else {
                            tongTien = Double.parseDouble(soLuongBT.getText()) * thuoc.getDonGia();
                            tongTienBT.setText(formatNumber.format(tongTien));
                        }
                    }
                }
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Lỗi không xác định");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }
    }

    // tinh tong tien input so luong
    public void tinhTongTien(KeyEvent event) throws SQLException {
        char inputChar = event.getCharacter().charAt(0);
        TextField textField = (TextField) event.getSource();
        String textFieldId = textField.getId();
        // Allow only numeric input (0-9)
        if (!(Character.isDigit(inputChar) || inputChar == '\b' || inputChar == '\u0000')) {
            if (textFieldId.equals("soLuongBT")) {
                soLuongBT.setText(null);
            }
            event.consume();
        }

        Integer maThuoc = cbMaThuoc.getSelectionModel().getSelectedIndex();
        if (soLuongBT.getText().isEmpty()) {
            tongTienBT.setText(null);
        } else {
            if (maThuoc > 0) {
                comboxMaThuoc();
            }
        }
    }

    // clear data
    public void clearDataBanThuoc() {
        nhapMaDonThuoc.setText(null);
        chonMaDonthuoc.getSelectionModel().select(0);
        cbMaThuoc.getSelectionModel().select(0);
        tenThuocBT.setText(null);
        soLuongBT.setText(null);
        donViTinhBT.setText(null);
        giaBanBT.setText(null);
        tongTienBT.setText(null);
    }

    // save don hang
    public void saveDonHang() throws SQLException {
        if (!validateBanThuoc()) {
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                BanThuoc banThuoc = new BanThuoc();
                if (nhapMaDonThuoc.getText() != null && !nhapMaDonThuoc.getText().isEmpty()) {
                    banThuoc.setMaDonThuoc(nhapMaDonThuoc.getText());
                } else {
                    banThuoc.setMaDonThuoc(chonMaDonthuoc.getSelectionModel().getSelectedItem().toString());
                }
                banThuoc.setMaThuoc(cbMaThuoc.getSelectionModel().getSelectedItem().toString());
                banThuoc.setSoLuongBan(Integer.valueOf(soLuongBT.getText()));
                banThuoc.setMaNhanVien(dataLogin.getMaNhanVien());
                banThuoc.setTongTien(Double.valueOf(tongTienBT.getText().replaceAll(",", "")));
                banThuocService.Create(banThuoc);

                alert.setTitle("Message");
                alert.setHeaderText("Thêm đơn hàng thành công");
                alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                alert.showAndWait();

                clearDataBanThuoc();
                fillDataBanThuoc();
                comboBoxMaDonThuoc();
                btnGioHang.setDisable(true);

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message");
                alert.setHeaderText("Thêm đơn hàng thất bại");
                alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                alert.showAndWait();
            }
        }
    }

    public void onClickEdit() {
        BanThuoc data = new BanThuoc();
        if(!isCheck) {
            btnXoaDonThuoc.setDisable(false);
        }
        data = (BanThuoc) tableViewBanThuoc.getSelectionModel().getSelectedItem();
        idBanThuoc =  data.getId();
    }
    //delete don thuoc
   public void deleteDonHang(ActionEvent event) throws SQLException {
        try {
            banThuocService.Delete(idBanThuoc);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Xóa thành công");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
            fillDataBanThuoc();
            btnXoaDonThuoc.setDisable(true);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Không xóa đươc");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }

}
    // Validate ban thuoc
    public boolean validateBanThuoc () {
        boolean isCheck = false;
        Integer chonMaDonThuoc = chonMaDonthuoc.getSelectionModel().getSelectedIndex();
        String soLuong = soLuongBT.getText();
        String nhap = nhapMaDonThuoc.getText();
        Integer maThuoc = cbMaThuoc.getSelectionModel().getSelectedIndex();
        if (cbMaThuoc.getSelectionModel().getSelectedIndex() < 0 || soLuongBT.getText().isEmpty() ||
                (nhapMaDonThuoc.getText() != null && nhapMaDonThuoc.getText().isEmpty() &&  chonMaDonThuoc < 0)) {
            isCheck = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Vui lòng nhập đủ thông tin các trường");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }
        return isCheck;
    }

    public void showBtnInCBMaDonHang() {
        Integer chonMaDonThuoc = chonMaDonthuoc.getSelectionModel().getSelectedIndex();
        if(chonMaDonThuoc > 0 ){
            if(!dataLogin.getTenNhanVien().equals("admin")){
                btnIn.setDisable(false);
                btnGioHang.setDisable(false);
            } else {
                btnIn.setDisable(true);
                btnGioHang.setDisable(true);
            }
            maDonthuocValue = chonMaDonthuoc.getSelectionModel().getSelectedItem().toString();
        } else {
            btnIn.setDisable(true);
            btnGioHang.setDisable(true);
            maDonthuocValue = "";
        }
    }

    public void showBtnIn() {
        if(nhapMaDonThuoc.getText()!= null && !nhapMaDonThuoc.getText().isEmpty()){
            if(!dataLogin.getTenNhanVien().equals("admin")){
                btnIn.setDisable(false);
                btnGioHang.setDisable(false);
            } else {
                btnIn.setDisable(true);
                btnGioHang.setDisable(true);
            }
            maDonthuocValue = nhapMaDonThuoc.getText();
        } else {
            btnIn.setDisable(true);
            btnGioHang.setDisable(true);
            maDonthuocValue = "";
        }
    }

    public void onClickInput() {
        btnXoaDonThuoc.setDisable(true);
    }

    // In hoa don
    public void handlePrintReport() throws SQLException {
        ObservableList<BanThuoc> listDonThuoc = FXCollections.observableArrayList();
        listDonThuoc = banThuocService.getListHoaDon(maDonthuocValue);

        if (!listDonThuoc.isEmpty()) {
            DecimalFormat formatNumber = new DecimalFormat("###,##0.00");
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            String date = now.format(formatterDate);

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            fileChooser.setInitialFileName("HoaDon_" + maDonthuocValue + "_" + date + ".pdf");
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                try {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = now.format(formatter);

                    PDDocument document = new PDDocument();
                    PDPage page = new PDPage(new PDRectangle(105 * 72 / 25.4f, 160 * 72 / 25.4f));
                    document.addPage(page);
                    // font
                    InputStream fontStream = getClass().getResourceAsStream("/font/ARIAL.TTF");
                    PDType0Font font = PDType0Font.load(document, fontStream);
                    InputStream fontStreamBD = getClass().getResourceAsStream("/font/ARIALBD.TTF");
                    PDType0Font fontBD = PDType0Font.load(document, fontStreamBD);

                    PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
                    // line 1
                    contentStream.setFont(fontBD, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(85, 435);
                    contentStream.showText("NHÀ THUỐC TRÚC LY");
                    contentStream.endText();
                    // line 2
                    contentStream.beginText();
                    contentStream.setFont(font, 12);
                    contentStream.newLineAtOffset(10, 415);
                    contentStream.showText("Phường Hòa Hải, Quận Ngũ Hành Sơn, TP Đà Nẵng");
                    contentStream.endText();

                    // line 3
                    contentStream.beginText();
                    contentStream.setFont(font, 10);
                    contentStream.newLineAtOffset(85, 395);
                    contentStream.showText("www.nhathuoctly.com");
                    contentStream.endText();

                    // line 4
                    contentStream.beginText();
                    contentStream.setFont(font, 10);
                    contentStream.newLineAtOffset(95, 375);
                    contentStream.showText("Hotline: 0313456789");
                    contentStream.endText();

                    // line 5
                    contentStream.beginText();
                    contentStream.setFont(fontBD, 12);
                    contentStream.newLineAtOffset(85, 355);
                    contentStream.showText("HÓA ĐƠN THANH TOÁN");
                    contentStream.endText();

                    // line 6
                    contentStream.beginText();
                    contentStream.setFont(font, 12);
                    contentStream.newLineAtOffset(10, 335);
                    contentStream.showText("Ngày: " + formattedDate);

                    contentStream.newLineAtOffset(0, -15);
                    contentStream.showText("Đơn Hàng: " + maDonthuocValue);

                    contentStream.newLineAtOffset(0, -15);
                    contentStream.showText("Nhân Viên: " + dataLogin.getTenNhanVien());

                    contentStream.endText();

                    // header
                    contentStream.beginText();
                    contentStream.setFont(fontBD, 12); // In đậm
                    contentStream.newLineAtOffset(10, 290);
                    contentStream.showText("Tên Sản Phẩm");

                    contentStream.newLineAtOffset(100, -0);
                    contentStream.showText("Số Lượng");

                    contentStream.newLineAtOffset(110, -0);
                    contentStream.showText("Giá Tiền");

                    contentStream.endText();

                    // get list thuốc
                    int row = 285 - 15;
                    double totalAmount = 0.0;
                    for (int i = 0; i < listDonThuoc.size(); i++) {
                        contentStream.beginText();
                        contentStream.setFont(font, 12);
                        contentStream.newLineAtOffset(10, row);
                        contentStream.showText(listDonThuoc.get(i).getThuoc().getTenThuoc());

                        contentStream.newLineAtOffset(100, -0);
                        contentStream.showText(String.valueOf(listDonThuoc.get(i).getSoLuongBan()) + " " + listDonThuoc.get(i).getThuoc().getDonViTinh());

                        contentStream.newLineAtOffset(110, -0);
                        contentStream.showText(formatNumber.format(listDonThuoc.get(i).getTongTien()));

                        contentStream.endText();

                        totalAmount += listDonThuoc.get(i).getTongTien();
                        row = row - 15;
                    }

                    // Display total amount
                    contentStream.beginText();
                    contentStream.setFont(fontBD, 12);
                    contentStream.newLineAtOffset(10, row - 15);
                    contentStream.showText("Tổng Tiền: " + formatNumber.format(totalAmount) + " VND");
                    contentStream.endText();

                    // line 7
                    contentStream.beginText();
                    contentStream.setFont(fontBD, 10);
                    contentStream.newLineAtOffset(0, 40);
                    contentStream.showText("-Không áp dụng đổi trả với các sản phẩm khuyến mãi.");

                    contentStream.newLineAtOffset(0, -10);
                    contentStream.showText("-Khách hàng đồng ý nhận và thanh toán.");

                    contentStream.newLineAtOffset(0, -10);
                    contentStream.showText("-Vui lòng mang theo hóa đơn cho việc đổi trả.");

                    contentStream.newLineAtOffset(50, -10);
                    contentStream.showText("(Chỉ xuất hóa đơn tài chính trong ngày)");
                    contentStream.endText();

                    contentStream.close();
                    document.save(file);
                    document.close();

                    // update
                    banThuocService.Update(maDonthuocValue);

                    clearDataBanThuoc();
                    fillDataBanThuoc();
                    comboBoxMaDonThuoc();
                    System.out.println("PDF exported successfully!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText("In Hóa Đơn Thành Công");
                    alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                    alert.showAndWait();
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message");
                    alert.setHeaderText("In Hóa Đơn Thất Bại");
                    alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Mã Đơn Hàng Không Tồn tại");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }
    }



    // Doanh thu
    public void AreaChart(){
        try {
            ObservableList<BanThuoc> listDoanhThu = FXCollections.observableArrayList();
            //  date Now
            LocalDate now = LocalDate.now();
            int keyMonth = cmThang.getSelectionModel().getSelectedIndex()+1;
            // Clear chart
            chart.getData().clear();

            if(keyMonth > 0){
                // Get data
                listDoanhThu = banThuocService.getListBanThuocDoanhThu(keyMonth, now.getYear());

                //defining a series
                XYChart.Series series = new XYChart.Series();
                series.setName(cmThang.getSelectionModel().getSelectedItem().toString());

                chart.setTitle("Doanh Thu Trong Tháng " + keyMonth +", " + now.getYear());

                for(int i = 0; i < listDoanhThu.size(); i++) {
                    series.getData().add(new XYChart.Data(listDoanhThu.get(i).getDay(), listDoanhThu.get(i).getTongTien()));
                }
                // add chart
                chart.getData().add(series);
            }

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Lỗi. Vui lòng liên hệ admin");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }
    }

    // Show Login
    public void ShowLogin() throws IOException {
        FXMLLoader loaderRegister = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        Scene scene = new Scene(loaderRegister.load());
        Stage reg = new Stage();
        reg.setScene(scene);
        reg.show();
    }

    // Close app
    public void CloseApp() throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
        ShowLogin();
    }


}


