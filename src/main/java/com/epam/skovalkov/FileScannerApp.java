package com.epam.skovalkov;

import com.epam.skovalkov.api.ArgValidator;
import com.epam.skovalkov.api.Copier;
import com.epam.skovalkov.api.ResultGenerator;
import com.epam.skovalkov.exception.ValidationFailedException;
import com.epam.skovalkov.impl.ArgValidatorImpl;
import com.epam.skovalkov.impl.CopierImpl;
import com.epam.skovalkov.impl.ResultGeneratorImpl;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.Properties;

/**
 * Created by Sergei_Kovalkov on 1/24/2017.
 */
public class FileScannerApp {

    final static Logger logger = Logger.getLogger(FileScannerApp.class);

    public static void main(String[] args) {

        logger.info("Start application..");

        try {
            // 1. Validate args
            ArgValidator validator = new ArgValidatorImpl();
            Properties props = validator.getAppProperties(true);

            // 2. Copy files
            Copier copier = new CopierImpl(props);
            copier.copy();

            // 3. Generate report
            ResultGenerator generator = new ResultGeneratorImpl();
            generator.setProcessedFiles(copier.getProcessedFiles());
            generator.setNonProcessedFiles(copier.getNonProcessedFiles());
            generator.generateReport();

        } catch (ValidationFailedException vfe) {
            logger.error(vfe.getMessage());
        } catch (IllegalArgumentException iae){
            logger.error(iae.getMessage());
        } finally {
            logger.info("Application is stopped.");
        }

    }
}
