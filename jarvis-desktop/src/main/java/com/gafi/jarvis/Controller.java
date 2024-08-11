package com.gafi.jarvis;

import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class Controller {
    private int[][] generatedRandomValues;
    private String generatedValueString = "";
    private int[][] encConvData;
    private String encConvString = "";
    private String anglePolaireText = "";
    private String anglePolaireResult = null;

    @FXML
    private Label testLabel;
    @FXML
    private Label pointListLabel;
    @FXML
    private VBox generateDataBtn;
    @FXML
    private VBox chartBox;
    @FXML
    private VBox memberBox;

    @FXML
    protected void navigateToExoOne() {
        testLabel.setText("Generation des points aleatoires");
        pointListLabel.setText(generatedValueString);
        // Show page one section
        generateDataBtn.setVisible(true);
        generateDataBtn.setManaged(true);
        pointListLabel.setVisible(true);
        pointListLabel.setManaged(true);
        memberBox.setVisible(false);
        memberBox.setManaged(false);
        // Efface les enfants du chartBox
        chartBox.getChildren().clear();
    }
    @FXML
    protected void navigateToExoTwo() {
        testLabel.setText("Angle polaire");
        String plist = this.anglePolaireText + "=>" + this.anglePolaireResult;

        // Hide page one section
        generateDataBtn.setVisible(false);
        generateDataBtn.setManaged(false);
        pointListLabel.setVisible(true);
        pointListLabel.setManaged(true);
        memberBox.setVisible(false);
        memberBox.setManaged(false);
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

        // Plot Data
        addDataPoint(series, this.generatedRandomValues[0][0], this.generatedRandomValues[0][1], "p0");
        addDataPoint(series, this.generatedRandomValues[1][0], this.generatedRandomValues[1][1], "p1");
        addDataPoint(series, this.generatedRandomValues[2][0], this.generatedRandomValues[2][1], "p2");

        // Add the series to the ScatterChart
        scatterChart.getData().add(series);

        // Add the ScatterChart to the VBox
        chartBox.getChildren().add(scatterChart);
    }
    @FXML
    protected void navigateToExoThree() {
        testLabel.setText("Exercice 3");

        generateDataBtn.setVisible(false);
        generateDataBtn.setManaged(false);
        memberBox.setVisible(false);
        memberBox.setManaged(false);

        pointListLabel.setVisible(true);
        pointListLabel.setManaged(true);
        // Show third page section
        pointListLabel.setText(this.encConvString);

        // Efface les enfants du chartBox
        chartBox.getChildren().clear();

        // Créer la chart de liste de point
        createPointListChart(chartBox);

        // Créer la chart de envConv
        createEnconvChart(chartBox);
    }
    @FXML
    protected void navigateToMember() {
        testLabel.setText("Les membres de ce projet GAFI IGGLIA4 (2023-2024)");

        memberBox.setVisible(true);
        memberBox.setManaged(true);
        chartBox.getChildren().clear();
        generateDataBtn.setVisible(false);
        generateDataBtn.setManaged(false);
        pointListLabel.setVisible(false);
        pointListLabel.setManaged(false);
    }
    private void createPointListChart(VBox chartArea) {
        // Créer les axes X et Y
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        // Créer le ScatterChart
        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);

        // Create a Data Series and add data points
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Tout les points");

        // Add data points with labels
        for (int i = 0; i < this.generatedRandomValues.length; i++) {
            for (int j = 0; j < 2; j++) {
                addDataPoint(series, this.generatedRandomValues[i][0], this.generatedRandomValues[i][1], "p"+i);
            }
        }

        // Add the series to the ScatterChart
        scatterChart.getData().add(series);

        // Add the ScatterChart to the VBox
        chartArea.getChildren().add(scatterChart);
    }
    private void createEnconvChart(VBox chartArea) {
        // Créer les axes X et Y
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        ScatterChart<Number, Number> lineChart = new ScatterChart<>(xAxis, yAxis);

        // Create a Data Series and add data points
        XYChart.Series<Number, Number> seriesTwo = new XYChart.Series<>();
        seriesTwo.setName("Points des enveloppes convexe");

        // Add data points with labels

        for (int i = 0; i < this.encConvData.length; i++) {
            // System.out.print(this.encConvData[i][0] + "," + this.encConvData[i][1] + ") ;");
            seriesTwo.getData().add(new XYChart.Data<>(this.encConvData[i][0], this.generatedRandomValues[i][1]));
        }

        // Add the series to the scatterChartTwo
        lineChart.getData().add(seriesTwo);

        // Add the ScatterChart to the VBox
        chartArea.getChildren().add(lineChart);
    }
    private void addDataPoint(XYChart.Series<Number, Number> series, double x, double y, String name) {
        XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(x, y);

        // Create a label for the data point
        Label label = new Label(name);

        // Set the label as the node for this data point
        dataPoint.setNode(label);

        // Add the data point to the series
        series.getData().add(dataPoint);
    }
    public static int[][] convertTo2DArray(List<int[]> list) {
        // Initialize the 2D array with the size of the ArrayList
        int[][] array2D = new int[list.size()][];

        // Copy each int[] from the ArrayList to the 2D array
        for (int i = 0; i < list.size(); i++) {
            array2D[i] = list.get(i);
        }

        return array2D;
    }
    public static String showPoint(int[] point) {
        String result = "";
        for (int i = 0; i < 2; i++) {
            if(i == 0)
                result += "("+point[i]+",";
            else
                result += point[i]+")";
        }

        return result;
    }
    @FXML
    public void initialize() {
        // Get data from Algorithm
        Jarvis jv = new Jarvis(20);

        testLabel.setText("Generation des points aleatoires");
        int[][] random_values = jv.pointParHasard();
        this.generatedRandomValues = random_values;

        memberBox.setVisible(false);
        memberBox.setManaged(false);
        String newValue = "";
        for (int i = 0; i < random_values.length; i++) {
            for (int j = 0; j < 2; j++) {
                if(j == 0) {
                    // System.out.print("p"+i+" = ["+random_values[i][j]+",");
                    newValue += "p"+i+" = ("+random_values[i][j]+",";
                    this.generatedValueString += "p"+i+" = ("+random_values[i][j]+",";
                }
                else {
                    // System.out.print(random_values[i][j]+"]");
                    newValue += random_values[i][j]+") ";
                    this.generatedValueString += random_values[i][j]+") ";
                }
            }
        }

        // Set default data in View
        pointListLabel.setText(newValue);

        // Find envelop convex
        List<int[]> envconv = jv.findEnvConv(jv.getData());
        List<int[]> dataList = jv.getListData();

        this.encConvData = convertTo2DArray(envconv);

        for (int[] p : envconv) {
            this.encConvString += "p"+dataList.indexOf(p)+": "+"(" + p[0] + "," + p[1] + "); ";
        }

        // For exo 2
        this.anglePolaireText = "p0="+showPoint(this.generatedRandomValues[0])+" p1="+showPoint(this.generatedRandomValues[1])+" p2="+showPoint(this.generatedRandomValues[2]);
        this.anglePolaireResult = jv.anglePolaireInferieur(this.generatedRandomValues[0], this.generatedRandomValues[1], this.generatedRandomValues[2]);
    }
}
