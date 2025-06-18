package com.orangehrm.crm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * Utility class for handling PDF files.
 * Provides methods to extract text from PDF files using the Apache PDFBox library.
 */
public class PDFUtil {

    /**
     * Extracts text from a specified PDF file.
     * The method reads the PDF file, processes it using the Apache PDFBox library, and retrieves text from specific pages.
     *
     * @param fileName The name of the PDF file to be read.
     *                 The file should be located in the `Files` directory under the user's working directory.
     * @return A string containing the extracted text from the specified pages of the PDF file.
     *         If the file is not found or an error occurs during reading, an empty string is returned.
     * @throws FileNotFoundException If the specified file is not found.
     * @throws IOException If an I/O error occurs while reading the file or processing the PDF.
     */
    public static String getPDFText(String fileName) {
        String text = "";

        try {
            // Read the PDF file from the specified directory
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Files\\" + fileName);

            // Load the PDF file into the PDDocument class for processing
            PDDocument document = PDDocument.load(fis);

            // Create a PDFTextStripper instance to extract text from the PDF
            PDFTextStripper pdf = new PDFTextStripper();

            // Specify the range of pages to extract text from
            pdf.setStartPage(2);
            pdf.setEndPage(2);

            // Extract text from the specified pages
            text = pdf.getText(document);

        } catch (FileNotFoundException e) {
            // Print stack trace if the file is not found
            e.printStackTrace();
        } catch (IOException e) {
            // Print stack trace if an I/O error occurs
            e.printStackTrace();
        }

        return text;
    }
}
