package com.tigerit.exam;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sazzad
 */
public class Table {

    public Table() {
    }
    private String tableName;
    private Integer rowsNumber;
    private Integer colsNumber;
    private ArrayList<String> colsName;
    private ArrayList<RowData> rows;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getRowsNumber() {
        return rowsNumber;
    }

    public void setRowsNumber(Integer rowsNumber) {
        this.rowsNumber = rowsNumber;
    }

    public Integer getColsNumber() {
        return colsNumber;
    }

    public void setColsNumber(Integer colsNumber) {
        this.colsNumber = colsNumber;
    }

    public ArrayList<String> getColsName() {
        return colsName;
    }

    public void setColsName(String colsName) {
        String[] word = colsName.split("\\s");
        ArrayList<String> words = new ArrayList<>();
        for (String w : word) {
            words.add(w);
        }
        this.colsName = words;
    }

    public ArrayList<RowData> getRows() {
        return rows;
    }

    public void setRows(ArrayList<RowData> rows) {
        this.rows = rows;
    }

}
