package sdftest1;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main 
{
    public static void main( String[] args ) throws IOException
    {
        String csvPath = args[0];
        String tempPath = args[1];

        File csvFile = new File(csvPath);
        File tempFile = new File(tempPath);
        
        
        Scanner csvScan = new Scanner(csvFile);
        csvScan.useDelimiter("\n");
        

        //create variable names
        String csvHeader = "";
        csvHeader = csvScan.nextLine();
        // System.out.println(csvHeader);

        String[] vars = csvHeader.split(",");

        String var1 = "__" + vars[0] + "__";
        String var2 = "__" + vars[1] + "__";
        String var3 = "__" + vars[2] + "__";
        String var4 = "__" + vars[3] + "__";
        // System.out.println(var1 + var2 + var3 + var4);


        List<String> csvInfo = new LinkedList<>();
        while (csvScan.hasNext()) {
            csvInfo.add(csvScan.next());
        }
        // System.out.println(csvInfo);
        csvScan.close();

        Scanner tempScan = new Scanner(tempFile);
        StringBuffer buffer = new StringBuffer();

        while (tempScan.hasNextLine()) {
            buffer.append(tempScan.nextLine()+System.lineSeparator());
         }

         String tempContent = buffer.toString();
         String newContent;
         tempScan.close();

         for (int i =0; i < csvInfo.size(); i++){
            String[] terms = csvInfo.get(i).split(",");
            for (int j =0; j < terms.length; j++){
                terms[j] = terms[j].replace("\\n", System.lineSeparator());
                //     System.out.println(terms[j]);                
            } 
            String info1 = terms[0];
            String info2 = terms[1];
            String info3 = terms[2];
            String info4 = terms[3]; 
            // System.out.println(info1 +" " + info2 +" "+ info3 +" " + info4);
            newContent = tempContent.replaceAll(var1,info1);
            newContent = newContent.replaceAll(var2,info2);
            newContent = newContent.replaceAll(var3,info3);
            newContent = newContent.replaceAll(var4,info4);          
            System.out.println(newContent);
         }


    }
}
