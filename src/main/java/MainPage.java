import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.text.Style;
import javax.xml.soap.Text;
import java.sql.*;

public class MainPage {

    final TextField textUsername = new TextField();
    final PasswordField textPass = new PasswordField();

    public void buildScene(final BorderPane borderPane) {

        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20, 20, 20, 20));

        HBox welcome = new HBox();
        welcome.setSpacing(20);
        welcome.setAlignment(Pos.TOP_CENTER);
        Label labelwelcome = new Label("Bine ati venit la Hotelul Green House****");
        labelwelcome.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.ITALIC, 15));
        labelwelcome.setPrefWidth(300);
        welcome.getChildren().addAll(labelwelcome);


        Image image = new Image(getClass().getClassLoader().getResourceAsStream("images/2989014.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Poze poze = new Poze();
                poze.buildScene(borderPane);

            }
        });

        Button intraSiRezerva = new Button("Intra si rezerva");
        intraSiRezerva.setStyle("-fx-background-color:#0066cc; -fx-text-fill: black;");
        intraSiRezerva.setAlignment(Pos.TOP_CENTER);
        intraSiRezerva.setLineSpacing(50);
        intraSiRezerva.setPrefWidth(200);



        Button contacte = new Button("Contacte");
        contacte.setAlignment(Pos.CENTER_RIGHT);
        contacte.setStyle("-fx-background-color: rgb(60, 60, 60); -fx-text-fill: white;");
        contacte.setLineSpacing(50);
        contacte.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Contacts contacts = new Contacts();
                contacts.buildscene(borderPane);
            }
        });

        Button admin = new Button("Autentificare angajati");
        admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Adminlogin adminlogin = new Adminlogin();
                adminlogin.buildScene(borderPane);
            }
        });


        Button photo = new Button("Poze Hotel");
        photo.setAlignment(Pos.CENTER_RIGHT);
        photo.setStyle("-fx-background-color: rgb(120, 120, 120); -fx-text-fill: black;");
        photo.setLineSpacing(200);
        photo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Poze poze = new Poze();
                poze.buildScene(borderPane);
            }
        });


        Button intraincont = new Button("Autentificare Clienti");
        intraincont.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginUserPage loginUserPage = new LoginUserPage();
                loginUserPage.buildScene(borderPane);
            }
        });


        intraSiRezerva.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                login(borderPane);
            }
        });

        textPass.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                login(borderPane);
            }
        });

        vBox.getChildren().addAll(welcome, imageView,photo, intraSiRezerva,intraincont, admin, contacte);
        borderPane.setCenter(vBox);
    }

    private void login(BorderPane borderPane) {

        ListingPage listingPage = new ListingPage();
        listingPage.buildScene(borderPane);

    }


}
