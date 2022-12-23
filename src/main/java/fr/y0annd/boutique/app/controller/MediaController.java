package fr.y0annd.boutique.app.controller;

import java.awt.FlowLayout;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class MediaController implements Initializable {

	@FXML
	private SwingNode swingNode;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private MediaView mediaView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JTextField tf = new JTextField(30);
		tf.setText("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
		panel.add(tf);
		JButton btn = new JButton("Let's go Marie Jo");
		btn.addActionListener(e -> {
			Media media = new Media(tf.getText());

			MediaPlayer player = new MediaPlayer(media);
			mediaView.setMediaPlayer(player);
			player.volumeProperty().set(0);
			progressBar.progressProperty().bind(Bindings.createDoubleBinding(() -> {
				Duration current = player.getCurrentTime();
				Duration total = player.getCycleDuration();
				return current.toMillis() / total.toMillis();
			}, player.currentTimeProperty()));
			player.play();
		});
		panel.add(btn);
		Platform.runLater(() -> {
			swingNode.setContent(panel);
			panel.repaint();
		});
//		Executors.newSingleThreadExecutor().execute(() -> {
//			while (true) {
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				panel.repaint();
//			}
//		});
	}

}
