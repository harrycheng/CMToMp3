package com.harry.music;

import com.sun.java.swing.plaf.windows.WindowsTextFieldUI;

import java.io.*;

public class CMToMp3Main {

    public static void main(String[] args) {
        if( args.length < 1){
            System.out.println(" for example:  java -jar CMToMp3-1.0-SNAPSHOT.jar 25714082-128-20a62240b63638d3019771be4010b1e0.uc ");
            return;
        }

        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            String infileName = args[0];
            String outfileName = String.format("%s.mp3", infileName);

            File inFile = new File(infileName);
            File outFile = new File(outfileName);

            dis = new DataInputStream(new FileInputStream(inFile));
            dos = new DataOutputStream(new FileOutputStream(outFile));
            byte[] b = new byte[1024];
            int len;
            while ((len = dis.read(b)) != -1) {
                for (int i = 0; i < len; i++) {
                    b[i] ^= 0xa3;
                }
                dos.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}