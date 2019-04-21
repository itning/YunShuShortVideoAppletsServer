package top.itning.yunshuvideo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author itning
 * @date 2019/4/20 15:39
 */
@ConfigurationProperties(prefix = "file")
@Component
public class FileSpaceConfigProperties {
    /**
     * 头像存储目录
     */
    private String faceImgDir;
    /**
     * 背景音乐存储目录
     */
    private String bgmDir;
    /**
     * 视频存储目录
     */
    private String videoDir;
    /**
     * ffmpeg.exe 路径
     * F:\IDMDownload\ffmpeg-20190420-3a07aec-win64-static\bin\ffmpeg.exe
     */
    private String ffmpegEXEPath;

    public String getFaceImgDir() {
        return faceImgDir;
    }

    public void setFaceImgDir(String faceImgDir) {
        this.faceImgDir = faceImgDir;
    }

    public String getBgmDir() {
        return bgmDir;
    }

    public void setBgmDir(String bgmDir) {
        this.bgmDir = bgmDir;
    }

    public String getVideoDir() {
        return videoDir;
    }

    public void setVideoDir(String videoDir) {
        this.videoDir = videoDir;
    }

    public String getFfmpegEXEPath() {
        return ffmpegEXEPath;
    }

    public void setFfmpegEXEPath(String ffmpegEXEPath) {
        this.ffmpegEXEPath = ffmpegEXEPath;
    }
}
