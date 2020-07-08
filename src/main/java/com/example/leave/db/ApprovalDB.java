package com.example.leave.db;


import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.example.leave.ApprovalDTO;

/**
 * 模拟数据库操作
 * ignore the difference between dto and do
 */
@Repository
public class ApprovalDB {

    private final Collection<ApprovalDTO> data = new LinkedList<>();

    public void insert(ApprovalDTO dto) {
        data.add(dto);
    }

    public ApprovalDTO query(String seqNo, String approver) {
        return data.stream().filter(dto -> Objects.equals(seqNo, dto.getSeqNo()) && Objects.equals(approver, dto.getApprover()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("approval record not exist"));
    }

}
