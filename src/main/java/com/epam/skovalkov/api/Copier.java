package com.epam.skovalkov.api;

import com.epam.skovalkov.exception.ValidationFailedException;

import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sergei_Kovalkov on 1/24/2017.
 */
public interface Copier {

    void copy() throws ValidationFailedException;

    void copy(String inputFolder, String outputFolder, String mask, boolean autoDelete);

    void copy(File inputFolder, File outputFolder, String mask, boolean autoDelete);

    Map<String, Set<String>> getProcessedFiles();

    Map<String, Set<String>> getNonProcessedFiles();

}
