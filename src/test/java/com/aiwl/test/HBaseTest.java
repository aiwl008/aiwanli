package com.aiwl.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import com.aiwl.pms.hbase.HBaseDAO;

public class HBaseTest  {
	static Configuration conf = HBaseConfiguration.create();
	@Test
	public void testJ_PASSTHROUGH(){
		try {
			HTable testTable = new HTable(conf, "J_GPSINFO");
	    	StringBuffer sb = new StringBuffer();
	    	ResultScanner rs = HBaseDAO.scanRange(testTable, "0000042020161211000000", "0000042020161211235959");
	    	
	    	
	    	Double pulse = 0.0;
	    	
	    	int i=0;
	    	double startFule = 0.0;
	    	double lastFule = 0.0;
	    	double pluseMile = 0.0;
	    	DecimalFormat    df   = new DecimalFormat("######0.00");  
	    	String[] oldValue = null ;
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			String[] value = new String(CellUtil.cloneValue(cell)).split("\\|");
	    			sb.append(new String(CellUtil.cloneValue(cell))+"  ");
//	    			if("停车".equals(value[11])){
//	    				continue;
//	    			}
	    			if(i==0){
	    				oldValue = value;
	    				startFule = Double.parseDouble(value[6]);
	    			}else{
	    				
	    				Double distince = Distancebylonlat.getDistance(value[3], value[2],oldValue[3], oldValue[2]);
	    				oldValue = value;
	    				pulse = pulse+distince;
	    				pluseMile += (Double.parseDouble(value[6])-startFule);
	    				startFule = Double.parseDouble(value[6]);
	    			}
	    			i++;
	    			
	    		}
	    	}
	    	System.out.println("脉冲里程："+(pluseMile));
	    	System.out.println("GPS里程："+df.format(pulse));
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testJ_DRIVEPULSE(){
		try {
			HTable testTable = new HTable(conf, "J_DRIVEPULSE");
	    	StringBuffer sb = new StringBuffer();
//	    	ResultScanner rs = HBaseDAO.scanRange(testTable, "0000042020161212000000", "0000042020161212235959");
	    	ResultScanner rs = HBaseDAO.scanRange(testTable, "000000042020161212000000", "000000042020161212235959");
	    	
	    	List<Double> list = new ArrayList<Double>();
	    	Double pulse = 0.0;
	    	
	    	int i=0;
	    	double start = 0.0;
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			String[] value = new String(CellUtil.cloneValue(cell)).split("\\|");
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");

	    			if(i==0){
	    				
	    			}else{
	    				list.add(Double.parseDouble(value[2])-start);
	    			}
	    			start = Double.parseDouble(value[2]);
	    			i++;
	    			System.out.println(sb.toString());
	    			System.out.println(Double.parseDouble(value[2])-start);
	    			

	    		}
	    	}
	    	for(Double d:list){
	    		pulse = pulse+d;
	    	}
	    	System.out.println(pulse);
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testJ_OILINFO(){
		try {
			HTable testTable = new HTable(conf, "J_OILINFO");
	    	StringBuffer sb = new StringBuffer();
//	    	ResultScanner rs = HBaseDAO.scanRange(testTable, "0000042020161212000000", "0000042020161212235959");
	    	ResultScanner rs = HBaseDAO.scanRange(testTable, "0000042020161212102500", "0000042020161212104000");
	    	
	    	Double pulse = 0.0;
	    	
	    	int i=0;
	    	String[] oldValue = null ;
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			String[] value = new String(CellUtil.cloneValue(cell)).split("\\|");
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    			if(i==0){
	    				oldValue = value;
	    			}else{
	    				
	    				Double distince = Distancebylonlat.getDistance(value[3], value[2],oldValue[3], oldValue[2]);
	    				System.out.println(distince);
	    				oldValue = value;
	    				pulse = pulse+distince;
	    			}
	    			i++;

	    			

	    		}
	    	}
	    	
