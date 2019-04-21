package top.itning.yunshuvideo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
import top.itning.yunshuvideo.common.RestModel;
import top.itning.yunshuvideo.common.config.FileSpaceConfigProperties;
import top.itning.yunshuvideo.common.dto.UsersDTO;
import top.itning.yunshuvideo.service.UsersService;

import javax.servlet.http.HttpSession;
import java.io.IOException;

import static top.itning.yunshuvideo.common.CommonString.USER_SESSION_KEY;

/**
 * @author itning
 * @date 2019/4/20 15:11
 */
@Api(tags = {"用户接口"})
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final FileSpaceConfigProperties fileSpaceConfigProperties;
    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService, FileSpaceConfigProperties fileSpaceConfigProperties) {
        this.usersService = usersService;
        this.fileSpaceConfigProperties = fileSpaceConfigProperties;
    }

    /**
     * 上传头像
     *
     * @param file        {@link MultipartFile}
     * @param httpSession {@link HttpSession}
     * @return ResponseEntity
     */
    @ApiOperation(value = "上传头像", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class)
    @PostMapping("/uploadFaceImg")
    public ResponseEntity<?> uploadFaceImg(@ApiParam(value = "文件", required = true) @RequestParam("file") MultipartFile file,
                                           @ApiIgnore HttpSession httpSession) throws IOException {
        if (httpSession.getAttribute(USER_SESSION_KEY) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RestModel<>(HttpStatus.UNAUTHORIZED, "登陆过期"));
        }
        logger.debug("upload face image: {} {} ", file.getContentType(), file.getSize());
        UsersDTO users = (UsersDTO) httpSession.getAttribute(USER_SESSION_KEY);
        usersService.uploadFaceImage(file, users);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestModel<>(HttpStatus.CREATED, "上传成功"));
    }
}
