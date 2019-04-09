package cn.itcast.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerDemo {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("zk.connect", "weekend01:2181,weekend02:2181,weekend03:2181");
		props.put("metadata.broker.list","weekend01:9092,weekend02:9092,weekend03:9092");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		ProducerConfig config = new ProducerConfig(props);
		//new Producer<String, String>(config);    topic的名字类型，消息类型
		Producer<String, String> producer = new Producer<String, String>(config);

		// 发送业务消息
		// 读取文件 读取内存数据库 读socket端口
		for (int i = 1; i <= 100; i++) {
			Thread.sleep(500);
			//new KeyedMessage<String, String>():  topic的名字，消息类容
			producer.send(new KeyedMessage<String, String>("wordcount",
					"i said i love you baby for" + i + "times,will you have a nice day with me tomorrow"));
		}

	}
}