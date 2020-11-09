package pl.barwinscy.planeshifter.login_module;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;
import pl.barwinscy.planeshifter.login_module.entities.User;

@Mapper
public interface UserMapperV2 {

        UserMapperV2 INSTANCE = Mappers.getMapper(UserMapperV2.class);


        //UserDto mapUserToUserDto(User user);

        //User mapUserDtoToUser(UserDto userDto);
}
