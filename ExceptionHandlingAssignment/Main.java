



import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import  java.util.Collections;
// main class
public class Main {
    public static void main(String[] args)
    {
        try {
            // take file name from the command line
            String filename = args[0];
            // test extention of the file
            if (!(filename.endsWith(".arxml"))) {
                throw new NotValidAutosarFileException("Not Valid Extention");
            }
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            StringBuilder fileContent = new StringBuilder(); // we will use string builder to edit string
            // empty file exception
            if (file.length() == 0) {
                throw new EmptyAutosarFileException("Warning: Empty file");
            }
            int var;
            // read file content
            while ((var = fis.read()) != -1) // read method will throw an ioException will be handled below
            {
                fileContent.append((char) var);
            }
            // System.out.println(fileContent); // test input content
            String content = fileContent.toString();
            Scanner cursor = new Scanner(content);
            ArrayList<Container> containers = new ArrayList<>();
            while (cursor.hasNextLine()) {
                String line = cursor.nextLine();
                // get id
                if (line.contains("<CONTAINER")) {
                    String id = line.substring(line.indexOf("ID="), line.indexOf(">"));
                    String sntag = cursor.nextLine();
                    String sn = sntag.substring(sntag.indexOf("E>") + 1, sntag.indexOf("</"));
                    String lntag = cursor.nextLine();
                    String ln = lntag.substring(lntag.indexOf("E>") + 1, lntag.indexOf("</"));
                    Container container = new Container(id, sn, ln);
                    containers.add(container);
                }
            }
            String newName;
            Collections.sort(containers);
            newName = filename.substring(0, filename.indexOf(".arxml")) + "_mod.arxml";
            FileOutputStream fos = new FileOutputStream(newName);
            fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes());
            fos.write("\n<AUTOSAR>\n".getBytes());
            for (int i = 0; i < containers.size(); i++) {
                fos.write(containers.get(i).toString().getBytes());
            }
            fos.write("</AUTOSAR>".getBytes());        } catch (FileNotFoundException e)
        {
            e = new FileNotFoundException("File Not found");
            System.out.println(e.getMessage());
        } catch (NotValidAutosarFileException e) {

        } catch (EmptyAutosarFileException e)
        {

        } catch (IOException e)
        {
            e = new IOException("Warning i/o Exception");
        }
    }
}
