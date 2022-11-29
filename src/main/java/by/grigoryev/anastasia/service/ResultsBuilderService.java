package by.grigoryev.anastasia.service;

public interface ResultsBuilderService {

    void buildResults(String action, String name);

    String saveResults();

    void clearResults();

}
