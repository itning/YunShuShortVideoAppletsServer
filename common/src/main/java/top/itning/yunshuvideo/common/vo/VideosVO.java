package top.itning.yunshuvideo.common.vo;

import java.util.Date;

/**
 * @author itning
 * @date 2019/4/20 23:06
 */
public class VideosVO {
    private String id;

    private String userId;

    private String audioId;

    private String videoDesc;

    private String videoPath;

    private Float videoSeconds;

    private Integer videoWidth;

    private Integer videoHeight;

    private String coverPath;

    private Long likeCounts;

    private Integer status;

    private Date createTime;

    private String faceImg;

    private String nickName;

    public VideosVO() {
    }

    public VideosVO(String id, String userId, String audioId, String videoDesc, String videoPath, Float videoSeconds, Integer videoWidth, Integer videoHeight, String coverPath, Long likeCounts, Integer status, Date createTime, String faceImg, String nickName) {
        this.id = id;
        this.userId = userId;
        this.audioId = audioId;
        this.videoDesc = videoDesc;
        this.videoPath = videoPath;
        this.videoSeconds = videoSeconds;
        this.videoWidth = videoWidth;
        this.videoHeight = videoHeight;
        this.coverPath = coverPath;
        this.likeCounts = likeCounts;
        this.status = status;
        this.createTime = createTime;
        this.faceImg = faceImg;
        this.nickName = nickName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAudioId() {
        return audioId;
    }

    public void setAudioId(String audioId) {
        this.audioId = audioId;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Float getVideoSeconds() {
        return videoSeconds;
    }

    public void setVideoSeconds(Float videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    public Integer getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    public Integer getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public Long getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(Long likeCounts) {
        this.likeCounts = likeCounts;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "VideosVO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", audioId='" + audioId + '\'' +
                ", videoDesc='" + videoDesc + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", videoSeconds=" + videoSeconds +
                ", videoWidth=" + videoWidth +
                ", videoHeight=" + videoHeight +
                ", coverPath='" + coverPath + '\'' +
                ", likeCounts=" + likeCounts +
                ", status=" + status +
                ", createTime=" + createTime +
                ", faceImg='" + faceImg + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
