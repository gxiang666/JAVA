package cn.itcast.hadoop.mr.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


/**
 * combiner������ѭreducer�Ĺ淶
 * ���԰�������һ����map���񱾵����е�reducer
 * ʹ��combiner��ʱ��Ҫע������
 * 1��combiner������������ݷ�������Ҫ�ܸ�mapper��reducerƥ��
 * 2��combiner����֮����Ӱ�����յ�ҵ���߼�������
 * @author duanhaitao@itcast.cn
 *
 */
public class WCCombiner extends Reducer<Text, LongWritable, Text, LongWritable>{

}
