package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /*
        查询所有父子菜单信息
     */
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> subMenuListByPid = menuMapper.findSubMenuListByPid(pid);
        return subMenuListByPid;
    }

    /*
        查询所有菜单信息
     */
    @Override
    public List<Menu> findAllMenu() {
        List<Menu> allMenu = menuMapper.findAllMenu();
        return allMenu;
    }

    /*
        根据ID查询菜单信息
      */
    @Override
    public Menu findMenuById(Integer id) {

        return menuMapper.findMenuById(id);
    }

    /*
        添加菜单信息
     */
    @Override
    public void saveMenu(Menu menu) {
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);

        menuMapper.saveMenu(menu);

    }

    /*
        修改菜单信息
     */
    @Override
    public void updateMenu(Menu menu) {
        Date date = new Date();
        menu.setUpdatedTime(date);

        menuMapper.updateMenu(menu);
    }

    /*
        删除菜单信息
     */
    @Override
    public void deleteMenu(int id) {
        menuMapper.deleteMenu(id);
    }
}
