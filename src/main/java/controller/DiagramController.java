package controller;

import dao.JoinDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Andrey Volinskiy on 12.02.2018.
 */
public class DiagramController implements Initializable {
    @FXML
    private PieChart pieChart;
    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        btnBack.setOnAction(this::close);
    }

    private void close(ActionEvent event) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        stage.setOnHiding(event1 -> new TableController().load());
    }

    private void init() {
        pieChart.setData(getChartData());
        pieChart.setTitle("Complete/incomplete orders");
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setLegendVisible(true);
        pieChart.setClockwise(false);
        pieChart.setLabelsVisible(true);
    }

    private ObservableList<PieChart.Data> getChartData() {
        int completed = new JoinDao().countOfCompleted();
        int inCompleted = new JoinDao().countOfIncompleted();
        ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList();
        observableList.add(new PieChart.Data("Completed", completed));
        observableList.add(new PieChart.Data("Incompleted", inCompleted));
        return observableList;
    }
}

