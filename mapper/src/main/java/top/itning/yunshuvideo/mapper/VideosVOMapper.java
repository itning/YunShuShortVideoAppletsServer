package top.itning.yunshuvideo.mapper;

import top.itning.yunshuvideo.common.vo.VideosVO;

import java.util.List;

public interface VideosVOMapper {
    List<VideosVO> queryAllVideos();
}