package hu.bme.mit.yakindu.analysis.workhere;

import java.io.IOException;
import java.util.Scanner;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.yakindu.base.types.Event;
import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;
import org.yakindu.sct.model.stext.stext.EventDefinition;
import org.yakindu.sct.model.stext.stext.VariableDefinition;

import hu.bme.mit.model2gml.Model2GML;
import hu.bme.mit.yakindu.analysis.RuntimeService;
import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;
import hu.bme.mit.yakindu.analysis.modelmanager.ModelManager;

public class Main {
	@Test
	public void test() {
		main(new String[0]);
	}
	
	public static void main(String[] args) {
		ModelManager manager = new ModelManager();
		Model2GML model2gml = new Model2GML();
		
		// Loading model
		EObject root = manager.loadModel("model_input/example.sct");
		
		// Reading model
		Statechart s = (Statechart) root;
		
		TreeIterator<EObject> iterator = s.eAllContents();
		int count  = 0;
/*		while (iterator.hasNext()) {
			EObject content = iterator.next();
			if(content instanceof State) {
				State state = (State) content;
				//System.out.println(state.getName());
				for(Transition t: state.getOutgoingTransitions()) {
					System.out.println(t.getSource().getName()+" -> "+t.getTarget().getName());
				}
				if(state.getOutgoingTransitions().size()== 0) {
					System.out.println("Trap state "+ state.getName());
				}
				if(state.getName()== null) {
					System.out.println("No name set for this state.Suggested name NewName"+count);
				}
			}
			count=count+1;
		}*/
		
		//Feladat 4.3
		/*while(iterator.hasNext()) {
			EObject content = iterator.next();
			if(content instanceof VariableDefinition) {
				VariableDefinition var = (VariableDefinition) content;
				System.out.println(var.getName());
			}
			if(content instanceof EventDefinition) {
				EventDefinition event = (EventDefinition) content;
				System.out.println(event.getName());
			}
				
		}*/
		
		//Feladat 4.4
		
		/*System.out.println("public static void print(IExampleStatemachine s) { ");
		while(iterator.hasNext()) {
		EObject content = iterator.next();
		if(content instanceof VariableDefinition) {
			VariableDefinition var = (VariableDefinition) content;
			String variablename = var.getName();
			String getname = variablename.substring(0, 1).toUpperCase().concat(variablename.substring(1));
			System.out.println("  System.out.println(\""+variablename+" = \" + s.getSCInterface().get"+getname+"());");
		}
		}
		System.out.println("}");
		
		*/
		
		
		//Feladat 4.5
		
		System.out.println("public static void main(String[] args) throws IOException {\n ExampleStatemachine s = new ExampleStatemachine();\n s.setTimer(new TimerService());\n RuntimeService.getInstance().registerStatemachine(s, 200);\n s.init();\n s.enter();\n s.runCycle();\n Scanner scanner = new Scanner(System.in);\n while(true) {\n String command = scanner.next();\n switch(command) {\n");
		while(iterator.hasNext()) {
		EObject content = iterator.next();
		if(content instanceof EventDefinition) {
			EventDefinition event = (EventDefinition) content;
			String eventname = event.getName();
			String raisename = eventname.substring(0, 1).toUpperCase().concat(eventname.substring(1));
			System.out.println("				case \""+eventname+"\":");
			System.out.println("					s.raise"+raisename+"();");
			System.out.println("					s.runCycle();\r\n" + 
					"					print(s);\r\n" + 
					"					break;");
		}
		}
		System.out.println("				case \"exit\":\r\n" + 
				"					System.exit(0);\r\n" + 
				"				}\r\n" + 
				"			}\r\n" + 
				"		};");
		
		iterator = s.eAllContents();
		System.out.println("\n\n\n\n");
		System.out.println("public static void print(IExampleStatemachine s) { ");
		while(iterator.hasNext()) {
		EObject content = iterator.next();
		if(content instanceof VariableDefinition) {
			VariableDefinition var = (VariableDefinition) content;
			String variablename = var.getName();
			String getname = variablename.substring(0, 1).toUpperCase().concat(variablename.substring(1));
			System.out.println("  System.out.println(\""+variablename+" = \" + s.getSCInterface().get"+getname+"());");
		}
		}
		System.out.println("}");

		// Transforming the model into a graph representation
		String content = model2gml.transform(root);
		// and saving it
		manager.saveFile("model_output/graph.gml", content);
	}
}
