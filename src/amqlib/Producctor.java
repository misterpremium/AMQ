package amqlib;

/*import java.io.BufferedReader;  
import java.io.InputStreamReader;*/

import javax.naming.*;  
import javax.jms.*;  
  
public class Producctor {  
	public void enviaMensajeCola(String mundo) throws JMSException {
		try {   //Create and start connection  
	        InitialContext 	initCtx=new InitialContext();
	        Context ctx = (Context) initCtx.lookup("java:comp/env");
	        QueueConnectionFactory f=(QueueConnectionFactory)ctx.lookup("jms/ConnectionFactory");  
	        QueueConnection con=f.createQueueConnection();  
	        con.start();  
	        //2) create queue session  
	        QueueSession ses=con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);  
	        //3) get the Queue object  
	        Queue t=(Queue)ctx.lookup("jms/queue");  
	        //4)create QueueSender object         
	        QueueSender sender=ses.createSender(t);  
	        //5) create TextMessage object  
	        TextMessage msg=ses.createTextMessage();  
	          
	        //6) write message  
	        //BufferedReader b=new BufferedReader(new InputStreamReader(System.in));  
	        
	        String s = mundo;
	        msg.setText(s);  
	        //7) send message  
	        
	        sender.send(msg);
	       
	        System.out.println("Message successfully sent.");  
	          
	        //8) connection close
	        
	        con.close();
	      
	    }
	    
	    catch(Exception e){System.out.println("Este es el error " + e);}
}
    public static void main(String[] args) throws JMSException {  
    	Producctor p = new Producctor();
        p.enviaMensajeCola("Hola Mundo");
         
    }  
}  
  
/*import javax.annotation.Resource;
import amqlib.Main;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

// imports

public class Producctor {
	
    @Resource(mappedName = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName = "jms/Queue")
    public static Queue queue;

    public void enviaMensajeCola(String mundo) throws JMSException {
    	System.out.println(connectionFactory);

        Connection connection = null;
        Session session = null;

        MessageProducer producer = null;
        Message message = null;
        boolean esTransaccional = false;
    //    ConnectionFactory connectionFactory = Main.createJmsConnectionFactory("jms/ConnectionFactory");
        
        System.out.println("Valor connectioFactory linea 34 Producctor.java :" + connectionFactory);
        try {
        	
            connection = connectionFactory.createConnection();
            // Recordar llamar a start() para permitir el envio de mensajes
            connection.start();
            // Creamos una sesion sin transaccionalidad y con envio de acuse automatico
            session = connection.createSession(esTransaccional, Session.AUTO_ACKNOWLEDGE);
            // Creamos el productor a partir de una cola
            producer = session.createProducer(queue);
            // Creamos un mensaje sencillo de texto
            message = session.createTextMessage(mundo);
            // Mediante le productor, enviamos el mensaje
            producer.send(message);
			
            System.out.println("Enviado mensaje [" + mundo + "]");
        } catch (Exception e){
    		//e.printStackTrace();
        	e.printStackTrace();
        	System.out.println("Error de Aplicación " + e.getMessage());
    		//ok ="not ok";
    	}finally {
            // Cerramos los recursos
            producer.close();
            session.close();
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
    	Producctor p = new Producctor();
        p.enviaMensajeCola("Hola Mundo");
        p.enviaMensajeCola("Adios Mundo");
    }
}*/