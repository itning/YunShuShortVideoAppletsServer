package top.itning.yunshuvideo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
import top.itning.yunshuvideo.common.RestModel;
import top.itning.yunshuvideo.common.dto.UsersDTO;
import top.itning.yunshuvideo.common.entity.Comments;
import top.itning.yunshuvideo.common.entity.VideoStatusEnum;
import top.itning.yunshuvideo.common.entity.Videos;
import top.itning.yunshuvideo.service.VideoService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

import static top.itning.yunshuvideo.common.CommonString.USER_SESSION_KEY;

/**
 * 视频控制器
 *
 * @author itning
 * @date 2019/4/20 19:14
 */
@Api(tags = {"视频接口"})
@RestController
@RequestMapping("/video")
public class VideoController {
    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    /**
     * 上传视频
     *
     * @param file        视频文件
     * @param httpSession {@link HttpSession}
     * @return ResponseEntity
     */
    @ApiOperation(value = "上传视频", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class)
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@ApiParam("背景音乐ID") String bgmId,
                                    @ApiParam(value = "视频秒数", required = true) @RequestParam(name = "duration") float videoSeconds,
                                    @ApiParam(value = "视频宽度", required = true) @RequestParam(name = "width") int videoWidth,
                                    @ApiParam(value = "视频高度", required = true) @RequestParam(name = "height") int videoHeight,
                                    @ApiParam("视频描述") String desc,
                                    @ApiParam(value = "文件", required = true) @RequestParam("file") MultipartFile file,
                                    @ApiIgnore HttpSession httpSession) throws IOException {
        if (httpSession.getAttribute(USER_SESSION_KEY) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RestModel<>(HttpStatus.UNAUTHORIZED, "登陆过期"));
        }
        logger.debug("upload video: {} {} ", file.getContentType(), file.getSize());
        UsersDTO users = (UsersDTO) httpSession.getAttribute(USER_SESSION_KEY);
        Videos videos = new Videos();
        videos.setAudioId(bgmId);
        videos.setVideoDesc(desc);
        videos.setVideoSeconds(videoSeconds);
        videos.setVideoWidth(videoWidth);
        videos.setVideoHeight(videoHeight);
        videos.setCoverPath("");
        videos.setLikeCounts(0L);
        videos.setStatus(VideoStatusEnum.SUCCESS.value);
        videos.setCreateTime(new Date());
        videoService.saveVideo(file, users, videos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 所有视频
     *
     * @param page     当前页数
     * @param pageSize 每页大小
     * @return ResponseEntity
     */
    @ApiOperation(value = "所有视频", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class)
    @GetMapping("/showAll")
    public ResponseEntity<?> showAll(@ApiParam("当前页数") @RequestParam(required = false, defaultValue = "1") int page,
                                     @ApiParam("每页大小") @RequestParam(required = false, defaultValue = "5") int pageSize) {
        logger.debug("show all videos page {} size {}", page, pageSize);
        return ResponseEntity.ok(new RestModel<>(videoService.getAllVideos(page, pageSize)));
    }

    /**
     * 评论
     *
     * @param comment         {@link Comments}
     * @param fatherCommentId ?
     * @param toUserId        ?
     * @param httpSession     {@link HttpSession}
     * @return ResponseEntity
     */
    @ApiOperation(value = "评论", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class)
    @PostMapping("/saveComment")
    public ResponseEntity<?> saveComment(@ApiParam("评论") Comments comment,
                                         @ApiParam("") String fatherCommentId,
                                         @ApiParam("") String toUserId,
                                         @ApiIgnore HttpSession httpSession) {
        if (httpSession.getAttribute(USER_SESSION_KEY) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RestModel<>(HttpStatus.UNAUTHORIZED, "登陆过期"));
        }
        UsersDTO users = (UsersDTO) httpSession.getAttribute(USER_SESSION_KEY);
        comment.setFatherCommentId(fatherCommentId);
        comment.setToUserId(toUserId);
        videoService.saveComment(users, comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取视频评论
     *
     * @param videoId  视频ID
     * @param page     页数
     * @param pageSize 每页数量
     * @return ResponseEntity
     */
    @ApiOperation(value = "获取视频评论", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class)
    @GetMapping("/getVideoComments")
    public ResponseEntity<?> getVideoComments(@ApiParam(value = "视频ID", required = true) @RequestParam String videoId,
                                              @ApiParam("当前页数") @RequestParam(required = false, defaultValue = "1") int page,
                                              @ApiParam("每页大小") @RequestParam(required = false, defaultValue = "5") int pageSize) {
        logger.debug("get video comments video id {}", videoId);
        return ResponseEntity.ok(new RestModel<>(videoService.getAllComments(videoId, page, pageSize)));
    }
}
