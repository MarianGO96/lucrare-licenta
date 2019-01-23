import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.sql.*;

public class LoginUserPage {


    public void buildScene(BorderPane borderPane) {

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
        Label labelutilizator = new Label("Autentificarea Clientilor");
        labelutilizator.setAlignment(Pos.TOP_CENTER);
        labelutilizator.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.ITALIC, 15));
        vBox.getChildren().addAll(labelutilizator,button);

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
                    PreparedStatement stm = connection.prepareStatement("select * from users where numeutilizator = ? and parola =?");
                    stm.setString(1, enteredUsername);
                    stm.setString(2, enteredPass);

                    ResultSet resultSet = stm.executeQuery();
                    if (resultSet.next()) {
                        resultLogin.setText("Logare efectuata cu succes!!");
                        Session.user = resultSet.getInt("id");
                        ListingPage listingPage = new ListingPage();
                        listingPage.buildScene(borderPane);

                    } else {
                        resultLogin.setText("Username-ul sau Parola este gresita!!");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        vBox.getChildren().addAll(lineUsername ,liniPass, buttonLogin, resultLogin);

        Label labelSchimbaPass = new Label("Schimba parola");

        HBox lineUsername2 = new HBox();
        lineUsername2.setSpacing(5);
        Label lableUsername2 = new Label("Utilizator");
        lableUsername2.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.REGULAR, 14));
        lableUsername2.setPrefWidth(90);

        TextField username2 = new TextField();
        lineUsername2.getChildren().addAll(lableUsername2,username2);

        HBox liniPass2 = new HBox();
        liniPass2.setSpacing(5);
        Label labelPass2 = new Label("Parola actuala");
        labelPass2.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.REGULAR, 14));
        labelPass2.setPrefWidth(90);

        PasswordField passwordField2 = new PasswordField();
        liniPass2.getChildren().addAll(labelPass2,passwordField2);


        HBox liniPass3 = new HBox();
        liniPass3.setSpacing(5);
        Label labelPass3 = new Label("Parola noua");
        labelPass3.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.REGULAR, 14));
        labelPass3.setPrefWidth(90);

        PasswordField passwordField3 = new PasswordField();
        liniPass3.getChildren().addAll(labelPass3,passwordField3);

        Button buttonLogin2 = new Button();
        buttonLogin2.setText("Efectueaza");
        buttonLogin2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String enteredUsername = username2.getText();
                String enteredPass = passwordField2.getText();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
                    PreparedStatement stm = connection.prepareStatement("select * from users where numeutilizator = ? and parola =?");
                    stm.setString(1, enteredUsername);
                    stm.setString(2, enteredPass);

                    ResultSet resultSet = stm.executeQuery();
                    if (resultSet.next()) {
                        resultLogin.setText("Parola a fost schimbata cu succes!!");
                        Session.user = resultSet.getInt("id");
                        PreparedStatement stmSchimbaPass = connection.prepareStatement("update users set parola =? where numeutilizator = ?");
                        stmSchimbaPass.setString(1, passwordField3.getText());
                        stmSchimbaPass.setString(2, enteredUsername);

                        stmSchimbaPass.executeUpdate();

                    } else {
                        resultLogin.setText("Username-ul sau Parola este gresita!!");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        vBox.getChildren().addAll(labelSchimbaPass,lineUsername2, liniPass2, liniPass3, buttonLogin2);

        borderPane.setCenter(vBox);
    }
}