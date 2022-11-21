package by.grigoryev.anastasia.mapper;

import by.grigoryev.anastasia.dto.TelegramUserDto;
import by.grigoryev.anastasia.model.TelegramUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TelegramUserMapper {

    TelegramUserDto toTelegramUserDto(TelegramUser telegramUser);

}
