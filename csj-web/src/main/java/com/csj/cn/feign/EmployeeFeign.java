package com.csj.cn.feign;

import com.csj.cn.dto.Employee;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "csj-service")
public interface EmployeeFeign {

    @PostMapping(value = "/employee/insertEmployees")
    boolean insertEmployees(List<Employee> employeeList);

    @GetMapping(value = "/employee/selectEmployeeList")
    List<Employee> selectEmployeeList(@RequestParam(value = "searchStr", required = false) String searchStr);

    @PostMapping(value = "/employee/insertEmployee")
    boolean insertEmployee(@RequestBody Employee employee);

    @GetMapping(value = "/employee/delEmployees")
    boolean delEmployees(@RequestParam(value = "id") Integer... id);
}
