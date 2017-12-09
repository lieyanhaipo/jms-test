package com.lb.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.net.URL;

/**
 * @author libing
 * @desc  队列模式基类
 * @date 2017/12/9 12:57
 */
public class AppBase {
    protected static final String URL = "tcp://localhost:61616";
    protected static final String QUEUE_NAME = "queue-test";

    protected static Connection createConnection(String url) throws JMSException {
        //1创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        //2创建连接
        Connection connection = connectionFactory.createConnection();

        return connection;
    }

    protected static Session createSession(Connection connection) throws JMSException {
        //4 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        return session;
    }

    protected static Destination createDestination(Session session,String queueName) throws JMSException {
        return session.createQueue(queueName);
    }
}
