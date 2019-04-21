package top.itning.yunshuvideo.mapper;

import org.apache.ibatis.annotations.Param;
import top.itning.yunshuvideo.common.entity.SearchRecords;
import top.itning.yunshuvideo.common.entity.SearchRecordsExample;

import java.util.List;

public interface SearchRecordsMapper {
    long countByExample(SearchRecordsExample example);

    int deleteByExample(SearchRecordsExample example);

    int deleteByPrimaryKey(String id);

    int insert(SearchRecords record);

    int insertSelective(SearchRecords record);

    List<SearchRecords> selectByExample(SearchRecordsExample example);

    SearchRecords selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SearchRecords record, @Param("example") SearchRecordsExample example);

    int updateByExample(@Param("record") SearchRecords record, @Param("example") SearchRecordsExample example);

    int updateByPrimaryKeySelective(SearchRecords record);

    int updateByPrimaryKey(SearchRecords record);
}