

import java.io.File;

import java.io.IOException;

 

import javax.swing.JDialog;

import javax.swing.JFileChooser;

import javax.swing.JOptionPane;

import javax.swing.filechooser.FileNameExtensionFilter;

import javax.xml.parsers.ParserConfigurationException;

 

import org.xml.sax.SAXException;

 

public class FileLister {

               

                XmlSplitter object=null;

                String logFilePath1, UserInput1,FilePath,isSingleFile;

                boolean Flag;

                  public FileLister(String logFilePath2, String UserInput2, XmlSplitter obj) {

                UserInput1=UserInput2;

                logFilePath1=logFilePath2;

                object=obj;

               

                  

                    }

               

 

                public String Lister(String UserInput) throws IOException, ParserConfigurationException, SAXException {

 

                               if(UserInput.equalsIgnoreCase("Yes")||UserInput.equalsIgnoreCase("No"))

                               {

                              

                               if (UserInput.equalsIgnoreCase("Yes")) {

                                              

                                               String Path=getFileFromUser();

                                               return Path;

                               } else

 

                               {

                                              

                               isSingleFile=object.checkforMultiple();

         FilePath=logFilePath1;

         File file1=new File(FilePath);

         if(file1.isDirectory()&& isSingleFile.equalsIgnoreCase("Yes"))

         {

                 JOptionPane.showMessageDialog(null, "Enter The Complete File Path ,You Have Entered Directory Path LogParserData.xml File");

                 System.exit(0);

         }

         if(file1.isFile()&& isSingleFile.equalsIgnoreCase("No"))

         {

                 JOptionPane.showMessageDialog(null, "Enter The Directory Path ,You Have Entered File Path Path LogParserData.xml File");

                 System.exit(0);

         }

         if(isSingleFile.equalsIgnoreCase("Yes") ||isSingleFile.equalsIgnoreCase("No"))

         {

         if(isSingleFile.equalsIgnoreCase("Yes"))

                 

         {

                 Flag=true;

         }

        

         else

                 

         {

                 Flag=false;

         }

         }

         else

         {

                 JOptionPane.showMessageDialog(null, "Enter Either Yes Or No In isSingleFile Field Of the LogParserData.xml File");

                 System.exit(0);

                 return null;

         }

                               }

                               }

                               else

                               {

                                               JOptionPane.showMessageDialog(null, "Enter Either Yes Or No In User Input Field Of the LogParserData.xml File");

                                               System.exit(0);

                                               return null;

                               }

                               return FilePath;

                }

  public boolean PassFlag()

  { 

                  

                return Flag;

                 

  }

                public String getFileFromUser() throws IOException

                {

                              

                              

                                JDialog.setDefaultLookAndFeelDecorated(true);

                                   int response = JOptionPane.showConfirmDialog(null, "Select Yes For Folder \n No For Single File", "\nSelect No For Single File",

                                       JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                                   if (response == JOptionPane.NO_OPTION) {

                                              

                                              

                                                               JFileChooser fc=new JFileChooser();

                                                              

                                                               fc.removeChoosableFileFilter(fc.getAcceptAllFileFilter());

                                                               FileNameExtensionFilter ff=new FileNameExtensionFilter("txt" ,"txt files (*.txt)", "log","log files (*.log)");

                                                               fc.addChoosableFileFilter(ff);//ff added to filechooser

                                                               fc.setFileFilter(ff);//st ff as default selection

                                                               fc.setFileSelectionMode(JFileChooser.FILES_ONLY);//user must select a file not folder

                                                               fc.setMultiSelectionEnabled(false);//disabled selection of multiple files

                                                               fc.showOpenDialog(null);

                                                              

                                                               int returnValue = fc.showOpenDialog(null);

                                                               File file5=null;

                                                               String filePath = null;

                                                               if (returnValue == JFileChooser.APPROVE_OPTION) {

                                                                              file5 = fc.getSelectedFile();

                                                               }

                                                               if (file5 != null) {

                                                                              filePath = file5.getPath();          

                                                                              Flag=true;

                                                                              filePath=filePath.replace('\\','/');

                                                                              return filePath;

                                                                             

                                                               }                                                            

                                     System.out.println("No button clicked");//

                                   }

                                   else if (response == JOptionPane.YES_OPTION) {

                                               JFileChooser chooser = new JFileChooser();

                                                   chooser.setCurrentDirectory(new java.io.File("."));

                                                   chooser.setDialogTitle("choosertitle");

                                                   chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                                                   chooser.setAcceptAllFileFilterUsed(false);

 

                                                   if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                                                    

                                                     String DirectoryPath=chooser.getSelectedFile().toString();

                                                     Flag=false;

                                                     DirectoryPath=DirectoryPath.replace('\\','/');

                                                     return DirectoryPath;

                                                   } else {

                                                     System.out.println("No Selection ");

                                                   }

                                   } else if (response == JOptionPane.CLOSED_OPTION) {

                                     System.out.println("JOptionPane closed");

                                   }

                                               return null;

 

                  }

 

 

                }

 
