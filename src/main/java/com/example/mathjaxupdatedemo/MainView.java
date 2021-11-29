package com.example.mathjaxupdatedemo;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "")
@JavaScript("https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-chtml.js")
public class MainView extends VerticalLayout {
	
	private Div content;
	
	private Button loadJsButton;
	
	
	public MainView() {
		content = new Div();
		loadJsButton = new Button("Load JS");
	}
	
	@PostConstruct
	void init() {
		content.add("\\(\\int_0^\\infty \\frac{x^3}{e^x-1}\\,dx = \\frac{\\pi^4}{15}\\)");
		
		content.addAttachListener(new ComponentEventListener<AttachEvent>() {
			/* Once the description is on the webpage, use MathJax's JavaScript
			 * to format the math if necessary. */
			@Override
			public void onComponentEvent(AttachEvent event) {
				final UI ui = UI.getCurrent();
				if (ui != null) {
					ui.getPage().executeJs("MathJax.typeset(null);");
				}
			}
		});
//		loadJsButton.addClickListener((e) -> {
//			final UI ui = UI.getCurrent();
//			if (ui != null) {
//				ui.getPage().executeJs("console.log('Running MathJax');");
//				ui.getPage().executeJs("MathJax.typeset(null);");
//				
//			}
//		});
		content.add(loadJsButton);
		add(content);
	}

}
