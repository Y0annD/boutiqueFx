module fr.y0annd {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;
	requires javafx.graphics;
	requires javafx.media;
	requires javafx.swing;
	
	exports fr.y0annd.boutique.app.controller;
	opens fr.y0annd.boutique.app to javafx.graphics, javafx.fxml;
	opens fr.y0annd.boutique.app.controller to javafx.fxml;
	opens fr.y0annd.boutique.metier to javafx.base;
	
}
