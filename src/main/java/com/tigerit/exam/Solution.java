package com.tigerit.exam;


import static com.tigerit.exam.IO.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {
    @Override
    public void run() {
        
 
// Test Case Input
        Integer testCase = readLineAsInteger();
      
        
        
        for (Integer i = 1; i <= testCase; i++) {
              ArrayList<Table> TableList = new ArrayList<>();
              TableList.clear();
              
// Table number input

             Integer tableNumber = readLineAsInteger();
             
// HashTable for mappint table to index and table column to index or representing uninqely             
             
             HashMap<String,Integer> hashTable = new HashMap<>();
             HashMap<String,HashMap<String,Integer> > hashTableColumn = new HashMap<>();
             hashTable.clear();
             hashTableColumn.clear();
              for (Integer j = 0; j < tableNumber; j++) {
                  
                 Table table = new Table();
                 
// Input of Table attributes

                 table.setTableName(readLine());
// Dimension                 
                 String tableXY = readLine();
                 ArrayList<String> tableXYList = new ArrayList();
                 tableXYList.addAll(Arrays.asList(tableXY.split("\\s")));
                 table.setColsNumber(Integer.parseInt(tableXYList.get(0)));
                 table.setRowsNumber(Integer.parseInt(tableXYList.get(1)));
// Cols Name
                 table.setColsName(readLine());
                  HashMap<String,Integer> ColumnNameMap = new HashMap<>();
                  ColumnNameMap.clear();
                  Integer cnt = 0;
                 for(String w:table.getColsName() ){
                    ColumnNameMap.put(w, cnt++);
                 }
                 
                 hashTableColumn.put(table.getTableName(), ColumnNameMap);
                 
                 ArrayList<RowData> tableRowDataList = new ArrayList<>();
                 for(Integer k = 0; k<table.getRowsNumber(); k++)
                 {
                     
                 RowData data = new RowData();
                 ArrayList<Integer> rowDataList = new  ArrayList<>();
                 String dataListArray[] = readLine().split("\\s");
                 for(String dt: dataListArray)
                 {
                     rowDataList.add(Integer.parseInt(dt));
                 }
                 data.setRowData(rowDataList);
                 tableRowDataList.add(data);
                  
                 
                 }
                 table.setRows(tableRowDataList);
                 TableList.add(table);
                 hashTable.put(table.getTableName(), j);
                 
                 
           }
              IO.printLine("Test: ".concat(i.toString()));
              
           Integer serialNumberOfData = 0;   
           Integer numberOfQuery = readLineAsInteger();
           
           for(Integer j = 0; j<numberOfQuery; j++) { 
             ArrayList<ArrayList<String>> joinQuery = new ArrayList<>();

               
               for(Integer queryLine = 0; queryLine<4; queryLine++)
               {                    ArrayList<String> singleLine = new ArrayList<>();
                    singleLine.clear();
                     singleLine.addAll(Utility.lineBreakToWord(readLine()));
                     joinQuery.add(singleLine);
               }
                HashMap<String,String> tableToTableMp = new HashMap<>();
                HashMap<String,Integer>tableToIndex = new HashMap<>();
           tableToTableMp.clear();
           tableToIndex.clear();
           
           if(joinQuery.get(1).size() == 3)
           {
               tableToTableMp.put(joinQuery.get(1).get(2), joinQuery.get(1).get(1));
               tableToIndex.put(joinQuery.get(1).get(2), 1);
               
               
           }
            tableToTableMp.put(joinQuery.get(1).get(1), joinQuery.get(1).get(1));
            tableToIndex.put(joinQuery.get(1).get(1), 1);
            if(joinQuery.get(2).size() == 3)
           {
               tableToTableMp.put(joinQuery.get(2).get(2), joinQuery.get(2).get(1));
               tableToIndex.put(joinQuery.get(2).get(2), 2);
           }
            tableToTableMp.put(joinQuery.get(2).get(1), joinQuery.get(2).get(1));
            tableToIndex.put(joinQuery.get(2).get(1), 2);
            
            
          
           
            String tabelOneAndData = joinQuery.get(3).get(1);
            Integer IndexOfDotInTableOne = tabelOneAndData.indexOf(".");
            String tableOne = tabelOneAndData.substring(0,IndexOfDotInTableOne);
            String columnTable1 = tabelOneAndData.substring(IndexOfDotInTableOne+1);
            String tabelTwoAndData = joinQuery.get(3).get(3);
            Integer IndexOfDotInTableTwo = tabelTwoAndData.indexOf(".");
            String tableTwo = tabelTwoAndData.substring(0,IndexOfDotInTableTwo);
            String columnTable2 = tabelTwoAndData.substring(IndexOfDotInTableTwo+1);
            
            String firstTableToJoin = tableToTableMp.get(tableOne);
            String secondTableToJoin = tableToTableMp.get(tableTwo);
            Integer firstTableCompareRow = hashTableColumn.get(firstTableToJoin).get(columnTable1);
            Integer secondTableCompareRow = hashTableColumn.get(secondTableToJoin).get(columnTable2);
            Table table1 = TableList.get(hashTable.get(firstTableToJoin));
            Table table2 = TableList.get(hashTable.get(secondTableToJoin));
            
            ArrayList<String> resultofQuery = new ArrayList<>();

            if(joinQuery.get(0).get(1).equals("*")){
             StringBuilder tableHead = Utility.ListToString(table1.getColsName());
             tableHead.append(" ").append(Utility.ListToString(table2.getColsName()));
             IO.printLine(tableHead);
            resultofQuery = Utility.jointable(table1.getRows(), table2.getRows(), firstTableCompareRow, secondTableCompareRow);
            
            resultofQuery.forEach((data)-> {
                IO.printLine(data);
            });
            
            }
            else {
                ArrayList<Integer> firstTableSelectedColumn = new ArrayList<>();
                ArrayList<Integer> secondTableSelectedColumn = new ArrayList<>();
                ArrayList<Integer> firstTableDataPosition = new ArrayList<>();
                ArrayList<Integer> secondTableDataPosition = new ArrayList<>();
                ArrayList<String> tableheader = new ArrayList<>();
                
                serialNumberOfData = 0;
                for(String word: joinQuery.get(0))
                 {
                     if(word.contains(".")){
                         String[] tt = word.split("\\.");
                         String tableName = tableToTableMp.get(tt[0]);
                         Integer tableIndex = tableToIndex.get(tableName);
                         tableheader.add(tt[1]);
                         Integer tableColIndex =  hashTableColumn.get(tableToTableMp.get(tt[0])).get(tt[1]);
                        
                         if(tableIndex == 1){
                             firstTableSelectedColumn.add(tableColIndex);
                             firstTableDataPosition.add(serialNumberOfData);
                         }
                         else
                         {
                             secondTableSelectedColumn.add(tableColIndex);
                             secondTableDataPosition.add(serialNumberOfData);

                         }
                         serialNumberOfData++;
                         
                     }
                }
                
                 StringBuilder tableHead = Utility.ListToString(tableheader);
                 IO.printLine(tableHead);
                
                 resultofQuery = Utility.jointable(table1.getRows(), table2.getRows(), firstTableCompareRow, secondTableCompareRow, firstTableSelectedColumn,
                         secondTableSelectedColumn, firstTableDataPosition, secondTableDataPosition);
                 resultofQuery.forEach((data)-> {
                 IO.printLine(data);
            });
            }
            System.out.println();
         
           }
                        
        }
       
    }
}
