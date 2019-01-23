import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class HotelBooking extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {

            primaryStage.setTitle("Green House");
            primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("images/Symbole-MG.jpg")));




        MainPage loginPage = new MainPage();

        BorderPane borderPane = new BorderPane();

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Optiuni");

        MenuItem menuitempaginaprincipala = new MenuItem("Pagina principala");
        menuitempaginaprincipala.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainPage loginPage1 = new MainPage();
                loginPage.buildScene(borderPane);
            }
        });

        MenuItem menuitemallrooms = new MenuItem("Rezervare Noua ");
        menuitemallrooms.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ListingPage listingPage = new ListingPage();
                listingPage.buildScene(borderPane);
            }
        });
        MenuItem menuItemmyreservations = new MenuItem("Rezervarile mele");
        menuItemmyreservations.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MyReservationsPage myReservationsPage = new MyReservationsPage();
                myReservationsPage.buildScene(borderPane);
            }
        });
        MenuItem menuItemcontacts = new MenuItem("Contacte");
        menuItemcontacts.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Contacts contacts = new Contacts();
                contacts.buildscene(borderPane);
            }
        });

        MenuItem menuItempoze = new MenuItem("Poze");
        menuItempoze.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Poze poze = new Poze();
                poze.buildScene(borderPane);
            }
        });

        menu.getItems().addAll(menuitempaginaprincipala,menuitemallrooms,menuItemmyreservations,menuItempoze,menuItemcontacts);
        menuBar.getMenus().add(menu);

        borderPane.setTop(menuBar);

        Scene scene = new Scene(borderPane,565,550);
        primaryStage.setScene(scene);

        loginPage.buildScene(borderPane);
        primaryStage.show();


    }
}
