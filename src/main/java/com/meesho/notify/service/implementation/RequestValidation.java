package com.meesho.notify.service.implementation;

import com.meesho.notify.models.request.SearchRequest;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class RequestValidation {

    public boolean validate(Object object){
        ValidatorFactory validatorFactory = Validation
                .buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Object>> validationErrors = validator
                .validate(object);

        return validationErrors.isEmpty();
    }

}
