import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Microbe extends GameObject implements MicrobeIF {
	MicrobeIF i;
	public Microbe(GraphicsContext gc, double x, double y, Image img) {
		super(gc, x, y);
		this.img = img;
		i = new AliveMicrobe(gc,x,y);
		// TODO Auto-generated constructor stub
	}

	public boolean overlap(GameObject check) {
		// TODO Auto-generated method stub
		boolean b1 = check.x > this.x;
		boolean b2 = check.x < this.x + 30;
		boolean b3 = check.y > this.y;
		boolean b4 = check.y < this.y + 30;
		System.out.println(b1+" "+b2+" "+b3+" "+b4);
		return b1 && b2 && b3 && b4;
	}
	
	public void update(){
		i.update();
		super.update();
	}

}
