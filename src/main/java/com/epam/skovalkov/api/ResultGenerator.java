package com.epam.skovalkov.api;

import java.util.Map;
import java.util.Set;

/**
 * Created by Sergei_Kovalkov on 1/24/2017.
 */
public interface ResultGenerator {

    void generateReport();

    void setProcessedFiles(Map<String, Set<String>> processedFiles);

    void setNonProcessedFiles(Map<String, Set<String>> processedFiles);

}
