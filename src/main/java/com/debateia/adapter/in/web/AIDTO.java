package com.debateia.adapter.in.web;

import com.debateia.adapter.out.persistence.AIEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AIDTO {
    private Integer id;
    private String trait;
    private String endpoint;
    private Boolean enabled;
    private Integer userId;

    public static AIDTO toDto(AIEntity entity) {
        AIDTO dto = new AIDTO();
        dto.setId(entity.getId());
        dto.setTrait(entity.getTrait());
        dto.setEndpoint(entity.getEndpoint());
        dto.setEnabled(entity.getEnabled());
        dto.setUserId(entity.getUser().getId());
        return dto;
    }
}
