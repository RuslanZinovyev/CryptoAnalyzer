package commands;

import model.Parameter;
import model.Result;
import services.AnalyzerService;
import services.ReadService;

public class AnalyzeCommand implements Command {
    private final AnalyzerService analyzerService;

    public AnalyzeCommand(Parameter parameter) {
        ReadService sampleText = new ReadService(parameter.getInput());
        ReadService encryptedText = new ReadService(parameter.getOutput());
        analyzerService = new AnalyzerService(sampleText.readFile(), encryptedText.readFile());
    }

    @Override
    public Result execute() {
        analyzerService.analyzeSample();
        return new Result("The file has been analyzed" + analyzerService.analyzeSample().toString());
    }
}
