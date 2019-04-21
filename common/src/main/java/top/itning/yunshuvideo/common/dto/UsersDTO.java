package top.itning.yunshuvideo.common.dto;

import java.io.Serializable;

/**
 * @author itning
 * @date 2019/4/19 13:25
 */
public class UsersDTO implements Serializable {
    private String id;

    private String username;

    private String faceImage;

    private String nickname;

    private Integer fansCounts;

    private Integer followCounts;

    private Integer receiveLikeCounts;

    public UsersDTO(String id, String username, String faceImage, String nickname, Integer fansCounts, Integer followCounts, Integer receiveLikeCounts) {
        this.id = id;
        this.username = username;
        this.faceImage = faceImage;
        this.nickname = nickname;
        this.fansCounts = fansCounts;
        this.followCounts = followCounts;
        this.receiveLikeCounts = receiveLikeCounts;
    }

    public UsersDTO() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage == null ? null : faceImage.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getFansCounts() {
        return fansCounts;
    }

    public void setFansCounts(Integer fansCounts) {
        this.fansCounts = fansCounts;
    }

    public Integer getFollowCounts() {
        return followCounts;
    }

    public void setFollowCounts(Integer followCounts) {
        this.followCounts = followCounts;
    }

    public Integer getReceiveLikeCounts() {
        return receiveLikeCounts;
    }

    public void setReceiveLikeCounts(Integer receiveLikeCounts) {
        this.receiveLikeCounts = receiveLikeCounts;
    }

    @Override
    public String toString() {
        return "UsersDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", faceImage='" + faceImage + '\'' +
                ", nickname='" + nickname + '\'' +
                ", fansCounts=" + fansCounts +
                ", followCounts=" + followCounts +
                ", receiveLikeCounts=" + receiveLikeCounts +
                '}';
    }
}
