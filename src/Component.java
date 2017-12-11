import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.paint.Color;

public abstract class Component extends GameObject {

	protected Component nextComponent;
	protected Component prevComponent;
	protected float power;
	protected ContextMenu contextMenu = new ContextMenu();
	protected PowerCons powerCons;
	public Component(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		Component thisComp = this;
		MenuItem moveButton = new MenuItem("Move");
		moveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GameState.setHeldComponent(thisComp);
			}
		});
		MenuItem deleteLeftButton = new MenuItem("< Remove Left Wire");
		deleteLeftButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(prevComponent != null){
					prevComponent.nextComponent = null;
					prevComponent.resetPower();
					prevComponent = null;
					resetPower();
				}
			}
		});
		MenuItem deleteRightButton = new MenuItem("Remove Right Wire >");
		deleteRightButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(nextComponent != null){
					nextComponent.prevComponent = null;
					nextComponent.resetPower();
					nextComponent = null;
					resetPower();
					
				}
			}
		});
		MenuItem deleteButton = new MenuItem("Delete");
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GameState.getList().remove(thisComp);
			}
		});


		// Add MenuItem to ContextMenu
		contextMenu.getItems().addAll(moveButton, deleteLeftButton,deleteRightButton,deleteButton);
	}

	public void setNode(Component comp, int select ){
		if(comp != this){
			if(select == 2 && prevComponent == null && comp.nextComponent == null){
				comp.nextComponent = this;
				prevComponent = comp;
				comp.resetPower();
			}else if(select == 1 && nextComponent == null && comp.prevComponent == null){
				comp.prevComponent = this;
				nextComponent = comp;
				comp.resetPower();
			}
		}
	}

	public boolean getEdge(double x, double y, int select){
		boolean yVal = y > this.y && y < this.y + 30;
		if(select == 1){
			if(x > this.x -10 && x < this.x+15 && yVal ){
				return true;
			}
		}else if(x > this.x +15 && x < this.x +40 && yVal){
			return true;
		}
		return false;
	}

	public void setPower(float power){
		System.out.println(getClass() + " a " +power);
		if(power != this.power){
			System.out.println(getClass() + " b " +power);
			this.power = power;
			if(nextComponent != null)
				nextComponent.setPower(power);
		}
	}
	
	public void resetPower(){
		if(nextComponent == null || prevComponent == null){
			setPower(0);
		}else{
			calculatePower(0,null);
		}
	}

	protected float calculatePower(float curPower, Component startNode){
		if(this == startNode){
			System.out.println(curPower + " " + this.getClass());
			if(curPower < 0)
				curPower = 0;
			setPower(curPower);
			return curPower;
		}else{
			if(startNode == null)
				startNode = this;
			curPower = consumePower(curPower);
		}
		System.out.println(getClass() + " " +curPower);
		return nextComponent.calculatePower(curPower, startNode);
	}

	public void update(){
		super.update();
		gc.setFill(Color.RED);
		gc.fillRect(x-9,y+12, 9, 6);
		gc.setFill(Color.BLUE);
		gc.fillRect(x+30, y+12, 9, 6);
		if(nextComponent != null){
			if(power != 0){
				gc.setLineWidth(3);
				gc.setStroke(Color.YELLOW);
				gc.strokeLine(x + 39, y+15, nextComponent.x-9, nextComponent.y+15);
				gc.setStroke(Color.BLACK);
				gc.setLineWidth(1);
			}
			gc.strokeLine(x + 39, y+15, nextComponent.x-9, nextComponent.y+15);
		}
	}

	public void rightClick(ContextMenuEvent event){
		if(click(event.getX(), event.getY()))
			contextMenu.show(gc.getCanvas(),event.getScreenX(),event.getSceneY());;
	}
	
	private float consumePower(float curPower){
		return powerCons.consumePower(curPower);
	}
	
	protected void setPowerCons(PowerCons powerCons){
		this.powerCons = powerCons;
	}
	
	public boolean click(double x, double y){
		if(x > this.x && x < this.x+30 && y > this.y && y < this.y + 30)
			return true;
		return false;

	}

}
