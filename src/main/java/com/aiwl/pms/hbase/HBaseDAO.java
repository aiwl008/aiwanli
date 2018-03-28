package com.aiwl.pms.hbase;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.ColumnRangeFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SkipFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseDAO {
    static Configuration conf = HBaseConfiguration.create();
    /**
     * hbase建表
     * @param tablename 表名
     * @param columnFamily 列族
     * @throws Exception
     */
    public static void createTable(String tablename, String columnFamily) throws Exception {
        HBaseAdmin admin = new HBaseAdmin(conf);
        if(admin.tableExists(tablename)) {
            System.out.println("Table exists!");
            System.exit(0);
        }
        else {
            HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tablename));
            tableDesc.addFamily(new HColumnDescriptor(columnFamily));
            admin.createTable(tableDesc);
            System.out.println("create table success!");
        }
        admin.close();
        
    }
    
    /**
     * 删除表
     * @param tablename 表名
     * @return
     * @throws IOException
     */
    public static boolean deleteTable(String tablename) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(conf);
        if(admin.tableExists(tablename)) {
            try {
                admin.disableTable(tablename);
                admin.deleteTable(tablename);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                admin.close();
                return false;
            }
        }
        admin.close();
        return true;
    }
    /**
     * 存储一行数据
     * @param HTable, create by : HTable table = new HTable(conf, "tablename")
     * @param rowKey
     * @param columnFamily
     * @param identifier
     * @param data
     * @throws Exception
     */
    public static void putRow(HTable table, String rowKey, String columnFamily, String identifier, String data) throws Exception{
        Put p1 = new Put(Bytes.toBytes(rowKey));
        p1.add(Bytes.toBytes(columnFamily), Bytes.toBytes(identifier), Bytes.toBytes(data));
        table.put(p1);
        System.out.println("put '"+rowKey+"', '"+columnFamily+":"+identifier+"', '"+data+"'");
    }
    
    /**
     * 查询一行的数据
     * @param HTable, create by : HTable table = new HTable(conf, "tablename")
     * @param rowKey
     * @throws Exception
     */
    public static Result getRow(HTable table, String rowKey) throws Exception {
        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = table.get(get);
        System.out.println("Get: "+result);
        return result;
    }
    
    /**
     * 删除一行数据
     * @param HTable, create by : HTable table = new HTable(conf, "tablename")
     * @param rowKey
     * @throws Exception
     */
    public static void deleteRow(HTable table, String rowKey) throws Exception {
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
        System.out.println("Delete row: "+rowKey);
    }
    
    /**
     * 遍历表的全部数据
     * @param HTable, create by : HTable table = new HTable(conf, "tablename")
     * @throws Exception
     */
    public static ResultScanner scanAll(HTable table) throws Exception {
        Scan s =new Scan();
        ResultScanner rs = table.getScanner(s);
        return rs;
    }
    
    /**
     * 遍历一段范围内的数据
     * @param HTable, create by : HTable table = new HTable(conf, "tablename")
     * @param startrow 开始行号
     * @param endrow   结束行号
     * @throws Exception
     */
    public static ResultScanner scanRange(HTable table,String startrow,String endrow) throws Exception {
        Scan s =new Scan(Bytes.toBytes(startrow),Bytes.toBytes(endrow));
        ResultScanner rs = table.getScanner(s);
        return rs;
    }
    
    
    /***
     *  （1）比较运算符 CompareFilter.CompareOp
			比较运算符用于定义比较关系，可以有以下几类值供选择：
			
			    EQUAL              相等
			    GREATER            大于
			    GREATER_OR_EQUAL   大于等于
			    LESS               小于
			    LESS_OR_EQUAL      小于等于
			    NOT_EQUAL          不等于
			
			
		


   */    
    /***
     * FilterList 代表一个过滤器链，它可以包含一组即将应用于目标数据集的过滤器，过滤器间具有“与” FilterList.Operator.MUST_PASS_ALL 和“或” FilterList.Operator.MUST_PASS_ONE 关系。
     * @param table
     * @param arr
     * @return
     * @throws IOException
     */
   
    /***
     * 列值过滤器
     * @param table
     * @param arr 列族|列|列支
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByFilter(HTable table,List<String> arr) throws IOException{  
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);  //“与” FilterList.Operator.MUST_PASS_ALL 和“或” FilterList.Operator.MUST_PASS_ONE 关系。
        Scan s1 = new Scan();  
        for(String v:arr){ 
            String [] s=v.split(",");  
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(s[0]),Bytes.toBytes(s[1]),CompareOp.EQUAL,Bytes.toBytes(s[2]))); //EQUAL 相等 
            // 添加下面这一行后，则只返回指定的cell，同一行中的其他cell不返回  
//          s1.addColumn(Bytes.toBytes(s[0]), Bytes.toBytes(s[1]));  
        }  
        s1.setFilter(filterList);  
        ResultScanner ResultScannerFilterList = table.getScanner(s1);  
       return ResultScannerFilterList;
    }  
    
    
    
    
    /**
     * （2）比较器  ByteArrayComparable
			通过比较器可以实现多样化目标匹配效果，比较器有以下子类可以使用：
		
		    BinaryComparator               匹配完整字节数组 
		    BinaryPrefixComparator         匹配字节数组前缀 
		    BitComparator
		    NullComparator
		    RegexStringComparator          正则表达式匹配
		    SubstringComparator            子串匹配
     */
    /***
     * 查询任意以arr打头的值
     * @param table
     * @param arr
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByFilterRegexString(HTable table,List<String> arr) throws IOException{
    	FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);  //“与” FilterList.Operator.MUST_PASS_ALL 和“或” FilterList.Operator.MUST_PASS_ONE 关系。
    	for(String v:arr){
    		 String [] s=v.split(",");  
    		 RegexStringComparator comp = new RegexStringComparator(s[2]);////任意以2打头的值
             filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(s[0]),Bytes.toBytes(s[1]),CompareOp.EQUAL,comp)); //EQUAL 相等 
    	}
    	Scan scan = new Scan();  
    	scan.setFilter(filterList);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    /***
     * 检测一个子串是否存在于值中（大小写不敏感）
     * @param table
     * @param arr
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByFilterSubstring(HTable table,List<String> arr) throws IOException{
    	FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);  //“与” FilterList.Operator.MUST_PASS_ALL 和“或” FilterList.Operator.MUST_PASS_ONE 关系。
    	for(String v:arr){
    		 String [] s=v.split(",");  
    		 SubstringComparator comp = new SubstringComparator(s[2]);////任意以2打头的值
             filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(s[0]),Bytes.toBytes(s[1]),CompareOp.EQUAL,comp)); //EQUAL 相等 
    	}
    	Scan scan = new Scan();  
    	scan.setFilter(filterList);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    
    
    
    /***
     * 列族FamilyFilter过滤
     * 表中arr列族
     * @param arr
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByFamilyFilterBinary(HTable table,String arr) throws IOException{
    	FamilyFilter ff = new FamilyFilter(CompareOp.EQUAL , new BinaryComparator(Bytes.toBytes(arr))); 
    	Scan scan = new Scan();  
    	scan.setFilter(ff);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    /***
     * 列族FamilyFilter过滤
     * 表中存在以arr打头的列族
     * @param table
     * @param arr
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByFamilyFilterBinaryPrefix(HTable table,String arr) throws IOException{
    	FamilyFilter ff = new FamilyFilter(CompareOp.EQUAL , new BinaryPrefixComparator(Bytes.toBytes(arr)));
    	Scan scan = new Scan();  
    	scan.setFilter(ff);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    
    /***
     * 基于限定符Qualifier（列）过滤
     * 表中arr列的数据
     * @param table
     * @param arr
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByQualifierFilterBinary(HTable table,String arr) throws IOException{
    	QualifierFilter ff = new QualifierFilter( CompareOp.EQUAL , new BinaryComparator(Bytes.toBytes(arr)));
    	Scan scan = new Scan();  
    	scan.setFilter(ff);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    /***
     * 基于限定符Qualifier（列）过滤
     * 表中以arr打头的列
     * @param table
     * @param arr
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByQualiFilterBinaryPrefix(HTable table,String arr) throws IOException{
    	QualifierFilter ff = new QualifierFilter( CompareOp.EQUAL ,new BinaryPrefixComparator(Bytes.toBytes(arr)));
    	Scan scan = new Scan();  
    	scan.setFilter(ff);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    
    /***
     * 列名(即Qualifier)前缀过滤数据,一个列名是可以出现在多个列族中的，该过滤器将返回所有列族中匹配的列。所有列族中，arr打头的列的数据.
     * @param table
     * @param arr
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByColumnPrefixFilter(HTable table,String arr) throws IOException{
    	ColumnPrefixFilter ff = new ColumnPrefixFilter(Bytes.toBytes(arr));
    	Scan scan = new Scan();  
    	scan.setFilter(ff);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    /***
     * 查询多列的数据
     * @param table
     * @param prefixes 列名
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByMultipleColumnPrefixFilter(HTable table,byte[][] prefixes) throws IOException{
        //返回所有行中以BELONG或者CREATE打头的列的数据
        MultipleColumnPrefixFilter ff = new MultipleColumnPrefixFilter(prefixes);
    	Scan scan = new Scan();  
    	scan.setFilter(ff);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    
    /****
     * 返回所有列中从startColumn到endColumn打头的列的数据
     * @param table
     * @param startColumn
     * @param endColumn
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByColumnRangeFilter(HTable table,byte[] startColumn,byte[] endColumn) throws IOException{
        ColumnRangeFilter ff = new ColumnRangeFilter(startColumn, true, endColumn, true);
    	Scan scan = new Scan();  
    	scan.setFilter(ff);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    
    
    /***
     * 查询行号包含str的数据
     * @param table
     * @param str
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByRowFilter(HTable table,String str) throws IOException{
    	RowFilter rf = new RowFilter(CompareOp.EQUAL , new SubstringComparator(str));
    	Scan scan = new Scan();  
    	scan.setFilter(rf);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    
    /***
     * 从startRows开始取rows行
     * @param table 
     * @param startRows  开始行号
     * @param rows       行数
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByPageFilter(HTable table,String startRows,Long rows) throws IOException{
    	PageFilter pf = new PageFilter(rows);
    	Scan scan = new Scan();  
    	scan.setFilter(pf);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    
    
   /***
    * 
    * 查询列族中列值不为str的数据
    * @param table
    * @param str   列值
    * @return
    * @throws IOException
    */
    public static ResultScanner selectBySkipFilter(HTable table,String str) throws IOException{
    	SkipFilter sf = new SkipFilter(new ValueFilter(CompareOp.NOT_EQUAL, new BinaryComparator(Bytes.toBytes(str))));//只要列值为str的列，整行都被过滤掉。
    	Scan scan = new Scan();  
    	scan.setFilter(sf);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    
    /***
     * 查询第一个cell的值
     * @param table
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByFirstKeyOnlyFilter(HTable table) throws IOException{
    	FirstKeyOnlyFilter fkof = new FirstKeyOnlyFilter();
    	Scan scan = new Scan();  
    	scan.setFilter(fkof);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    
   
}
