import javafx.scene.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;

public class Cell {

	private int value;
	private StackPane body;
	private Text content;
	private Rectangle background;	

	public Cell(int v, Color c) {
	
		this.value   	= v;
		this.body   	= new StackPane();
		
		if (v != 0) {
			this.content 	= new Text(Integer.toString(v));
			this.content.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
			this.content.setFill(Color.WHITE);
			this.background = new Rectangle(80.0, 80.0, c);
		} else {
			this.content 	= new Text();
			this.background = new Rectangle(80.0, 80.0, Color.WHITE);
		}

		body.getChildren().addAll(background, content);
		body.setAlignment(Pos.CENTER);
		//body.setMargin(content, new Insets(0, 10, 0, 0));

	}

	public StackPane getBody() {
		return this.body;
	}

	public void setAspect(int v) {
		
		if (v != 0) {
			
			background.setFill(Color.RED);
			content.setText(Integer.toString(v));
			content.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
			content.setFill(Color.WHITE);

		} else {
			
			background.setFill(Color.WHITE);
		}

	}

}
