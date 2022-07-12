package controllers;

import commands.Command;
import model.Result;

public class CaesarController {

    public Result runCommand(Command command) {
        return command.execute();
    }

}
