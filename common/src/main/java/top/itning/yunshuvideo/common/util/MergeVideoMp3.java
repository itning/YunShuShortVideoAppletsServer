package top.itning.yunshuvideo.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 将视频和音频合成
 *
 * @author itning
 * @date 2019/4/20 22:31
 */
public final class MergeVideoMp3 {
    private static final Logger logger = LoggerFactory.getLogger(MergeVideoMp3.class);

    private String ffmpegEXE;

    public MergeVideoMp3(String ffmpegEXE) {
        super();
        this.ffmpegEXE = ffmpegEXE;
    }

    public void convertor(String videoInputPath, String mp3InputPath,
                          double seconds, String videoOutputPath) throws Exception {
        //ffmpeg.exe -i 苏州大裤衩.mp4 -i bgm.mp3 -t 7 -y 新的视频.mp4
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);

        command.add("-i");
        command.add(videoInputPath);

        command.add("-i");
        command.add(mp3InputPath);

        command.add("-t");
        command.add(String.valueOf(seconds));

        command.add("-y");
        command.add(videoOutputPath);
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

    public static void main(String[] args) {
        MergeVideoMp3 ffmpeg = new MergeVideoMp3("C:\\ffmpeg\\bin\\ffmpeg.exe");
        try {
            ffmpeg.convertor("C:\\苏州大裤衩.mp4", "C:\\music.mp3", 7.1, "C:\\这是通过java生产的视频.mp4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
