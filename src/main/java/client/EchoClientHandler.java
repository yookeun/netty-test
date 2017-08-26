package client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * Created by yookeun on 2017. 8. 26..
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    //소켓채널이 최초 활성화할때 실행된다
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String sendMessage = "Hello, Netty";

        ByteBuf messageBuffer = Unpooled.buffer();
        messageBuffer.writeBytes(sendMessage.getBytes());

        StringBuilder builder = new StringBuilder();
        builder.append("전송한 문자열[");
        builder.append(sendMessage);
        builder.append("]");
        System.out.println(builder.toString());
        ctx.writeAndFlush(messageBuffer);
    }

    //서버로터 수신된 메시지가 있을때 호출되는 메소드이다
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String readMessage = ((ByteBuf) msg).toString(Charset.defaultCharset());
        StringBuilder builder = new StringBuilder();
        builder.append("수신한 문자열[");
        builder.append(readMessage);
        builder.append("]");
        System.out.println(builder.toString());
    }

    //channelrRead 메소드가 수행되고 나면 실행된다
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
