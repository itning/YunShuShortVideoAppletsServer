package top.itning.yunshuvideo.mapper;

import org.apache.ibatis.annotations.Param;
import top.itning.yunshuvideo.common.entity.Bgm;
import top.itning.yunshuvideo.common.entity.BgmExample;

import java.util.List;

public interface BgmMapper {
    long countByExample(BgmExample example);

    int deleteByExample(BgmExample example);

    int deleteByPrimaryKey(String id);

    int insert(Bgm record);

    int insertSelective(Bgm record);

    List<Bgm> selectByExample(BgmExample example);

    List<Bgm> selectAll();

    Bgm selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Bgm record, @Param("example") BgmExample example);

    int updateByExample(@Param("record") Bgm record, @Param("example") BgmExample example);

    int updateByPrimaryKeySelective(Bgm record);

    int updateByPrimaryKey(Bgm record);
}