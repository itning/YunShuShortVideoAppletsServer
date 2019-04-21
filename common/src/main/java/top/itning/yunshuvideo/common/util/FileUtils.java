package top.itning.yunshuvideo.common.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * 文件工具类
 *
 * @author itning
 */
public final class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {
    }

    /**
     * 获取文件扩展名
     *
     * @param file {@link MultipartFile}
     * @return 文件扩展名
     */
    public static String getExtensionName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extensionName = "";
        if (originalFilename != null) {
            int i = originalFilename.lastIndexOf(".");
            if (i != -1) {
                extensionName = file.getOriginalFilename().substring(i);
            }
        }
        return extensionName;
    }

    public static String getFileMD5(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return DigestUtils.md5Hex(fileInputStream);
        } catch (Exception e) {
            logger.error("getFileMD5 method error: ", e);
            throw new RuntimeException(e);
        }
    }
}
