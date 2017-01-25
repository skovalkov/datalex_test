package com.epam.skovalkov.impl;

import com.epam.skovalkov.api.Copier;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.log4j.Logger;
import java.io.*;
import java.text.MessageFormat;
import java.util.*;

import static com.epam.skovalkov.constant.Constants.*;

/**
 * Created by Sergei_Kovalkov on 1/24/2017.
 */
public class CopierImpl implements Copier{

    private Properties props;

    public CopierImpl(Properties props){
        super();
        this.props = props;
    }

    final static Logger logger = Logger.getLogger(CopierImpl.class);

    private Map<String, Set<String>> processedFiles = new HashMap<String, Set<String>>();
    private Map<String, Set<String>> nonProcessedFiles = new HashMap<String, Set<String>>();


    public void copy() throws IllegalArgumentException {
        if (props != null){
            String inputFolder = props.getProperty(INPUT_FOLDER);
            String outputFolder = props.getProperty(OUTPUT_FOLDER);
            String mask = props.getProperty(MASK);
            Boolean autoDelete = Boolean.valueOf(props.getProperty(AUTO_DELETE));

            copy(inputFolder, outputFolder, mask, autoDelete);
        } else {
            throw new IllegalArgumentException(EMPTY_PARAMS);
        }
    }

    public void copy(String inputFolder, String outputFolder, String mask, boolean autoDelete) {
        File source = new File(inputFolder);
        File output = new File(outputFolder);
        copy(source, output, mask, autoDelete);
    }

    public void copy(File source, File outputFolder,String mask, boolean autoDelete) {

        if (!outputFolder.exists()){
            outputFolder.mkdirs();
        }

        if (source.exists() && !source.isDirectory()){
            throw new IllegalArgumentException(IS_NOT_DIRECTORY);
        }

        Collection<File> items2 = FileUtils.listFiles(source, new WildcardFileFilter(mask), new WildcardFileFilter("*"));
        for (File item : items2) {
            try {
                FileUtils.copyFileToDirectory(item, outputFolder);
                if (autoDelete){
                    FileUtils.deleteQuietly(item);
                    logger.debug(MessageFormat.format(FILE_WAS_DELETED, item.getPath()));
                }
                addFilenameToReportStorage(processedFiles, item);
            } catch (IOException e) {
                logger.error(MessageFormat.format(INVALID_FILE_OR_DESTINATION, item.getName(), outputFolder.getPath()));
                addFilenameToReportStorage(nonProcessedFiles, item);
            }
        }
    }

    private void addFilenameToReportStorage(Map<String, Set<String>> storage , File item) {
        if (!storage.containsKey(item.getParent())){
            storage.put(item.getParent(), new HashSet<String>());
        }
        Set<String> files = storage.get(item.getParent());
        files.add(item.getName());
    }

    public Map<String, Set<String>> getProcessedFiles() {
        return processedFiles;
    }

    public Map<String, Set<String>> getNonProcessedFiles() {
        return nonProcessedFiles;
    }
}
