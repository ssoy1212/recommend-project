package com.item.brand.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Brand {
    private final Long id;
    private final String name;
    private final Long viewRank;
    private final LocalDateTime createdDt;
    private final LocalDateTime updatedDt;

    @Builder
    public Brand(Long id, String name, Long viewRank, LocalDateTime createdDt, LocalDateTime updatedDt) {
        this.id = id;
        this.name = name;
        this.viewRank = viewRank;
        this.createdDt = createdDt;
        this.updatedDt = updatedDt;
    }
    public static Brand from(BrandRequest request) {
        return Brand.builder()
                .name(request.getName())
                .viewRank(request.getViewRank())
                .build();
    }

    public Brand update(BrandRequest request) {
        return Brand.builder()
                .id(id)
                .name(request.getName())
                .viewRank(request.getViewRank())
                .updatedDt(updatedDt)
                .createdDt(createdDt)
                .build();
    }
}
