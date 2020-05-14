package com.csj.cn.feignapi;

import com.csj.cn.dto.Employee;
import com.csj.cn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/5/1216:00
 */
@RestController
@RequestMapping(value = "/employee")
public class EmployeeFeignApi {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/insertEmployees")
    public boolean insertEmployees(List<Employee> employeeList) {
        return employeeService.insertEmployees(employeeList);
    }

    @GetMapping(value = "/selectEmployeeList")
    public List<Employee> selectEmployeeList(@RequestParam(value = "searchStr", required = false) String searchStr) {
        return employeeService.selectEmployeeList(searchStr);
    }

    @PostMapping(value = "/insertEmployee")
    public boolean insertEmployee(@RequestBody Employee employee) {
        return employeeService.insertEmployee(employee);
    }

    @GetMapping(value = "/delEmployees")
    public boolean delEmployees(@RequestParam(value = "id") Long... id) {
        return employeeService.delEmployees(id);
    }

}
