
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
			}
			if(selected != null){
				gc.setFill(Color.BLUE);
				if(select == 2){
					gc.fillOval(selected.x-5, selected.y+12, 6, 6);
				}else{
					gc.fillOval(selected.x+25, selected.y+12, 6, 6);
				}
			}
		} 
	};
	EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>(){
		@Override
		public void handle(MouseEvent mouseEvent){
			if(GameState.holding || mouseEvent.getButton() == MouseButton.SECONDARY){
				GameState.holding = false;
			}else if(!GameState.holding){
				for(GameObject go : GameState.list){
					if(go instanceof Component){
						Component comp = (Component)go;
						comp.contextMenu.hide();
						if(selected != null){
							boolean edge = comp.getEdge(mouseEvent.getX(), mouseEvent.getY(), select);
							if(edge){
								selected.setNode(comp,select);
								selected = null;
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
	};
	EventHandler<ContextMenuEvent> contextHandler = new EventHandler<ContextMenuEvent>(){

		@Override
		public void handle(ContextMenuEvent event) {
			for(GameObject go : GameState.list){
				if(go instanceof Component){
					((Component) go).rightClick(event);
				}
			}
		}
		
	};
	EventHandler<MouseEvent> moveHandler = new EventHandler<MouseEvent>(){
		@Override
		public void handle(MouseEvent event) {
			if(GameState.holding){
				GameState.go.x = event.getX();
				GameState.go.y = event.getY();
			}			
		}
	};
	ChangeListener<Number> sliderHandler = new ChangeListener<Number>(){

		@Override
		public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
			GameState.setVolume(new_val.floatValue());
		}
		
	};
	
	public static void main(String[] args) {
		launch(args);
	}	

	@Override
	public void start(Stage stage) throws Exception {
		root=new Pane();
		scene=new Scene(root,800,600);
		stage.setScene(scene);
		stage.show();

		root.getChildren().addAll(new AddButton("battery",10, 50),new AddButton("switch",10, 80)
				,new AddButton("buzzer",10, 110), new AddButton("motor",10,140));
		canvas = new Canvas(600,600);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
		canvas.setLayoutX(200);
		root.getChildren().add(canvas);
		
		GameState.factory = new Factory(gc);
		GameState.list.add(GameState.factory.createProduct("bulb", 100, 100));
		GameState.list.add(GameState.factory.createProduct("bulb", 300, 100));
		GameState.list.add(GameState.factory.createProduct("bulb", 200, 200));
		GameState.list.add(GameState.factory.createProduct("battery", 200, 10));
		
		canvas.setOnMouseClicked(clickHandler);
		canvas.setOnContextMenuRequested(contextHandler);
		canvas.setOnMouseMoved(moveHandler);
		Label label = new Label("Volume:");
		label.setLayoutX(10);
		label.setLayoutY(10);
		Slider slider = new Slider();
		slider.setMin(0);
		slider.setMax(100);
		slider.setValue(100);
		slider.setLayoutX(10);
		slider.setLayoutY(30);
		slider.valueProperty().addListener(sliderHandler);
		GameState.setVolume(100);
		
		//slider.set
		root.getChildren().addAll(slider,label);
		timer.start();

	}
}