import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Statistics {
    public void buildscene(BorderPane borderPane) {

        VBox vBox = new VBox();
        Label labelnume = new Label("Statistica");
        labelnume.setFont(Font.font(String.valueOf(FontWeight.BOLD), FontPosture.ITALIC, 20));
        vBox.getChildren().addAll(labelnume);


        Button button = new Button("Inapoi");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Rapoarte rapoarte = new Rapoarte();
                rapoarte.buildscene(borderPane);
            }
        });

        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Ani");
        xAxis.getCategories().addAll("Clienti");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Vizitarori");

        StackedBarChart stackedBarChart = new StackedBarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Clienti");
        dataSeries1.getData().add(new XYChart.Data("2005", 132));
        dataSeries1.getData().add(new XYChart.Data("2006", 192));
        dataSeries1.getData().add(new XYChart.Data("2007", 283));
        dataSeries1.getData().add(new XYChart.Data("2008", 361));
        dataSeries1.getData().add(new XYChart.Data("2009", 400));
        dataSeries1.getData().add(new XYChart.Data("2010", 380));
        dataSeries1.getData().add(new XYChart.Data("2011", 430));
        dataSeries1.getData().add(new XYChart.Data("2012", 715));
        dataSeries1.getData().add(new XYChart.Data("2013", 936));
        dataSeries1.getData().add(new XYChart.Data("2014", 1025));
        dataSeries1.getData().add(new XYChart.Data("2015", 1360));
        dataSeries1.getData().add(new XYChart.Data("2016", 1520));
        dataSeries1.getData().add(new XYChart.Data("2017", 1720));


        stackedBarChart.getData().add(dataSeries1);
        vBox.getChildren().addAll(button,stackedBarChart);
        borderPane.setCenter(vBox);
    }

}
