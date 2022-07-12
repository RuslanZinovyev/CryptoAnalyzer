package commands;

import model.Result;
import services.ReadService;

public class ReadFileCommand implements Command {
    private final ReadService readService;

    public ReadFileCommand(ReadService readService) {
        this.readService = readService;
    }

    @Override
    public Result execute() {
         readService.readFile().toString();
         return new Result("");
    }
}
