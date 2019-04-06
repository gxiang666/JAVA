package cn.itcast.bigdata.hbase;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

public class HbaseDao {

	
	@Test//插入数据
	public void insertTest() throws Exception{
		
		Configuration conf =  HBaseConfiguration.create();
		//配置zookeeper的地址
		conf.set("hbase.zookeeper.quorum", "weekend05:2181,weekend06:2181,weekend07:2181");
		//表格，HBase插入数据只能一条条的插入
		HTable nvshen = new HTable(conf, "nvshen");
		//行键
		Put name = new Put(Bytes.toBytes("rk0001"));
		//列族，key-value
		name.add(Bytes.toBytes("base_info"), Bytes.toBytes("name"), Bytes.toBytes("angelababy"));
		
		Put age = new Put(Bytes.toBytes("rk0001"));
		age.add(Bytes.toBytes("base_info"), Bytes.toBytes("age"), Bytes.toBytes(18));
		
		ArrayList<Put> puts = new ArrayList<>();
		puts.add(name);
		puts.add(age);
		
		nvshen.put(puts);
		
	}
	
	
	
	//
	public static void main(String[] args) throws Exception {
		
		Configuration conf =  HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "weekend05:2181,weekend06:2181,weekend07:2181");
		
		//负责创建表格
		HBaseAdmin admin = new HBaseAdmin(conf);
		
		//表名
		TableName name = TableName.valueOf("nvshen");
		
		//表
		HTableDescriptor desc = new HTableDescriptor(name);
		
		//列族
		HColumnDescriptor base_info = new HColumnDescriptor("base_info");
		HColumnDescriptor extra_info = new HColumnDescriptor("extra_info");
		
		//版本
		base_info.setMaxVersions(5);
		
		//添加行键
		desc.addFamily(base_info);
		desc.addFamily(extra_info);
		
		//创建表
		admin.createTable(desc);
		
		
	}
	
	
	
}
