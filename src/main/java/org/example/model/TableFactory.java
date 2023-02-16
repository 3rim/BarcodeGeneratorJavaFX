package org.example.model;

import org.example.utility.TableLayoutEnum;

/**
 * TableFactory for creating the Tables.
 *
 */
public class TableFactory {

    /**
     * Creates a table(ITEXT) for storing the data in cells.
     * @param tableType The TableLayoutType
     * @return A Table(IText)
     */
    public ITableLayout createTable(String tableType){
        ITableLayout table = null;
        switch (tableType.toUpperCase()){
            case "LAYOUT1": table = new LyrecoA4();
            break;
            case "LAYOUT2": table = new AveryA4();
        }
        return table;
    }

    /**
     * Creates a table(ITEXT) for storing the data in cells.
     * @param tableType The TableLayoutType
     * @return A Table(IText)
     */
    public ITableLayout createTable(TableLayoutEnum tableType){
        ITableLayout table = null;
        switch (tableType){
            case LYRECO_A4: table = new LyrecoA4();
                break;
            case AVERY_A4: table = new AveryA4();
                break;
        }
        return table;
    }
}
