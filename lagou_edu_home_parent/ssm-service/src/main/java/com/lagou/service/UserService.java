package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {

    /*
        用户分页&多条件查询
     */
    public PageInfo findAllUserByPage(UserVo userVo);
    /*测试create_time和update_time解析问题*/
    public List<User> findAllUser(UserVo userVo);
    /*
       修改用户状态
     */
    public void updateUserStatus(int id, String status);

    /*
        用户登陆
     */

    public User login(User user) throws Exception;

    /*
        分配角色（回显）
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
        用户关联角色
     */
    public void userContextRole(UserVo userVo);

    /*
        获取用户权限，进行菜单动态展示
     */
    public ResponseResult getUserPermissions(Integer userid);

}
