package com.item.brand.domain;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BrandRequest{
    private final Long id;
    private final String name;
    private final Long viewRank;
    private final LocalDateTime updatedDt;

    @Builder
    public BrandRequest(Long id, String name, Long viewRank, LocalDateTime updatedDt) {
        this.id =  id;
        this.name = name;
        this.viewRank = viewRank;
        this.updatedDt = updatedDt;
    }
}
