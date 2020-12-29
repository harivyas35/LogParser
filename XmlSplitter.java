
import java.io.File;

import java.io.FileFilter;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.io.InputStream;

import java.util.ArrayList;

import java.util.Properties;

 

import javax.swing.JFileChooser;

import javax.swing.filechooser.FileNameExtensionFilter;

import javax.xml.parsers.DocumentBuilder;

import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;

 

import org.w3c.dom.Document;

import org.w3c.dom.Node;

import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

 

public class XmlSplitter {

                String xmlFilePath;

               

                public void readXmlFile()

                {

                              

                               JFileChooser fc=new JFileChooser();

                              

                               fc.removeChoosableFileFilter(fc.getAcceptAllFileFilter());

                               FileNameExtensionFilter ff=new FileNameExtensionFilter("xml" ,"xml files (*.xml)", "xml");

                               fc.addChoosableFileFilter(ff);//ff added to filechooser

                               fc.setFileFilter(ff);//st ff as default selection

                               fc.setFileSelectionMode(JFileChooser.FILES_ONLY);//user must select a file not folder

                               fc.setMultiSelectionEnabled(false);//disabled selection of multiple files

                               fc.showOpenDialog(null);

                              

                               int returnValue = fc.showOpenDialog(null);

                               File file5=null;

                               if (returnValue == JFileChooser.APPROVE_OPTION) {

                                               file5 = fc.getSelectedFile();

                               }

                               if (file5 != null) {

                                               xmlFilePath = file5.getPath();

                                              

                                              

                                               xmlFilePath=xmlFilePath.replace('\\','/');

                                               System.out.println("The Xml path is "+xmlFilePath);

                               }                                            

                }

               

                public ArrayList<String> ParseXML() throws SAXException, IOException, ParserConfigurationException {

               

 

                               DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

                               DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

                               Document document1 = docBuilder.parse(xmlFilePath);

                               Node n = document1.getFirstChild();

                               NodeList nl = n.getChildNodes();

                               Node an, an2;

                               ArrayList<String> LogTypes = new ArrayList<String>();

 

                               for (int i = 0; i < nl.getLength(); i++) {

                                               an = nl.item(i);

                                               if (an.getNodeType() == Node.ELEMENT_NODE) {

                                                               NodeList nl2 = an.getChildNodes();

 

                                                               for (int i2 = 0; i2 < nl2.getLength(); i2++) {

                                                                              an2 = nl2.item(i2);

                                                                              if (an2.hasChildNodes()) {

                                                                                              LogTypes.add(an2.getFirstChild().getTextContent());

                                                                              }

                                                               }

                                               }

                               }

 

                               return LogTypes;

                }

               

                public String checkforMultiple() throws ParserConfigurationException, SAXException, IOException

                {

                               DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

                               DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

                               Document document1 = docBuilder.parse(xmlFilePath);

                               NodeList isFile=document1.getElementsByTagName("isSingleFile");                

                               Node isisFile=isFile.item(0);

                               String isFileCheck;

                               isFileCheck=isisFile.getTextContent();

                              

                                               return isFileCheck;

                }

                public String getFilePath() throws ParserConfigurationException, SAXException, IOException

               

                {

                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

                Document document1 = docBuilder.parse(xmlFilePath);

                NodeList getLogFilePath=document1.getElementsByTagName("LogFilePath");

                Node LogFilePath=getLogFilePath.item(0);

                String FilePath;

                FilePath=LogFilePath.getTextContent();

               

                               return FilePath;

                              

                }

                public String checkForUserInput () throws ParserConfigurationException, SAXException, IOException

                {

                               DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

                               DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

                               Document document1 = docBuilder.parse(xmlFilePath);

                               NodeList getLogFilePath=document1.getElementsByTagName("UserInput");

                               Node LogFilePath=getLogFilePath.item(0);

                               String UserInput;

                               UserInput=LogFilePath.getTextContent();

                              

                              

 

                                               return UserInput;

               

                                              

               

                }

public String getOutputFilePath() throws ParserConfigurationException, SAXException, IOException

               

                {

                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

                Document document1 = docBuilder.parse(xmlFilePath);

                NodeList getLogFilePath=document1.getElementsByTagName("FilePath");

                Node outputFilePath=getLogFilePath.item(0);

                String FilePath;

                FilePath=outputFilePath.getTextContent();

               

                               return FilePath;

                              

                }

public int getWorksheetLimit() throws ParserConfigurationException, SAXException, IOException

{

                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

                Document document1 = docBuilder.parse(xmlFilePath);

                NodeList limit=document1.getElementsByTagName("worksheetLimit");                        

                Node worksheetLimit=limit.item(0);

                String isFileCheck;

                int limitForWorksheet;

                isFileCheck=worksheetLimit.getTextContent();

                limitForWorksheet=Integer.parseInt(isFileCheck);

               

                               return limitForWorksheet;

}

 

public String getlogFilesListFileLocation() throws ParserConfigurationException, SAXException, IOException

{

                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

                Document document1 = docBuilder.parse(xmlFilePath);

                NodeList limit=document1.getElementsByTagName("logFilesListFileLocation");                        

                Node worksheetLimit=limit.item(0);

                String logFilesListFileLocation;

                int limitForWorksheet;

                logFilesListFileLocation=worksheetLimit.getTextContent();

                //limitForWorksheet=Integer.parseInt(isFileCheck);

               

                               return logFilesListFileLocation;

}

 

}
