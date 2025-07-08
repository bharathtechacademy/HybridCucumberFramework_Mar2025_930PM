package com.orangehrm.crm.framework.api.commons;

    import com.orangehrm.crm.framework.web.commons.WebCommons;
//    import org.apache.jmeter.engine.StandardJMeterEngine;
//    import org.apache.jmeter.report.config.ConfigurationException;
//    import org.apache.jmeter.report.dashboard.GenerationException;
//    import org.apache.jmeter.report.dashboard.ReportGenerator;
//    import org.apache.jmeter.reporters.ResultCollector;
//    import org.apache.jmeter.save.SaveService;
//    import org.apache.jmeter.util.JMeterUtils;
//    import org.apache.jorphan.collections.HashTree;

    import java.io.File;
    import java.io.IOException;
    import java.nio.file.Paths;
  

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
        /**
        public static void runJMeterScript(String jmxFileName) throws IOException, ConfigurationException, GenerationException {

            //1. Store Jmeter home directory in variable
            String jmeterHome ="src/test/resources/jmeter";

            //2. Set the Jmeter test results csv file path
            String resultsFile = "TestResults_"+ WebCommons.uniqueId("ddMMyyyyhhmmss") +".csv";
            String resultsFilePath = Paths.get(jmeterHome, "results", resultsFile).toString();

            //3. Set the Jmeter executable file path
            String testPlanPath = Paths.get(jmeterHome, jmxFileName).toString();

            //4. Set the Jmeter property file path
            String propertiesFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\jmeter\\bin\\jmeter.properties";

            //5. Set the Jmeter home path
            JMeterUtils.setJMeterHome(jmeterHome);

            //6. Load all JMeter Properties and Configuration
            JMeterUtils.loadJMeterProperties(propertiesFilePath);

            //7. Load JMeter executable file.
            File testPlanFile = new File(testPlanPath);
            HashTree testPlanTree = SaveService.loadTree(testPlanFile);

            //8.Collect and Save all the test results coming from the JMX execution
            ResultCollector resultCollector = new ResultCollector();
            resultCollector.setFilename(resultsFilePath);

            //9.  Run the JMeter Script
            StandardJMeterEngine jmeter = new StandardJMeterEngine();
            testPlanTree.add(testPlanTree.getArray()[0],resultCollector);
            jmeter.configure(testPlanTree);
            jmeter.run();

            //10. Generate Html report for Performance Test Results
            ReportGenerator report = new ReportGenerator(resultsFilePath,null);
            report.generate();


        }
*/

    }