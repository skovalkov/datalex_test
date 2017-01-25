package com.epam.skovalkov.api;

import com.epam.skovalkov.exception.ValidationFailedException;
import java.util.Properties;

/**
 * Created by Sergei_Kovalkov on 1/24/2017.
 */
public interface ArgValidator {

    Properties getAppProperties(boolean autoValidate) throws ValidationFailedException;

    void validateProperties(Properties props) throws ValidationFailedException;

    void validateMandatoryProperties(Properties props)  throws ValidationFailedException;

    void validateNonMandatoryProperties(Properties props);
}
