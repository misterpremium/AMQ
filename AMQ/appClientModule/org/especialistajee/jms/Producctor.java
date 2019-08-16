package org.especialistajee.jms;

import javax.annotation.Resource;
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
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/Queue")
    private static Queue queue;

    public void enviaMensajeCola(String mundo) throws JMSException {

        Connection connection = null;
        Session session = null;

        MessageProducer producer = null;
        Message message = null;
        boolean esTransaccional = false;

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
        } finally {
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
}