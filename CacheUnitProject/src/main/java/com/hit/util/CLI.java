package com.hit.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Scanner;

public class CLI extends Observable implements Runnable {

    String start;
    String shutdown;
    
    String userString;
    
    Scanner in;
    PrintWriter out;
    
    public CLI(InputStream in, OutputStream out) {
        this.out = new PrintWriter(out, true);
        this.in = new Scanner(in);
        
        start = "start";
        shutdown = "stop";
        
        userString = "";
    }
    
    public void write(String string) {
        out.println(string);
    }
    
    public void run() {
        int flag = 0;
        write("Please enter your command");
        do {
            userString = in.nextLine();
            if(userString.equals(start)) {
                write("Starting server.....");
                flag = 1;
                setChanged();
                notifyObservers(start);
            }
            else if (userString.equals(shutdown)) {
                write("Shutdown server");
                flag = 2;
                setChanged();
                notifyObservers(shutdown);
            }
            else {
                write("Not a valid command");
            }
        } while (flag == 0);
    }
}
