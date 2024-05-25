package application;

import javafx.scene.control.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Driver extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		VBox vBox = new VBox(30);
		Image image = new Image(
				new FileInputStream("C:\\Users\\EASY LIFE\\Eclipse IDE\\.metadata\\Gaza\\src\\application\\Map1.jpeg"));
		ImageView imageView = new ImageView(image);
		int height = 750;
		int width = 950;
		imageView.setFitHeight(height);
		imageView.setFitWidth(width);
		AnchorPane pane = new AnchorPane();
		pane.setPrefHeight(750);
		pane.setPrefWidth(1500);

		Label from = new Label("From");
		from.setFont(new Font("system", 14));
		from.setTextFill(Color.web("Black"));
		from.setStyle("-fx-font-weight: bold");
		from.setFont(Font.font("Baskerville Old Face", 22));
		from.setPrefWidth(100);
		from.setPrefHeight(30);

		ComboBox<String> comboBoxSource = new ComboBox<String>();

		comboBoxSource.setPrefHeight(30);
		comboBoxSource.setPrefWidth(200);
		comboBoxSource.setEditable(true);
		comboBoxSource.setPromptText("choose city .....");
		comboBoxSource.setStyle("-fx-background-color: black;");
		comboBoxSource.setStyle("-fx-border-color : black ; " + " -fx-border-width : 1;" + "-fx-text-fill: black");

		Label to = new Label("To");
		to.setFont(Font.font("Baskerville Old Face", 22));
		to.setTextFill(Color.web("Black"));
		to.setStyle("-fx-font-weight: bold");
		to.setPrefWidth(100);
		to.setPrefHeight(30);

		ComboBox<String> comboBoxTarget = new ComboBox<String>();

		comboBoxTarget.setPromptText("choose city .....");
		comboBoxTarget.setPrefHeight(30);
		comboBoxTarget.setPrefWidth(200);
		comboBoxTarget.setEditable(true);
		comboBoxTarget.setStyle("-fx-background-color: black;");
		comboBoxTarget.setStyle("-fx-border-color : black ; " + " -fx-border-width : 1;" + "-fx-text-fill: black");

		Label pathLabel = new Label("Path");
		pathLabel.setFont(Font.font("Baskerville Old Face", 22));
		pathLabel.setPrefWidth(100);
		pathLabel.setPrefHeight(30);

		pathLabel.setTextFill(Color.web("Black"));
		pathLabel.setStyle("-fx-font-weight: bold");

		TextArea path = new TextArea();
		path.setPrefHeight(100);
		path.setPrefWidth(250);
		path.setStyle("-fx-border-color : black ; " + " -fx-border-width : 2;" + "-fx-text-fill: black");

		Label DistanceLabel = new Label("Distance(Km)");

		DistanceLabel.setFont(Font.font("Baskerville Old Face", 22));
		DistanceLabel.setTextFill(Color.web("Black"));
		DistanceLabel.setStyle("-fx-font-weight: bold");
		DistanceLabel.setPrefWidth(100);
		DistanceLabel.setPrefHeight(30);

		TextField distance = new TextField();
		distance.setPrefHeight(30);
		distance.setPrefWidth(200);
		distance.setAlignment(Pos.CENTER);
		distance.setStyle("-fx-border-color : black ; " + " -fx-border-width : 2;" + "-fx-text-fill: black");

		Button runButton = new Button("Run");
		runButton.setPrefHeight(20);
		runButton.setPrefWidth(100);
		runButton.setMnemonicParsing(false);

		runButton.setStyle(
				"-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5 10 5 10;");

		Button reset = new Button("Reset");
		reset.setPrefHeight(20);
		reset.setPrefWidth(100);
		reset.setStyle(
				"-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5 10 5 10;");

		Graph.yourWidth = 950;
		Graph.yourHeight = 750;
		ArrayList<Vertex> cities = Graph.readcities(height, width);

		pane.setStyle("-fx-background-color: gray");

		HBox hBox = new HBox(20);
		hBox.getChildren().addAll(runButton, reset);

		HBox hBox1 = new HBox(20);
		hBox1.getChildren().addAll(pathLabel, path);

		HBox hBox2 = new HBox(20);
		hBox2.getChildren().addAll(DistanceLabel, distance);

		HBox hBox3 = new HBox(20);
		hBox3.getChildren().addAll(from, comboBoxSource);

		HBox hBox4 = new HBox(20);
		hBox4.getChildren().addAll(to, comboBoxTarget);

		vBox.getChildren().addAll(hBox3, hBox4, hBox1, hBox2, hBox);
		vBox.setLayoutX(1000);
		vBox.setLayoutY(10);

		pane.getChildren().addAll(vBox, imageView);
		Group g = new Group();
		g.getChildren().addAll(pane, imageView);

		for (int i = 0; i < cities.size(); i++) {
			comboBoxSource.getItems().add(cities.get(i).getcity().getName());
			comboBoxTarget.getItems().add(cities.get(i).getcity().getName());
		}

		ArrayList<RadioButton> array = new ArrayList<>();
		for (int i = 0; i < cities.size(); i++) {
			RadioButton radioButton = new RadioButton(cities.get(i).getcity().getName());
			radioButton.setOnMouseClicked(e -> {
				for (int j = 0; j < array.size(); j++) {
					array.get(j).setSelected(false);
				}
				if (comboBoxSource.getValue() == null || comboBoxSource.getValue().equals("")) {
					radioButton.setSelected(true);
					comboBoxSource.setValue(radioButton.getId());
				} else if (comboBoxTarget.getValue() == null || comboBoxTarget.getValue().equals("")) {
					radioButton.setSelected(true);
					comboBoxTarget.setValue(radioButton.getId());
				}
			});
			radioButton.setId(cities.get(i).getcity().getName());
			radioButton.setLayoutX(cities.get(i).getcity().getMercatorX());
			radioButton.setLayoutY(height - cities.get(i).getcity().getMercatorY());
			radioButton.setTextFill(Color.BLACK);
			radioButton.setStyle("-fx-font-size:7px;" + "-fx-font-weight: bold");
			array.add(radioButton);
			g.getChildren().addAll(radioButton);

		}

		Scene scene = new Scene(g, 1500, 750);
		primaryStage.setScene(scene);

		primaryStage.show();

		reset.setOnAction(e -> {
			deleteLines(g);
			comboBoxSource.setValue("");
			comboBoxTarget.setValue("");
			path.clear();
			distance.clear();
		});

		runButton.setOnAction(e -> {
			Graph graph = new Graph();
			deleteLines(g);
			try {
				Vertex v = Graph.Search(comboBoxSource.getValue().trim());
				Vertex v4 = Graph.Search(comboBoxTarget.getValue().trim());

				graph.dijkstra(v, v4);

				ArrayList<String> ar = graph.PrintPath(v, v4);
				String s = "";
				for (int i = 0; i < ar.size(); i++) {
					s += "->" + ar.get(i) + " \n";
				}
				if (v4.getDistance() == Double.MAX_VALUE)
					distance.setText(" No Distance");
				else
					distance.setText(String.valueOf(v4.getDistance() + "  Km"));
				path.setText(s);

				recScenes(v4, g, height);

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
	}

	public static Group recScenes(Vertex target, Group myPane, int height) {
		Group lineGroup = new Group();
		myPane.getChildren()
				.removeIf(node -> node instanceof Group && ((Group) node).getChildren().contains(lineGroup));

		myPane.getChildren().add(lineGroup);

		while (target != null) {
			Vertex prev = target.getPrev();
			if (prev == null)
				break;

			Line line = new Line(target.getcity().getX() + 5, height - target.getcity().getY() + 5,
					prev.getcity().getX() + 5, height - prev.getcity().getY() + 5);
			line.setStyle("-fx-stroke: #ffea00;" + "-fx-stroke-width: 2px;");
			addArrow(lineGroup, line);
			lineGroup.getChildren().add(line);
			line.getStyleClass().add("line");

			target = prev;
		}
		return myPane;
	}

	private static void addArrow(Group g, Line line) {
		double arrowSize = 10.0;

		double angle = Math.atan2(line.getEndY() - line.getStartY(), line.getEndX() - line.getStartX());

		double x1 = line.getStartX() + arrowSize * Math.cos(angle + Math.toRadians(30));
		double y1 = line.getStartY() + arrowSize * Math.sin(angle + Math.toRadians(30));

		double x2 = line.getStartX() + arrowSize * Math.cos(angle - Math.toRadians(30));
		double y2 = line.getStartY() + arrowSize * Math.sin(angle - Math.toRadians(30));

		Polygon arrowhead = new Polygon(line.getStartX(), line.getStartY(), x1, y1, x2, y2);
		arrowhead.setFill(Color.web("gray"));
		g.getChildren().add(arrowhead);
	}

	public void deleteLines(Group g) {
		g.getChildren().removeIf(node -> node instanceof Line || node instanceof Polygon);
	}

}