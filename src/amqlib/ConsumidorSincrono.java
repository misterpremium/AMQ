package amqlib;

// imports
/*import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
*/
//import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

//import org.apache.activemq.MessageAvailableConsumer;
//import org.apache.activemq.usage.TempUsage;

import javax.jms.*;
//import javax.naming.Context;
import javax.naming.InitialContext;  
  
public class ConsumidorSincrono {
	
	
	public void recibirMensaje() throws JMSException{
		 try{  
	            //1) Create and start connection  
	            InitialContext initCtx=new InitialContext();
	            //Context ctx = (Context) initCtx.lookup("java:comp/env");
	            QueueConnectionFactory f=(QueueConnectionFactory)initCtx.lookup("java:jboss/exported/jms/fabrica");  
	            QueueConnection con=f.createQueueConnection();  
	            con.start();  
	            //2) create Queue session  
	            QueueSession ses=con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);  
	            //3) get the Queue object  
	            InitialContext initCtx2 =new InitialContext();
	            Queue t=(Queue)initCtx2.lookup("jms/queue");  
	            //4)create QueueReceiver  
	            QueueReceiver receiver=ses.createReceiver(t);  
	              
	            //5) create listener object  
	            Escuchador listener=new Escuchador();  
	              
	            //6) register the listener object with receiver  
	            receiver.setMessageListener(listener);  
	              
	            System.out.println("Receiver1 is ready, waiting for messages...");  
	            while(true){                  
	                Thread.sleep(1000);  
	            }  
	        }catch(Exception e){System.out.println(e);}  
		
		
	}
    public static void main(String[] args)  throws JMSException{  
    	ConsumidorSincrono c = new ConsumidorSincrono();
    	c.recibirMensaje();
       
    }  
    

}
























//###############################################################################
/*
public class ConsumidorSincrono {

    @Resource(mappedName = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/Queue")
    private static Queue queue;

    public void recibeMensajeSincronoCola() throws JMSException {

        Connection connection = null;
        Session session = null;

        MessageConsumer consumer = null;
        Message message = null;
        boolean esTransaccional = false;

        try {
            connection = connectionFactory.createConnection();
            // Recordar llamar a start() para permitir la recepción de mensajes
            connection.start();
            // Creamos una sesion sin transaccionalidad y con envio de acuse automatico
            session = connection.createSession(esTransaccional, Session.AUTO_ACKNOWLEDGE);
            // Creamos el consumidor a partir de una cola
            consumer = session.createConsumer(queue);
            // Recibimos un mensaje de texto
            message = consumer.receive();

            // Sacamos el mensaje por consola
            System.out.println("Recibido sincrono [" + message + "]");
            System.out.println("Fin sincrono");
        } finally {
            // Cerramos los recursos
            consumer.close();
            session.close();
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        ConsumidorSincrono p = new ConsumidorSincrono();
        p.recibeMensajeSincronoCola();
    }
}*/