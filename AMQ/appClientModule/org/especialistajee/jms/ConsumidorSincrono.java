package org.especialistajee.jms;

// imports
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;


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
}