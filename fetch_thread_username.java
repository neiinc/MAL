import java.util.*;
import java.net.*;

//Clipboard
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

//to find - href="/profile/

class fetch_thread_username
{
    static String content = null;
    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);
        URLConnection connection = null;
        
        try
        {
            System.out.println("Enter Link:");
            String link=sc.nextLine();
            System.out.print("Fetching...");
            
            connection =  new URL(link).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        find();
    }

    static void find()
    {
        String userClip="";

        while(content.length()!=0)
        {
            int pos=content.indexOf("href=\"/profile/");
            if(pos==-1)
                break;
            int posS=content.indexOf("\"",pos+6);

            String username=content.substring(pos+15,posS);

            if(userClip.indexOf(username)==-1)
            {
                userClip=userClip+"@"+username+"\n";
            }
            content=content.substring(posS);
        }
        System.out.println("\b\b\b\b\b\b\b\b\b\b\bDone!!         ");
        System.out.println("");
        System.out.println(userClip);
        System.out.println("All the above usernames have been copied to clipboard.");
        System.out.println("~made by neiinc (https://myanimelist.net/profile/neiinc)");

        StringSelection stringSelection = new StringSelection(userClip);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}