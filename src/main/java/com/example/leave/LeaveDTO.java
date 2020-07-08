package com.example.leave;

import java.io.Serializable;

import lombok.Data;

@Data
public class LeaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请假序号，唯一标识
     */
    private String seqNo;
    /**
     * 员工编号
     */
    private String no;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 员工所在部门
     */
    private String department;
    /**
     * 请假类型
     * 1-年休假，婚假
     * 2-事假
     */
    private Integer type;
    /**
     * 请假天数
     */
    private Integer day;
    /**
     * 请假原因
     */
    private String reason;
}
