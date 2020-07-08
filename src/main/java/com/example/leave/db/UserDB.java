package com.example.leave.db;


import static com.example.leave.FlowConstants.USER_DM;
import static com.example.leave.FlowConstants.USER_GM;
import static com.example.leave.FlowConstants.USER_HR;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.leave.LeaveDTO;
import com.example.leave.RelLeaveHandlerDTO;

/**
 *
 */
@Repository
public class UserDB {

    private final Collection<RelLeaveHandlerDTO> data = new LinkedList<>();

    public String getHr() {
        return USER_HR;
    }

    public String getDm(String department) {
        return department + USER_DM;
    }

    public String getGm() {
        return USER_GM;
    }

    public void saveRelLeaveHandler(String handler, LeaveDTO leaveDTO) {
        RelLeaveHandlerDTO dto = new RelLeaveHandlerDTO();
        dto.setHandler(handler);
        dto.setLeaveDTO(leaveDTO);
        data.add(dto);
    }

    public void delRelLeaveHandler(String handler, String seqNo) {
        data.removeAll(
                data.stream().filter(d -> Objects.equals(d.getHandler(), handler) && Objects.equals(d.getLeaveDTO().getSeqNo(), seqNo))
                        .collect(Collectors.toList())
        );

    }


    public List<LeaveDTO> getUserTasks(String user) {
        return data.stream().filter(rel -> Objects.equals(rel.getHandler(), user))
                .map(RelLeaveHandlerDTO::getLeaveDTO)
                .collect(Collectors.toList());
    }
}
