package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import model.service.NhanVienSevice;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChangePassWordController implements Initializable {
    @FXML
    private TextField tpass;
    @FXML
    private TextField maNhanVien;
    @FXML
    private Button btnThoat;
    @FXML
    private Label Loi;

    private NhanVienSevice nhanVienService = new NhanVienSevice();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void save() throws Exception {
        try {
            if (!maNhanVien.getText().isBlank() && !tpass.getText().isBlank()) {
                boolean isCheck;
                isCheck =  nhanVienService.getByMaNhanVien(maNhanVien.getText());
                if (!isCheck) {
                    String pass = BCrypt.hashpw(tpass.getText(), BCrypt.gensalt());
                    nhanVienService.ChangePass(pass, maNhanVien.getText());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText("Lưu Thành Công");
                    alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
                    alert.showAndWait();
                    exit();
                } else {
                    Loi.setVisible(true);
                    Loi.setText("Mã Nhân Viên Chưa Đúng");
                }

            } else {
                // them message
                if (tpass.getText().isBlank()) {
                    Loi.setVisible(true);
                    Loi.setText("Mật Khẩu Không Được Để Trống");
                    // them message  mat khau khong dc dể trong
                } else {
                    Loi.setVisible(true);
                    Loi.setText("Mã Nhân Viên Không Được Để Trống");
                    }
                }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Lưu Mật Khẩu Nhân Viên Thất Bại");
            alert.getDialogPane().setPrefSize(400, 100); //sets size of alert box
            alert.showAndWait();
        }
    }

    // Show form login
    public void ShowLogin() throws IOException {
        FXMLLoader loaderRegister = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        Scene scene = new Scene(loaderRegister.load());
        Stage reg = new Stage();
        reg.setScene(scene);
        reg.show();
    }

    // close form
    public void exit () throws IOException {
        Stage stage = (Stage) btnThoat.getScene().getWindow();
        stage.close();
        ShowLogin();
    }
}
