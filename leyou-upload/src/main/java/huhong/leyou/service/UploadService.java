package huhong.leyou.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif");
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    @Autowired
    private FastFileStorageClient fastFileStorageClient;


    public String uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        //校验文件的类型
        String contentType = file.getContentType();
        if(!CONTENT_TYPES.contains(contentType)){
            LOGGER.info("文件类型不合法: " + originalFilename);
            return null;
        }
        try {
            // 校验文件内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if(bufferedImage == null){
                LOGGER.info("文件内容不合法: " + originalFilename);
            }

            // 保存到服务器
//            file.transferTo(new File("/Users/huhong/求职/黑马/11-乐优商城/leyou/image/" + originalFilename));
            String s = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath storePath = this.fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), s, null);

            // 返回url进行回显
            return "http://image.leyou.com/" + storePath.getFullPath();
        } catch (IOException e) {
            LOGGER.info("服务器内部错误");
            e.printStackTrace();
        }
        return null;
    }
}
