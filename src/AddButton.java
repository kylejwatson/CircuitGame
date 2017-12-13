import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class AddButton extends Button {
	
	public AddButton(String text,float x, float y) {
		super();
		String fText = text;
		EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Factory.getFactory().createProduct(fText, 0, 0);
			}
		};
		setOnAction(buttonHandler);
		text = "Add " + text.substring(0,1).toUpperCase() + text.substring(1);
		setText(text);
		setLayoutX(x);
		setLayoutY(y);
	}

}
