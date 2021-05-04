package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /*
        用户分页&多条件组合查询
     */
    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);

        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);
        return pageInfo;
    }


    /*
        修改用户状态
    */
    @Override
    public void updateUserStatus(int id, String status) {

        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdateTime(new Date());
        userMapper.updateUserStatus(user);
    }

    /*
        用户登陆
     */
    @Override
    public User login(User user) throws Exception {

        // 1.调用mapper方法 user2:包含了密文密码
        //根据用户名在数据库里查询密文密码，有可能为空，所以要判断
        User user2 = userMapper.login(user);
        //第一个参数为明文密码123456(前台传递)，第二个为密钥，第三个为密文密码
        if (user2 != null && Md5.verify(user.getPassword(), "lagou", user2.getPassword())) {
            return user2;
        } else {
            return null;
        }

    }

    /*
        分配角色（回显）
     */
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);

        return list;
    }


    /*
        分配角色
    */
    @Override
    public void userContextRole(UserVo userVo) {

        // 1.根据用户ID清空中间表关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());

        // 2.再重新建立关联关系
        for (Integer roleid : userVo.getRoleIdList()) {

            // 封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleid);

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);

            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);

        }

    }

    /*
        获取用户权限信息
     */
    @Override
    public ResponseResult getUserPermissions(Integer userid) {

        // 1. 获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userid);

        // 2. 获取角色ID，保存到List集合中
        ArrayList<Integer> roleIds = new ArrayList<>();

        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        // 3.根据角色ID查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        // 4.查询封装父菜单关联的子菜单
        /*
        疑问：为什么能封装到parentMenu中？
        通过debug发现该循环每执行一次parentMenu中的subMenuList便会被subMenu赋值
        注意：基本类型数组，不可改变。引用类型数组（除String类型）可以改变，因为此时引用地址一致。
         */
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        // 5.获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);


        // 6. 封装数据并返回
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", parentMenu);
        map.put("resourceList", resourceList);


        return new ResponseResult(true, 200, "获取用户权限信息成功", map);
    }
}
