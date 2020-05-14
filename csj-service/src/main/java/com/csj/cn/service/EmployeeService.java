package com.csj.cn.service;

import com.csj.cn.dto.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * 批量添加员工
     *
     * @param employeeList
     * @return
     */
    boolean insertEmployees(List<Employee> employeeList);

    /**
     * 查询员工列表
     *
     * @param searchStr
     * @return
     */
    List<Employee> selectEmployeeList(String searchStr);

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    boolean insertEmployee(Employee employee);

    /**
     * 批量删除员工
     *
     * @param id
     * @return
     */
    boolean delEmployees(Long... id);
}
