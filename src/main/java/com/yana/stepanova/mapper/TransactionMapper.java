package com.yana.stepanova.mapper;

import com.yana.stepanova.config.MapperConfig;
import com.yana.stepanova.dto.transaction.TransactionDto;
import com.yana.stepanova.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = AccountMapper.class)
public interface TransactionMapper {
    @Mapping(source = "accountFrom.id", target = "accountFromId")
    @Mapping(source = "accountFrom.iban", target = "ibanFrom")
    @Mapping(source = "accountTo.id", target = "accountToId")
    @Mapping(source = "accountTo.iban", target = "ibanTo")
    TransactionDto toDto(Transaction transaction);
}
