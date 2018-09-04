package com.yin.netty.action;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PlainOioServer {
    public void server(int port) throws Exception{
        //bind server to port
        final ServerSocket socket = new ServerSocket(port);

        try {
            while (true){
                //accept connection
                final Socket clientSocket = socket.accept();

                System.out.println("Accepted connection from "+clientSocket);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream out;
                        try {
                            out = clientSocket.getOutputStream();
                            //write message to connected lient
                            out.write("Hi!\r\n".getBytes(CharsetUtil.UTF_8));
                            out.flush();
                            clientSocket.close();
                        }catch (IOException e){
                            try {
                                clientSocket.close();
                            }catch (IOException e1){
                                e1.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            socket.close();
        }
    }

    public static void main(String[] args) throws Exception {
        new PlainOioServer().server(8888);
    }
}
