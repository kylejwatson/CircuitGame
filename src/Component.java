import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.paint.Color;

/**
 * Super class for all elements mimicking parts of a circuit
 * @author Kyle Watson
 *
 */
public abstract class Component extends GameObject {

	private Component nextComponent;
	private Component prevComponent;
	protected float power;
	protected ContextMenu contextMenu = new ContextMenu();
	private PowerConsumption powerConsumption;

	/**
	 * @param gc the {@link GraphicsContext} that the component will be drawn to
	 * @param x position of the image
	 * @param y position of the image
	 */
	public Component(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		Component thisComp = this;
		MenuItem moveButton = new MenuItem("Move");
		moveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GameState.getGameState().setHeldComponent(thisComp);
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
				if(prevComponent != null){
					prevComponent.nextComponent = null;
					prevComponent.resetPower();
					prevComponent = null;
				}
				if(nextComponent != null){
					nextComponent.prevComponent = null;
					nextComponent.resetPower();
					nextComponent = null;
				}
				GameState.getGameState().removeGameObject(thisComp);
			}
		});


		// Add MenuItem to ContextMenu
		contextMenu.getItems().addAll(moveButton, deleteLeftButton,deleteRightButton,deleteButton);
	}

	
	/**
	 * Links this with {@code comp} so they can be considered in the same circuit
	 * @param comp to link with 
	 * @param select 1 will link this right to {@code comp}s left and 2 will do vice-versa
	 */
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

	/**
	 * Gets the side which the point lands on with some leeway by a few pixels
	 * @param x value to check
	 * @param y value to check
	 * @return the side the point hit, 1 is left and 2 is right
	 */
	public int getEdge(double x, double y){
		boolean yVal = y > this.y && y < this.y + 30;
		if(prevComponent == null && x > this.x -10 && x < this.x+15 && yVal)
			return 2;
		else if(nextComponent == null && x > this.x +15 && x < this.x +40 && yVal)
			return 1;
		
		return 0;
	}

	
	/**
	 * Sets the power for this and any other {@link Component}s in the same circuit
	 * @param power value to set for the circuit
	 */
	private void setPower(float power){
		System.out.println(getClass() + " a " +power);
		if(power != this.power){
			System.out.println(getClass() + " b " +power);
			this.power = power;
			if(nextComponent != null)
				nextComponent.setPower(power);
		}
	}
	
	/**
	 * Calculates and then sets the power for the entire circuit
	 */
	protected void resetPower(){
		if(nextComponent == null || prevComponent == null){
			setPower(0);
		}else{
			calculatePower(0,null);
		}
	}

	/**
	 * Recursive method to get the power of the circuit
	 * @param curPower the cumulative power (should be set to 0 to start)
	 * @param startNode the {@link Component} to start and end on in the loop
	 * @return the final calculated power
	 */
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
		if(nextComponent != null)
			return nextComponent.calculatePower(curPower, startNode);
		else
			return 0f;
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

	/**
	 * Handles right click events by showing a {@link ContextMenu}
	 * @param event that was fired
	 */
	public void rightClick(ContextMenuEvent event){
		if(isClicked(event.getX(), event.getY()))
			contextMenu.show(gc.getCanvas(),event.getScreenX(),event.getSceneY());;
	}
	
	/**
	 * Manipulates the current power in the calculations
	 * @param curPower cumulative power in the circuit
	 * @return cumulative power after manipulation
	 */
	private float consumePower(float curPower){
		return powerConsumption.consumePower(curPower);
	}
	
	/**
	 * Sets how power is manipulated when calculated
	 * @param powerConsumption
	 */
	protected void setPowerConsumption(PowerConsumption powerConsumption){
		this.powerConsumption = powerConsumption;
	}
	
	/**
	 * Checks if the point lands on the component
	 * @param x position of the point
	 * @param y position of the point
	 * @return if the point hit
	 */
	public boolean isClicked(double x, double y){
		if(x > this.x && x < this.x+30 && y > this.y && y < this.y + 30)
			return true;
		return false;

	}

}
