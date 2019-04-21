package top.itning.yunshuvideo.mapper;

import org.apache.ibatis.annotations.Param;
import top.itning.yunshuvideo.common.entity.Videos;
import top.itning.yunshuvideo.common.entity.VideosExample;

import java.util.List;

public interface VideosMapper {
    long countByExample(VideosExample example);

    int deleteByExample(VideosExample example);

    int deleteByPrimaryKey(String id);

    int insert(Videos record);

    int insertSelective(Videos record);

    List<Videos> selectByExample(VideosExample example);

    Videos selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Videos record, @Param("example") VideosExample example);

    int updateByExample(@Param("record") Videos record, @Param("example") VideosExample example);

    int updateByPrimaryKeySelective(Videos record);

    int updateByPrimaryKey(Videos record);
}