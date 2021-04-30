package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;

public interface ResourceService {

   /*
        资源分页&多条件查询
     */

    public PageInfo<Resource> findAllResourceByPage(ResourseVo resourseVo);

    /*
        添加资源信息
     */
    public void saveResource(Resource resource);

    /*
        修改资源信息
     */
    public void updateResource(Resource resource);

    /*
        删除资源信息
     */
    public void deleteResource(int id);
}
