package fr.y0annd.boutique.app.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class SaisieNomVilleController {
	@FXML
	private Label lblNomVille;
	@FXML
	private TextField txtVille;
	@FXML
	private Button btnValidate;
	@FXML
	private Label lblCodePostal;

	// Event Listener on Button[#btnValidate].onAction
	@FXML
	public void onValider(ActionEvent event) {
		String codePostal;
		switch (txtVille.getText()) {
		case "Brest":
			codePostal = "29200";
			break;
		case "Quimper":
			codePostal = "29000";
			break;
		case "Plougastel-Daoulas":
			codePostal = "29470";
			break;
		default:
			codePostal = "Nom de ville inconu";
			break;
		}
		lblCodePostal.setText(codePostal);
	}
}
