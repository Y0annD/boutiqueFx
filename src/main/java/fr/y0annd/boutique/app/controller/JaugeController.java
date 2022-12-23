package fr.y0annd.boutique.app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import fr.y0annd.boutique.app.model.Rate;
import javafx.animation.RotateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Composant de type Jauge permettant d'afficher la charge d'un objet.
 *
 */
public class JaugeController extends StackPane implements Initializable {
	/** Panneau principal. */
	@FXML
	protected StackPane rootPane;
	/** Barre de progression. */
	@FXML
	protected ProgressBar progressBar;
	/** Label associé à la charge. */
	@FXML
	protected Label rateLabel;

	/** Objet contenant les informations de la jauge. */
	protected Rate mRate;
	/** Property du label pour effectuer le binding avec le label d'affichage. */
	protected StringProperty labelProperty;
	/**
	 * Mode d'affichage.
	 * <ul>
	 * <li>False: On affiche le pourcentage</li>
	 * <li>True: On affiche le nombre d'éléments et la valeur max</li>
	 * </ul>
	 */
	protected BooleanProperty mDisplayMode;
	/** Tooltip pour le survol. */
	protected Tooltip mTooltip;

	/** Classes CSS pour le fond de la barre de progression. */
	protected List<String> colorClasses = Arrays.asList("green-bar", "orange-bar", "red-bar");

	/**
	 * Constructeur de la jauge.
	 */
	public JaugeController() {
		mDisplayMode = new SimpleBooleanProperty();
		labelProperty = new SimpleStringProperty();
		mTooltip = new Tooltip();
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
		Tooltip.install(rootPane, mTooltip);

	}

	/**
	 * Constructeur de la jauge avec passage en paramètre de l'objet métier.
	 * 
	 * @param rate objet métier contenant les informations de la jauge
	 */
	public JaugeController(Rate rate) {
		this();
		mRate = rate;
		mRate.getRateProperty().addListener((observable, oldValue, newValue) -> {
			updateJauge(rate);
		});
		progressBar.progressProperty().bind(mRate.getRateProperty());
		rateLabel.textProperty().bind(labelProperty);
		labelProperty.bind(Bindings.when(mDisplayMode)
				.then(Bindings.createStringBinding(() -> mRate.getValue() + "/" + mRate.getMax(),
						mRate.getValueProperty(), mRate.getMaxProperty()))
				.otherwise(Bindings.createStringBinding(() -> {
					long roundedRate = Math.round(mRate.getRate() * 100);
					return String.valueOf(roundedRate) + " %";
				}, mRate.getRateProperty())));
		updateJauge(rate);
//		RotateTransition firstRotation = new RotateTransition(Duration.seconds(0.2), JaugeController.this);
//		firstRotation.ro
	}

	public void updateJauge(Rate rate) {
		String color;
		long roundedRate = Math.round(rate.getRate() * 100);
		mTooltip.setText(roundedRate + " % \n" + rate.getValue() + "/" + rate.getMax());
		if (rate.getRate() < 0.80) {
			color = "green-bar";
		} else if (rate.getRate() <= 1) {
			color = "orange-bar";
		} else {
			color = "red-bar";
		}
		progressBar.getStyleClass().removeAll(colorClasses);
		progressBar.getStyleClass().add(color);
		rateLabel.getStyleClass().clear();
		rateLabel.getStyleClass().add(color);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootPane.setOnMouseClicked(e -> {
			System.out.println("Toggle");
			mDisplayMode.set(!mDisplayMode.get());
			updateJauge(mRate);
		});
	}

}
