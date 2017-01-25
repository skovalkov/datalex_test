package com.epam.skovalkov.constant;

/**
 * Created by Sergei_Kovalkov on 1/24/2017.
 */
public class Constants {

    // Mandatory property names

    public final static  String INPUT_FOLDER = "inputFolder";
    public final static  String OUTPUT_FOLDER = "outputFolder";
    public final static  String REPORT_FILENAME = "report.txt";

    //Non-mandatory property names
    public final static  String MASK = "mask";
    public final static  String AUTO_DELETE = "autoDelete";

    // String patterns for messages
    public static String EMPTY_PARAMS = "Parameters of application are empty.";
    public static String EMPTY_MANDATORY_PARAM = "Mandatory param {0} is empty";
    public static String EMPTY_NON_MANDATORY_PARAM = "Non-mandatory param {0} is empty";
    public static String INVALID_MANDATORY_PARAM = "Mandatory param {0} has non-valid value : {1}";
    public static String INVALID_NON_MANDATORY_PARAM = "Non-mandatory param {0} has non-valid value : {1}";
    public static String INVALID_FILE_OR_DESTINATION = "File {0} or destination folder {1} is invalid.";
    public static String REPORT_CANNOT_BE_CREATED = "Report file {0} cannot be created.Report generation will be ommited. ";
    public static String IS_NOT_DIRECTORY = "Folder {0} is not a directory";
    public static String FILE_WAS_DELETED = "File {0} was deleted successfully";


}
