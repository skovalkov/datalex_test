package com.epam.skovalkov.impl;

import com.epam.skovalkov.api.ResultGenerator;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.epam.skovalkov.constant.Constants.REPORT_CANNOT_BE_CREATED;
import static com.epam.skovalkov.constant.Constants.REPORT_FILENAME;

/**
 * Created by Sergei_Kovalkov on 1/24/2017.
 */
public class ResultGeneratorImpl implements ResultGenerator {

    final static Logger logger = Logger.getLogger(CopierImpl.class);

    private Map<String, Set<String>> processedFiles = new HashMap<String, Set<String>>();
    private Map<String, Set<String>> nonProcessedFiles = new HashMap<String, Set<String>>();


    public void generateReport(){
        FileWriter reportWriter = null;
        try {
            reportWriter = new FileWriter(new File(REPORT_FILENAME));
            reportWriter.write("Report on " + Calendar.getInstance().getTime());
            reportWriter.write("\n/------------------------/");
            if (processedFiles.size() > 0){
                reportWriter.write("\nSuccessfully processed files: ");
                reportWriter.write("\n/------------------------/");
                reportWriter.flush();
                // generate report about suuccessfully processed files
                generateReportFromMap(reportWriter, processedFiles);
            }
            if (nonProcessedFiles.size() > 0){
                reportWriter.write("\n");
                reportWriter.write("\nUnsuccessfully processed files: ");
                reportWriter.write("\n/------------------------/");
                // generate report about unsuuccessfully processed files
                generateReportFromMap(reportWriter, nonProcessedFiles);
            }
            reportWriter.flush();
        } catch (IOException e) {
            logger.error(MessageFormat.format(REPORT_CANNOT_BE_CREATED, REPORT_FILENAME));
        } finally {
            if (reportWriter != null) {
                try {
                    reportWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void generateReportFromMap(FileWriter reportWriter, Map<String, Set<String>> map) throws IOException {
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            if (entry.getKey() != null) {
                reportWriter.write("\nFolder : " + entry.getKey());
                reportWriter.flush();
                Set<String> files = entry.getValue();
                for (String filename : files) {
                    reportWriter.write("\n - " + filename);
                    reportWriter.flush();
                }
            }
        }
    }

    public void setProcessedFiles(Map<String, Set<String>> processedFiles) {
        this.processedFiles = processedFiles;
    }

    public void setNonProcessedFiles(Map<String, Set<String>> nonProcessedFiles) {
        this.nonProcessedFiles = nonProcessedFiles;
    }
}
