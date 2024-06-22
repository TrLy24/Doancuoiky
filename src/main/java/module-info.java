module com.example.tlpharmacy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires jasperreports;
    requires org.apache.pdfbox;
    requires org.hibernate.orm.core;
    requires hibernate.entitymanager;
    requires java.persistence;

    opens controller to javafx.fxml;
    exports controller;
    exports com.example.tlpharmacy;
    opens com.example.tlpharmacy to javafx.fxml;
    exports Util;
    opens Util to javafx.fxml;
    opens model.entity to org.hibernate.orm.core;
    opens model.response to org.hibernate.orm.core;
}