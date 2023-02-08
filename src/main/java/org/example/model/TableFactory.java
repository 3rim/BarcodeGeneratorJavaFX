package org.example.model;

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
            case "LAYOUT1": table = new Layout1();
            break;
            case "LAYOUT2": table = new Layout2();
        }

        System.out.println("Created:" +tableType);
        return table;
    }
}
