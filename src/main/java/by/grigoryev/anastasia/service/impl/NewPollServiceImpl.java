package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.model.NewPollAnswer;
import by.grigoryev.anastasia.model.NewPollQuestion;
import by.grigoryev.anastasia.model.NewPollTitle;
import by.grigoryev.anastasia.model.TelegramUser;
import by.grigoryev.anastasia.repository.NewPollAnswerRepository;
import by.grigoryev.anastasia.repository.NewPollQuestionRepository;
import by.grigoryev.anastasia.repository.NewPollTitleRepository;
import by.grigoryev.anastasia.repository.TelegramUserRepository;
import by.grigoryev.anastasia.service.NewPollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewPollServiceImpl implements NewPollService {

    private final NewPollTitleRepository newPollTitleRepository;

    private final NewPollQuestionRepository newPollQuestionRepository;

    private final NewPollAnswerRepository newPollAnswerRepository;

    private final TelegramUserRepository telegramUserRepository;

    @Override
    public void createTitle(String title, Long telegramId) {
        TelegramUser telegramUser = telegramUserRepository.findFirstByTelegramIdOrderByTimeOfRegistrationDesc(telegramId);

        NewPollTitle newPollTitle = NewPollTitle.builder()
                .title(title)
                .telegramUserId(telegramUser.getId())
                .timeOfCreation(LocalDateTime.now())
                .build();

        newPollTitleRepository.save(newPollTitle);

        log.info("NewPollServiceImpl createNewPoll " + newPollTitle);
    }

    @Override
    public void createQuestion(String question, Long telegramId) {
        TelegramUser telegramUser = telegramUserRepository.findFirstByTelegramIdOrderByTimeOfRegistrationDesc(telegramId);

        NewPollQuestion newPollQuestion = NewPollQuestion.builder()
                .question(question)
                .telegramUserId(telegramUser.getId())
                .timeOfCreation(LocalDateTime.now())
                .build();

        newPollQuestionRepository.save(newPollQuestion);

        log.info("NewPollServiceImpl createQuestion " + newPollQuestion);
    }

    @Override
    public void createAnswer(String answer, Long telegramId) {
        TelegramUser telegramUser = telegramUserRepository.findFirstByTelegramIdOrderByTimeOfRegistrationDesc(telegramId);

        NewPollAnswer newPollAnswer = NewPollAnswer.builder()
                .answer(answer)
                .telegramUserId(telegramUser.getId())
                .timeOfCreation(LocalDateTime.now())
                .build();

        newPollAnswerRepository.save(newPollAnswer);

        log.info("NewPollServiceImpl createAnswer " + newPollAnswer);
    }

}
