package online.xuanwei.bbs.controller;

import com.alibaba.fastjson.JSONObject;
import online.xuanwei.bbs.model.User;
import online.xuanwei.bbs.provider.QCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class FileControlle {

    @Autowired
    QCloudProvider qCloudProvider;

    @PostMapping("/publish/upload")
    @ResponseBody
    public Object editormdPic (@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest httpServletRequest) throws Exception{

        /*
        String trueFileName = file.getOriginalFilename();

        User user = (User)httpServletRequest.getSession().getAttribute("user");
        String prefix = user.getAccountId();
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));

        String fileName = prefix+ System.currentTimeMillis()+suffix;

        String path = ResourceUtils.getURL("static/images/").getPath();
        System.out.println(path);


        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject res = new JSONObject();
        res.put("url", "http://localhost:9999"+"/images/"+fileName);
        res.put("success", 1);
        res.put("message", "upload success!");

        return res;

         */

        String url = qCloudProvider.upload(file);
        JSONObject res = new JSONObject();
        res.put("url", url);
        res.put("success", 1);
        res.put("message", "upload success!");
        return  res;


    }

}
