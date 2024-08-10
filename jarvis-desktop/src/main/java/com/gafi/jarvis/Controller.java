package com.gafi.jarvis;

import com.sun.jdi.PrimitiveValue;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Controller {
    private String data = "[12, 34], [12, 34], [12, 34], [12, 34], [12, 34]";

    @FXML
    private Label testLabel;
    @FXML
    private Label pointListLabel;
    @FXML
    private Label description;
    @FXML
    private Button generateDataBtn;
    @FXML
    private VBox chartBox;

    @FXML
    protected void navigateToExoOne() {
        testLabel.setText("Exercice 1");
        pointListLabel.setText(data);
        description.setText("Géneration de données aléatoire");
        // Show page one section
        generateDataBtn.setVisible(true);
        generateDataBtn.setManaged(true);
        pointListLabel.setVisible(true);
        pointListLabel.setManaged(true);
        // Efface les enfants du chartBox
        chartBox.getChildren().clear();
    }
    @FXML
    protected void navigateToExoTwo() {
        testLabel.setText("Exercice 2");
        description.setText("Vérification de point");
        String plist = "p1(0,5); p2(0,5); p3(0,5) => OUI";
        // Hide page one section
        generateDataBtn.setVisible(false);
        generateDataBtn.setManaged(false);
        pointListLabel.setManaged(false);
        // Show second page section
        pointListLabel.setText(plist);

        // Efface les enfants du chartBox
        chartBox.getChildren().clear();
        // Créer le chart
        // Créer les axes X et Y
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("axe X");
        yAxis.setLabel("axe Y");

        // Créer le ScatterChart
        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);

        // Create a Data Series and add data points
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Points");

        series.getData().add(new XYChart.Data<>(1, 5));
        series.getData().add(new XYChart.Data<>(2, 4));
        series.getData().add(new XYChart.Data<>(3, 6));

        // Add the series to the ScatterChart
        scatterChart.getData().add(series);

        // Add the ScatterChart to the VBox
        chartBox.getChildren().add(scatterChart);
    }
    @FXML
    protected void navigateToExoThree() {
        testLabel.setText("Exercice 3");
        description.setText("Enveloppe convexe");

        // Show third page section
        pointListLabel.setText(data);

        // Efface les enfants du chartBox
        chartBox.getChildren().clear();

        // Créer le chart
        // Créer les axes X et Y
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("axe X");
        yAxis.setLabel("axe Y");

        // Créer le ScatterChart
        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);

        // Create a Data Series and add data points
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Points");

        series.getData().add(new XYChart.Data<>(1, 5));
        series.getData().add(new XYChart.Data<>(2, 4));
        series.getData().add(new XYChart.Data<>(3, 6));
        series.getData().add(new XYChart.Data<>(4, 8));
        series.getData().add(new XYChart.Data<>(5, 7));

        // Add the series to the ScatterChart
        scatterChart.getData().add(series);

        // Add the ScatterChart to the VBox
        chartBox.getChildren().add(scatterChart);
    }
    @FXML
    public void initialize() {
        pointListLabel.setText(data);
    }
}
