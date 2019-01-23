import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.*;

public class MyReservationsPage {

    public void buildScene(final BorderPane borderPane) {

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        FlowPane flowPane = new FlowPane();
        scrollPane.setContent(flowPane);
        flowPane.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        flowPane.setVgap(20);
        flowPane.setHgap(20);




        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from rooms  join reservations on rooms.id=reservations.room_Id where reservations.user_id=" + Session.user + ";");
            while (resultset.next()) {
                VBox room = new VBox();
                room.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                room.setSpacing(5);

                room.setBorder(
                        new Border(
                                new BorderStroke(Color.ROYALBLUE, BorderStrokeStyle.DASHED, new CornerRadii(5), new BorderWidths(3)
                                )
                        ));
                Image image = new Image(resultset.getString("image"));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(120);
                imageView.setPreserveRatio(true);
                Label labelTitle = new Label(resultset.getString("title"));
                Label labelDesc = new Label(resultset.getString("description"));
                Label labelPrice = new Label(resultset.getString("price"));
                Label labelLeiperNight = new Label(resultset.getString("leipernight"));
                Label labelStartDate = new Label(resultset.getString("startDate"));
                Label labelEndDate = new Label(resultset.getString("endDate"));
                Label labelmicdejun = new Label(resultset.getString("micdejun"));



                room.getChildren().addAll(scrollPane, imageView, labelTitle, labelDesc, labelPrice, labelLeiperNight, labelStartDate, labelEndDate, labelmicdejun);


                room.setId((resultset.getString("id")));
                room.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        String roomId = ((Node) event.getSource()).getId();
                        ReservationPage reservationPage = new ReservationPage(roomId);

                        reservationPage.buildScene(borderPane);

                    }
                });

                Button revokebooking = new Button("Anuleaza Rezervarea");
                revokebooking.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String roomId = ((Node) event.getSource()).getParent().getId();
                        Connection connection = null;
                        try {
                            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("delete from reservations where room_id = " + roomId + " and user_id=" +  Session.user);

                           buildScene(borderPane);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                room.getChildren().add(revokebooking);
                flowPane.getChildren().add(room);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        borderPane.setCenter(scrollPane);

    }
}
