package top.itning.yunshuvideo.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.itning.yunshuvideo.common.config.FileSpaceConfigProperties;
import top.itning.yunshuvideo.common.dto.UsersDTO;
import top.itning.yunshuvideo.common.entity.Users;
import top.itning.yunshuvideo.common.entity.UsersExample;
import top.itning.yunshuvideo.common.util.FileUtils;
import top.itning.yunshuvideo.mapper.UsersMapper;
import top.itning.yunshuvideo.service.UsersService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 用户服务实现
 *
 * @author itning
 * @date 2019/4/19 13:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UsersServiceImpl implements UsersService {
    private final UsersMapper usersMapper;

    private final ModelMapper modelMapper;

    private final FileSpaceConfigProperties fileSpaceConfigProperties;

    @Autowired
    public UsersServiceImpl(UsersMapper usersMapper, ModelMapper modelMapper, FileSpaceConfigProperties fileSpaceConfigProperties) {
        this.usersMapper = usersMapper;
        this.modelMapper = modelMapper;
        this.fileSpaceConfigProperties = fileSpaceConfigProperties;
    }

    @Override
    public boolean queryUserNameIsExist(String username) {
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andUsernameEqualTo(username);
        return usersMapper.countByExample(usersExample) > 0;
    }

    @Override
    public UsersDTO saveUsers(Users users) {
        String id = UUID.randomUUID().toString().replace("-", "");
        users.setId(id);
        users.setPassword(DigestUtils.md5Hex(users.getPassword()));
        users.setFansCounts(0);
        users.setFollowCounts(0);
        users.setReceiveLikeCounts(0);
        if (StringUtils.isBlank(users.getNickname())) {
            users.setNickname(users.getUsername());
        }
        usersMapper.insert(users);
        return modelMapper.map(usersMapper.selectByPrimaryKey(id), UsersDTO.class);
    }

    @Override
    public UsersDTO queryUserForLogin(String username, String password) {
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria()
                .andUsernameEqualTo(username)
                .andPasswordEqualTo(DigestUtils.md5Hex(password));
        List<Users> users = usersMapper.selectByExample(usersExample);
        if (users.isEmpty()) {
            return null;
        }
        return modelMapper.map(users.get(0), UsersDTO.class);
    }

    @Override
    public void uploadFaceImage(MultipartFile file, UsersDTO users) throws IOException {
        String faceImageName = users.getId() + FileUtils.getExtensionName(file);
        file.transferTo(new File(fileSpaceConfigProperties.getFaceImgDir() + File.separator + faceImageName));
        Users saveUsers = new Users();
        saveUsers.setId(users.getId());
        saveUsers.setFaceImage(faceImageName);
        usersMapper.updateByPrimaryKeySelective(saveUsers);
    }
}
