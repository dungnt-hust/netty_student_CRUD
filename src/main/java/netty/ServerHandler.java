package netty;

import Dao.StudentDao;
import io.netty.channel.*;

import java.util.Date;
import java.util.List;
//
//public class ServerHandler extends SimpleChannelInboundHandler<String> {
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) {
//        System.out.println("[SERVER]" + ctx.channel().remoteAddress() + " has joined!\n");
//        ChannelFuture f = ctx.writeAndFlush("Actived");
//        f.addListener(ChannelFutureListener.CLOSE);
//    }
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
//        System.out.println("[SERVER]" + ctx.channel().remoteAddress() + " has joined!\n");
//        if(s.contains("list")){
//            System.out.println("wait for list");
//            StudentDao studentDao = new StudentDao();
//            studentDao.listStudent(ctx);
//        }
//        else if (s.contains("create")){
//            String[] dataStudent = s.split("\\s+");
//            StudentDao studentDao = new StudentDao();
//            int idStudent = studentDao.addStudent(dataStudent[1], dataStudent[2], Integer.parseInt(dataStudent[3]));
//            ctx.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + idStudent + '\n');
//        }
//        else if (s.contains("delete")){
////            String[] dataStudent = s.split("\\s+");
////            StudentDao studentDao = new StudentDao();
////            studentDao.deleteEmployee(Integer.parseInt(dataStudent[1]));
////            ctx.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + "remove SuccessFully" + '\n');
//        }
//        else{
//            ctx.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + s + '\n');
//        }
//    }
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("[SERVER]" + ctx.channel().remoteAddress() + " has left\n");
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
//        cause.printStackTrace();
//        ctx.close();
//    }
//}

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[SERVER]" + ctx.channel().remoteAddress() + " has joined!\n");
        ctx.write("Welcome to " + ctx.channel().remoteAddress() + "!\r\n");
        ctx.write("It is " + new Date() + " now.\r\n");
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println("[SERVER]" + ctx.channel().remoteAddress() + " has joined!\n");
        if(s.contains("list")){
            System.out.println("wait for list");
            StudentDao studentDao = new StudentDao();
            studentDao.listStudent(ctx);
        }
        else if (s.contains("create")){
            String[] dataStudent = s.split("\\s+");
            StudentDao studentDao = new StudentDao();
            int idStudent = studentDao.addStudent(dataStudent[1], dataStudent[2], Integer.parseInt(dataStudent[3]));
            ctx.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + idStudent + '\n');
        }
        else if (s.contains("delete")){
            String[] dataStudent = s.split("\\s+");
            StudentDao studentDao = new StudentDao();
            studentDao.deleteStudent(Integer.parseInt(dataStudent[1]));
            ctx.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + "remove SuccessFully" + '\n');
        }
        else if (s.contains("update")){
            String[] dataStudent = s.split("\\s+");
            StudentDao studentDao = new StudentDao();
            studentDao.updateStudent(Integer.parseInt(dataStudent[1]), Integer.parseInt(dataStudent[2]));
            ctx.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + "update SuccessFully" + '\n');
        }
        else{
            ctx.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + s + '\n');
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}