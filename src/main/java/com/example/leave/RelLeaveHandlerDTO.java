package com.example.leave;

import java.io.Serializable;

import lombok.Data;

@Data
public class RelLeaveHandlerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private LeaveDTO leaveDTO;
    private String handler;
}
