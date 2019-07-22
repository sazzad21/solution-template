package com.tigerit.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author sazzad
 */
public class Utility {

    public Utility() {
    }

    public static ArrayList<String> lineBreakToWord(String sentence) {
        String[] word = sentence.split("[, ]+");
        ArrayList<String> words = new ArrayList<>();
        for (String w : word) {
            words.add(w);
        }
        return words;
    }

    public static ArrayList<String> lineBreakToWordForDotEqual(String sentence) {
        String[] word = sentence.split("\\s");
        ArrayList<String> words = new ArrayList<>();
        for (String w : word) {
            words.add(w);
        }
        return words;
    }

    public static void PrintData(ArrayList<RowData> tableData1, ArrayList<RowData> tableData2, Integer table1Condition, Integer table2Condition) {

    }

    public static ArrayList<String> jointable(ArrayList<RowData> tableData1, ArrayList<RowData> tableData2, Integer table1Condition, Integer table2Condition) {
        ArrayList<StringBuilder> result = new ArrayList<>();
        tableData1.forEach((_item) -> {
            tableData2.forEach((data2) -> {
                ArrayList<Integer> result1 = new ArrayList<>();
                StringBuilder object = new StringBuilder();
                if (_item.getRowData().get(table1Condition).equals(data2.getRowData().get(table2Condition))) {
                    result1.addAll(_item.getRowData());
                    result1.addAll(data2.getRowData());
                    result.add(numberListToString(result1));
                }
            });
        });

        return toArrayListString(result);

    }

    public static ArrayList<String> jointable(ArrayList<RowData> tableData1, ArrayList<RowData> tableData2,
            Integer table1Condition, Integer table2Condition, ArrayList<Integer> firstTableSelectedColumn, ArrayList<Integer> secondTableSelectedColumn,
            ArrayList<Integer> firstTableDataPosition, ArrayList<Integer> secondTableDataPosition) {
        ArrayList<StringBuilder> result = new ArrayList<>();
        tableData1.forEach((_item) -> {
            for (RowData data2 : tableData2) {
                //tableData2.forEach((data2) -> {
                ArrayList<Integer> result1 = new ArrayList<>();
                for (Integer index = 0; index < (firstTableDataPosition.size() + secondTableDataPosition.size()); index++) {
                    result1.add(1);
                }
                StringBuilder object = new StringBuilder();
                if (_item.getRowData().get(table1Condition).equals(data2.getRowData().get(table2Condition))) {
                    Integer firstTableCntIndex = 0;
                    Integer secondTableCntIndex = 0;
                    for (Integer dataIndex : firstTableSelectedColumn) //firstTableSelectedColumn.forEach((dataIndex) -> 
                    {
                        Integer PP = firstTableDataPosition.get(firstTableCntIndex);
                        result1.set(PP, _item.getRowData().get(dataIndex));
                        firstTableCntIndex++;

                    }
                    //);
                    for (Integer dataIndex : secondTableSelectedColumn) //secondTableSelectedColumn.forEach((dataIndex) ->
                    {
                        Integer PP = secondTableDataPosition.get(secondTableCntIndex);
                        result1.set(PP, data2.getRowData().get(dataIndex));
                        secondTableCntIndex++;
                    }
                    //);

                    result.add(numberListToString(result1));

                    if (table2Condition == 0) {
                        break;
                    }
                }

            }
            //);
        });

        return toArrayListString(result);

    }

    public static StringBuilder ListToString(ArrayList<String> stringList) {
        StringBuilder obj = new StringBuilder("");
        Integer i = 0;
        for (String data : stringList) {
            if (i == 0) {
                obj.append(data);
                i++;
            } else {
                obj.append(" ").append(data);
            }
        }

        return obj;
    }

    public static StringBuilder numberListToString(ArrayList<Integer> stringList) {
        StringBuilder obj = new StringBuilder("");
        Integer i = 0;
        for (Integer data : stringList) {
            if (i == 0) {
                obj.append(data.toString());
                i++;
            } else {
                obj.append(" ").append(data.toString());
            }
        }

        return obj;
    }

    public static ArrayList<String> toArrayListString(ArrayList<StringBuilder> data) {
        ArrayList<String> result = new ArrayList<>();
        data.forEach((d) -> {
            result.add(d.toString());
        });
        Collections.sort(result);
        return result;
    }

}
