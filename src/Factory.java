//Factory Design Pattern
import javafx.scene.canvas.GraphicsContext;

interface FactoryIF { 
	GameObject createProduct(String discrim, double x, double y);
}

class Factory implements FactoryIF {

	private GraphicsContext gc;
	private static Factory instance;
	private Factory() {
		super();
	}

	public static Factory getFactory(){
		if(instance == null)
			instance = new Factory();
		return instance;
	}
	public void setGraphicsContext(GraphicsContext gc){
		this.gc = gc;
	}
	@Override
	public GameObject createProduct(String discrim, double x, double y) {
		Component co = null;
		switch(discrim){//add to gamestate holding 
		case "cell":
			co = new Cell(gc,x,y);
			break;
		case "bulb":
			co = new Bulb(gc,x,y);
			break;
		case "switch":
			co = new Switch(gc,x,y);
			break;
		case "buzzer":
			co = new Buzzer(gc,x,y);
			break;
		case "motor":
			co = new Motor(gc,x,y);
			break;
		}
		if(co != null){
			GameState.getGameState().setHeldComponent(co);
			GameState.getGameState().getList().add(co);
			return co;
		}
		return new GameObject(gc,0,0);
	}
}