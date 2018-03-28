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
     * hbase����
     * @param tablename ����
     * @param columnFamily ����
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
     * ɾ����
     * @param tablename ����
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
     * �洢һ������
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
     * ��ѯһ�е�����
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
     * ɾ��һ������
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
     * �������ȫ������
     * @param HTable, create by : HTable table = new HTable(conf, "tablename")
     * @throws Exception
     */
    public static ResultScanner scanAll(HTable table) throws Exception {
        Scan s =new Scan();
        ResultScanner rs = table.getScanner(s);
        return rs;
    }
    
    /**
     * ����һ�η�Χ�ڵ�����
     * @param HTable, create by : HTable table = new HTable(conf, "tablename")
     * @param startrow ��ʼ�к�
     * @param endrow   �����к�
     * @throws Exception
     */
    public static ResultScanner scanRange(HTable table,String startrow,String endrow) throws Exception {
        Scan s =new Scan(Bytes.toBytes(startrow),Bytes.toBytes(endrow));
        ResultScanner rs = table.getScanner(s);
        return rs;
    }
    
    
    /***
     *  ��1���Ƚ������ CompareFilter.CompareOp
			�Ƚ���������ڶ���ȽϹ�ϵ�����������¼���ֵ��ѡ��
			
			    EQUAL              ���
			    GREATER            ����
			    GREATER_OR_EQUAL   ���ڵ���
			    LESS               С��
			    LESS_OR_EQUAL      С�ڵ���
			    NOT_EQUAL          ������
			
			
		


   */    
    /***
     * FilterList ����һ�����������������԰���һ�鼴��Ӧ����Ŀ�����ݼ��Ĺ�����������������С��롱 FilterList.Operator.MUST_PASS_ALL �͡��� FilterList.Operator.MUST_PASS_ONE ��ϵ��
     * @param table
     * @param arr
     * @return
     * @throws IOException
     */
   
    /***
     * ��ֵ������
     * @param table
     * @param arr ����|��|��֧
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByFilter(HTable table,List<String> arr) throws IOException{  
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);  //���롱 FilterList.Operator.MUST_PASS_ALL �͡��� FilterList.Operator.MUST_PASS_ONE ��ϵ��
        Scan s1 = new Scan();  
        for(String v:arr){ 
            String [] s=v.split(",");  
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(s[0]),Bytes.toBytes(s[1]),CompareOp.EQUAL,Bytes.toBytes(s[2]))); //EQUAL ��� 
            // ���������һ�к���ֻ����ָ����cell��ͬһ���е�����cell������  
//          s1.addColumn(Bytes.toBytes(s[0]), Bytes.toBytes(s[1]));  
        }  
        s1.setFilter(filterList);  
        ResultScanner ResultScannerFilterList = table.getScanner(s1);  
       return ResultScannerFilterList;
    }  
    
    
    
    
    /**
     * ��2���Ƚ���  ByteArrayComparable
			ͨ���Ƚ�������ʵ�ֶ�����Ŀ��ƥ��Ч�����Ƚ����������������ʹ�ã�
		
		    BinaryComparator               ƥ�������ֽ����� 
		    BinaryPrefixComparator         ƥ���ֽ�����ǰ׺ 
		    BitComparator
		    NullComparator
		    RegexStringComparator          ������ʽƥ��
		    SubstringComparator            �Ӵ�ƥ��
     */
    /***
     * ��ѯ������arr��ͷ��ֵ
     * @param table
     * @param arr
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByFilterRegexString(HTable table,List<String> arr) throws IOException{
    	FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);  //���롱 FilterList.Operator.MUST_PASS_ALL �͡��� FilterList.Operator.MUST_PASS_ONE ��ϵ��
    	for(String v:arr){
    		 String [] s=v.split(",");  
    		 RegexStringComparator comp = new RegexStringComparator(s[2]);////������2��ͷ��ֵ
             filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(s[0]),Bytes.toBytes(s[1]),CompareOp.EQUAL,comp)); //EQUAL ��� 
    	}
    	Scan scan = new Scan();  
    	scan.setFilter(filterList);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    /***
     * ���һ���Ӵ��Ƿ������ֵ�У���Сд�����У�
     * @param table
     * @param arr
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByFilterSubstring(HTable table,List<String> arr) throws IOException{
    	FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);  //���롱 FilterList.Operator.MUST_PASS_ALL �͡��� FilterList.Operator.MUST_PASS_ONE ��ϵ��
    	for(String v:arr){
    		 String [] s=v.split(",");  
    		 SubstringComparator comp = new SubstringComparator(s[2]);////������2��ͷ��ֵ
             filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(s[0]),Bytes.toBytes(s[1]),CompareOp.EQUAL,comp)); //EQUAL ��� 
    	}
    	Scan scan = new Scan();  
    	scan.setFilter(filterList);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    
    
    
    /***
     * ����FamilyFilter����
     * ����arr����
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
     * ����FamilyFilter����
     * ���д�����arr��ͷ������
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
     * �����޶���Qualifier���У�����
     * ����arr�е�����
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
     * �����޶���Qualifier���У�����
     * ������arr��ͷ����
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
     * ����(��Qualifier)ǰ׺��������,һ�������ǿ��Գ����ڶ�������еģ��ù���������������������ƥ����С����������У�arr��ͷ���е�����.
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
     * ��ѯ���е�����
     * @param table
     * @param prefixes ����
     * @return
     * @throws IOException
     */
    public static ResultScanner selectByMultipleColumnPrefixFilter(HTable table,byte[][] prefixes) throws IOException{
        //��������������BELONG����CREATE��ͷ���е�����
        MultipleColumnPrefixFilter ff = new MultipleColumnPrefixFilter(prefixes);
    	Scan scan = new Scan();  
    	scan.setFilter(ff);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    
    /****
     * �����������д�startColumn��endColumn��ͷ���е�����
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
     * ��ѯ�кŰ���str������
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
     * ��startRows��ʼȡrows��
     * @param table 
     * @param startRows  ��ʼ�к�
     * @param rows       ����
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
    * ��ѯ��������ֵ��Ϊstr������
    * @param table
    * @param str   ��ֵ
    * @return
    * @throws IOException
    */
    public static ResultScanner selectBySkipFilter(HTable table,String str) throws IOException{
    	SkipFilter sf = new SkipFilter(new ValueFilter(CompareOp.NOT_EQUAL, new BinaryComparator(Bytes.toBytes(str))));//ֻҪ��ֵΪstr���У����ж������˵���
    	Scan scan = new Scan();  
    	scan.setFilter(sf);
    	ResultScanner ResultScannerFilterList = table.getScanner(scan);
        return ResultScannerFilterList;
    }
    
    /***
     * ��ѯ��һ��cell��ֵ
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
