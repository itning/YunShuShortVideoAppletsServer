package top.itning.yunshuvideo.service;

import top.itning.yunshuvideo.common.entity.Bgm;

import java.util.List;

/**
 * 背景音乐服务
 *
 * @author itning
 * @date 2019/4/20 18:18
 */
public interface BgmService {
    /**
     * 获取所有BGM
     *
     * @return BGM集合
     */
    List<Bgm> queryBgmList();
}
