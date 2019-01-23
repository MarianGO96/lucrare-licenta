import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class Rapoarte {
    public void buildscene(BorderPane borderPane) {
        VBox vBox = new VBox();
        Label labelnume = new Label("Rapoarte");
        labelnume.setFont(Font.font(String.valueOf(FontWeight.BOLD), 20));
        vBox.getChildren().add(labelnume);

        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        Button inapoi = new Button("Inapoi");
        inapoi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MainPage loginPage = new MainPage();
                loginPage.buildScene(borderPane);
            }
        });

        javafx.scene.text.Text text = new javafx.scene.text.Text();
        text.wrappingWidthProperty().set(345);
        Button statisticspage = new Button("Statistica");
        statisticspage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Statistics statistics = new Statistics();
                statistics.buildscene(borderPane);
            }
        });

        vBox.setPadding(new Insets(10, 10, 10, 10));






        Button ceamaiscumpacamera = new Button("Cea mai scumpa camera");
        ceamaiscumpacamera.setPrefHeight(9);
        ceamaiscumpacamera.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
                    PreparedStatement stm = connection.prepareStatement("SELECT max(price) as largest from rooms;");
                    ResultSet resultset = stm.executeQuery();
                    resultset.next();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Cea mai scumpa camera ");
                    try {
                        alert.setContentText(resultset.getString("largest") + " lei pentru o noapte ");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Optional<ButtonType> resultAlert = alert.showAndWait();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        Button buttonieftinacamera = new Button("Cea mai ieftina camera");
        buttonieftinacamera.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
                    PreparedStatement stm = connection.prepareStatement("SELECT min(price) as minima from rooms;");
                    ResultSet resultset = stm.executeQuery();
                    resultset.next();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Cea mai ieftina camera ");
                    try {
                        alert.setContentText(resultset.getString("minima") + " lei pentru o noapte ");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Optional<ButtonType> resultAlert = alert.showAndWait();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
        Button ceamairezervatacamera = new Button("Cea mai rezervată camera");
        ceamairezervatacamera.setLineSpacing(9);
        ceamairezervatacamera.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
                    PreparedStatement stm = connection.prepareStatement("SELECT count(*) as nr_rez, room_id FROM java_fxbooking.reservations group by room_id\n" +
                            "order by count(*) desc limit 1;");
                    ResultSet resultset = stm.executeQuery();
                    resultset.next();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Cea mai rezervata camera");
                    try {
                        alert.setContentText(resultset.getString("room_id") + " este id-ul celei mai rezervate camera avand " +
                            resultset.getString("nr_rez") + " rezervari!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Optional<ButtonType> resultAlert = alert.showAndWait();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Button celMaiActivUser = new Button("Cel mai activ client");
        celMaiActivUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
                    PreparedStatement stm = connection.prepareStatement(" SELECT count(*) as nr_rez, numeutilizator FROM java_fxbooking.reservations reservations\n" +
                            "join java_fxbooking.users users on users.id = reservations.user_id\n" +
                            "group by user_id\n" +
                            "order by count(*) desc limit 1;");
                    ResultSet resultset = stm.executeQuery();
                    resultset.next();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Cel mai activ client");
                    try {
                        alert.setContentText(resultset.getString("numeutilizator") + " este cel mai activ client avand " +
                                resultset.getString("nr_rez") + " rezervari!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Optional<ButtonType> resultAlert = alert.showAndWait();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        TextField lunaTotalRezervari = new TextField();
        Button totalrezervari = new Button("Totalul rezervarilor pentru luna");
        Label alegeluna = new Label("Alegeti luna");
        HBox hBoxtotalrezervari = new HBox();
        hBoxtotalrezervari.setSpacing(10);
        hBoxtotalrezervari.getChildren().addAll(lunaTotalRezervari, totalrezervari);


        totalrezervari.setPrefHeight(9);
        totalrezervari.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int month = Integer.parseInt(lunaTotalRezervari.getText());
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
                    PreparedStatement stm = connection.prepareStatement("SELECT count(id) \n" +
                            "  FROM reservations \n" +
                            " WHERE startdate between  '2018/" + month+ "/01' and '2018/" + month+"/30'\n" +
                            "   or enddate between '2018/"+ month +"/01'  and '2018/"+ month+"/30';");
                    ResultSet resultset = stm.executeQuery();
                    resultset.next();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Totalul rezervarilor ");
                    try {
                        alert.setContentText(resultset.getString("count(id)") + " este totalul de rezervari ");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Optional<ButtonType> resultAlert = alert.showAndWait();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        TextField orasRezervari = new TextField();
        Button orasRezervariB = new Button("Totalul rezervarilor pentru oras");
        Label alegeOras = new Label("Alegeti orasul");
        HBox hOrasRezervari = new HBox();
        hOrasRezervari.setSpacing(10);
        hOrasRezervari.getChildren().addAll( orasRezervari, orasRezervariB);


        orasRezervariB.setPrefHeight(9);
        orasRezervariB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String oras = orasRezervari.getText();
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
                    PreparedStatement stm = connection.prepareStatement("select count(*) as nr, adresa from reservations r join java_fxbooking.users u\n" +
                            "on r.user_id = u.id where adresa = '" + oras +"'");
                    ResultSet resultset = stm.executeQuery();
                    resultset.next();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Totalul rezervarilor ");
                    try {
                        alert.setContentText(resultset.getString("nr") + " este totalul de rezervari ");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Optional<ButtonType> resultAlert = alert.showAndWait();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });


        Button raportSex = new Button("Raport rezervari in functie de sex");
        raportSex.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
                    PreparedStatement stm = connection.prepareStatement(" select count(*) as nr, sex from reservations r join java_fxbooking.users u\n" +
                            "on r.user_id = u.id group by u.sex");
                    ResultSet resultset = stm.executeQuery();
                    resultset.next();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Raport rezervari in functie de sex");
                    try {
                        alert.setContentText(resultset.getString("nr") + " sunt " +
                                resultset.getString("sex") + ", ");
                        resultset.next();
                        alert.setContentText(alert.getContentText() + resultset.getString("nr") + " sunt " +
                                resultset.getString("sex"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Optional<ButtonType> resultAlert = alert.showAndWait();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });



        TextField emailRezervari = new TextField();
        Button emailRezervariB = new Button("Totalul rezervarilor cu email");
        Label alegeEmail = new Label("Alegeti tipul de email");
        HBox hEmailRezervari = new HBox();
        hEmailRezervari.setSpacing(10);
        hEmailRezervari.getChildren().addAll( emailRezervari, emailRezervariB);


        emailRezervariB.setPrefHeight(9);
        emailRezervariB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String email = emailRezervari.getText();
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
                    PreparedStatement stm = connection.prepareStatement("select count(*) as nr from reservations r join java_fxbooking.users u\n" +
                            "on r.user_id = u.id where email like '%" + email + "%'");
                    ResultSet resultset = stm.executeQuery();
                    resultset.next();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Totalul rezervarilor ");
                    try {
                        alert.setContentText(resultset.getString("nr") + " este totalul de rezervari ");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Optional<ButtonType> resultAlert = alert.showAndWait();
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        vBox.setSpacing(10);
        vBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        vBox.getChildren().addAll(inapoi, ceamairezervatacamera, celMaiActivUser, raportSex, alegeluna, hBoxtotalrezervari, alegeOras, hOrasRezervari, alegeEmail, hEmailRezervari,  statisticspage);
        borderPane.setCenter(vBox);
    }
}
