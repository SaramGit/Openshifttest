package com.nt.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;

import com.nt.component.MessageHandler;



@Configuration
public class MqConfig {

	@Autowired
	JmsListenerEndpointRegistrar registrar;

	@Autowired
	private MessageHandler queueController;

	@Value( "${ibm.queues.sampleQueues}" )
	String[] sampleQueues;

	@Value( "${demo.concurrency.size.low}" )
	Integer messageConcurrencyLow;

	@Value( "${demo.concurrency.size.high}" )
	Integer messageConcurrencyHigh;

	String jmsMessageConcurrency = "";

	@PostConstruct
	public void init(){
		jmsMessageConcurrency = String.format( "%s-%s", messageConcurrencyLow, messageConcurrencyHigh );
        configureJmsSendMessage();
		configureJmsListeners( registrar );
	}

	private void configureJmsSendMessage() {
		for( final String queueName : sampleQueues ){
			queueController.send(queueName, "Hello i am ready to go...");
		}	
	}

	public void configureJmsListeners( JmsListenerEndpointRegistrar registrar ){
		int i = 0;
		for( final String queueName : sampleQueues ){
			SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
			endpoint.setId( "demo-" + i++ );
			endpoint.setDestination( queueName );
			endpoint.setConcurrency( jmsMessageConcurrency );
			
			endpoint.setMessageListener( message -> {
			    queueController.recv(queueName, message);
			} );
			registrar.registerEndpoint( endpoint );
		}
	}
}
