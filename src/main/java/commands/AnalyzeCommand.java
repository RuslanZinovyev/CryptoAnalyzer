package commands;

import model.Parameter;
import model.Result;
import services.AnalyzerService;
import services.ReadService;

public class AnalyzeCommand implements Command {
    private final AnalyzerService analyzerService;

    public AnalyzeCommand(Parameter parameter) {
        ReadService readService = new ReadService(parameter.getInput());
        analyzerService = new AnalyzerService(readService.readFile(), parameter.getOutput());
    }

    @Override
    public Result execute() {
        analyzerService.analyzeEncryptedText();
        return new Result("The file has been analyzed and decrypted");
    }
}
