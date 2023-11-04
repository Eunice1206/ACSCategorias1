module com.mycompany.acscategorias1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.acscategorias1 to javafx.fxml;
    exports com.mycompany.acscategorias1;
    
    opens Modelo;
    
    requires java.sql;
}
