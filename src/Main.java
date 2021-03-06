
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.util.Duration;

import javafx.animation.Animation;
import javafx.animation.Transition;


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

	private int animationStep;


	private boolean solvable;
	
	private TilePane grid;

	private ArrayList<Cell> cells;

	private Text status;	

	// Aqui vai os algoritmos e inicialização;	
	@Override
	public void init() {
		Parameters p = getParameters();
		
		List<String> args = p.getRaw();

		this.searchAlgorithm = null;

		this.animationStep = 0;

		this.solutionPath = null;
		this.search_method_name = "Breadth-Search";	

		this.inputBoard = new Board(args.get(0));
		
		int[][] targetSolution = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};

		this.solutionBoard = new Board(targetSolution);

		this.solvable = inputBoard.isSolvable();	

		status = new Text();
		
		status.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

		cells = new ArrayList<Cell>();

		int[][] rawBoad = inputBoard.getBoard();
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++){
				int value = rawBoad[i][j];
				cells.add(new Cell(value,Color.RED));
			}
		}
	}


    @Override
    public void start(Stage primaryStage) {

	StackPane layout = new StackPane();

	//ArrayList<Rectangle> tiles = new ArrayList<Rectangle>();

	grid = new TilePane();

	grid.setVgap(4);
	grid.setHgap(4);

	grid.setPrefColumns(3);
	grid.setPrefRows(3);

	//grid.setPadding(new Insets(5,5,5,5));

	
	for (int i = 0; i < 9; i++) {
		grid.getChildren().add(cells.get(i).getBody());	
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

	playBtn.setOnAction(new EventHandler<ActionEvent>() {
	 
		@Override
		public void handle(ActionEvent event) {
			final Animation animation = new Transition() {
				{
					//setCycleCount();
					setRate(0.3);	
					setCycleDuration(Duration.seconds(600));	
				}

				@Override
				protected void interpolate(double frac) {
					
					if (animationStep >= 0) {
						updateGrid(solutionPath.get(animationStep));
						animationStep--;
					}

				}
			

			};
			
			animation.play();

		}
	});	


	Button runBtn = new Button("Run");
	runBtn.setPrefSize(240,20);
	runBtn.setOnAction(new EventHandler<ActionEvent>() {
	 
		@Override
		public void handle(ActionEvent event) {
		 
			if (solvable) {
				
				if (search_method_name.equals("Breadth-Search")) {
					searchAlgorithm = new BreadthSearch(inputBoard, solutionBoard); // nao integrei com o codigo do michael ainda [por breadth]	
				} else {
					searchAlgorithm = new DepthSearch(inputBoard, solutionBoard); // nao integrei com o codigo do michael ainda [por breadth]	
				}
					status.setFill(Color.GREEN);
					status.setText("SEARCHING ANSWARE ...");
					
					searchAlgorithm.run();
					solutionPath = searchAlgorithm.getSolutionPath();	
				
					String totalNodes   = Integer.toString(searchAlgorithm.getTotalOfNodes()); 
					String solutioNodes = Integer.toString(solutionPath.size()); 

					animationStep = solutionPath.size() - 1;

					status.setText("Nodos Gerados: " + totalNodes + "\nNodos na Solução: " + solutioNodes ); 


			} else {
				status.setFill(Color.RED);
				status.setText("UNSOLVABLE PUZZLE");
			}	
	    }
        });




	StackPane summary = new StackPane();

	Rectangle summBackroung = new Rectangle(270,120, Color.WHITE);

	summBackroung.setStrokeWidth(2);

    	summBackroung.setStroke(Color.BLACK);

	status = new Text();
	
	//status.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
	//status.setFill(Color.WHITE);

	summary.getChildren().addAll(summBackroung, status);

	menuLayout.getChildren().addAll(grid, rbLayout, runBtn, playBtn, summary);

	
	layout.getChildren().add(menuLayout);	
	

	layout.setMargin(menuLayout, new Insets(10,45,0,45));
	
	Scene scene = new Scene(layout, 320, 500);
        primaryStage.setTitle("8puzzle!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	private void updateGrid(Board b) {
	
		/*
		 *	[Parei aqui]
		 *	No código do michael, quando chama play parece que passa uma board que eh nula
		 * */	
		int[][] rawBoard = b.getBoard();
	
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				cells.get( ( (i * 3) + j) ).setAspect(rawBoard[i][j]);
			}			
		}
	}

 public static void main(String[] args) {
        launch(args);
    }
}
