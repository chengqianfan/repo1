package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    public ResourceService resourceService;

    /*
     资源分页&多条件查询
      */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourseVo resourseVo) {

        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourseVo);

        return new ResponseResult(true, 200, "资源信息分页多条件查询成功", pageInfo);

    }
    /*
        添加&更新资源信息
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        if(resource.getId() == null){
            resourceService.saveResource(resource);
            return new ResponseResult(true,200,"添加资源成功",null);
        }else {
            resourceService.updateResource(resource);
            return new ResponseResult(true,200,"修改资源成功",null);

        }

    }
    /*
        删除资源信息
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(int id){
        resourceService.deleteResource(id);
        return new ResponseResult(true,200,"删除资源成功",null);
    }

}
