package com.ovft.configure.sys.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import sun.applet.Main;

import java.io.IOException;
import java.util.UUID;

/**
 * @author vvtxw
 * @create 2019-04-23 14:12
 */
public class FastUtil {
    public static StorageClient client = null;

    static {

        try {
            ClientGlobal.initByTrackers("120.27.16.130:22122");
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

    /**
     * 删除文件
     *
     * @param group       组名 如：group1
     * @param storagePath 不带组名的路径名称 如：M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return -1失败,0成功
     */
    public static Integer delete_file(String group, String storagePath) {
        int result = -1;
        try {
            result = client.delete_file(group, storagePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return result;
    }


}
