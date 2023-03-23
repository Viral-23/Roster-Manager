module tuition.project3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens tuition.project3 to javafx.fxml;
    exports tuition.project3;
}