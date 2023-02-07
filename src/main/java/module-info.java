module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires itextpdf;

    opens org.example to javafx.fxml;
    opens org.example.controller to javafx.fxml;

    exports org.example;
    exports org.example.controller;

    /**
     * https://stackoverflow.com/questions/67372505/java-lang-illegalaccessexception-module-javafx-base-cannot-access-class-sample
     * Die Ordner mussen scheinbar exportiert werden, sonst kann javafx nicht auf die Ordner wie Controller etc. zugreifen
     */
}
