package fr.y0annd.boutique.app.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

import fr.y0annd.boutique.app.CountTask;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BackgroundTaskController implements Initializable {
	@FXML
	private Button btnActiver;
	@FXML
	private Label lblTache1;
	@FXML
	private Button btnInterromre;
	@FXML
	private Label lblTache2;

	private CountTask mTask = new CountTask(10_000_000);

	// Event Listener on Button[#btnActiver].onAction
	@FXML
	public void onActivate(ActionEvent event) {
		if(mTask.isDone()) {
			mTask = new CountTask(10_000_000);
		}
		lblTache1.textProperty().bind(mTask.messageProperty());
		lblTache2.textProperty().bind(mTask.progressProperty().asString());
		Executors.newSingleThreadExecutor().execute(mTask);
	}

	// Event Listener on Button[#btnInterromre].onAction
	@FXML
	public void onCancel(ActionEvent event) {
		mTask.cancel();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnActiver.disableProperty().bind(mTask.runningProperty());
		btnInterromre.disableProperty().bind(mTask.runningProperty().not());
		
	}
}
