package com.example.leave.listener;

import static com.example.leave.FlowConstants.KEY_PASS_DM;
import static com.example.leave.FlowConstants.KEY_PASS_GM;
import static com.example.leave.FlowConstants.KEY_PASS_HR;
import static com.example.leave.FlowConstants.NODE_DM;
import static com.example.leave.FlowConstants.NODE_GM;
import static com.example.leave.FlowConstants.NODE_HR;

import java.util.Map;
import java.util.Objects;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import com.example.leave.ApprovalDTO;
import com.example.leave.db.ApprovalDB;

import lombok.RequiredArgsConstructor;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class CompleteTaskListener implements TaskListener {

    private final ApprovalDB approvalDB;

    @Override
    public void notify(DelegateTask delegateTask) {
        String handler = delegateTask.getAssignee();
        Objects.requireNonNull(handler);
        //删除之前的审批记录，数据库的话，可以用状态来标识

        ApprovalDTO approvalDTO = approvalDB.query(delegateTask.getExecution().getBusinessKey(), handler);
        String taskDefinitionKey = delegateTask.getTaskDefinitionKey();
        Map<String, Object> variables = delegateTask.getExecution().getVariables();
        Integer status = approvalDTO.getStatus();
        if (Objects.equals(taskDefinitionKey, NODE_HR)) {
            variables.put(KEY_PASS_HR, status);
        } else if (Objects.equals(taskDefinitionKey, NODE_DM)) {
            variables.put(KEY_PASS_DM, status);
        } else if (Objects.equals(taskDefinitionKey, NODE_GM)) {
            variables.put(KEY_PASS_GM, status);
        }
        delegateTask.getExecution().setVariables(variables);
    }
}
