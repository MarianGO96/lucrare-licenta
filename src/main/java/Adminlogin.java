import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.sql.*;

public class Adminlogin {
    final TextField textUsername = new TextField();
    final PasswordField textPass = new PasswordField();

    public void buildScene(final BorderPane borderPane) {
        VBox vBox = new VBox();
        Button button = new Button("Inapoi");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MainPage loginPage = new MainPage();
                loginPage.buildScene(borderPane);
            }
        });
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        Label labeladmin = new Label("Autentificarea  Angajatilor");
        labeladmin.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.ITALIC, 15));
        vBox.getChildren().addAll(labeladmin,button);

        HBox lineUsername = new HBox();
        lineUsername.setSpacing(5);
        Label lableUsername = new Label("Utilizator");
        lableUsername.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.REGULAR, 15));
        lableUsername.setPrefWidth(90);

        TextField username = new TextField();
        lineUsername.getChildren().addAll(lableUsername,username);

        HBox liniPass = new HBox();
        liniPass.setSpacing(5);
        Label labelPass = new Label("Parola");
        labelPass.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.REGULAR, 15));
        labelPass.setPrefWidth(90);

        PasswordField passwordField = new PasswordField();
        liniPass.getChildren().addAll(labelPass,passwordField);

        Label resultLogin = new Label();
        Button buttonLogin = new Button();
        buttonLogin.setText("Autentificare");
        buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String enteredUsername = username.getText();
                String enteredPass = passwordField.getText();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
                    PreparedStatement stm = connection.prepareStatement("select * from employers where utilizator = ? and parola =?");
                    stm.setString(1, enteredUsername);
                    stm.setString(2, enteredPass);

                    ResultSet resultSet = stm.executeQuery();
                    if (resultSet.next()) {
                        resultLogin.setText("Logare efectuata cu succes!!");
                        Session.user = resultSet.getInt("id");
                        if(resultSet.getInt("id_function") == 1) {
                            Rapoarte rapoarte = new Rapoarte();
                            rapoarte.buildscene(borderPane);
                        }
                        if(resultSet.getInt("id_function") == 2) {
                            AllReservationsPage allReservationsPage = new AllReservationsPage();
                            allReservationsPage.buildScene(borderPane);
                        }
                    } else {
                        resultLogin.setText("Username-ul sau Parola este gresita!!");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        vBox.getChildren().addAll(lineUsername ,liniPass, buttonLogin, resultLogin);
        borderPane.setCenter(vBox);
    }
}
