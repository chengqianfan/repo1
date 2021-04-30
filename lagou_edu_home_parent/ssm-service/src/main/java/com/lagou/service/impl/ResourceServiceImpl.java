package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;


    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourseVo resourseVo) {

        //分页查询
        PageHelper.startPage(resourseVo.getCurrentPage(), resourseVo.getPageSize());

        List<Resource> allResourceByPage = resourceMapper.findAllResourceByPage(resourseVo);

        PageInfo<Resource> pageInfo = new PageInfo<>(allResourceByPage);

        return pageInfo;
    }

    /*
        添加资源信息
     */
    @Override
    public void saveResource(Resource resource) {
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resourceMapper.saveResource(resource);
    }

    /*
        修改资源信息
     */
    @Override
    public void updateResource(Resource resource) {
        Date date = new Date();
        resource.setUpdatedTime(date);
        resourceMapper.updateResource(resource);
    }

    /*
        删除资源信息
    */
    @Override
    public void deleteResource(int id) {
        resourceMapper.deleteResource(id);
    }
}
