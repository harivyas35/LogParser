

import java.awt.FlowLayout;

import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;

 

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import javax.swing.JProgressBar;

import javax.swing.SwingUtilities;

import javax.xml.parsers.ParserConfigurationException;

 

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.xssf.streaming.SXSSFSheet;

 

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import org.xml.sax.SAXException;

 

public class FileParser {

      

       static SXSSFWorkbook workbook = new SXSSFWorkbook(100);

 

       public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

             int firstFile=1;

             int worksheetLimit;

             ArrayList<String> LogType = new ArrayList<String>();

             String LogFilePath,UserInput,Path,outputFilePath,logFilesListFileLocation;

             boolean Flag;

            

             XmlSplitter obj=new XmlSplitter(); 

             obj.readXmlFile();

            

             LogFilePath=obj.getFilePath();           

             UserInput=obj.checkForUserInput();

            

             outputFilePath=obj.getOutputFilePath();

             logFilesListFileLocation=obj.getlogFilesListFileLocation();

            

             FileLister objj=new FileLister(LogFilePath, UserInput,obj);

             LogType=obj.ParseXML();

            

             Path=objj.Lister(UserInput);

             Flag= objj.PassFlag();

            

             FileParser object =new FileParser(); 

             worksheetLimit=obj.getWorksheetLimit();

            

             int [] arr=new int[ LogType.size()];

             int [] sheetName=new int[ LogType.size()];

            

             for(int i=0;i<LogType.size();i++)

             {

                    arr[i]=0;

             }

             for(int i=0;i<LogType.size();i++)

             {

                    sheetName[i]=0;

             }

             ArrayList<String>[] dataList = (ArrayList<String>[]) new ArrayList[LogType.size()];

             for (int i = 0; i < LogType.size(); i++) {

                 dataList[i] = new ArrayList<String>();               

             }

            

            object.createExcel(Flag, LogType,Path,firstFile,dataList,outputFilePath, object,worksheetLimit,logFilesListFileLocation);            

                  

       }

      

    

