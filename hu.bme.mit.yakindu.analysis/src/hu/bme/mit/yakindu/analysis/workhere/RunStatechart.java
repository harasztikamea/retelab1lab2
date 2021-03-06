package hu.bme.mit.yakindu.analysis.workhere;

import java.io.IOException;
import java.util.Scanner;

import hu.bme.mit.yakindu.analysis.RuntimeService;
 import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;



public class RunStatechart {
	
	public static void main(String[] args) throws IOException {
		ExampleStatemachine s = new ExampleStatemachine();
		s.setTimer(new TimerService());
		RuntimeService.getInstance().registerStatemachine(s, 200);
		s.init();
		s.enter();
		s.runCycle();
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String command = scanner.next();
			switch(command) {
			case "black":
				s.raiseBlack();
				s.runCycle();
				print(s);
				break;
			case "start":
				s.raiseStart();
				s.runCycle();
				print(s);
				break;
			case "white":
				s.raiseWhite();
				s.runCycle();
				print(s);
				break;
			case "exit":
				System.exit(0);
			}
		}
	}

	public static void print(IExampleStatemachine s) {
		System.out.println("W = " + s.getSCInterface().getWhiteTime());
		System.out.println("B = " + s.getSCInterface().getBlackTime());
	}

}
