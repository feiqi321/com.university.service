package com.ovft.configure.sys.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * @author vvtxw
 * @create 2019-04-23 14:12
 */
public class FastUtil {
    public static StorageClient client = null;
    public static final String  CONFI_FILE= "fdfs_client.conf";


   /* @Value("${fastdfs.tracker_servers}")
    private static String tracker_servers;*/
//    String file = this.getClass().getResource("conf/fdfs_client.conf").getFile();

    static {

        try {
//              ClientGlobal.init(CONFI_FILE);
            ClientGlobal.initByTrackers("192.168.5.161:22122");
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerService = trackerClient.getConnection();
            client = new StorageClient(trackerService, null);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static String uploadFile(byte[] fileBytes, String extName) {
        String filePath = "";

        try {
            String[] upload_appender_file = client.upload_appender_file(fileBytes, extName, null);
            filePath = upload_appender_file[0] + "/" + upload_appender_file[1];

        } catch (Exception e) {
            e.printStackTrace();
        }

        return filePath;
    }

    public static void uploadFile() throws IOException, MyException {
        String file_buff = "d://1.png";
        String file_ext_name="png";
        String[] upload_appender_file = client.upload_appender_file(file_buff, file_ext_name, null);
        System.out.println(upload_appender_file[0]);
        System.out.println(upload_appender_file[1]);

        System.out.println("文件路径为："+upload_appender_file[0]+"/"+upload_appender_file[1]);


    }

    public static void main(String[] args) {
        try {
            uploadFile();
        } catch (IOException | MyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
