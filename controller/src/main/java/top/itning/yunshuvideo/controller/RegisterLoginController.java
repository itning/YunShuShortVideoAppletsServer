package top.itning.yunshuvideo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import top.itning.yunshuvideo.common.RestModel;
import top.itning.yunshuvideo.common.dto.UsersDTO;
import top.itning.yunshuvideo.common.entity.Users;
import top.itning.yunshuvideo.service.UsersService;

import javax.servlet.http.HttpSession;

import static top.itning.yunshuvideo.common.CommonString.USER_SESSION_KEY;

/**
 * @author itning
 * @date 2019/4/19 11:51
 */
@Api(tags = {"注册登陆接口"})
@RestController
@RequestMapping("/security")
public class RegisterLoginController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterLoginController.class);

    private final UsersService usersService;

    @Autowired
    public RegisterLoginController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * 注册用户
     *
     * @param users 用户信息
     * @return ResponseEntity
     */
    @ApiOperation(value = "注册", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class)
    @PostMapping("/register")
    public ResponseEntity<?> register(@ApiParam(value = "用户信息", required = true) Users users) {
        if (StringUtils.isAnyBlank(users.getUsername(), users.getPassword())) {
            return ResponseEntity.badRequest().body(new RestModel<>(HttpStatus.BAD_REQUEST, "用户名/密码不能为空"));
        }
        boolean exist = usersService.queryUserNameIsExist(users.getUsername());
        logger.debug("userName {} queryUserNameIsExist: {}", users.getUsername(), exist);
        if (exist) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new RestModel<>(HttpStatus.CONFLICT, "用户名已存在"));
        }
        UsersDTO usersDTO = usersService.saveUsers(users);
        logger.debug("saved users: {}", usersDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestModel<>("注册成功", usersDTO));
    }

    /**
     * 用户登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return ResponseEntity
     */
    @ApiOperation(value = "登陆", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class)
    @PostMapping("/login")
    public ResponseEntity<?> login(@ApiParam(value = "用户名", required = true) @RequestParam String username,
                                   @ApiParam(value = "密码", required = true) @RequestParam String password,
                                   @ApiIgnore HttpSession httpSession) {
        logger.debug("login username: {} and password: {}", username, password);
        UsersDTO usersDTO = usersService.queryUserForLogin(username, password);
        logger.debug("login users: {}", usersDTO);
        if (usersDTO == null) {
            return ResponseEntity.ok(new RestModel<>(HttpStatus.NOT_FOUND, "用户名/密码错误"));
        }
        httpSession.setAttribute(USER_SESSION_KEY, usersDTO);
        return ResponseEntity.ok(new RestModel<>("登陆成功", usersDTO));
    }

    /**
     * 注销登陆
     *
     * @param httpSession {@link HttpSession}
     * @return ResponseEntity
     */
    @ApiOperation(value = "注销登陆", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ResponseEntity.class)
    @GetMapping("/logout")
    public ResponseEntity<?> logout(@ApiIgnore HttpSession httpSession) {
        httpSession.removeAttribute("users");
        httpSession.invalidate();
        return ResponseEntity.ok(new RestModel<>(HttpStatus.OK, "注销成功"));
    }
}
