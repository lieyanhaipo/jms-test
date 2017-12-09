package com.lb.jms.queue;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author libing
 * @desc 队列模式消费者
 * @date 2017/12/9 12:53
 */
public class AppConsumer {
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

        //6 创建消费者
        MessageConsumer messageConsumer = session.createConsumer(destination);

        //7 创建监听器
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收消息"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //8 关闭连接
        //connection.close();
    }
}
