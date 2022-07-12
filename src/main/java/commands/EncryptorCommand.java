package commands;

import model.Parameter;
import model.Result;
import services.EncryptionService;
import services.ReadService;
import services.WriteService;

public class EncryptorCommand implements Command {

    private Parameter parameter;
    private ReadService readService;
    private EncryptionService encryptionService;
    private WriteService writeService;

    public EncryptorCommand(Parameter parameter) {
        this.parameter = parameter;
        readService = ReadService.getInstance(parameter.getInput());
        encryptionService = EncryptionService.getInstance(parameter.getSecurityKey(), readService.readFile());
        writeService = WriteService.getInstance(parameter.getOutput(), encryptionService.encryptText());
    }

    @Override
    public Result execute() {
        writeService.writeFile();
        return new Result("The file was encrypted and saved in the following path: " + parameter.getOutput().toPath());
    }

}
