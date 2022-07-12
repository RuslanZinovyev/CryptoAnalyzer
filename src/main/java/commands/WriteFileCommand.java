package commands;

import model.Result;
import services.WriteService;

public class WriteFileCommand implements Command {
    private final WriteService writeService;

    public WriteFileCommand(WriteService writeService) {
        this.writeService = writeService;
    }

    @Override
    public Result execute() {
        writeService.writeFile();
        return new Result("");
    }
}
