package com.epam.skovalkov;

import static com.epam.skovalkov.constant.Constants.*;
import static org.junit.Assert.*;
import com.epam.skovalkov.api.Copier;
import com.epam.skovalkov.exception.ValidationFailedException;
import com.epam.skovalkov.impl.CopierImpl;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Sergei_Kovalkov on 1/24/2017.
 */
public class CopierTest {

    private String DEFAULT_INPUT_FOLDER = "./src/test/resources/input";
    private String DEFAULT_OUTPUT_FOLDER = "./src/test/resources/output";
    private String DEFAULT_MASK = "*";
    private Properties props;

    @Before
    public void initialize(){
        props = new Properties();
        props.put(INPUT_FOLDER,DEFAULT_INPUT_FOLDER);
        props.put(OUTPUT_FOLDER,DEFAULT_OUTPUT_FOLDER);
        props.put(AUTO_DELETE, true);
        props.put(MASK,DEFAULT_MASK);
    }

   @Test
   public void copyFile() throws ValidationFailedException {
       Copier copier = new CopierImpl(props);
        copier.copy();
        assertTrue(true);
   }

    @Test
    public void copyAllFiles() throws ValidationFailedException {
        /*
        * It suggest that both files will be selected - 'Test_1.txt' and 'Text_2.txt'
        */
        Copier copier = new CopierImpl(props);
        copier.copy();
        File out = new File(DEFAULT_OUTPUT_FOLDER);
        assertTrue(new File(out, "Test_1.txt").exists());
        assertTrue(new File(out, "Test_2.txt").exists());
        cleanDirectory(out);
    }

    @Test
    public void copyMatchedFile() throws ValidationFailedException {
        /*
        * It suggest that both files will be selected - 'Test_1.txt' and 'Text_2.txt'
        */
        props.setProperty(MASK,"Test_1*");
        Copier copier = new CopierImpl(props);
        copier.copy();
        File out = new File(DEFAULT_OUTPUT_FOLDER);
        assertTrue(new File(out, "Test_1.txt").exists());
        assertFalse(new File(out, "Test_2.txt").exists());
        cleanDirectory(out);
        props.setProperty(MASK,DEFAULT_MASK);
    }

    @Test
    public void copyMatchedFileInSubfolder() throws ValidationFailedException {
        /*
        * It suggest that both files will be selected - 'Test_1.txt' and 'Text_2.txt'
        */
        props.setProperty(MASK,"Test_2*");
        Copier copier = new CopierImpl(props);
        copier.copy();
        File out = new File(DEFAULT_OUTPUT_FOLDER);
        assertTrue(new File(out, "Test_2.txt").exists());
        assertFalse(new File(out, "Test_1.txt").exists());
        cleanDirectory(out);
        props.setProperty(MASK,DEFAULT_MASK);
    }

    private void cleanDirectory(File folder){
        try {
            FileUtils.cleanDirectory(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
