
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class EvolutionGame extends Application {
	Pane root;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
	Component selected;
	int select;
	float power;
	Component head;
	Random rnd = new Random(System.currentTimeMillis());
	int count=0;
	AnimationTimer timer = new AnimationTimer() {

		@Override
		public void handle(long now) {
			gc.setFill(Color.WHITE);
			gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
			for(GameObject obj : GameState.list)
			{
				obj.update();
				if(obj instanceof Component){
					Component comp = (Component) obj;
					if(comp.nextComponent != null)
						gc.strokeLine(comp.x + 30, comp.y+15, comp.nextComponent.x, comp.nextComponent.y+15);
					if(comp.prevComponent != null){
						gc.strokeLine(comp.x , comp.y+15, comp.prevComponent.x+30, comp.prevComponent.y+15);
					}
					
				}
			}
			if(selected != null){
				gc.setFill(Color.BLUE);
				if(select == 1){
					gc.fillRect(selected.x, selected.y+15, 5, 5);
				}else{
					gc.fillRect(selected.x+30, selected.y+15, 5, 5);
				}
			}
		} };
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		root=new Pane();
		scene=new Scene(root,800,600);
		stage.setScene(scene);
		stage.show();

		root.getChildren().add(new AddButton("battery",10, 50));
		root.getChildren().add(new AddButton("switch",10, 80));
		root.getChildren().add(new AddButton("buzzer",10, 110));
		canvas = new Canvas(700,600);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
		canvas.setLayoutX(100);
		root.getChildren().add(canvas);
		
		GameState.factory = new Factory(gc);
		GameState.list.add(GameState.factory.createProduct("bulb", 100, 100));
		GameState.list.add(GameState.factory.createProduct("bulb", 300, 100));
		GameState.list.add(GameState.factory.createProduct("bulb", 200, 200));
		GameState.list.add(GameState.factory.createProduct("battery", 200, 10));
		
		EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent mouseEvent){
				if(GameState.discrim == "holding"){
					GameState.discrim = "none";
				}else if(GameState.discrim == "none"){
					for(GameObject go : GameState.list){
						if(go instanceof Component){
							Component comp = (Component)go;
							if(mouseEvent.getButton() == MouseButton.SECONDARY){
								comp.rightClick(mouseEvent.getX(), mouseEvent.getY());
								
								comp.calculatePower(0,null);
							}else{
								if(selected != null){
									boolean edge = comp.getEdge(mouseEvent.getX(), mouseEvent.getY(), select);
									if(edge){
										selected.setNode(comp,select);
										selected = null;
										comp.calculatePower(0,null);
									}
								}else{
									boolean edgeLeft = comp.getEdge(mouseEvent.getX(), mouseEvent.getY(), 1);
									boolean edgeRight = comp.getEdge(mouseEvent.getX(), mouseEvent.getY(), 2);
									if(edgeLeft){
										selected = comp;
										select = 2;
									}else if(edgeRight){
										selected = comp;
										select = 1;
									}
								}
							}
						}
					}
				}
			}
		};
		canvas.setOnMouseClicked(clickHandler);
		EventHandler<MouseEvent> moveHandler = new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				if(GameState.discrim == "holding"){
					GameState.go.x = event.getX();
					GameState.go.y = event.getY();
				}
				
			}
			
		};
		canvas.setOnMouseMoved(moveHandler);
		timer.start();

	}
}