package com.lagou.dao;

import com.lagou.domain.Menu;
import com.lagou.domain.MenuVo;
import com.lagou.domain.UserVo;

import java.util.List;

public interface MenuMapper {

    /*
        查询所有父子菜单信息
     */

    public List<Menu> findSubMenuListByPid(int pid);

    /*
        查询所有菜单列表
     */
    public List<Menu> findAllMenu(MenuVo menuVo);

    /*
        根据ID查询菜单信息
     */
    public Menu findMenuById(Integer id);


    /*
        添加菜单信息
     */
    public void saveMenu(Menu menu);

    /*
        修改菜单信息
     */
    public void updateMenu(Menu menu);
    /*
        删除菜单信息
     */
    public void deleteMenu(int id);
}
