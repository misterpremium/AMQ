package amqlib;

import javax.jms.JMSException;

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
	public static void ejecutarCola(String saludo, String despedida) throws JMSException {
		
		Producctor p = new Producctor();
        p.enviaMensajeCola(saludo);
        p.enviaMensajeCola(despedida);
        ConsumidorSincrono c = new ConsumidorSincrono();
        c.recibeMensajeSincronoCola();
        System.out.println("DONE");
	}

	public static void main(String[] args) throws Exception {
    	ejecutarCola("hola", "adios");
    	
    }
    

}

	
