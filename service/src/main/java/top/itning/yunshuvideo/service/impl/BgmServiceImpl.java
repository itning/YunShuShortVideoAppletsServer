package top.itning.yunshuvideo.service.impl;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.itning.yunshuvideo.common.entity.Bgm;
import top.itning.yunshuvideo.common.entity.BgmExample;
import top.itning.yunshuvideo.mapper.BgmMapper;
import top.itning.yunshuvideo.service.BgmService;

import java.util.List;

/**
 * BGM服务实现类
 *
 * @author itning
 * @date 2019/4/20 18:19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BgmServiceImpl implements BgmService {
    private final BgmMapper bgmMapper;

    public BgmServiceImpl(BgmMapper bgmMapper) {
        this.bgmMapper = bgmMapper;
    }

    @Override
    public List<Bgm> queryBgmList() {
        return  bgmMapper.selectAll();
    }
}
