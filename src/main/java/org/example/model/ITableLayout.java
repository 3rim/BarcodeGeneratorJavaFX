package org.example.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;

public interface ITableLayout {

    PdfPTable getTable();
    Document getDocument();

    PdfPTable getNewTable();
    Document getNewDocument();

}
