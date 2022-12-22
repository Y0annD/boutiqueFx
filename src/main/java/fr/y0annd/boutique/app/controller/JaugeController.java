package fr.y0annd.boutique.app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import fr.y0annd.boutique.app.model.Rate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;

public class JaugeController extends StackPane implements Initializable {

	@FXML
	protected StackPane rootPane;
	@FXML
	protected ProgressBar progressBar;
	@FXML
	protected Label rateLabel;
	@FXML
	protected Label unitLabel;

	protected Rate mRate;
	protected StringProperty labelProperty;
	protected BooleanProperty mDisplayMode;

	protected List<String> colorClasses = Arrays.asList("green-bar", "orange-bar", "red-bar");

	public JaugeController() {
		mDisplayMode = new SimpleBooleanProperty();
		labelProperty = new SimpleStringProperty();
		try {
			URL url = getClass().getClassLoader().getResource("Gauge.fxml");

			FXMLLoader loader = new FXMLLoader(url,
					ResourceBundle.getBundle("fr.y0annd.boutique.internationalisation.boutique"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public JaugeController(Rate rate) {
		this();
		mRate = rate;
		mRate.getRateProperty().addListener((observable, oldValue, newValue) -> {
			init(rate);
//			progressBar.styleProperty().set("-fx-background-color: "+ color);
		});
		progressBar.progressProperty().bind(mRate.getRateProperty());
		rateLabel.textProperty().bind(labelProperty);
		unitLabel.visibleProperty().bind(mDisplayMode);
		init(rate);
	}

	public void init(Rate rate) {
		String color;
		System.out.println("new Value: " + rate.getRate());
		if (rate.getRate() < 0.80) {
			color = "green-bar";
		} else if (rate.getRate() <= 1) {
			color = "orange-bar";
		} else {
			color = "red-bar";
		}
		progressBar.getStyleClass().removeAll(colorClasses);
		progressBar.getStyleClass().add(color);

		if (mDisplayMode.get()) {
			labelProperty.set(String.valueOf(Math.round(rate.getRate() * 100)));
		} else {
			labelProperty.set(rate.getValue() + " / " + rate.getMax());
		}
	}

//	public void setValue(int value) {
//		mValue.set(value);
//	}
//
//	public int getValue() {
//		return mValue.get();
//	}
//
//	public IntegerProperty getValueProperty() {
//		return mValue;
//	}
//
//	public void setMax(int value) {
//		mMax.set(value);
//	}
//
//	public int getMax() {
//		return mMax.get();
//	}
//
//	public IntegerProperty getMaxProperty() {
//		return mMax;
//	}
//
//	public void setRate(double value) {
//		mRate.set(value);
//	}
//
//	public double getRate() {
//		return mRate.get();
//	}
//
//	public DoubleProperty getRateProperty() {
//		return mRate;
//	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootPane.setOnMouseClicked(e -> {
			System.out.println("Toggle");
			mDisplayMode.set(!mDisplayMode.get());
			init(mRate);
		});
//		progressBar.setOnMouseClicked(e->{
//			mDisplayMode.set(!mDisplayMode.get());
//		});
	}

}
