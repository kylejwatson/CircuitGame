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
	Button batteryButton;
	Button mateButton;
	GraphicsContext gc;
	ArrayList<GameObject> list = new ArrayList<GameObject>();
	Component selected;
	GameObject holding;
	int select;
	String discrim;
	float power;
	Component head;
	Random rnd = new Random(System.currentTimeMillis());
	int count=0;
	AnimationTimer timer = new AnimationTimer() {

		@Override
		public void handle(long now) {
			gc.setFill(Color.WHITE);
			gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
			for(GameObject obj : list)
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
		
		batteryButton = new Button();
		batteryButton.setText("Add Battery");
		batteryButton.setLayoutX(10);
		batteryButton.setLayoutY(50);
		root.getChildren().add(batteryButton);
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
		list.add(factory.createProduct("bulb", 100, 100));
		list.add(factory.createProduct("bulb", 300, 100));
		list.add(factory.createProduct("bulb", 200, 200));
		list.add(factory.createProduct("battery", 200, 10));
		
		EventHandler<ActionEvent> batteryHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				holding = factory.createProduct("battery", 0, 0);
				list.add(holding);
				discrim = "holding";
			}
		};
		batteryButton.setOnAction(batteryHandler);
		EventHandler<ActionEvent> mateHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			}
		};
		mateButton.setOnAction(mateHandler);
		EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent mouseEvent){
				if(discrim == "holding"){
					discrim = "none";
				}else if(discrim == "none"){
					for(GameObject go : list){
						if(go instanceof Component){
							Component comp = (Component)go;
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
								System.out.println(edgeLeft +" " + edgeRight);
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
		};
		canvas.setOnMouseClicked(clickHandler);
		EventHandler<MouseEvent> moveHandler = new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				System.out.println(discrim);
				if(discrim == "holding"){
					holding.x = event.getX();
					holding.y = event.getY();
				}
				
			}
			
		};
		canvas.setOnMouseMoved(moveHandler);
		timer.start();

	}
}