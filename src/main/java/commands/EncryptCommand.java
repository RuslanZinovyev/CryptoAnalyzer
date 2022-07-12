package commands;

import model.Parameter;
import model.Result;
import services.EncryptionService;
import services.ReadService;
import services.WriteService;

public class EncryptCommand implements Command {
    private final Parameter parameter;
    private final WriteService writeService;

    public EncryptCommand(Parameter parameter) {
        this.parameter = parameter;
        ReadService readService = new ReadService(parameter.getInput());
        EncryptionService encryptionService = new EncryptionService(parameter.getSecurityKey(), readService.readFile());
        writeService = new WriteService(parameter.getOutput(), encryptionService.encryptText());
    }

    @Override
    public Result execute() {
        writeService.writeFile();
        return new Result("The file was encrypted and saved in the following path: " + parameter.getOutput().toPath());
    }

}
