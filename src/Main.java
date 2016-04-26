
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

import javafx.scene.layout.TilePane;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.paint.Color;

import javafx.geometry.Insets;

import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;

import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {

	StackPane layout = new StackPane();

	//ArrayList<Rectangle> tiles = new ArrayList<Rectangle>();

	TilePane grid = new TilePane();

	grid.setVgap(4);
	grid.setHgap(4);

	grid.setPrefColumns(3);
	grid.setPrefRows(3);

	//grid.setPadding(new Insets(5,5,5,5));

	for (int i = 0; i < 9; i++) {
		Rectangle tmp = new Rectangle(80.0,80.0, Color.RED);
		
		grid.getChildren().add(tmp);	
	}

	

	VBox menuLayout = new VBox();	
	
	//menuLayout.setPadding(new Insets(5));
	menuLayout.setSpacing(10);

	HBox rbLayout = new HBox();
	rbLayout.setSpacing(10);

	ToggleGroup tgroup = new ToggleGroup();

	RadioButton depthBtn = new RadioButton("Depth-Search");
	depthBtn.setToggleGroup(tgroup);

	RadioButton breadth = new RadioButton("Breadth-Search");
	breadth.setToggleGroup(tgroup);

	rbLayout.getChildren().addAll(depthBtn, breadth);

	Button playBtn = new Button("Play");
	playBtn.setPrefSize(240,20);

	StackPane summary = new StackPane();

	Rectangle summBackroung = new Rectangle(270,120, Color.BLUE);

	Text status = new Text("Aqui vai o:\n sumario");
	
	status.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
	status.setFill(Color.WHITE);

	summary.getChildren().addAll(summBackroung, status);

	menuLayout.getChildren().addAll(grid, rbLayout, playBtn, summary);

	
	layout.getChildren().add(menuLayout);	
	

	layout.setMargin(menuLayout, new Insets(10,45,0,45));
	
	Scene scene = new Scene(layout, 320, 480);
        primaryStage.setTitle("8puzzle!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



 public static void main(String[] args) {
        launch(args);
    }
}
