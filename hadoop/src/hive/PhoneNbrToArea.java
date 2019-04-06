package cn.itcast.bigdata;

import java.util.HashMap;

import org.apache.hadoop.hive.ql.exec.UDF;

//HIVE自定义函数
public class PhoneNbrToArea extends UDF{

	private static HashMap<String, String> areaMap = new HashMap<>();
	
	
	static {
		areaMap.put("1388", "beijing");
		areaMap.put("1399", "tianjin");
		areaMap.put("1366", "nanjing");
	}
	
	//一定要用public修饰才能被hive调用，执行的时候会被调用
	public String evaluate(String pnb) {
		
		String result  = areaMap.get(pnb.substring(0,4))==null? (pnb+"    huoxing"):(pnb+"  "+areaMap.get(pnb.substring(0,4)));		
		
		return result;
	}
	
}