	    	System.out.println(pulse);
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	 public   void testHbaseAll()  {
	    try {
			HBaseDAO.deleteTable("J_PASSTHROUGH");
			
	    	HBaseDAO.createTable("test", "info");
	    	HTable testTable = new HTable(conf, "test");
	    	HBaseDAO.putRow(testTable,"100001", "info","age","28");
	    	HBaseDAO.putRow(testTable,"100001", "info","address","anywhere");
	    	HBaseDAO.putRow(testTable,"100001", "info","name1","guoqi");
	    	HBaseDAO.putRow(testTable,"100002", "info","age","27");
//	    	HBaseDAO.putRow(testTable,"100002", "info","name","guoqi");
	    	HBaseDAO.putRow(testTable,"100002", "info","address","789456");
	    	HBaseDAO.putRow(testTable,"100003", "info","age","26");
	    	HBaseDAO.putRow(testTable,"100003", "info","name","dongfeng");
	    	HBaseDAO.putRow(testTable,"100003", "info","address","huilongguan");
	    	
	    	
	    	
	    			 
	    	Result r = HBaseDAO.getRow(testTable, "100001");
	    	StringBuffer sb = new StringBuffer();
	    	for(Cell cell:r.rawCells()){
//	    		System. out .println("Rowkey : " +Bytes. toString (r.getRow())+"   Familiy:Quilifier : " +Bytes.toString (CellUtil.cloneQualifier(cell))+ "   Value : " +Bytes. toString (CellUtil. cloneValue (cell))+"   Time : " +cell.getTimestamp() );
	    		sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    	}
	    	System.out.println(sb.toString());
//	    	HBaseDAO.deleteRow(testTable, "100001");
	    	r = HBaseDAO.getRow(testTable, "100001");
	    	for(Cell cell:r.rawCells()){
	    		sb.append(new String(CellUtil.cloneValue(cell)));
	    	}
	    	
	    	System.out.println("**********************************************************************");
	    	
	    	
	    	ResultScanner rs = HBaseDAO.scanAll(testTable);
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	System.out.println("**********************************************************************");
	    	List<String> arr = new ArrayList<String>();
	    	arr.add("info,age,26");
	    	rs = HBaseDAO.selectByFilter(testTable,  arr);
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	
	    	System.out.println("**********************************************************************");
	    	arr.clear();
	    	arr = new ArrayList<String>();
	    	arr.add("info,age,2");
	    	rs = HBaseDAO.selectByFilterRegexString(testTable, arr);
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	
	    	System.out.println("**********************************************************************");
	    	
	    	arr.clear();
	    	arr = new ArrayList<String>();
	    	arr.add("info,age,2");
	    	rs = HBaseDAO.selectByFilterSubstring(testTable, arr);
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	System.out.println("**********************************************************************");
	    	
	    	rs = HBaseDAO.selectByFamilyFilterBinary(testTable, "inf");
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	
	    	System.out.println("**********************************************************************");
	    	
	    	rs = HBaseDAO.selectByFamilyFilterBinaryPrefix(testTable, "inf");
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	System.out.println("**********************************************************************");
	    	
	    	rs = HBaseDAO.selectByQualifierFilterBinary(testTable, "name1");
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	
	    	System.out.println("**********************************************************************");
	    	
	    	rs = HBaseDAO.selectByQualiFilterBinaryPrefix(testTable, "nam");
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	
	    	System.out.println("**********************************************************************");
	    	
	    	rs = HBaseDAO.selectByColumnPrefixFilter(testTable, "nam");
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	
			System.out.println("**********************************************************************");
			byte[][] prefixes = new byte[][] {Bytes.toBytes("name"), Bytes.toBytes("age")};
	    	rs = HBaseDAO.selectByMultipleColumnPrefixFilter(testTable, prefixes);
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	
	    	System.out.println("**********************************************************************");
	    	byte[] startColumn =Bytes.toBytes("a"); 
	    	byte[] endColumn =Bytes.toBytes("d");
	    	rs = HBaseDAO.selectByColumnRangeFilter(testTable, startColumn, endColumn);
	    	
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	
	    	System.out.println("**********************************************************************");
	    	rs = HBaseDAO.selectByRowFilter(testTable,"002");
	    	
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	
	    	System.out.println("**********************************************************************");
	    	rs = HBaseDAO.selectByPageFilter(testTable, "100001", 1L);
	    	
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	
	    	System.out.println("**********************************************************************");
	    	rs = HBaseDAO.selectBySkipFilter(testTable, "28");
	    	
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	System.out.println("**********************************************************************");
	    	rs = HBaseDAO.selectByFirstKeyOnlyFilter(testTable);
	    	
	    	for(Result result:rs){
	    		sb = new StringBuffer();
	    		for(Cell cell:result.rawCells()){
	    			sb.append(new String(CellUtil.cloneQualifier(cell))+" = "+ new String(CellUtil.cloneValue(cell))+"  ");
	    		}
	    		System.out.println(sb.toString());
	    	}
	    	
	    	testTable.close();
	    	HBaseDAO.deleteTable("test");
	    	
	    	} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
}
