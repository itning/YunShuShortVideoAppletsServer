package top.itning.yunshuvideo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.itning.yunshuvideo.common.RestModel;
import top.itning.yunshuvideo.service.BgmService;

/**
 * 背景音乐
 *
 * @author itning
 * @date 2019/4/20 18:27
 */
@Api(tags = {"背景音乐接口"})
@RestController
@RequestMapping("/bgm")
public class BgmController {
    private final BgmService bgmService;

    @Autowired
    public BgmController(BgmService bgmService) {
        this.bgmService = bgmService;
    }

    /**
     * 获取所有BGM
     *
     * @return ResponseEntity
     */
    @ApiOperation(value = "获取所有BGM", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class)
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(new RestModel<>(bgmService.queryBgmList()));
    }

}
