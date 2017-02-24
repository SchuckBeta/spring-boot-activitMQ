package com.example.jms.activemq.queue;

import javax.jms.JMSException;
import javax.jms.Queue;

public class Qtopic implements Queue{

	@Override
	public String getQueueName() throws JMSException {
		return QSval.TOPIC_TEST;
	}
}