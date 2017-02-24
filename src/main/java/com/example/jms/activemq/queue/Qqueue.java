package com.example.jms.activemq.queue;

import javax.jms.JMSException;
import javax.jms.Queue;

public class Qqueue implements Queue{

	@Override
	public String getQueueName() throws JMSException {
		return QSval.QUEUE_TEST;
	}
}
