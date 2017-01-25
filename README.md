# FileScanner
This application scans input folder(with or without subfolders) and copy files
to output folder if filenames are acceptable to specified rule.



#Installation
This app should be built with "gradle build" command before running from root
of the project. After that you can run this app via "gradle run" command from root.


#Usage
This application can help to get set of files, for example with particular
date template in title.
For using this app you need to set mandatory system properties:
- inputFolder
- outputFolder

Also you can specify non-mandatory system properties:
- mask (filename pattern)
- autoDelete (delete files after processing)
