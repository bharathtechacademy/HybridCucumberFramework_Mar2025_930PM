package com.orangehrm.crm.framework.api.commons;

    import java.io.File;

    /**
     * Utility class for JMeter-related common operations.
     */
    public class JMeterCommons {

        /**
         * Deletes the specified directory and all its contents recursively.
         * @param dir The directory to delete.
         */
        public static void deleteDirectory(File dir) {
            // List all files in the directory
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    // If the file is a directory, delete its contents recursively
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        // Delete the file
                        file.delete();
                    }
                }
            }
            // Finally, delete the directory itself
            dir.delete();
        }

        /**
         * Common method to run JMeter tests.
         */
        public static void runJMeterScript(String jmxFilePath) {

            //
        }


    }