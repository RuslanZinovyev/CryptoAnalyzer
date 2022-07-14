package commands;

import model.Result;
import services.ReadService;

public class ReadCommand implements Command {
    private final ReadService readService;

    public ReadCommand(ReadService readService) {
        this.readService = readService;
    }

    @Override
    public Result execute() {
         readService.readFile();
         return new Result("");
    }
}
