package commands;

import model.Result;
import services.WriteService;

public class WriteCommand implements Command {
    private final WriteService writeService;

    public WriteCommand(WriteService writeService) {
        this.writeService = writeService;
    }

    @Override
    public Result execute() {
        writeService.writeFile();
        return new Result("");
    }
}
