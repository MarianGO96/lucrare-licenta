
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.sql.*;

public class ListingPage {


    public void buildScene(final BorderPane borderPane) {

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        FlowPane flowPane = new FlowPane();
        scrollPane.setContent(flowPane);
        flowPane.setVgap(20);
        flowPane.setHgap(20);
        HBox hBox = new HBox();


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_fxbooking?user=root&password=Golubenco1996");
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select *  from rooms");
            while (resultset.next()) {
                VBox room = new VBox();
                room.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                room.setSpacing(5);

                room.setBorder(
                        new Border(
                                new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3)
                                )
                        ));
                Image image = new Image(resultset.getString("image"));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200);
                imageView.setPreserveRatio(true);
                Label labelTitle = new Label(resultset.getString("title"));
                Label labelDesc = new Label(resultset.getString("description"));
                Label labelPrice = new Label(resultset.getString("price"));
                Label labelLeiperNight = new Label(resultset.getString("leipernight"));
                Label labelmicdejun = new Label(resultset.getString("micdejun"));
                Label labellid = new Label(resultset.getString("id" )+ " : este idiul unic al camerei");



                room.getChildren().addAll(imageView, labelTitle, labelDesc, labelPrice, labelLeiperNight,labelmicdejun,labellid);
                room.setId((resultset.getString("id")));
                room.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        String roomId = ((Node) event.getSource()).getId();
                        ReservationPage reservationPage = new ReservationPage(roomId);

                        reservationPage.buildScene(borderPane);

                    }
                });
                flowPane.getChildren().addAll(room);



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        borderPane.setCenter(scrollPane);

    }

}
