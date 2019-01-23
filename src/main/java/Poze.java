import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Poze {


    public void buildScene(BorderPane borderPane) {


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


        VBox vBox = new VBox();
        Label labelnume = new Label("Pozele Hotelului");
        labelnume.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.ITALIC, 15));
        vBox.getChildren().addAll(labelnume);

        scrollPane.setContent(vBox);
        Button button = new Button("Inapoi");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MainPage loginPage = new MainPage();
                loginPage.buildScene(borderPane);
            }
        });
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));


        TitledPane titledPanereceptie = new TitledPane();
        titledPanereceptie.setText("RECEPTIE");
        titledPanereceptie.setContent(new ImageView("http://www.oferteinbulgaria.ro/usr/pics/locations/466/thumbs/thumb_500_500_b_bulgaria_nisipurile_de_aur_hotel_metropol_11062.jpg"));


        TitledPane titledPanerestaurant = new TitledPane();
        titledPanerestaurant.setText("RESTAURANT");
        titledPanerestaurant.setContent(new ImageView("https://www.ringhotels.com/uploads/picture/9366/image/cropped_7cb5333a3322e762_Restaurant_Wels_-_Hotel_Munte_am_Stadtwald_-_zugeschnitten.jpg"));


        TitledPane titledPanesladesport = new TitledPane();
        titledPanesladesport.setText("SALA DE SPORT");
        titledPanesladesport.setContent(new ImageView("https://www.budapesthotels-budapesthotelbooking.com/hotel_pic/hotel-divinus-debrecen/b-fitness-services-in-debrecen-hotel-divinus-hotel-divinus-debrecen.jpg"));

        TitledPane titledPanebazin = new TitledPane();
        titledPanebazin.setText("BAZIN");
        titledPanebazin.setContent(new ImageView("http://www.oferteinbulgaria.ro/usr/pics/locations/425/thumbs/thumb_500_500_b_bulgaria_sfantul_constantin_si_elena_hotel_aqua_azur_21689.jpg"));

        TitledPane titledPanesauna = new TitledPane();
        titledPanesauna.setText("SAUNA");
        titledPanesauna.setContent(new ImageView("http://www.travelro.ro/images/poiana%20brasov/hotel-aurelius-imparatul-romanilor-poiana-brasov-11.jpg"));

        TitledPane titledPanesport = new TitledPane();
        titledPanesport.setText("SPORT LA AER LIBER");
        titledPanesport.setContent(new ImageView("https://alexstana94.files.wordpress.com/2010/04/tenis1.jpg"));


        vBox.getChildren().addAll(button, titledPanereceptie, titledPanerestaurant, titledPanesladesport, titledPanebazin, titledPanesauna, titledPanesport);
        borderPane.setCenter(scrollPane);
    }



}