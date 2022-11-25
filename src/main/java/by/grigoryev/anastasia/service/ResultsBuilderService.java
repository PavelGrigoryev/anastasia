package by.grigoryev.anastasia.service;

import reactor.core.publisher.Mono;

public interface ResultsBuilderService {

    Mono<StringBuilder> buildResults(String action, String name);

    String showResults();

    void clearResults();

}
