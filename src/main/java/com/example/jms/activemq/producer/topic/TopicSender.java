package com.example.jms.activemq.producer.topic;

import java.io.Serializable;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.ProducerCallback;
import org.springframework.jms.core.SessionCallback;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * 
 * @description   Topic生产者发送消息到Topic
 * 
 */

@Component("topicSender")
public class TopicSender {
	
	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTemplate;
	
	/**
	 * 发送一条消息到指定的队列（目标）
	 * @param queueName 队列名称
	 * @param message 消息内容
	 */
	public void send(String topicName,final String message){
		jmsTemplate.send(topicName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
	
	public void sendMessage(final Destination destination, final Serializable obj) {
        //使用MessageConverter的情况
        jmsTemplate.convertAndSend(destination, obj);
        jmsTemplate.execute(new SessionCallback<Object>() {
            public Object doInJms(Session session) throws JMSException {
                MessageProducer producer = session.createProducer(destination);
                Message message = session.createObjectMessage(obj);
                producer.send(message);
                return null;
            }

        });
        
        jmsTemplate.execute(new ProducerCallback<Object>() {

            public Object doInJms(Session session, MessageProducer producer)
                    throws JMSException {
                Message message = session.createObjectMessage(obj);
                producer.send(destination, message);
                return null;
            }

        });
    }
	
//	@Autowired
//    private JmsMessagingTemplate jmsTemplate; 
//	
//	/**
//	 * 发送一条消息到指定的队列（目标）
//	 * @param queueName 队列名称
//	 * @param message 消息内容
//	 */
//	public void send(Queue queue,final String message){
//		try {
//			jmsTemplate.convertAndSend(queue.getQueueName(), message);
//		} catch (MessagingException | JMSException e) {
//			e.printStackTrace();
//		}
//	}
}

