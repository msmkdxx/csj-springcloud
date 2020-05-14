package com.csj.cn.controller;

import com.csj.cn.dto.Employee;
import com.csj.cn.exception.ExcelException;
import com.csj.cn.feign.EmployeeFeign;
import com.csj.cn.utils.ExcelUtils;
import com.csj.cn.utils.ReturnResult;
import com.csj.cn.utils.ReturnResultUtils;
import com.csj.cn.vo.EmployeeVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/5/1217:27
 */
@RestController
@RequestMapping(value = "/employeeFeign")
public class EmployeeController {
    @Autowired
    private EmployeeFeign employeeFeign;

    @GetMapping(value = "/selectEmployeeList")
    public ReturnResult<List<Employee>> selectEmployeeList(@RequestParam(value = "searchStr", required = false) String searchStr) {
        List<Employee> employeeList = employeeFeign.selectEmployeeList(searchStr);
        if (CollectionUtils.isEmpty(employeeList)) return ReturnResultUtils.returnFail(111, "查无员工");

        return ReturnResultUtils.returnSucess(employeeList);
    }

    @GetMapping(value = "/delEmployees")
    public ReturnResult delEmployees(@RequestParam(value = "id") Integer... id) {
        if (employeeFeign.delEmployees(id)) return ReturnResultUtils.returnSucess();

        return ReturnResultUtils.returnFail(113, "删除员工失败");
    }

    @PostMapping(value = "/uploadExcel")
    public ReturnResult uploadExcel(@RequestParam(value = "file") MultipartFile excel) {
        InputStream inputStream = null;
        try {
            //判断文件后缀是否以.xls 或者 .xlsx 结尾
            if (ExcelUtils.determineExcelIsFormatted(excel)) throw new ExcelException("文件格式有误");

            //获取文件输入流
            inputStream = excel.getInputStream();
            //获得User实体类对象集合
            List<Employee> employees = (List<Employee>) ExcelUtils.readExcel(inputStream, EmployeeVo.class, 1, 1);
            if (ObjectUtils.isEmpty(employees)) return ReturnResultUtils.returnFail(121, "文件内容为空");
            //批量插入到数据库表中
            employeeFeign.insertEmployees(employees);
            return ReturnResultUtils.returnSucess();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭字节输入流，释放资源
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ReturnResultUtils.returnFail(122, "上传Excel失败！！！");
    }

}
