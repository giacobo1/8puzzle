
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

import javafx.scene.control.*;
import javafx.scene.control.RadioButton;
import javafx.beans.value.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.application.Application.Parameters;

import java.util.List;


import javafx.beans.*;




public class Main extends Application {

	private Board inputBoard;
	private Board solutionBoard;
	private Search searchAlgorithm;

	private ArrayList<Board> solutionPath;

	private String search_method_name;


	private boolean solvable;

	private Text status;	

	// Aqui vai os algoritmos e inicialização;	
	@Override
	public void init() {
		Parameters p = getParameters();
		
		List<String> args = p.getRaw();
	
		this.searchAlgorithm = null;

		this.solutionPath = null;
		this.search_method_name = null;	

		this.inputBoard = new Board(args.get(0));
		
		int[][] targetSolution = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};

		this.solutionBoard = new Board(targetSolution);

		this.solvable = inputBoard.isSolvable();	

		status = new Text();
		
		status.setFont(Font.font("Verdana", FontWeight.BOLD, 18));


	}


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
	breadth.setSelected(true);

	tgroup.selectedToggleProperty().addListener( new ChangeListener<Toggle>() {
		@Override
		public void changed(ObservableValue <? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

					
			RadioButton chk = (RadioButton)new_toggle.getToggleGroup().getSelectedToggle();
			search_method_name = chk.getText(); 
			    
			//System.out.format("%s\n", chk.getText());
		}
	});

	rbLayout.getChildren().addAll(depthBtn, breadth);

	Button playBtn = new Button("Play");
	playBtn.setPrefSize(240,20);
	
	Button runBtn = new Button("Run");
	runBtn.setPrefSize(240,20);
	runBtn.setOnAction(new EventHandler<ActionEvent>() {
	 
		@Override
		public void handle(ActionEvent event) {
		 
			if (solvable) {
				
				if (search_method_name.equals("Breadth-Search")) {
					searchAlgorithm = new DepthSearch(inputBoard, solutionBoard); // nao integrei com o codigo do michael ainda [por breadth]	
				} else {
					searchAlgorithm = new DepthSearch(inputBoard, solutionBoard); // nao integrei com o codigo do michael ainda [por breadth]	
				}
					status.setFill(Color.GREEN);
					status.setText("SEARCHING ANSWARE ...");
					
					searchAlgorithm.run();
					solutionPath = searchAlgorithm.getSolutionPath();	
				
					String totalNodes   = Integer.toString(searchAlgorithm.getTotalOfNodes()); 
					String solutioNodes = Integer.toString(solutionPath.size()); 


					status.setText("Nodos Gerados: " + totalNodes + "\nNodos na Solução: " + solutioNodes ); 


			} else {
				status.setFill(Color.RED);
				status.setText("UNSOLVABLE PUZZLE");
			}	
	    }
        });




	StackPane summary = new StackPane();

	Rectangle summBackroung = new Rectangle(270,120, Color.BLUE);

	status = new Text();
	
	status.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
	status.setFill(Color.WHITE);

	summary.getChildren().addAll(summBackroung, status);

	menuLayout.getChildren().addAll(grid, rbLayout, runBtn, playBtn, summary);

	
	layout.getChildren().add(menuLayout);	
	

	layout.setMargin(menuLayout, new Insets(10,45,0,45));
	
	Scene scene = new Scene(layout, 320, 500);
        primaryStage.setTitle("8puzzle!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



 public static void main(String[] args) {
        launch(args);
    }
}
