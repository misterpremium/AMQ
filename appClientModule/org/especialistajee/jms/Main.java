package org.especialistajee.jms;

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

    public static void main(String[] args) throws Exception {
    	Producctor p = new Producctor();
        p.enviaMensajeCola("Hola Mundo");
        p.enviaMensajeCola("Adios Mundo");
        ConsumidorSincrono c = new ConsumidorSincrono();
        c.recibeMensajeSincronoCola();
    }
    }

	
}