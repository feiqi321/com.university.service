package com.ovft.configure.sys.web;

import com.ovft.configure.sys.utils.FastUtil;
import com.ovft.configure.sys.utils.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-04-23 15:06
 */
@RestController
public class FileController {
    @Value("${fastdfs.storage.url}")
    private String storageHost;


    @RequestMapping(value = "pic/upload", method = RequestMethod.POST)
    public Map<String, Object> uploadImge(MultipartFile uploadFile, String dir) {
        String originalFilename = uploadFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        Map<String, Object> map = new HashMap<String, Object>();
        String filePath = null;
        try {
            filePath = FastUtil.uploadFile(uploadFile.getBytes(), extName);
            filePath = storageHost + filePath;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("message", e);
            return map;
        }
        map.put("error", 0);
        map.put("url", filePath);
        return map;
    }

}
