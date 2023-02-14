package org.example.model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * TODO: Rename class
 */
public class Layout1 implements ITableLayout{

    private final Document document= new Document(PageSize.A4,0,0,0,0);
    private PdfPTable table;

    public Layout1(){
        initTable();
    }

    @Override
    public PdfPTable getTable() {
        return table;
    }

    @Override
    public Document getDocument() {
        return document;
    }

    @Override
    public PdfPTable getNewTable() {
        initTable();
        return table;
    }

    @Override
    public Document getNewDocument() {
        return  new Document(PageSize.A4,0,0,0,0);
    }

    private void initTable(){
        table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        // Center elements in cells
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        //table.getDefaultCell().setMinimumHeight(84f);
        table.getDefaultCell().setFixedHeight(84f);
        table.getDefaultCell().setPadding(15);
        table.getDefaultCell().setBorderColor(BaseColor.WHITE);
    }
}
