package com.aiwl.common.utils;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aiwl.common.exception.Zw56Exception;

import sun.misc.BASE64Decoder;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

@Component
public class FtpUtil {
	
	private static final Logger log = LoggerFactory.getLogger(FtpUtil.class);

//	@Value("${ftp.maxsize}")
//	private int maxsize;
	
	private static int maxsize = 5;
	
	public static FtpClient create(Map<String, String> map) throws Exception{
		try{
			String url = map.get("url");
			Integer port = Integer.parseInt(map.get("port"));
			String username = map.get("username");
			String password = map.get("password");
			String root = map.get("root");
			SocketAddress addr = new InetSocketAddress(url, port);
			// 连接
			FtpClient ftp = FtpClient.create();
			ftp.connect(addr);
			// 登陆
			ftp.login(username, password.toCharArray());
			ftp.setBinaryType();
			if(root != null){
				ftp.changeDirectory(root);
			}
			return ftp;
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("创建Ftp连接失败");
		}
		
	}
	
	
	public static FtpClient upload(FtpClient ftp, MultipartFile file, File targetFile) throws Exception{
		String path = targetFile.getParent();
		String name = targetFile.getName();
		ftp = switchDir(ftp, path);
		byte[] bytes = file.getBytes();
		OutputStream os = ftp.putFileStream(name);
		os.write(bytes);
		os.flush();
		os.close();
		return ftp;
	}
	
	/**
	 * 上传文件
	 * 
	 * @param multipartFile
	 * @param ftpFile
	 * @param ftp
	 * @return
	 */
	public String upload(String base64, FtpClient ftp, String url) {
		OutputStream os = null;
		FileInputStream fis = null;
		try {

			// 创建一个缓冲区
			BASE64Decoder decoder = new BASE64Decoder();
			base64 = base64.substring(base64.indexOf(",")+1);
			byte[] bytes = decoder.decodeBuffer(base64);
			if (bytes.length > 1024 * 1024 * maxsize) {
				log.debug(bytes.length + "=============bytes");
				throw new Zw56Exception("图片不能大于" + maxsize + "M");
			}
			// 将ftp文件加入输出流中。输出到ftp上
			
			File file = new File(url);
			String path = file.getParent();
			String name = file.getName();
			try {
				ftp = switchDir(ftp, path);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			os = ftp.putFileStream(name);
			os.write(bytes);
			log.debug("upload success!!");
			return url;
		} catch (FtpProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
				if (fis != null)
					fis.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return url;
	}
	
	public static FtpClient upload(FtpClient ftp, File file, File targetFile) throws Exception{
		String path = targetFile.getParent();
		String name = targetFile.getName();
		ftp = switchDir(ftp, path);
		byte[] buffer = null;  
		FileInputStream fis = new FileInputStream(file);  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        byte[] b = new byte[1024];  
        int n;  
        while ((n = fis.read(b)) != -1)  
        {  
            bos.write(b, 0, n);  
        }  
        fis.close();  
        bos.close();  
        buffer = bos.toByteArray();  
		OutputStream os = ftp.putFileStream(name);
		os.write(buffer);
		os.flush();
		os.close();
		return ftp;
	}
	
	private static FtpClient switchDir(FtpClient ftp, String path) throws Exception{
		path = path.replace("\\", "/");
		for(String s:path.split("/")){
			try{
				ftp.makeDirectory(s);
			}catch(Exception e){
			}
			try{
				ftp.changeDirectory(s);
			}catch(Exception e){
			}
			
		}
		return ftp;
	}
	
	
	public static void download(FtpClient ftpClient, String remoteFile, String localFile) {
        InputStream is = null;
        FileOutputStream os = null;
        try {
            //获取远程机器上的文件filename，借助TelnetInputStream把该文件传送到本地。
            try {
				is = ftpClient.getFileStream(remoteFile);
			} catch (FtpProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            File file_in = new File(localFile);
            os = new FileOutputStream(file_in);
            byte[] bytes = new byte[1024];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
            }
            System.out.println("download success");
        } catch (IOException ex) {
            System.out.println("not download");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally{
            try {
                if(is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(os != null){
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
}
