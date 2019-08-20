package amqlib;

import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

/*
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
*/
// imports

public class Main {
	public static ConnectionFactory createJmsConnectionFactory(String jndiName) {
        CachingConnectionFactory connectionFactory = null;
        try {
            Context jndiContext = new InitialContext();
            Context envContext = (Context) jndiContext.lookup("java:comp/env");
            ActiveMQConnectionFactory activeMQConnectionFactory = (ActiveMQConnectionFactory) envContext.lookup(jndiName);
            connectionFactory = new CachingConnectionFactory(activeMQConnectionFactory);
            ExceptionListener exceptionListener = null;
			connectionFactory.setExceptionListener(exceptionListener);
        } catch (NamingException e) {
            String msg = String.format("Unable to get JMS container with name %s ", jndiName);      
            throw new RuntimeException(msg, e);
        }
        return connectionFactory;
    }
	public static void ejecutarCola(String saludo, String despedida) throws JMSException {
		
		//createJmsConnectionFactory("jms/ConnectionFactory");
		Producctor p = new Producctor();
      
		p.enviaMensajeCola(saludo);
        p.enviaMensajeCola(despedida);
        //*/
		
		ConsumidorSincrono c = new ConsumidorSincrono();
        c.recibirMensaje();
        System.out.println("DONE");
	}

	public static void main(String[] args) throws Exception {
    	ejecutarCola("hola", "adios");
    	
    }
    

}

	
