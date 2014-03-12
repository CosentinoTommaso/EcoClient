/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author cosentino.tommaso
 */
public class EcoClient {
    public static void main(String[] args) {
        int port;
        String host;
        
  try{
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci host: ");
        host = sc.nextLine();
        System.out.println("Inserisci porta: ");
        port = sc.nextInt();
        InetAddress serverAddress=InetAddress./*getLocalHost();*/getByName(host);        
        Socket s=new Socket(serverAddress,port);        
        BufferedReader streamIn=new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter streamOut=new PrintWriter(new OutputStreamWriter (s.getOutputStream()),true); //stream x gestire il flusso di input
        BufferedReader std_in=new BufferedReader(new InputStreamReader(System.in)); //input da tastiera
        PrintWriter std_out=new PrintWriter(new OutputStreamWriter (System.out),true);
        
        while(true){
            System.out.println("Inserisci stringa: ");
            String scritta=std_in.readLine();
            streamOut.println(scritta);
            streamOut.flush();
            String in_arrivo = streamIn.readLine();
            if(in_arrivo.equals("fine")){
                System.out.println("Uscita");
                break; 
            }
            
            System.out.println(in_arrivo);
        }
        
        
    }catch(IOException e){
        System.err.println(e);
    }
    }
}
