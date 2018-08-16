package com.yang.netty.encoder;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import com.yang.netty.pojo.ChannelBufferByteOutput;

public class MarshallingEncoder {
	private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
	Marshaller marshaller;

	public MarshallingEncoder() throws IOException {
		final MarshallerFactory marshallerFactory = Marshalling
				.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		marshaller = marshallerFactory.createMarshaller(configuration);
	}

	protected void encode(Object msg, ByteBuf out) throws Exception {
		try {
			int lengthPos = out.writerIndex();
			out.writeBytes(LENGTH_PLACEHOLDER);
			ChannelBufferByteOutput output = new ChannelBufferByteOutput(out);
			marshaller.start(output);
			marshaller.writeObject(msg);
			marshaller.finish();
			out.setInt(lengthPos, out.writerIndex() - lengthPos - 4);
		} finally {
			marshaller.close();
		}
	}
}