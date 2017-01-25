package com.epam.skovalkov.impl;

import static com.epam.skovalkov.constant.Constants.*;

import com.epam.skovalkov.api.ArgValidator;
import com.epam.skovalkov.exception.ValidationFailedException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.Properties;

/**
 * Created by Sergei_Kovalkov on 1/24/2017.
 */
public class ArgValidatorImpl implements ArgValidator {

    final static Logger logger = Logger.getLogger(ArgValidatorImpl.class);

    public Properties getAppProperties(boolean autoValidate) throws ValidationFailedException {
        Properties props = new Properties();
        props.setProperty(INPUT_FOLDER,System.getProperty(INPUT_FOLDER, ""));
        props.setProperty(OUTPUT_FOLDER,System.getProperty(OUTPUT_FOLDER, ""));
        props.setProperty(MASK,System.getProperty(MASK, ""));
        props.setProperty(AUTO_DELETE,System.getProperty(AUTO_DELETE, ""));
        if (autoValidate){
            validateProperties(props);
        }
        return props;
    }

    public void validateProperties(Properties props) throws ValidationFailedException {
        validateMandatoryProperties(props);
        validateNonMandatoryProperties(props);
    }

    public void validateMandatoryProperties(Properties props) throws ValidationFailedException {
        String inputFolderPath = props.getProperty(INPUT_FOLDER);
        String outputFolderPath = props.getProperty(OUTPUT_FOLDER);

        if (inputFolderPath == null || StringUtils.isEmpty(inputFolderPath.trim()) ){
            throw new ValidationFailedException(MessageFormat.format(EMPTY_MANDATORY_PARAM, INPUT_FOLDER));
        }
        if (outputFolderPath == null || StringUtils.isEmpty(outputFolderPath.trim()) ){
            throw new ValidationFailedException(MessageFormat.format(EMPTY_MANDATORY_PARAM, OUTPUT_FOLDER));
        }
    }

    public void validateNonMandatoryProperties(Properties props) {
        String mask = props.getProperty(MASK);
        if (mask == null || StringUtils.isEmpty(props.getProperty(MASK).trim())){
            logger.warn(MessageFormat.format(EMPTY_NON_MANDATORY_PARAM, MASK));
            props.put(MASK, "*");
        }

        // Another two properties - SUBFOLDER and AUTO_DELETE will be converted to Boolean
        // through Boolean.valueOf() method. If value will be null or another string
        // except "true"(case insensitive) then Boolean.FALSE will be returned
    }

}
