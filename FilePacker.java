/////////////////////
//
// Final Packing Code
//
////////////////////
import java.io.*;
import java.util.*;

class FilePacker
{
    public static void main(String A[]) throws Exception
    {
       Scanner sobj = new Scanner(System.in);
       String FolderName = null;
       String PackFileName = null;
       String header ="";
       byte Buffer[] = new byte[1024]; // for data reading
      byte bHeader[] = null; // for header in bytes
       int iRet = 0;
       int size = 0;
       int i = 0, j = 0;

       FileOutputStream foobj = null;
       FileInputStream  fiobj = null;

       System.out.println("Enter Folder Name : ");
       FolderName = sobj.nextLine();

       System.out.println("Enter The Name Of Packed File : ");
       PackFileName = sobj.nextLine();


       File fobjfolder = new File(FolderName);

       if((fobjfolder.exists()) &&(fobjfolder.isDirectory()))
       {
         System.out.println("Folder Exist");

         File fobjpack = new File(PackFileName);
        
         fobjpack.createNewFile(); // pack file gets created 

         foobj = new FileOutputStream(fobjpack);

         File fArr[] = fobjfolder.listFiles();

         System.out.println("Number Of Files In Folder : "+fArr.length);

         for(i = 0; i < fArr.length; i++)
         {
            fiobj = new FileInputStream(fArr[i]);

            header = header + fArr[i].getName();
            header = header + " ";
            header = header + fArr[i].length();


            size = 100 - header.length();

            for(j = 1; j <= size; j++)  // create header with white spaces 
            {
              header = header + " ";
            }

            bHeader = header.getBytes();


            // write file name and size

            foobj.write(bHeader);
            

            // loop to read from fiobj and write to foobj   
            
            while((iRet = fiobj.read(Buffer)) != -1)
            {
              foobj.write(Buffer,0,iRet);
            }
          

            fiobj.close();
            header = "";
         }

         foobj.close();
         sobj.close();     
       }
       else
       {
         System.out.println("Folder Is Not Exist");
       }
    }
    
}
