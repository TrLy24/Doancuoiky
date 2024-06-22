package controller;

import Util.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.response.NhanVien;
import org.mindrot.jbcrypt.BCrypt;
import model.service.NhanVienSevice;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button btnLogin;

    @FXML
    private Button btnChangPass;

    @FXML
    private TextField tmaNhanVien;

    @FXML
    private PasswordField tpass;

    @FXML
    private Label loginError;

    private NhanVienSevice nhanVienService = new NhanVienSevice();

    private static final String HOST = "localhost";
    private static final int PORT = 1434;  // Sửa cổng thành 1434

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
               EntityManager em = HibernateUtil.getEntityManager();
        loginError.setVisible(false);
    }

    // login
    public  void login (ActionEvent event) {
        try {
            if (tmaNhanVien.getText().isBlank() == false && tpass.getText().isBlank() == false) {
                NhanVien nhanVien = new NhanVien();
                nhanVien = nhanVienService.login(tmaNhanVien.getText());
                if(nhanVien.getId() != null) {
                    if(BCrypt.checkpw(tpass.getText(), nhanVien.getPassword())){
                        loginError.setVisible(false);

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Dashboard.fxml"));
                        Scene scene = new Scene(loader.load());
                        Stage dashboard = new Stage();

                        DashboardController dashboardController = loader.getController();
                        dashboardController.initData(nhanVien);

                        dashboard.setScene(scene);
                        dashboard.show();
                        // close form login
                        CloseApp();

                    } else {
                        loginError.setVisible(true);
                        loginError.setText("Mã Nhân Viên Và Password Không Đúng");
                    }
                } else {
                    loginError.setVisible(true);
                    loginError.setText("Nhân Viên Không Tồn Tại");
                }

            } else {
                loginError.setVisible(true);
                  loginError.setText("Vui Lòng Nhập Mã Nhân Viên Và Password");
            }

        }catch(Exception e) {
            loginError.setVisible(true);
            loginError.setText("Lỗi Đăng Nhập");
        }
    }

//    @FXML
//    private void login() {
//       String maNhanVien = tmaNhanVien.getText();
//       String password = tpass.getText();
//
//        try {
//            ServerSocket serverSocket = new ServerSocket(8080);
//            Socket clientSocket = new Socket();
//            try {
                    //dia
//                 clientSocket = serverSocket.accept();
//            } catch (IOException  e){
//                e.printStackTrace();
//            }
//
//            System.out.println("Client connected: " + clientSocket.getInetAddress());
//
//            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//
//            out.println(maNhanVien);
//            out.println(password);
//            String response = in.readLine();
//
//            if ("success".equals(response)) {
//                showAlert(Alert.AlertType.INFORMATION, "Login Successful!", "Welcome " + tmaNhanVien.getText());
//            } else {
//                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid email or password");
//            }
//
//        } catch (IOException e) {
//            showAlert(Alert.AlertType.ERROR, "error", e.getMessage());
//        }
//    }
//
//    private void showAlert(Alert.AlertType alertType, String title, String message) {
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
    // change pass
    public void ChangePass(ActionEvent event) throws IOException {
        FXMLLoader loaderRegister = new FXMLLoader(getClass().getResource("/Fxml/ChangePassWord.fxml"));
        Scene scene = new Scene(loaderRegister.load());
        Stage reg = new Stage();
        reg.setScene(scene);
        reg.show();
        CloseApp();
    }

    // Close app
    public void CloseApp() {
        Stage stage = (Stage) btnChangPass.getScene().getWindow();
        stage.close();
    }

}
