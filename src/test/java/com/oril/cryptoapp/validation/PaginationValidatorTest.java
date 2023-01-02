package com.oril.cryptoapp.validation;

import com.oril.cryptoapp.exception.PaginationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaginationValidatorTest {

    @InjectMocks
    private PaginationValidator paginationValidator;

    @Test
    void checkIfValidatePaginationParam() {
        assertThrows(PaginationException.class, () ->
            paginationValidator.validatePaginationParam(0, 0));
        assertThrows(PaginationException.class, () ->
                paginationValidator.validatePaginationParam(-1, 1));
        assertThrows(PaginationException.class, () ->
                paginationValidator.validatePaginationParam(-1, 0));

    }
}