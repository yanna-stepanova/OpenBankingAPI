package com.yana.stepanova.mapper;

import com.yana.stepanova.config.MapperConfig;
import com.yana.stepanova.dto.account.AccountDto;
import com.yana.stepanova.dto.account.CreateAccountRequestDTO;
import com.yana.stepanova.model.Account;
import com.yana.stepanova.model.Currency;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Objects;

@Mapper(config = MapperConfig.class, uses = UserMapper.class)
public interface AccountMapper {
    @Mapping(target = "currencyName", ignore = true)
    Account toModel(CreateAccountRequestDTO requestDto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.email", target = "userEmail")
    AccountDto toDto(Account account);

    @AfterMapping
    default void setCurrencyName(@MappingTarget Account account,
                             CreateAccountRequestDTO requestDTO) {
        if (requestDTO.currencyName() != null) {
            account.setCurrencyName(Objects.requireNonNull(
                    Currency.getByType(requestDTO.currencyName())).getCode());
        }
    }
}
