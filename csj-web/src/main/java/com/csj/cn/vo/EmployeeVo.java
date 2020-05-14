package com.csj.cn.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class EmployeeVo extends BaseRowModel implements Serializable {
    private static final long serialVersionUID = 2753134683927931738L;

    @ApiModelProperty(value = "员工id")
    private Integer id;

    @ExcelProperty(value = "员工姓名",index = 0)
    @ApiModelProperty(value = "员工姓名")
    private String name;

    @ExcelProperty(value = "手机号",index = 1)
    @ApiModelProperty(value = "手机号")
    private String phone;

    @ExcelProperty(value = "年龄",index = 2)
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ExcelProperty(value = "性别",index = 3)
    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ExcelProperty(value = "学历",index = 4)
    @ApiModelProperty(value = "学历")
    private String education;

    @ExcelProperty(value = "所属部门",index = 5)
    @ApiModelProperty(value = "所属部门")
    private Integer deptId;

    @ExcelProperty(value = "在职状态",index = 6)
    @ApiModelProperty(value = "所属部门")
    private Integer delFlag;

    @ExcelProperty(value = "入职日期",index = 7)
    @ApiModelProperty(value = "入职日期")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date entryDate;
}
