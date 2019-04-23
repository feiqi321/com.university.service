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


    @Value("${fastdfs.tracker_servers}")
    private static String tracker_servers;

    static {
        try {
//            ClientGlobal.init("fdfs_client.conf");
            ClientGlobal.initByTrackers(tracker_servers);
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


}
