module fr.y0annd {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;
	requires javafx.graphics;
	requires javafx.media;
	requires javafx.swing;

	exports fr.y0annd.boutique.app.controller;
	exports fr.y0annd.boutique.app.components;
	exports fr.y0annd.boutique.app.model;

	opens fr.y0annd.boutique.app to javafx.graphics, javafx.fxml;
	opens fr.y0annd.boutique.app.components to javafx.fxml;
	opens fr.y0annd.boutique.app.controller to javafx.fxml;
	opens fr.y0annd.boutique.metier to javafx.base;

}
