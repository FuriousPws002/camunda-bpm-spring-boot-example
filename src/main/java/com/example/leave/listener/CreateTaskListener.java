package com.example.leave.listener;


import static com.example.leave.FlowConstants.NODE_GM;
import static com.example.leave.FlowConstants.NODE_HR;

import java.util.Map;
import java.util.Objects;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import com.example.leave.LeaveDTO;
import com.example.leave.db.UserDB;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class CreateTaskListener implements TaskListener {

    private final UserDB userDB;
    private final ObjectMapper objectMapper;

    @Override
    public void notify(DelegateTask delegateTask) {
        String taskDefinitionKey = delegateTask.getTaskDefinitionKey();
        Map<String, Object> variables = delegateTask.getExecution().getVariables();
        String assignee;
        if (Objects.equals(taskDefinitionKey, NODE_HR)) {
            assignee = userDB.getHr();
        } else if (Objects.equals(taskDefinitionKey, NODE_GM)) {
            assignee = userDB.getGm();
        } else {
            assignee = userDB.getDm(Objects.toString(variables.get("department")));
        }
        delegateTask.setAssignee(assignee);
        LeaveDTO leaveDTO = objectMapper.convertValue(variables, LeaveDTO.class);
        userDB.saveRelLeaveHandler(assignee, leaveDTO);
    }

//    private String assignment() {
//
//    }
}
