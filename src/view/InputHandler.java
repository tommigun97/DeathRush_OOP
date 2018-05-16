package view;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.input.KeyCode;
import utilities.Input;

/**
 * This class is responsible for processing the inputs of the user.
 *
 */
public final class InputHandler {

    private static final InputHandler INPUTHANDLER = new InputHandler();

    private boolean w = false;
    private boolean s = false;
    private boolean a = false;
    private boolean d = false;

    private InputHandler() {
    };

    /**
     * Getter of the singleton.
     * 
     * @return The singleton instance of this class.
     */
    public static InputHandler getInputHandler() {
        return InputHandler.INPUTHANDLER;
    }

    /**
     * Getter of the current inputs. This method must be called every frame.
     * 
     * @return A list of the current inputs.
     */
    public List<Input> getList() {
        final List<Input> currentInputList = new LinkedList<>();
        if (this.w) {
            currentInputList.add(Input.W);
        }
        if (this.s) {
            currentInputList.add(Input.S);
        }
        if (this.a) {
            currentInputList.add(Input.A);
        }
        if (this.d) {
            currentInputList.add(Input.D);
        }
        return currentInputList;
    }

    /**
     * This method empties the list of the input. Call this method every time a new
     * game is created.
     * 
     */
    public void emptyList() {
        this.w = false;
        this.s = false;
        this.a = false;
        this.d = false;
    }

    /**
     * This method is called when a user press a key.
     * 
     * @param code
     *            The KeyCode of the pressed key
     */
    void press(final KeyCode code) {
        this.process(code, true);
    }

    /**
     * This method is called when a key is released.
     * 
     * @param code
     *            The KeyCode of the released key.
     */
    void release(final KeyCode code) {
        this.process(code, false);
    }

    /**
     * Private method. It can change the status of a key.
     * 
     * @param code
     *            The KeyCode of the key pressed by the user.
     * @param action
     *            The current status of the key
     */
    private void process(final KeyCode code, final boolean action) {
        if (code == KeyCode.W) {
            this.w = action;
        } else if (code == KeyCode.A) {
            this.a = action;
        } else if (code == KeyCode.S) {
            this.s = action;
        } else if (code == KeyCode.D) {
            this.d = action;
        }

    }

}
