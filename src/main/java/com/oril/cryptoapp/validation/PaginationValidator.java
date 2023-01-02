package com.oril.cryptoapp.validation;

import com.oril.cryptoapp.exception.PaginationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaginationValidator {

    public void validatePaginationParam(int pageNum, int pageSize) {
        if (pageNum < 0 || pageSize < 1) {
            log.error("Page number must be more than -1, page size more than 0");
            throw new PaginationException("Page number must be more than -1, page size more than 0");
        }
    }

}
