package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.model.*;
import by.grigoryev.anastasia.repository.*;
import by.grigoryev.anastasia.service.NewPollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewPollServiceImpl implements NewPollService {

    private final NewPollTitleRepository newPollTitleRepository;

    private final NewPollQuestionRepository newPollQuestionRepository;

    private final NewPollAnswerRepository newPollAnswerRepository;

    private final NewPollUserRepository newPollUserRepository;

    @Override
    public void createTitle(String title, Long telegramId) {
        NewPollUser newPollUser = newPollUserRepository.findFirstByTelegramIdOrderByTimeOfRegistrationDesc(telegramId);

        NewPollTitle newPollTitle = NewPollTitle.builder()
                .title(title)
                .telegramId(newPollUser.getTelegramId())
                .telegramUserId(newPollUser.getId())
                .timeOfCreation(LocalDateTime.now())
                .build();

        newPollTitleRepository.save(newPollTitle);

        log.info("NewPollServiceImpl createNewPoll " + newPollTitle);
    }

    @Override
    public void createQuestion(String question, Long telegramId) {
        NewPollTitle newPollTitle = newPollTitleRepository.findFirstByTelegramIdOrderByTimeOfCreationDesc(telegramId);

        NewPollQuestion newPollQuestion = NewPollQuestion.builder()
                .question(question)
                .telegramId(newPollTitle.getTelegramId())
                .newPollTitleId(newPollTitle.getId())
                .timeOfCreation(LocalDateTime.now())
                .build();

        newPollQuestionRepository.save(newPollQuestion);

        log.info("NewPollServiceImpl createQuestion " + newPollQuestion);
    }

    @Override
    public void createAnswer(String answer, Long telegramId) {
        NewPollQuestion newPollQuestion = newPollQuestionRepository.findFirstByTelegramIdOrderByTimeOfCreationDesc(telegramId);

        NewPollAnswer newPollAnswer = NewPollAnswer.builder()
                .answer(answer)
                .telegramId(newPollQuestion.getTelegramId())
                .newPollQuestionId(newPollQuestion.getId())
                .timeOfCreation(LocalDateTime.now())
                .build();

        newPollAnswerRepository.save(newPollAnswer);

        log.info("NewPollServiceImpl createAnswer " + newPollAnswer);
    }

    @Override
    public void save(User user) {
        NewPollUser newPollUser = NewPollUser.builder()
                .telegramId(user.getId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .timeOfRegistration(LocalDateTime.now())
                .languageCode(user.getLanguageCode())
                .build();

        newPollUserRepository.save(newPollUser);

        log.info("NewPollServiceImpl save " + newPollUser);
    }

}
