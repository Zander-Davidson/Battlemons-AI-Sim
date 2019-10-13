package server;

import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route // localhost:/
@JavaScript("frontend://script.js")
public class MainView extends Div {

	public MainView() {
		getElement().executeJs("greet($0)", "cLiEnT");
	}
}
