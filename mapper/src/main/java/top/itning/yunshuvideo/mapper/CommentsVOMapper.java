package top.itning.yunshuvideo.mapper;

import top.itning.yunshuvideo.common.vo.CommentsVO;

import java.util.List;

/**
 * @author itning
 * @date 2019/4/21 13:41
 */
public interface CommentsVOMapper {
    List<CommentsVO> queryComments(String videoId);
}
