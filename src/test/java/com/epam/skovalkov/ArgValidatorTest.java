package com.epam.skovalkov;

import static com.epam.skovalkov.constant.Constants.*;
import static org.junit.Assert.*;

import com.epam.skovalkov.api.ArgValidator;
import com.epam.skovalkov.exception.ValidationFailedException;
import com.epam.skovalkov.impl.ArgValidatorImpl;
import org.junit.Before;
import org.junit.Test;
import java.util.Properties;



/**
 * Created by Sergei_Kovalkov on 1/24/2017.
 */
public class ArgValidatorTest{

    private String DEFAULT_INPUT_FOLDER = "./src/test/resources/input";
    private String DEFAULT_OUTPUT_FOLDER = "./src/test/resources/output";
    private String DEFAULT_MASK = "*";
    private ArgValidator validator;
    private Properties props;

    @Before
    public void initialize(){
        validator = new ArgValidatorImpl();
        props = new Properties();
        props.put(INPUT_FOLDER,DEFAULT_INPUT_FOLDER);
        props.put(OUTPUT_FOLDER,DEFAULT_OUTPUT_FOLDER);
        props.put(AUTO_DELETE, true);
        props.put(MASK,DEFAULT_MASK);
    }

    @Test(expected = ValidationFailedException.class)
    public void validateEmptyMandatoryProperty() throws ValidationFailedException{
        props.put(INPUT_FOLDER, "");
        validator.validateMandatoryProperties(props);
    }

    @Test
    public void validateEmptyNonMandatoryProperty(){
        props.put(MASK, "");
        validator.validateNonMandatoryProperties(props);
        String mask = props.getProperty(MASK);
        assertTrue(DEFAULT_MASK.equals(mask));
    }

    @Test
    public void validateNonEmptyNonMandatoryProperty(){
        props.put(MASK, "test_test");
        validator.validateNonMandatoryProperties(props);
        assertTrue(props.containsKey(MASK));
    }

}
