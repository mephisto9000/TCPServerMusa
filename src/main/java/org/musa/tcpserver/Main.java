/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.musa.tcpserver;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.musa.payload.SpaceMarine;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.integration.handler.AbstractReplyProducingMessageHandler;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.util.TestingUtilities;
import org.springframework.integration.test.util.SocketUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;

/**
 * Demonstrates the use of a gateway as an entry point into the integration flow.
 * The message generated by the gateway is sent over tcp by the outbound gateway
 * to the inbound gateway. In turn the inbound gateway sends the message to an
 * echo service and the echoed response comes back over tcp and is returned to
 * the test case for verification.
 *
 * The test uses explicit transformers to convert the byte array payloads to
 * Strings.
 *
 * Several other samples are provided as JUnit test-cases:
 *
 * <ul>
 *     <li>TcpClientServerDemoWithConversionServiceTest</li>
 *     <li>TcpServerConnectionDeserializeTest</li>
 *     <li>TcpServerCustomSerializerTest</li>
 * </ul>
 *
 * @author Gunnar Hillert
 *
 */
public final class Main {

	/**
	 * Prevent instantiation.
	 */
	private Main() {}

	/**
	 * Load the Spring Integration Application Context
	 *
	 * @param args - command line arguments
	 */
	public static void main(final String... args) {
            
            System.out.println("preparing to launch the server app");
            
            final GenericXmlApplicationContext context = setupContext(); 
            context.registerShutdownHook();
            
            final AbstractServerConnectionFactory crLfServer = context.getBean(AbstractServerConnectionFactory.class);
            
            SubscribableChannel channel = (SubscribableChannel) context.getBean("teleportChannel");
		channel.subscribe(new AbstractReplyProducingMessageHandler() {

			@Override
			protected Object handleRequestMessage(Message<?> requestMessage) {
				
                                SpaceMarine spaceMarine = (SpaceMarine) requestMessage.getPayload();

				
                                System.out.println(spaceMarine.getName());
                                
				return requestMessage;
			}
		});
            
            TestingUtilities.waitListening(crLfServer, 10000L);
                        
            System.out.println("Server app is up and running at port "+crLfServer.getPort());
                        
	}
 
       
	public static GenericXmlApplicationContext setupContext() {
		final GenericXmlApplicationContext context = new GenericXmlApplicationContext();

		System.out.print("Detect open server socket...");
		int availableServerSocket = SocketUtils.findAvailableServerSocket(5678);

		final Map<String, Object> sockets = new HashMap<String, Object>();
		sockets.put("availableServerSocket", availableServerSocket);

		final MapPropertySource propertySource = new MapPropertySource("sockets", sockets);

		context.getEnvironment().getPropertySources().addLast(propertySource);

		System.out.println("using port " + context.getEnvironment().getProperty("availableServerSocket"));

		context.load("classpath:META-INF/spring/integration/serverContext.xml");
                
		context.registerShutdownHook();
		context.refresh();

		return context;
	} 
}
