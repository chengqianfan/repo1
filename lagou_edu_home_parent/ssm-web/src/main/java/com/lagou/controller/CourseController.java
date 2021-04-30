package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController  //组合注解（Controller和ResponseBody）
@RequestMapping("/course") //请求映射路径
public class CourseController {

    @Autowired
    private CourseService courseService;

    /*
        多条件课程列表查询
     */
    //@RequestBody把前台请求发送的JSON串封装到CourseVO对象中，与ResponseBody功能相反
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO) {

        //调用service
        List<Course> list = courseService.findCourseByCondition(courseVO);
    /*把查询结果封装到实体类ResponseResult中，因为接口文档中的响应结果不止list,
    还有其它三个：
    {
	"success": true,
	"state": 0,
	"message": "响应成功",
	"content": {课程数据}
    }
    */
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;
    }


    /*
        课程图片上传
        响应示例:
        {
            "success": true,
            "state": 200,
            "message": "响应成功",
            "content": {
                    "fileName": "1597112871741.JPG",
                    "filePath": "http://localhost:8080/upload/1597112871741.JPG"
              }
         }
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {


        //1.判断接收到的上传文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }

        //2.获取项目部署路径

        // D:\apache-tomcat-8.5.56\webapps\ssm_web\
        //D:\Software\Java\apache-tomcat-8.5.55-windows-x64\apache-tomcat-8.5.55
        // String getRealPath(String path) 返回包含给定虚拟路径的实际路径的字符串
        /*
        / 斜杠被服务器解析地址为:http://ip:port/工程名/ 映射到 IDEA 代码的 web 目录
        */
        String realPath = request.getServletContext().getRealPath("/");
        // D:\apache-tomcat-8.5.56\webapps
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));


        //3.获取原文件名
        //lagou.jpg
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名
        //12421321.jpg
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传

        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        // 如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录：" + filePath);
        }

        // 图片就进行了真正的上传
        file.transferTo(filePath);

        // 6. 将文件名和文件路径返回，进行响应
        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);

        map.put("filePath", "http://localhost:8080/upload/" + newFileName);
        //ResponseResult对象通过@ResponseBody把对象转换成JSON响应给前台
        //前台解析JSON中的map拿到地址值，进而回显图片
        //@ResponseBody通过@RestController在CourseController类上已经配置
        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

        return responseResult;

    }


    /*
        新增课程信息及讲师信息
        新增课程信息和修改课程信息要写在同一个方法中
        (点击编辑按钮和新增的页面几乎一样，除了编辑页面有回显信息)
     */
    //@RequestBody把前台发送的JSON串封装到courseVO对象实体中
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        if (courseVO.getId() == null) {
            //调用service
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        } else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }


    }

    /*
        根据ID查询具体的课程信息及关联的讲师信息
     */

    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id) {
        CourseVO courseVO = courseService.findCourseById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "根据ID查询课程信息成功", courseVO);
        return responseResult;

    }

    /*
        课程状态管理
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStauts(Integer id, Integer status) {

        //调用service,传递参数，完成课程状态的变更
        courseService.updateCourseStatus(id, status);

        // 响应数据
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);

        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态变更成功", map);

        return responseResult;

    }


}
