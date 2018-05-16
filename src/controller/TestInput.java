package controller;



import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import javafx.scene.input.KeyCode;
import model.Direction;
import utilities.Input;
import view.InputHandler;

public class TestInput {
	
	/*@Test
	public void test1() {
		Controller controller = new Controller();
		Assert.assertTrue("fail shotUp", controller.translateShot(Input.SHOT_UP).equals(Direction.N));
		Assert.assertTrue("fail shotDown", controller.translateShot(Input.SHOT_DOWN).equals(Direction.S));
		Assert.assertTrue("fail shotLeft", controller.translateShot(Input.SHOT_LEFT).equals(Direction.W));
		Assert.assertTrue("fail shotRight", controller.translateShot(Input.SHOT_RIGHT).equals(Direction.E));
		Assert.assertTrue("fail Nothing", controller.translateShot(Input.D).equals(Direction.NOTHING));
	}
	
	@Test
	public void test2() {
		Controller controller = new Controller();
		List<Direction> list = new LinkedList<>();
		list.add(Direction.N);
		list.add(Direction.S);
		InputHandler in = InputHandler.getInputHandler();
		in.process(KeyCode.W, true);
		in.process(KeyCode.S, true);
		in.process(KeyCode.A, true);
		in.process(KeyCode.UP, true);
		in.process(KeyCode.DOWN, true);
		System.out.println("ciao");
		System.out.println(in.getShotList());
		System.out.println(controller.processInput());
		Assert.assertTrue("ciao", controller.processInput().equals(list));
		
	}*/
}
