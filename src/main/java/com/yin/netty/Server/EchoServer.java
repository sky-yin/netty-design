package com.yin.netty.server;

import com.yin.netty.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

    final int port;

    public EchoServer(int port){
        this.port = port;
    }

    public void start() throws Exception{

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(group).channel(NioServerSocketChannel.class).localAddress(port)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            ChannelFuture  f = server.bind().sync();

            System.out.println(EchoServer.class.getName() + " started and listen " + f.channel().localAddress());

            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServer(65535).start();
    }

}

