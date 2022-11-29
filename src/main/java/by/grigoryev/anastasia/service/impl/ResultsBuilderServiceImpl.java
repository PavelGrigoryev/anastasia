package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.service.ResultsBuilderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResultsBuilderServiceImpl implements ResultsBuilderService {

    private final StringBuilder resultsBuilder = new StringBuilder();

    @Override
    public void buildResults(String action, String name) {
        resultsBuilder.append("\n")
                .append(name)
                .append(" на вопрос № ")
                .append(action.charAt(0))
                .append(" выбрал ответ № ")
                .append(action.substring(2));

        log.info("ResultsBuilderServiceImpl buildResults " + resultsBuilder);
    }

    @Override
    public String saveResults() {
        return resultsBuilder.toString();
    }

    @Override
    public void clearResults() {
        resultsBuilder.setLength(0);
    }
}
