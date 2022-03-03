package netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

public class ClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg){
        System.out.println(msg);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable msg){
        msg.printStackTrace();
        ctx.close();
    }
}