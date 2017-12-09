package com.lb.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author libing
 * @desc 队列模式生产者
 * @date 2017/12/9 12:53
 */
public class AppProducer {
    private static final String URL = "tcp://localhost:61616";
    private static final String QUEUE_NAME = "queue-test";

    public static void main(String[] args) throws JMSException {
        //1创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);

        //2创建连接
        Connection connection = connectionFactory.createConnection();

        //3启动连接
        connection.start();

        //4 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        //5 创建目标
        Destination destination = session.createQueue(QUEUE_NAME);

        //6 创建生产者
        MessageProducer producer = session.createProducer(destination);

        for(int i=0;i<100;i++){
            //7 创建消息
            TextMessage textMessage = session.createTextMessage("test" + i);
            //8 发布消息
            producer.send(textMessage);

            System.out.println("发送消息"+textMessage.getText());
        }
        //9 关闭连接
        connection.close();
    }
}
