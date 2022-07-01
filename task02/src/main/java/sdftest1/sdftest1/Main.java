package sdftest1;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Main 
{
    public static void main( String[] args ) 
    {
        try {
            Socket sock = new Socket("68.183.239.26",80);
            System.out.println("connected");
            
            OutputStream os = sock.getOutputStream();
            InputStream is = sock.getInputStream();

            ObjectOutputStream oos = new ObjectOutputStream(os);           
            ObjectInputStream ois = new ObjectInputStream(is);

            String serverReq = ois.readUTF();

            String[] line = serverReq.split(" ");
            String reqID = line[0];
            String listInt = line[1];

            //testing
            // System.out.println(reqID + listInt);

            String [] intToCalc = listInt.split(",");

            float totalSum = 0;

            for (int i =0; i < intToCalc.length; i++ ){
                totalSum = totalSum + Integer.parseInt(intToCalc[i]);  
            }
            float ans = totalSum / intToCalc.length ;

            oos.writeUTF(reqID);
            oos.writeUTF("Ong Jun Ping");
            oos.writeUTF("ongjunping@gmail.com");
            oos.writeFloat(ans);
            oos.flush();

            Boolean serverResp = null;
            serverResp = ois.readBoolean();

            if (serverResp){
                System.out.println("SUCCESS");
                sock.close();
            } else if (serverResp == false) {
                String errorResp = ois.readUTF();
                sock.close();
                System.out.printf("FAILED %s", errorResp);
            }
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}