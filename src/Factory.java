//Factory Design Pattern
import javafx.scene.canvas.GraphicsContext;

interface FactoryIF { 
	GameObject createProduct(String discrim, double x, double y);
}

class Factory implements FactoryIF {

	private GraphicsContext gc;
	
	public Factory(GraphicsContext gc) {
		super();
		this.gc = gc;
	}

	@Override
	public GameObject createProduct(String discrim, double x, double y) {
		switch(discrim){
		case "battery":
			return new Battery(gc,x,y);
		case "bulb":
			return new Bulb(gc,x,y);
		case "switch":
			return new Switch(gc,x,y);
		case "buzzer":
			return new Buzzer(gc,x,y);
		case "motor":
			return new Motor(gc,x,y);
		}
		return new GameObject(gc,0,0);
	}
}