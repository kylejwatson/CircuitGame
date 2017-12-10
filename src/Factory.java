import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

interface FactoryIF { 
	GameObject createProduct(String discrim, double x, double y);
}

class Factory implements FactoryIF {

	GraphicsContext gc;
	
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
		}
		return new GameObject(gc,0,0);
	}
}