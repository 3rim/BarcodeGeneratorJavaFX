package org.example.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.List;

/**
 * Lyreco A4 Multipurpose Label
 * 52,5 x 29,7 mm
 * 40 Pcs
 */
public class LyrecoA4 implements ITableLayout{

    private final Document document= new Document(PageSize.A4,0,0,0,0);
    private PdfPTable table;

    public LyrecoA4(){
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

    @Override
    public void add(List<String> values,PdfContentByte pdfContentByte,boolean toBarcode) {
        if (toBarcode) {
            Barcode128 barcode128 = new Barcode128();
            barcode128.setCodeType(Barcode128.CODE128);
            barcode128.setBarHeight(35f);
            for (String data : values) {
                barcode128.setCode(data);
                Image code128Img = barcode128.createImageWithBarcode(pdfContentByte, null, null);
                table.addCell(code128Img);
            }
        } else {
            values.forEach(table::addCell);
        }
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
