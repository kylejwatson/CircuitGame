import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class EvolutionGame extends Application {
	Pane root;
	Scene scene;
	Canvas canvas;
	Button addCatButton;
	Button mateButton;
	GraphicsContext gc;
	ArrayList<GameObject> list = new ArrayList<GameObject>();
	ArrayList<Cat> catList = new ArrayList<Cat>();
	AnimalIF[] selected = new AnimalIF[2];
	int select;
	Random rnd = new Random(System.currentTimeMillis());
	int count=0;
	AnimationTimer timer = new AnimationTimer() {

		@Override
		public void handle(long now) {
			gc.setFill(Color.WHITE);
			gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
			if(selected[0] != null){
				gc.setFill(Color.BLUE);
				gc.fillRect(selected[0].getX()-5,selected[0].getY()-5,110,80);
			}if(selected[1] != null){
				gc.setFill(Color.BLUE);
				gc.fillRect(selected[1].getX()-5,selected[1].getY()-5,110,80);
			}
			for(GameObject obj : list)
			{
				obj.update();
			}
		} };
		
	Factory factory;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		root=new Pane();
		scene=new Scene(root,800,600);
		stage.setScene(scene);
		stage.show();

		addCatButton = new Button();
		addCatButton.setText("Add Cat");
		addCatButton.setLayoutX(10);
		addCatButton.setLayoutY(50);
		root.getChildren().add(addCatButton);
		mateButton = new Button();
		mateButton.setText("Mate");
		mateButton.setLayoutX(10);
		mateButton.setLayoutY(80);
		root.getChildren().add(mateButton);		
		
		canvas = new Canvas(700,600);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
		canvas.setLayoutX(100);
		root.getChildren().add(canvas);
		
		factory = new Factory(gc);
		
		EventHandler<ActionEvent> catHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				list.add(factory.createProduct("cat", Math.random()*700, Math.random()*600));
				catList.add((Cat)list.get(list.size()-1));
			}
		};
		addCatButton.setOnAction(catHandler);
		EventHandler<ActionEvent> mateHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(selected[1] != null){
					Cat cat = (Cat)selected[0].clone();
					cat.inherit(selected[1]);
					list.add(cat);
					catList.add(cat);
				}
			}
		};
		mateButton.setOnAction(mateHandler);
		EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent mouseEvent){
				System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
				for(Cat cat : catList){
					if(cat.pointCollides(mouseEvent.getX(), mouseEvent.getY())){
						if(selected[0] != cat && selected[1] != cat){
							selected[select] = cat;
							if(select == 1)
								select = 0;
							else
								select = 1;
						}
					}
				}
			}
		};
		canvas.setOnMouseClicked(clickHandler);
		timer.start();

	}
}



