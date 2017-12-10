import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Window;

public abstract class Component extends GameObject {

	Component nextComponent;
	Component prevComponent;
	float power;
	ContextMenu contextMenu = new ContextMenu();
	
	public Component(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		MenuItem item1 = new MenuItem("Menu Item 1");
	     item1.setOnAction(new EventHandler<ActionEvent>() {

	         @Override
	         public void handle(ActionEvent event) {
	             //label.setText("Select Menu Item 1");
	         }
	     });
	     MenuItem item2 = new MenuItem("Menu Item 2");
	     item2.setOnAction(new EventHandler<ActionEvent>() {

	         public void handle(ActionEvent event) {
	             //label.setText("Select Menu Item 2");
	         }
	     });

	     // Add MenuItem to ContextMenu
	     contextMenu.getItems().addAll(item1, item2);
	}
	
	public void setNode(Component comp, int select ){
		if(comp != this){
			if(select == 2 && prevComponent == null && comp.nextComponent == null){
				comp.nextComponent = this;
				prevComponent = comp;
			}else if(select == 1 && nextComponent == null && comp.prevComponent == null){
				comp.prevComponent = this;
				nextComponent = comp;
			}
		}
	}
	
	public boolean getEdge(double x, double y, int select){
		boolean yVal = y > this.y && y < this.y + 30;
		if(select == 1){
			if(x > this.x -10 && x < this.x+15 && yVal ){
				return true;
			}
		}else if(x > this.x +15 && x < this.x +30 && yVal){
			return true;
		}
		return false;
	}
	
	public void setPower(float power){
		if(power != this.power){
			this.power = power;
			nextComponent.setPower(power);
		}
	}
	
	public float calculatePower(float curPower, Component startNode){
		if(nextComponent == null || prevComponent == null)
			return 0;
		if(this == startNode){
			System.out.println(curPower + " " + this.getClass());
			if(curPower < 0)
				curPower = 0;
			setPower(curPower);
			return curPower;
		}else if(startNode == null){
			startNode = this;
		}
		return nextComponent.calculatePower(curPower, startNode);
	}
	
	public void update(){
		super.update();
		if(nextComponent != null){
			gc.setFill(Color.BLACK);
			gc.fillRect(x+30, y+12, 9, 6);
			if(power != 0)
				gc.setStroke(Color.RED);
			else
				gc.setStroke(Color.BLACK);
			gc.strokeLine(x + 39, y+15, nextComponent.x-9, nextComponent.y+15);

			gc.fillRect(nextComponent.x-9, nextComponent.y+12, 9, 6);
		}
	}
	
	public void rightClick(ContextMenuEvent event){
		//add a right click menu for deleting moving or destroying connections
		if(click(event.getX(), event.getY()))
			contextMenu.show(gc.getCanvas(),event.getScreenX(),event.getSceneY());;
	}
	
	public boolean click(double x, double y){
		if(x > this.x && x < this.x+30 && y > this.y && y < this.y + 30)
			return true;
		return false;
		
	}

}
