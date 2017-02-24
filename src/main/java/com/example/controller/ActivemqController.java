package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jms.activemq.producer.queue.QueueSender;
import com.example.jms.activemq.producer.topic.TopicSender;
import com.example.jms.activemq.queue.QSval;
import com.example.jms.activemq.queue.Qqueue;
import com.example.jms.activemq.queue.Qtopic;

@RestController
@RequestMapping("/jms/activemq")
public class ActivemqController {
	
	@Autowired 
	QueueSender queueSender;

	@Autowired
	TopicSender topicSender;
	
	/**
	 * 发送消息到队列
	 * Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("queueSender")
	public String queueSender(@RequestParam("message")String message){
		String opt="";
		try {
			queueSender.send(QSval.QUEUE_TEST, message);
//			queueSender.send(new Qqueue(), message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}
	
	/**
	 * 发送消息到主题
	 * Topic主题 ：放入一个消息，所有订阅者都会收到 
	 * 这个是主题目的地是一对多的
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("topicSender")
	public String topicSender(@RequestParam("message")String message){
		String opt = "";
		try {
			topicSender.send(QSval.TOPIC_TEST, message);
//			topicSender.send(new Qtopic(), message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}
	
}