package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.service.ResultsBuilderService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ResultsBuilderServiceImpl implements ResultsBuilderService {

    private final StringBuilder resultsBuilder = new StringBuilder();

    @Override
    public Mono<StringBuilder> buildResults(String action, String name) {
        return Mono.just(resultsBuilder.append("\n")
                .append(name)
                .append(" на вопрос № ")
                .append(action.charAt(0))
                .append(" выбрал ответ № ")
                .append(action.substring(2)))
                .log("buildResults");
    }

    @Override
    public String showResults() {
        return resultsBuilder.toString();
    }

    @Override
    public void clearResults() {
        resultsBuilder.setLength(0);
    }
}
