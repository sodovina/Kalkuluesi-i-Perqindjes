package calc;

import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainClass extends Application {
	private Kalkuluesi calc = new Kalkuluesi();
	private Button butoniRabat = new Button("Kalkulo p\u00EBrqindjen"), butoniVlere = new Button("Kalkulo vler\u00EBn");
	private TextField rabati = new TextField(""), vlera = new TextField(""), rez = new TextField("");
	private Text perqindjaText = new Text("P\u00EBrqindja:"), vleraText = new Text("Vlera \u20ac:"),
			rezText = new Text("");

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		DecimalFormat format = new DecimalFormat(",000");
		Pane pane = new Pane();
		butoniRabat.setLayoutX(10);
		butoniRabat.setLayoutY(250);
		butoniRabat.setPrefSize(120, 50);
		butoniRabat.setOnAction(e -> {
			try {
				double elRezulto = calc.kalkuloPerqindjen(new Double(rabati.getText()).doubleValue(),
						new Double(vlera.getText()).doubleValue());
				String rezultati = "" + elRezulto;
				int integjer = new Integer(rezultati.substring(0, rezultati.indexOf("."))).intValue();
				String decimal = rezultati.substring(rezultati.indexOf("."));
				rez.setText(format.format(integjer) + decimal + " \u20ac");
				rezText.setText(
						"Zbritje = " + (new Double(vlera.getText()) - elRezulto) + " , Vlera p\u00EBr pages\u00EB");
				rezText.setVisible(true);
				rez.setVisible(true);
			} catch (Exception e1) {
				Alert error = new Alert(AlertType.WARNING);
				error.setHeaderText(null);
				error.setTitle("Error");
				error.setContentText("Error: Duhet t\u00EB shkruani p\u00EBrqindjen dhe vler\u00EBn");
				error.showAndWait();
			}
		});
		butoniVlere.setLayoutX(130);
		butoniVlere.setLayoutY(250);
		butoniVlere.setPrefSize(120, 50);
		butoniVlere.setOnAction(e -> {
			try {
				String rezultati = "" + calc.kalkuloVleren(new Double(rabati.getText()).doubleValue(),
						new Double(vlera.getText()).doubleValue());
				int integjer = new Integer(rezultati.substring(0, rezultati.indexOf("."))).intValue();
				String decimal = rezultati.substring(rezultati.indexOf("."));
				rez.setText(format.format(integjer) + decimal + " \u20ac");
				rezText.setText("Vlera para zbritjes s\u00EB " + rabati.getText() + "%");
				rezText.setVisible(true);
				rez.setVisible(true);
			} catch (Exception e1) {
				Alert error = new Alert(AlertType.WARNING);
				error.setHeaderText(null);
				error.setTitle("Error");
				error.setContentText("Error: Duhet t\u00EB shkruani p\u00EBrqindjen dhe vler\u00EBn");
				error.showAndWait();
			}
		});
		perqindjaText.setLayoutX(20);
		perqindjaText.setLayoutY(127);
		rabati.setLayoutX(90);
		rabati.setLayoutY(110);
		rabati.setPromptText("P\u00EBrqindja %");
		rabati.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.contains(".") & !oldValue.contains(".")) {
					if (!newValue.isEmpty()) {
						try {
							int d = new Integer(newValue).intValue();
							rabati.setText("" + d);
						} catch (Exception e) {
							rabati.setText(oldValue);
						}
					}
				} else {
					if (newValue.isEmpty()) {
						rabati.setText(newValue);
					} else if (oldValue.contains(".")) {
						try {
							new Double(newValue).doubleValue();
							rabati.setText(newValue);
						} catch (Exception e) {
							rabati.setText(oldValue);
						}
					}
				}
			}
		});
		vleraText.setLayoutX(30);
		vleraText.setLayoutY(157);
		vlera.setLayoutX(90);
		vlera.setLayoutY(140);
		vlera.setPromptText("Vlera euro");
		vlera.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.contains(".") & !oldValue.contains(".")) {
					if (!newValue.isEmpty()) {
						try {
							int d = new Integer(newValue).intValue();
							vlera.setText("" + d);
						} catch (Exception e) {
							vlera.setText(oldValue);
						}
					}
				} else {
					if (newValue.isEmpty()) {
						vlera.setText(newValue);
					} else if (oldValue.contains(".")) {
						try {
							new Double(newValue).doubleValue();
							vlera.setText(newValue);
						} catch (Exception e) {
							vlera.setText(oldValue);
						}
					}
				}
			}
		});
		rezText.setVisible(false);
		rezText.setLayoutX(11);
		rezText.setLayoutY(195);
		rezText.prefHeight(240);
		rezText.setTextAlignment(TextAlignment.CENTER);
		rezText.setFont(new Font("Serif", 14));
		rez.setVisible(false);
		rez.setLayoutX(10);
		rez.setLayoutY(200);
		rez.setPrefSize(240, 25);
		rez.setAlignment(Pos.CENTER);
		rez.setEditable(false);
		rez.setFont(new Font("Serif", 18));
		pane.getChildren().addAll(rezText, perqindjaText, vleraText, butoniRabat, butoniVlere, rabati, vlera,
				rez);
		Scene scene = new Scene(pane, 250, 300);
		primaryStage.setTitle("Perqindja");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}