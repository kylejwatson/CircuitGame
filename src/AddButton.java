import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Button to access the {@link Factory} in order to add new {@link Component}s to the display list in {@link GameState}
 * @author Kyle Watson
 */
public class AddButton extends Button {
	
	/**
	 * Creates a button with label Add {@code text} where {@code text} is is capitalised appropriatley. 
	 * An appropriate event handler to create a {@link Component} of type {@code text} is also created.
	 * @param text the text on the button.
	 * @param x position of the button.
	 * @param y position of the button.
	 */
	public AddButton(String text,float x, float y) {
		super();
		String fText = text;
		EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Component co = (Component)Factory.getFactory().createProduct(fText, 0, 0);
				GameState.getGameState().setHeldComponent(co);
				
			}
		};
		setOnAction(buttonHandler);
		text = "Add " + text.substring(0,1).toUpperCase() + text.substring(1);
		setText(text);
		setLayoutX(x);
		setLayoutY(y);
	}

}
