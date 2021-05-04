package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
        用户分页&多条件组合查询方法
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {

        PageInfo pageInfo = userService.findAllUserByPage(userVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", pageInfo);
        List<User> list = pageInfo.getList();
        System.out.println(list);
        return responseResult;
    }


    /*
        修改用户状态
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id, String status) {
        userService.updateUserStatus(id, status);
        return new ResponseResult(true, 200, "修改用户状态成功", status);

    }


    /*
        用户登陆
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        //此方法为GET请求，不用加@RequestBody
        User user1 = userService.login(user);

        if (user1 != null) {

            // 保存用户id及access_token（随机字符串）到session中
            /*
             第二次请求的请求头中会携带access_token发送给后台，后台判断是否和session中的access_token是否一致
            */
            /*
              （1）向session中存储当前用户的id;
              （2）向session中存储access_token（随机字符串，如4a9a765c-1511-45f0-893d-b002d0008e99）;
              （3）将access_token和userid通过ResponseResult对象转换成Json串响应给前台）
           */
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            System.out.println(access_token);
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id", user1.getId());

            // 将查询出来的信息响应给前台
            Map<String, Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("user_id", user1.getId());

            // 将查询出来的user,也存到map中，因为在前台进行用户登出功能时需要用到user对象中的其它信息
            map.put("user", user1);

            return new ResponseResult(true, 1, "登陆成功", map);

        } else {
            return new ResponseResult(true, 400, "用户名密码错误", null);
        }

    }


    /*
        分配角色（回显）
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id) {

        List<Role> roleList = userService.findUserRelationRoleById(id);

        return new ResponseResult(true, 200, "分配角色回显成功", roleList);
    }


    /*
        分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo) {

        userService.userContextRole(userVo);

        return new ResponseResult(true, 200, "分配角色成功", null);
    }


    /*
        获取用户权限，进行菜单动态展示
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {

        // 1.获取请求头中的token
        String header_token = request.getHeader("Authorization");

        // 2.获取session中token
        String session_token = (String) request.getSession().getAttribute("access_token");

        // 3.判断token是否一致
        if (header_token.equals(session_token)) {
            // 获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            // 调用service,进行菜单信息查询
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        } else {
            ResponseResult responseResult = new ResponseResult(false, 400, "获取菜单信息失败", null);
            return responseResult;
        }


    }
}
