package com.example.leave.db;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.leave.LeaveDTO;

/**
 * 模拟数据库操作
 * ignore the difference between dto and do
 */
@Repository
public class LeaveDB {

    private final Collection<LeaveDTO> data = new LinkedList<>();

    public LeaveDTO insert(LeaveDTO dto) {
        dto.setSeqNo(UUID.randomUUID().toString());
        data.add(dto);
        return dto;
    }

    public LeaveDTO query(String seqNo) {
        if (Objects.isNull(seqNo)) {
            throw new IllegalArgumentException("seqNo must cannot be null");
        }
        List<LeaveDTO> dtos = data.stream()
                .filter(dto -> Objects.equals(seqNo, dto.getSeqNo()))
                .collect(Collectors.toList());
        return dtos.get(0);
    }

}
