package server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by yookeun on 2017. 8. 24..
 */
public class DisCardServerHandler extends SimpleChannelInboundHandler<Object> {

    //지정된 포트로 접속한 클라이언트가 데이터를 전송하면 자동으로 이 메소드가 실행된다
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //nothing to do
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
