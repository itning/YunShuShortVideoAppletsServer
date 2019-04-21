package top.itning.yunshuvideo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.itning.yunshuvideo.common.PagedResult;
import top.itning.yunshuvideo.common.config.FileSpaceConfigProperties;
import top.itning.yunshuvideo.common.dto.UsersDTO;
import top.itning.yunshuvideo.common.entity.Comments;
import top.itning.yunshuvideo.common.entity.Videos;
import top.itning.yunshuvideo.common.util.FetchVideoCover;
import top.itning.yunshuvideo.common.util.FileUtils;
import top.itning.yunshuvideo.common.vo.CommentsVO;
import top.itning.yunshuvideo.common.vo.VideosVO;
import top.itning.yunshuvideo.mapper.CommentsVOMapper;
import top.itning.yunshuvideo.mapper.VideosMapper;
import top.itning.yunshuvideo.mapper.VideosVOMapper;
import top.itning.yunshuvideo.service.VideoService;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author itning
 * @date 2019/4/20 21:55
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VideoServiceImpl implements VideoService {
    private final FileSpaceConfigProperties fileSpaceConfigProperties;
    private final VideosMapper videosMapper;
    private final VideosVOMapper videosVOMapper;
    private final CommentsVOMapper commentsVOMapper;

    @Autowired
    public VideoServiceImpl(FileSpaceConfigProperties fileSpaceConfigProperties, VideosMapper videosMapper, VideosVOMapper videosVOMapper, CommentsVOMapper commentsVOMapper) {
        this.fileSpaceConfigProperties = fileSpaceConfigProperties;
        this.videosMapper = videosMapper;
        this.videosVOMapper = videosVOMapper;
        this.commentsVOMapper = commentsVOMapper;
    }

    @Override
    public void saveVideo(MultipartFile file, UsersDTO usersDTO, Videos videos) throws IOException {
        videos.setId(UUID.randomUUID().toString().replace("-", ""));
        videos.setUserId(usersDTO.getId());
        String videoName = videos.getId() + FileUtils.getExtensionName(file);
        videos.setVideoPath(videoName);
        String videoFile = fileSpaceConfigProperties.getVideoDir() + File.separator + videoName;
        String coverFile = fileSpaceConfigProperties.getVideoDir() + File.separator + videos.getId() + ".jpg";
        file.transferTo(new File(videoFile));
        FetchVideoCover fetchVideoCover = new FetchVideoCover(fileSpaceConfigProperties.getFfmpegEXEPath());
        fetchVideoCover.getCover(videoFile, coverFile);
        videos.setCoverPath(videos.getId() + ".jpg");
        videosMapper.insertSelective(videos);
    }

    @Override
    public PagedResult<List<VideosVO>> getAllVideos(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<VideosVO> videos = videosVOMapper.queryAllVideos();
        PageInfo<VideosVO> pageInfo = new PageInfo<>(videos);
        PagedResult<List<VideosVO>> pagedResult = new PagedResult<>();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageInfo.getPages());
        pagedResult.setRecords(pageInfo.getTotal());
        pagedResult.setRows(videos);
        return pagedResult;
    }

    @Override
    public void saveComment(UsersDTO usersDTO, Comments comments) {
        comments.setId(UUID.randomUUID().toString().replace("-", ""));
        comments.setCreateTime(new Date());

    }

    @Override
    public PagedResult<List<CommentsVO>> getAllComments(String videoId, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<CommentsVO> comments = commentsVOMapper.queryComments(videoId);
        PageInfo<CommentsVO> pageList = new PageInfo<>(comments);
        PagedResult<List<CommentsVO>> grid = new PagedResult<>();
        grid.setTotal(pageList.getPages());
        grid.setRows(comments);
        grid.setPage(page);
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
