import javafx.scene.canvas.GraphicsContext;

public class DeadMicrobe implements MicrobeIF {
	float age = 0;
	public DeadMicrobe() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public
	void update(){
		if(age > 100){
		}
		age++;
	}

}
