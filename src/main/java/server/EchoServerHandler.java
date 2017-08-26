package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * Created by yookeun on 2017. 8. 26..
 */
//입력된 데이터를 처리하는 이벤트 핸들러인 ChannelInboundHandlerAdapter 를 상속받는다
public class EchoServerHandler extends ChannelInboundHandlerAdapter {



    //데이터 수신 이벤트 처리메소드다 클라이언트로투 데이터의 수신이 이루어졌을 때 네티가 자동으로 호출하는 이벤트이다
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //수신된 데이터를 가지고 네티의 버퍼객체로부터 문자열 데이터를 읽어온다
        //네티의 버퍼객체: 자바의 바이트버퍼(java.nio.ByteBuffer) 와 유사한 클래스로서 더 나은 성능과 편의성을 제공하는 네티의 버퍼클래스이다
        String readMessage = ((ByteBuf) msg).toString(Charset.defaultCharset());
        System.out.println("수신한 문자열 [" + readMessage + "]");
        //ctx는 채널파이프에 대한 이벤트를 처리한다 여기서는 서버에 연결된 클라이언트 채널로 입력받은 데이터를 그대로 전송한다
        ctx.write(msg);
    }


    //channelRead 이벤트의 처리가 완료된 후 자동으로 수행되는 이벤트 메소드로 채널파이프라인에서 자장된 버퍼를 전송하는 flush 메소드를 호출한다
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

}