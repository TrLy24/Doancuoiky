package model.service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class AuthenticateServer {
    private static final int PORT = 8080;
    private static Map<String, String> users = new HashMap<>();

    static {
        // Ví dụ: lưu trữ người dùng và mật khẩu trong bộ nhớ
        users.put("0001", "1234");
        users.put("0002", "adminpass");
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }

        } catch (IOException e) {
            System.out.println("error 1 " + e.getMessage());
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;


        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                String maNhanVien = in.readLine();
                String pass = in.readLine();

                if(users.containsKey(maNhanVien) && users.get(maNhanVien).equals(pass)){
                    out.println("SUCCESS");
                } else {
                    out.println("FAILURE");
                }

            } catch (IOException e) {
                System.out.println("error 2 " + e.getMessage());
                throw new RuntimeException(e);
            } finally {
                try {
                    socket.close();
                    System.out.println("error 2222 " + socket.getRemoteSocketAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
