package org.example.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Avery A4 63,5 x 33,9 mm
 * 24 Pcs
 * No. J859
 *
 */
public class AveryA4 implements ITableLayout{

    private final Document document = new Document(PageSize.A4,17.07f,17.07f, 36.985f,36.985f);
    private PdfPTable table;

    public AveryA4(){
        initTable();
    }

    private void initTable() {
        //We can set the width of each column by
        float columnWidth = 180.675f;
        float emptyWidth = 8.5f;

        float[] pointColumnWidths = {columnWidth,emptyWidth,columnWidth,emptyWidth,columnWidth};
        table = new PdfPTable(pointColumnWidths);
        //PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.getDefaultCell().setBorderColor(BaseColor.WHITE);
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
        return new Document(PageSize.A4,17.07f,17.07f, 36.985f,36.985f);
    }


    @Override
    public void add(List<String> list, PdfContentByte pdfContentByte, boolean toBarcode) {

        List<String> data = new ArrayList<>(list);

        PdfPCell cell;
        if(toBarcode)
        {
            Barcode128 barcode128 = new Barcode128();
            barcode128.setBarHeight(35f);
            barcode128.setCodeType(Barcode128.CODE128);

            for (int i = 1; !data.isEmpty(); i++) {
                //Columns 2 and 4 should remains empty!
                boolean emptyColumnIndex = (i % 2) ==0;
                if(!emptyColumnIndex)
                {
                    barcode128.setCode(data.get(0));
                    Image code128Image = barcode128.createImageWithBarcode(pdfContentByte, null, null);

                    cell = new PdfPCell(code128Image,true);
                    formatCell(cell);
                    table.addCell(cell);

                    data.remove(0);
                }
                else {
                    //Empty Cell
                    cell = new PdfPCell();
                    formatCell(cell);
                    table.addCell(cell);
                }
                if(i==5)
                    i=0;

            }
        }
        else
        {
            for (int i = 1; !data.isEmpty(); i++) {
                boolean isEven = (i % 2) ==0;
                if(!isEven)
                {
                    cell = new PdfPCell(Phrase.getInstance(data.get(0)));
                    formatCell(cell);
                    table.addCell(cell);
                    data.remove(0);
                }
                else {
                    //Empty Cell
                    cell = new PdfPCell();
                    formatCell(cell);
                    table.addCell(cell);
                }
                if(i==5)
                    i=0;
            }
        }

    }

    private void formatCell(PdfPCell cell){
        cell.setBorderColor(BaseColor.WHITE);
        float cellHeight = 95.45484251968f;
        cell.setFixedHeight(cellHeight);
        cell.setPadding(15f);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    }


}
