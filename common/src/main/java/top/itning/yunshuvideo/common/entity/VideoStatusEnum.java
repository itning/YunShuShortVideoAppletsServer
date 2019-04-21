package top.itning.yunshuvideo.common.entity;

/**
 * @author itning
 * @date 2019/4/20 22:02
 */
public enum VideoStatusEnum {
    /**
     * 发布成功
     */
    SUCCESS(1),

    /**
     * 禁止播放，管理员操作
     */
    FORBID(2);

    public final int value;

    VideoStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
