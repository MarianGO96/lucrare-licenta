import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Contacts {



    public void buildscene(BorderPane borderPane) {


        VBox vBox = new VBox();
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


        Image image = new Image("https://media2.homezz.ro/media/gmap/5/732565_1.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(270);
        imageView.setPreserveRatio(true);


        Label labelcontact = new Label("Contacte, Pensiunea Green House,Brașov");
        labelcontact.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.ITALIC, 20));
        GridPane.setConstraints(labelcontact, 0, 3);


        Image imagetel = new Image("https://image.freepik.com/free-icon/telephone-symbol-button_318-41893.jpg");
        ImageView imageViewtel = new ImageView(imagetel);
        imageViewtel.setFitHeight(20);
        imageViewtel.setPreserveRatio(true);
        Label labeltel = new Label("Telefon: +(40)743 404 503",imageViewtel);
        labeltel.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.REGULAR, 17));



        Image imagemail = new Image("https://cdn4.iconfinder.com/data/icons/simple-soft/512/open_mail-512.png");
        ImageView imageViewmail = new ImageView(imagemail);
        imageViewmail.setFitWidth(20);
        imageViewmail.setPreserveRatio(true);
        Label labelmail = new Label("Mail: green.house@gmail.com", imageViewmail);
        labelmail.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.REGULAR, 17));


        Label labeladdres = new Label("Adresa: Str. Canionului, nr. 17, loc. Timisu de Jos, judetul Brasov");
        GridPane.setConstraints(labeladdres, 0, 6);
        labeladdres.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.REGULAR, 17));


        Image imagefacebook = new Image("http://resolutionmedia.com/us/wp-content/uploads/sites/4/2013/06/FB-f-Logo_blue_530.png");
        ImageView imageViewfacebok = new ImageView(imagefacebook);
        imageViewfacebok.setFitWidth(20);
        imageViewfacebok.setPreserveRatio(true);
        Label labelfacebook = new Label("Facebook: Green House", imageViewfacebok);
        labelfacebook.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.REGULAR, 17));


        vBox.getChildren().addAll(button, imageView, labelcontact, imageViewtel,labeltel,imageViewmail, labelmail, labeladdres,imageViewfacebok,labelfacebook);
        borderPane.setCenter(vBox);

    }


}
