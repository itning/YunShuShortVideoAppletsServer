package top.itning.yunshuvideo.service;

import org.springframework.web.multipart.MultipartFile;
import top.itning.yunshuvideo.common.PagedResult;
import top.itning.yunshuvideo.common.dto.UsersDTO;
import top.itning.yunshuvideo.common.entity.Comments;
import top.itning.yunshuvideo.common.entity.Videos;
import top.itning.yunshuvideo.common.vo.CommentsVO;
import top.itning.yunshuvideo.common.vo.VideosVO;

import java.io.IOException;
import java.util.List;

/**
 * 视频服务
 *
 * @author itning
 * @date 2019/4/20 21:53
 */
public interface VideoService {
    /**
     * 保存视频
     *
     * @param file     视频文件
     * @param usersDTO 用户
     * @param videos   视频元信息
     * @throws IOException 文件保存异常
     */
    void saveVideo(MultipartFile file, UsersDTO usersDTO, Videos videos) throws IOException;

    /**
     * 分页返回所有视频
     *
     * @param page     当前页数
     * @param pageSize 分页大小
     * @return {@link PagedResult}
     */
    PagedResult<List<VideosVO>> getAllVideos(int page, int pageSize);

    /**
     * 保存留言
     *
     * @param usersDTO 用户
     * @param comments 留言
     */
    void saveComment(UsersDTO usersDTO, Comments comments);

    /**
     * 获取视频评论
     *
     * @param videoId  视频ID
     * @param page     页数
     * @param pageSize 每页数量
     * @return PagedResult
     */
    PagedResult<List<CommentsVO>> getAllComments(String videoId, int page, int pageSize);
}
