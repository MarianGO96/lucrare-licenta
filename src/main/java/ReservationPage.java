import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.apache.commons.validator.routines.EmailValidator;


import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class ReservationPage {
    String roomId;

    private TextField textfieldUser;
    private PasswordField textfieldpass;
    private TextField textfieldemail;
    private TextField textfieldcity;
    private TextField textfieldfirstName;
    private TextField textfieldLastName;
    private ComboBox textfieldsex;
    private TextField textfieldtel;
    private ComboBox textfieldadulti;
    private ComboBox textfieldcopii;


    ReservationPage(String roomId) {
        this.roomId = roomId;
    }

    public void buildScene(BorderPane borderPane) {

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        HBox parent = new HBox();

        VBox vBox = new VBox();

        Label numepagina = new Label(" Rezervarea camerei cu id = " + roomId);
        numepagina.setAlignment(Pos.TOP_CENTER);
        numepagina.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.ITALIC, 15));
        vBox.getChildren().add(numepagina);
        scrollPane.setContent(borderPane);
        Button button = new Button("Inapoi");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ListingPage listingPage = new ListingPage();
                listingPage.buildScene(borderPane);
            }
        });
        Label atentie =new Label("Atentie! Datele dumnevoastra se vor salva automat ");
        atentie.setAlignment(Pos.BASELINE_CENTER);
        atentie.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.ITALIC, 12));
        atentie.setTextFill(Color.RED);



        Image image = new Image("https://i.szalas.hu/hotels/469942/500x500/2989014.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);


        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        final DatePicker dateStart = new DatePicker();
        final DatePicker dateEnd = new DatePicker();
        Button reserve = new Button("Rezerva");

        Label validari = new Label();
        reserve.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                //verify availability

                LocalDate enteredDateStart = dateStart.getValue();
                LocalDate enteredDateEnd = dateEnd.getValue();

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (Session.user == 0) {
                    String tel = textfieldtel.getText();
                    try{
                        Integer.parseInt(tel);
                    } catch (NumberFormatException e) {
                        validari.setText("Numarul de telefon trebuie sa contina doar cifre");
                        return;
                    }

                    String email = textfieldemail.getText();
                    boolean valid = EmailValidator.getInstance().isValid(email);
                    if(!valid) {
                        validari.setText("Email-ul nu este valid");
                        return;
                    }
                    //save user
                    try {

                        PreparedStatement verifyAvailabilityStatement = connection.prepareStatement("SELECT * FROM reservations where startDate < ? and endDate > ? and room_id = ?");
                        verifyAvailabilityStatement.setDate(1, Date.valueOf(enteredDateStart));
                        verifyAvailabilityStatement.setDate(2, Date.valueOf(enteredDateStart));
                        verifyAvailabilityStatement.setInt(3, Integer.parseInt(roomId));

                        ResultSet isAnotherReservation = verifyAvailabilityStatement.executeQuery();
                        if (isAnotherReservation.next()) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Atentie");
                            alert.setContentText("Perioada selectata se suprapune cu o alta rezervare,va rugam alegeti o alta perioada ");
                            Optional<ButtonType> resultAlert = alert.showAndWait();
                            return;
                        }

                        PreparedStatement stm = connection.prepareStatement("insert into users values(null,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                        stm.setString(1, textfieldUser.getText());
                        stm.setString(2, textfieldpass.getText());
                        stm.setString(3, textfieldemail.getText());
                        stm.setString(4, textfieldcity.getText());
                        stm.setString(5, textfieldfirstName.getText());
                        stm.setString(6, textfieldLastName.getText());
                        stm.setString(7, textfieldsex.getValue().toString());
                        stm.setString(8, textfieldtel.getText());
                        stm.setString(9, textfieldadulti.getValue().toString());
                        stm.setString(10, textfieldcopii.getValue().toString());


                        stm.executeUpdate();

                        ResultSet generatedKeys = stm.getGeneratedKeys();
                        generatedKeys.next();
                        Session.user = generatedKeys.getInt(1);
                        System.out.println(Session.user);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                //save reservation
                try {
                    PreparedStatement verifyAvailabilityStatement = connection.prepareStatement("SELECT * FROM reservations where startDate < ? and endDate > ? and room_id = ?");
                    verifyAvailabilityStatement.setDate(1, Date.valueOf(enteredDateStart));
                    verifyAvailabilityStatement.setDate(2, Date.valueOf(enteredDateStart));
                    verifyAvailabilityStatement.setInt(3, Integer.parseInt(roomId));

                    ResultSet isAnotherReservation = verifyAvailabilityStatement.executeQuery();
                    if (isAnotherReservation.next()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Atentie");
                        alert.setContentText("Perioada selectata se suprapune cu o alta rezervare,va rugam alegeti o alta perioada ");
                        Optional<ButtonType> resultAlert = alert.showAndWait();
                        return;
                    }

                    PreparedStatement stm2 = connection.prepareStatement("insert into reservations values(null,?,?,?,?)");
                    stm2.setInt(1, Integer.parseInt(roomId));
                    stm2.setDate(2, Date.valueOf(enteredDateStart));
                    stm2.setDate(3, Date.valueOf(enteredDateEnd));
                    stm2.setInt(4, Session.user);

                    stm2.executeUpdate();

                    MyReservationsPage myReservationsPage = new MyReservationsPage();
                    myReservationsPage.buildScene(borderPane);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        vBox.getChildren().addAll(button, imageView, dateStart, dateEnd, reserve,validari, atentie);


        parent.getChildren().addAll(vBox, buildColumnRegisterUser());
        borderPane.setCenter(parent);


    }


    public GridPane buildColumnRegisterUser() {

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 30));
        gridPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        gridPane.setVgap(10);
        gridPane.setHgap(10);

        if (Session.user > 0) {
            return gridPane;
        }

        Label labelUser = new Label("Utilizator");
        textfieldUser = new TextField();
        GridPane.setConstraints(labelUser, 0, 2);
        GridPane.setConstraints(textfieldUser, 1, 2);


        Label labelpass = new Label("Parola");
        textfieldpass = new PasswordField();
        GridPane.setConstraints(labelpass, 0, 3);
        GridPane.setConstraints(textfieldpass, 1, 3);

        Label labelemail = new Label("Email");
        textfieldemail = new TextField();
        GridPane.setConstraints(labelemail, 0, 4);
        GridPane.setConstraints(textfieldemail, 1, 4);

        Label labeladcity = new Label("Oraș");
        textfieldcity = new TextField();
        GridPane.setConstraints(labeladcity, 0, 5);
        GridPane.setConstraints(textfieldcity, 1, 5);

        Label labelefirstName = new Label("Numele");
        textfieldfirstName = new TextField();
        GridPane.setConstraints(labelefirstName, 0, 6);
        GridPane.setConstraints(textfieldfirstName, 1, 6);

        Label labelLastName = new Label("Prenumele");
        textfieldLastName = new TextField();
        GridPane.setConstraints(labelLastName, 0, 7);
        GridPane.setConstraints(textfieldLastName, 1, 7);

        Label labelsex = new Label("Sex");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "M",
                        "F"
                );
        textfieldsex = new ComboBox(options);
        GridPane.setConstraints(labelsex, 0, 8);
        GridPane.setConstraints(textfieldsex, 1, 8);

        Label labeltelefon = new Label("Telefon");
        textfieldtel = new TextField();
        GridPane.setConstraints(labeltelefon, 0, 9);
        GridPane.setConstraints(textfieldtel, 1, 9);

        Label labeladulti = new Label("Adulți");

        ObservableList<String> optionstextfieldadulti =
                FXCollections.observableArrayList(

                        "0","1",
                        "2", "3", "4"
                );
        textfieldadulti = new ComboBox(optionstextfieldadulti);


        GridPane.setConstraints(labeladulti, 0, 10);
        GridPane.setConstraints(textfieldadulti, 1, 10);

        Label labelcopii = new Label("Copii");
        textfieldcopii = new ComboBox(optionstextfieldadulti);
        GridPane.setConstraints(labelcopii, 0, 11);
        GridPane.setConstraints(textfieldcopii, 1, 11);

        gridPane.getChildren().addAll(labelUser, textfieldUser, labelpass, textfieldpass, labelemail,
                textfieldemail, labeladcity, textfieldcity, labelefirstName, textfieldfirstName, labelLastName, textfieldLastName,
                labelsex, textfieldsex, labeltelefon, textfieldtel, labeladulti, textfieldadulti, labelcopii, textfieldcopii);

        return gridPane;

    }


}