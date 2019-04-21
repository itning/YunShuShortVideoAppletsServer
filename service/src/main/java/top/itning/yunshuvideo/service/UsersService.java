package top.itning.yunshuvideo.service;

import org.springframework.web.multipart.MultipartFile;
import top.itning.yunshuvideo.common.dto.UsersDTO;
import top.itning.yunshuvideo.common.entity.Users;

import java.io.IOException;

/**
 * 用户服务
 *
 * @author itning
 * @date 2019/4/19 13:17
 */
public interface UsersService {
    /**
     * 根据用户名判断用户是否存在
     *
     * @param username 用户名
     * @return 存在返回<code>true</code>
     */
    boolean queryUserNameIsExist(String username);

    /**
     * 保存用户
     *
     * @param users {@link Users}
     * @return 被保存的用户信息
     */
    UsersDTO saveUsers(Users users);

    /**
     * 根据用户名密码进行登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return {@link UsersDTO}
     */
    UsersDTO queryUserForLogin(String username, String password);

    /**
     * 用户上传头像
     *
     * @param file  文件
     * @param users 用户
     * @throws IOException 文件保存异常
     */
    void uploadFaceImage(MultipartFile file, UsersDTO users) throws IOException;
}
