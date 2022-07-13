package commands;

import model.Parameter;
import model.Result;
import services.DecryptionService;
import services.ReadService;
import services.WriteService;

public class DecryptCommand implements Command {
    private final Parameter parameter;
    private final WriteService writeService;

    public DecryptCommand(Parameter parameter) {
        this.parameter = parameter;
        ReadService readService = new ReadService(parameter.getInput());
        DecryptionService decryptionService = new DecryptionService(parameter.getSecurityKey(), readService.readFile());
        writeService = new WriteService(parameter.getOutput(), decryptionService.decryptText());
    }

    @Override
    public Result execute() {
        writeService.writeFile();
        return new Result("The file was decrypted and saved in the following path: " + parameter.getOutput().toPath());
    }
}
