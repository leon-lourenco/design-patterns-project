package com.designpatterns.behavioral.command.classic;

/**
 * The remote never knows it's controlling a Light specifically - it only knows it holds a
 * Command. That's what lets the same remote support undo for any command, and support a
 * completely different appliance later without changing a line here.
 */
public class RemoteControl {

    private Command lastCommand;

    public String pressButton(Command command) {
        String result = command.execute();
        lastCommand = command;
        return result;
    }

    public String pressUndo() {
        if (lastCommand == null) {
            return "Nothing to undo";
        }
        return lastCommand.undo();
    }
}
