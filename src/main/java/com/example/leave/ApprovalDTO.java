package com.example.leave;

import java.io.Serializable;

import lombok.Data;

@Data
public class ApprovalDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请假序号，唯一标识
     */
    private String seqNo;

    /**
     * 审批人
     */
    private String approver;

    /**
     * 审批状态
     * 1-通过
     * 0-拒绝
     */
    private Integer status;

    /**
     * 审批人
     */
    private String remark;
}