// 

       

       public void createExcel(boolean Flag,ArrayList <String > LogType,String Path,int firstFile,ArrayList <String> []dataList,String outputFilePath,FileParser object, int worksheetLimit,String logFilesListFileLocation)

       {

          String LineRead;

           int firstSheets=1;

           int sheetCounter = 0;

           int inputFilesCount=0;

           int [] sheetName=new int[ LogType.size()];

           int [] arr=new int[ LogType.size()];

           for(int i=0;i<LogType.size();i++)

           {

                  sheetName[i]=0;

           }

          

           for(int i=0;i<LogType.size();i++)

           {

                  arr[i]=0;

           }

           if(!Flag)

           {   

              

              int rowCount=0;

                  File folder = new File(Path);

                    File[] listOfFiles = folder.listFiles();

                    inputFilesCount=listOfFiles.length;

                   

                   

                    final int MAX = listOfFiles.length;

                    int ii=1;                   

                    final JFrame frame = new JFrame("Files Processing Please Wait");

                   

                    

                    final JProgressBar pb = new JProgressBar();

                    pb.setMinimum(0);

                    pb.setMaximum(MAX);

                    pb.setStringPainted(true);

                                

                    frame.setLayout(new FlowLayout());

                    frame.getContentPane().add(pb);

            

                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    frame.setSize(500, 200);

                    frame.setLocationRelativeTo(null);

                    frame.setVisible(true);

                    FileWriter writer = null;

                                  try {

                                        writer = new FileWriter(logFilesListFileLocation);

                                  } catch (IOException e1) {

                                       

                                        e1.printStackTrace();

                                  }

                                  try {

                                        writer.write("The Following Files Has Been Processed \n"+System.lineSeparator());

                                  } catch (IOException e2) {

                                       

                                        e2.printStackTrace();

                                  }

                    for(File str: listOfFiles) {

                      try {

                             

                                        writer.write(str + System.lineSeparator());

                                  } catch (IOException e) {

                                       

                                        e.printStackTrace();

                                  }

                    }

                    try {

                                        writer.close();

                                  } catch (IOException e1) {

                                       

                                        e1.printStackTrace();

                                  }

                    for (File file : listOfFiles)

                    {     

                   

                        if (file.isFile()) {

                           String FileName;

                           FileName=file.getName();

                           String Name=file.toString();

                          System.out.println("FileName from main  is" + Name);

                          BufferedReader br = null;

                                        try {

                                               br = new BufferedReader(new FileReader(file));

                                        } catch (FileNotFoundException e) {

                                              

                                               e.printStackTrace();

                                              

                                        }

                        

                          if(firstFile==1)

                          {

                          try {

                                               while ((LineRead = br.readLine()) != null)

                                                             {  for(int z=0;z<LogType.size();z++){

                                                                    if (LineRead.contains(LogType.get(z)))

                                                                    {

                                                                           dataList[z].add(LineRead);                                               

                                                                    }

                                                             }

                                                            

                                                             }

                                        } catch (IOException e) {

                                              

                                               e.printStackTrace();

                                        }

                          try {

                                               br.close();

                                        } catch (IOException e) {

                                              

                                               e.printStackTrace();

                                        }

                          firstFile++;

                          }

                                                   

                                      if (firstSheets==1)

                                            

                                      {

                                          Row row;

                                      for (int i=0;i<LogType.size();i++)                                                                               

                                      {                                       

                                            Sheet spreadsheet =  workbook.createSheet(LogType.get(i)+sheetCounter);

                                             sheetName[i]++;

                                            rowCount = spreadsheet.getLastRowNum();                                            

                                            for (int j=0;j<dataList[i].size();j++)                                            

                                            {                                              

                                               row = spreadsheet.createRow(j);

                                               Cell cell = row.createCell(0);                                        

                                               cell.setCellValue((dataList[i].get(j)));                                                                                              

                                              arr[i]++;

                                            }

                                       

                                          firstSheets++;

                                         

                                      }

                               

                        }

                                      else

                                      {

                                        for(int z=0;z<LogType.size();z++){

                                        BufferedReader br1 = null;

                                                                   try {

                                                                          br1 = new BufferedReader(new FileReader(file));

                                                                   } catch (FileNotFoundException e) {

                                                                         

                                                                          e.printStackTrace();

                                                                   }

                                                 Row row;

                                         try {

                                                                          while ((LineRead = br1.readLine()) != null)

                                                                             { 

                                                                                    if (LineRead.contains(LogType.get(z)))

                                                                                    {

                                                                                        SXSSFSheet spreadsheet=workbook.getSheet(LogType.get(z)+sheetCounter) ;

                                                                                        rowCount=spreadsheet.getLastRowNum();                                                        

                                                                                        if(rowCount/worksheetLimit>=1)

                                                                                        {                                                                 

                                                                                               try {

                                                                                                            object.createMulSheets(sheetName,LogType,z,br1,0,worksheetLimit);

                                                                                                     } catch (IOException e) {

                                                                                                           

                                                                                                            e.printStackTrace();

                                                                                                     }                                                                  

                                                                                        }

                                                                                         row = spreadsheet.createRow(++rowCount);

                                                                                         Cell cell = row.createCell(0);                                                 

                                                                                         cell.setCellValue(LineRead);

                                                                                         arr[z]++;

                                                                                                                                         

                                                                                    }

                                                                                  

                                                                             }

                                                                   } catch (IOException e) {

                                                                         

                                                                          e.printStackTrace();

                                                                   }

                                          

                                           

                               try {

                                                      br1.close();

                                               } catch (IOException e) {

                                                     

                                                      e.printStackTrace();

                                               }  

                                        }

                                      }                                      

                                      for(int i=0;i<LogType.size();i++)

                                      {

                                             dataList[i].clear();

                                                                                            

                                      }

                                      

                                      

                        

                                      final int currentValue = ii;                       

                                      try {

                                          SwingUtilities.invokeLater(new Runnable() {

                                              public void run() {

                                                  pb.setValue(currentValue);

                                              }

                                          });

                                          java.lang.Thread.sleep(100);

                                      } catch (InterruptedException e)

                                      {

                                          JOptionPane.showMessageDialog(frame, e.getMessage());

                                          JOptionPane.showMessageDialog(null, "Error Occured");

                                      }

 

                                      ii++;            

                                      

                                      

                                      

                     

                                                   

                        }

                       

                     

                    }

                    JOptionPane.showMessageDialog(null, "Wait Until The Files Processed Pop Up Is Displayed,Till Then Don't Close The Progress Window");

                    FileOutputStream out = null;

                                  try {

                                        out = new FileOutputStream(

                                                new File(outputFilePath));

                                  } catch (FileNotFoundException e) {

                                        JOptionPane.showMessageDialog(null, "Error Occured");

                                        e.printStackTrace();

                                  }

                         

                          try {

                                               workbook.write(out);

                                               JOptionPane.showMessageDialog(null, " \t" +inputFilesCount+" \t Files Has Been Processed");

                                        } catch (IOException e) {

                                               JOptionPane.showMessageDialog(null, "Error Occured");

                                               e.printStackTrace();

                                        } 

                          try {

                                               workbook.close();

                                               JOptionPane.showMessageDialog(null, "Output File Has Been Generated At \t" +outputFilePath+" \t Location");

                                        } catch (IOException e) {

                                               JOptionPane.showMessageDialog(null, "Error Occured");

                                               e.printStackTrace();

                                              

                                        }

                          try {

                                               out.close();

                                               JOptionPane.showMessageDialog(null, "Close The Progress Window");

                                        } catch (IOException e) {

                                               JOptionPane.showMessageDialog(null, "Error Occured");

                                               e.printStackTrace();

                                              

                                        }

           }

          

           

           

           else {

               SXSSFWorkbook workbook = new SXSSFWorkbook();

               Row row;

               File file1 = new File(Path);

               BufferedReader br = null;

                    try {

                           br = new BufferedReader(new FileReader(file1));

                    } catch (FileNotFoundException e) {

                          

                           e.printStackTrace();

                    }                                      

               try {

                           while ((LineRead = br.readLine()) != null)

                              {  for(int z=0;z<LogType.size();z++){

                                     if (LineRead.contains(LogType.get(z)))

                                     {

                                            dataList[z].add(LineRead);

                                            System.out.println("Done");

                                            arr[z]=dataList[z].size();

                                     }

                              }

                              }

                    } catch (IOException e) {

                          

                           e.printStackTrace();

                    }                                                                 

               for (int i=0;i<LogType.size();i++){

              SXSSFSheet spreadsheet =  workbook.createSheet(LogType.get(i));

              for(int j=0;j<dataList[i].size();j++)

                

              {

              row = spreadsheet.createRow(j);

              Cell cell = row.createCell(0);

            

            cell.setCellValue((dataList[i].get(j)));

           

              }

               }

               FileOutputStream out = null;

                    try {

                           out = new FileOutputStream(

                                new File(outputFilePath));

                    } catch (FileNotFoundException e) {

                           JOptionPane.showMessageDialog(null, "Error Occured");

                           e.printStackTrace();

                    }

             

              try {

                           workbook.write(out);

                           JOptionPane.showMessageDialog(null, "File Has Been Processed");

                    } catch (IOException e) {

                           JOptionPane.showMessageDialog(null, "Error Occured");

                           e.printStackTrace();

                    }

              try {

                           workbook.close();

                           JOptionPane.showMessageDialog(null, "Output File Has Been Generated At \t" +outputFilePath+ "\t Location");

                    } catch (IOException e) {

                           JOptionPane.showMessageDialog(null, "Error Occured");

                           e.printStackTrace();

                    }

              try {

                           out.close();

                    } catch (IOException e) {

                           JOptionPane.showMessageDialog(null, "Error Occured");

                           e.printStackTrace();

                    }

             

            

        }                  

               

       }

       public <BufferedReader> int createMulSheets(int [] sheetCounter,ArrayList<String> logType,int z, BufferedReader br,int j,int worksheetLimit) throws IOException

       { 

          String LineRead;

       int [] arrayCounter=sheetCounter;

         

        

       SXSSFSheet spreadsheet1=workbook.getSheet(logType.get(z)+(arrayCounter[z]-1));

       int t=spreadsheet1.getLastRowNum();

     if(spreadsheet1.getLastRowNum()<(worksheetLimit-1))

      

     {

        while ((LineRead =  ((java.io.BufferedReader) br).readLine()) != null)

          {

                if (LineRead.contains(logType.get(z)))

             {

                       

                       Row row;

                       row = spreadsheet1.createRow( t);

                 Cell cell = row.createCell(0);                                                  

                 cell.setCellValue(LineRead);                

                 t=t+1;

                 if(t/worksheetLimit>=1)

                 {

                    try{

                       createMulSheets(  arrayCounter, logType,z, br,0,worksheetLimit);

                    }

                    catch (IOException e)

                    {   JOptionPane.showMessageDialog(null, "Error Occured");

                           e.printStackTrace();

                    }

                       

                 }

             }

          }

     }

      

     else{

       

       SXSSFSheet spreadsheet=workbook.createSheet(logType.get(z)+arrayCounter[z]) ;     

       arrayCounter[z]++;    

          while ((LineRead = ((java.io.BufferedReader) br).readLine()) != null)

          {

                if (LineRead.contains(logType.get(z)))

               {

                       

                       Row row;

                       row = spreadsheet.createRow( j);

                   Cell cell = row.createCell(0);                                                 

                   cell.setCellValue(LineRead);                

                   j++;

                   if(j/worksheetLimit>=1)

                   {

                       try{

                       createMulSheets(  sheetCounter, logType,z, br,0,worksheetLimit);

                       }

                       catch(IOException e)

                       {

                            JOptionPane.showMessageDialog(null, "Error Occured");

                           e.printStackTrace();

                       }

                   }

               }

          }            

       }

       return t;

       }

      

      

       

}
