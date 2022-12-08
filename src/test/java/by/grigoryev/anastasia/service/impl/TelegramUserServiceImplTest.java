package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.model.TelegramUser;
import by.grigoryev.anastasia.repository.TelegramUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TelegramUserServiceImplTest {

    private TelegramUserServiceImpl telegramUserService;

    private TelegramUserRepository telegramUserRepository;

    @BeforeEach
    void init() {
        telegramUserRepository = mock(TelegramUserRepository.class);
        telegramUserService = spy(new TelegramUserServiceImpl(telegramUserRepository));
    }

    @Test
    void save() {
        User user = new User();
        user.setId(59190859107L);
        user.setUserName("Undead");
        user.setFirstName("Pavel");
        user.setLastName("Chichikov");
        user.setLanguageCode("en");

        TelegramUser mockedTelegramUser = TelegramUser.builder()
                .telegramId(user.getId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .timeOfRegistration(LocalDateTime.now())
                .languageCode(user.getLanguageCode())
                .build();

        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0))
                .when(telegramUserRepository)
                .save(any(TelegramUser.class));

        TelegramUser resultTelegramUser = telegramUserService.save(user);

        assertEquals(mockedTelegramUser.getTelegramId(), resultTelegramUser.getTelegramId());
        assertEquals(mockedTelegramUser.getUserName(), resultTelegramUser.getUserName());
        assertEquals(mockedTelegramUser.getFirstName(), resultTelegramUser.getFirstName());
        assertEquals(mockedTelegramUser.getLastName(), resultTelegramUser.getLastName());
        assertEquals(mockedTelegramUser.getLanguageCode(), resultTelegramUser.getLanguageCode());
    }
}