import javafx.scene.canvas.GraphicsContext;

public abstract class Component extends GameObject {

	Component nextComponent;
	Component prevComponent;
	float power;
	public Component(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void setNode(Component comp, int select ){
		if(select == 2 && prevComponent == null && comp.nextComponent == null){
			comp.nextComponent = this;
			prevComponent = comp;
		}else if(select == 1 && nextComponent == null && comp.prevComponent == null){
			comp.prevComponent = this;
			nextComponent = comp;
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
	
	public void rightClick(double x, double y){
		//add a right click menu for deleting moving or destroying connections
	}

}
