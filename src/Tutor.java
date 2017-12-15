import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Handles all lessons 
 * @author Kyle Watson
 *
 */
public class Tutor extends GameObject {
	private ArrayList<LessonIF> lessons = new ArrayList<LessonIF>();
	private int curLesson = 0;

	/**
	 * @param gc the {@link GraphicsContext} that the {@link Tutor} will be drawn to
	 * @param x position of the image
	 * @param y position of the image
	 */
	public Tutor(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img = new Image("res/prof.png");
		lessons.add(new Lesson("You can add components to the screen using the buttons on the left, try adding a bulb!"){
			@Override
			public void init() {
				GameState.getGameState().clearComponents();
			}
			
			@Override
			public boolean goalReached() {
				for(GameObject go : GameState.getGameState().getList()){
					if(go instanceof Bulb)
							return true;
				}
				return false;
			}
			
		});
		lessons.add(new Lesson("Now try adding a cell!"){
			@Override
			public void init() {
			}
			
			@Override
			public boolean goalReached() {
				for(GameObject go : GameState.getGameState().getList()){
					if(go instanceof Cell)
							return true;
				}
				return false;
			}
			
		});
		lessons.add(new Lesson("Connect the cell to the bulb and make a complete circuit. You can do this by clicking the positive (red) of one component and then the negative (blue) of another component. What happens to the bulb when you do this?"){
			@Override
			public void init() {
			}
			
			@Override
			public boolean goalReached() {
				for(GameObject go : GameState.getGameState().getList()){
					if(go instanceof Cell){
						Cell c = (Cell)go;
						if(c.power > 0)
							return true;
					}
				}
				return false;
			}
			
		});
		lessons.add(new Lesson("See what happens when you break the circuit by right clicking the bulb or cell and choosing remove left or right wire."){
			@Override
			public void init() {
			}
			
			@Override
			public boolean goalReached() {
				for(GameObject go : GameState.getGameState().getList()){
					if(go instanceof Cell){
						Cell c = (Cell)go;
						if(c.power == 0)
							return true;
					}
				}
				return false;
			}
			
		});
		lessons.add(new Lesson("Now try adding a switch into the circuit and see what happens when the switch is flipped by right clicking it and choosing flip switch."){
			@Override
			public void init() {
			}
			
			@Override
			public boolean goalReached() {
				for(GameObject go : GameState.getGameState().getList()){
					if(go instanceof Switch){
						Switch s = (Switch)go;
						if(s.getEdge(s.x+5, s.y+5) + s.getEdge(s.x+20, s.y+5) ==0 && s.power == 0)
							return true;
					}
				}
				return false;
			}
			
		});
		lessons.add(new Lesson("See how adding more cells to the circuit can change how the bulb and motor act in this circuit!"){
			@Override
			public void init() {
				GameState.getGameState().clearComponents();
				Component b = (Component)Factory.getFactory().createProduct("bulb", 200, 10);
				Component m = (Component)Factory.getFactory().createProduct("motor", 200, 200);
				Component c = (Component)Factory.getFactory().createProduct("cell", 300, 100);
				b.setNode(m, 1);
				m.setNode(c, 1);
				c.setNode(b, 1);
			}
			
			@Override
			public boolean goalReached() {
				for(GameObject go : GameState.getGameState().getList()){
					if(go instanceof Motor){
						Motor m = (Motor)go;
						if(m.power > 12)
							return true;
					}
				}
				return false;
			}
			
		});
		lessons.add(new Lesson("Have a go at building other circuits yourself, try using the buzzer with the switch!"){
			@Override
			public void init() {
			}
			
			@Override
			public boolean goalReached() {
				boolean b1 = false;
				boolean b2 = false;
				for(GameObject go : GameState.getGameState().getList()){
					if(go instanceof Switch){
						Switch s = (Switch)go;
						if(s.getEdge(s.x+5, s.y+5) + s.getEdge(s.x+20, s.y+5) ==0 && s.power == 0)
							b2 = true;
					}else if(go instanceof Buzzer){
						Buzzer b = (Buzzer)go;
						if(b.power > 0)
							b1 = true;
					}
				}
				return b1 && b2;
			}
			
		});
		lessons.get(curLesson).init();
		
		lessons.add(new NullLesson());
	}

	public void update(){
		gc.drawImage(img, x, y);
		LessonIF curLessonObj = lessons.get(curLesson);
		curLessonObj.update(x-180, y+10, gc);
		if(curLessonObj.goalReached()){
			SoundThread.getSoundThread().playSuccess();
			curLesson++;
			lessons.get(curLesson).init();
		}
	}
}
