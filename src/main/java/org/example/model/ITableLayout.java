package org.example.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.List;

/**
 * The size of PDF pages is not expressed in pixels but in points.
 *
 * 1 inch = 72 points
 * 1 inch = 25.4 mm
 * That leads to:
 *
 * 1 point = 0.352777778 mm
 *
 * Height of a cell = 30 mm = 85.35826771653 point (printer’s)
 * Width of a cell = 52 mm = 147,9543307087 point (printer’s)
 */
public interface ITableLayout {

    PdfPTable getTable();
    Document getDocument();

    PdfPTable getNewTable();
    Document getNewDocument();

     void add(List<String> values, PdfContentByte pdfContentByte, boolean toBarcode);

}
