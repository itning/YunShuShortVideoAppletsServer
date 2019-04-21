package top.itning.yunshuvideo.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 截取视频第一帧
 *
 * @author itning
 * @date 2019/4/20 22:31
 */
public final class FetchVideoCover {
    private static final Logger logger = LoggerFactory.getLogger(FetchVideoCover.class);
    /**
     * 视频路径
     */
    private String ffmpegEXE;

    public void getCover(String videoInputPath, String coverOutputPath) throws IOException {
        //ffmpeg.exe -ss 00:00:01 -i spring.mp4 -vframes 1 bb.jpg
        List<String> command = new java.util.ArrayList<>();
        command.add(ffmpegEXE);

        // 指定截取第1秒
        command.add("-ss");
        command.add("00:00:01");

        command.add("-y");
        command.add("-i");
        command.add(videoInputPath);

        command.add("-vframes");
        command.add("1");

        command.add(coverOutputPath);
        StringBuilder stringBuilder = new StringBuilder(command.size() * 2);
        for (String c : command) {
            stringBuilder.append(c).append(" ");
        }
        logger.debug(stringBuilder.toString());

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();
        try (InputStream errorStream = process.getErrorStream();
             InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
             BufferedReader br = new BufferedReader(inputStreamReader)) {
            String line = "";
            while ((line = br.readLine()) != null) {

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getFfmpegEXE() {
        return ffmpegEXE;
    }

    public void setFfmpegEXE(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }

    public FetchVideoCover() {
        super();
    }

    public FetchVideoCover(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }

    public static void main(String[] args) {
        // 获取视频信息。
        FetchVideoCover videoInfo = new FetchVideoCover("F:\\IDMDownload\\ffmpeg-20190420-3a07aec-win64-static\\bin\\ffmpeg.exe");
        try {
            videoInfo.getCover("C:\\Users\\wangn\\Desktop\\videoTemp\\video\\6ed65750e987451594e10606d6e40ffb.mp4", "C:\\Users\\wangn\\Desktop\\videoTemp\\video\\6ed65750e987451594e10606d6e40ffb.mp4.jpg");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
