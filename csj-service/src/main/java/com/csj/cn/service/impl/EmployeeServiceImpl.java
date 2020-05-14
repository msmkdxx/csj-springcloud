package com.csj.cn.service.impl;

import com.csj.cn.dto.Employee;
import com.csj.cn.dto.EmployeeExample;
import com.csj.cn.mapper.EmployeeMapper;
import com.csj.cn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/5/1216:01
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public boolean insertEmployees(List<Employee> employeeList) {
        return employeeMapper.insertEmployees(employeeList) == employeeList.size();
    }

    @Override
    public List<Employee> selectEmployeeList(String searchStr) {
        EmployeeExample employeeExample = new EmployeeExample();
        if (!StringUtils.isEmpty(searchStr)) {
            employeeExample.createCriteria().andNameLike('%' + searchStr + '%');
        }
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        if (!CollectionUtils.isEmpty(employeeList)) return employeeList;

        return null;
    }

    @Override
    public boolean insertEmployee(Employee employee) {
        return employeeMapper.insertSelective(employee) > 0;
    }

    @Override
    public boolean delEmployees(Long... id) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andIdIn(Arrays.asList(id));
        Employee employee = new Employee();
        employee.setDelFlag(1);
        return employeeMapper.updateByExampleSelective(employee, employeeExample) == id.length;
    }
}
