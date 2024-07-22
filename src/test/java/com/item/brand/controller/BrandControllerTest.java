package com.item.brand.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.item.brand.domain.Brand;
import com.item.common.enums.MessageCode;
import com.item.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 리스트_조회시_ID와_NAME을_가져온다() throws Exception {
        // given
        Brand brand = Brand.builder()
                .id(1L)
                .name("BRAND_A").build();
        // when
        final ResultActions result = mockMvc.perform(get("/brands/find/{name}","BRAND_A")
                .accept(MediaType.APPLICATION_JSON));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].brand.id").value(brand.getId()))
                .andExpect(jsonPath("$[0].brand.name").value(brand.getName()));
    }

    @Test
    void 조회_BY_ID() throws Exception {
        Brand brand = Brand.builder()
                .id(3L)
                .name("BRAND_C").build();
        // when
        final ResultActions result = mockMvc.perform(get("/brands/{id}",3)
                .accept(MediaType.APPLICATION_JSON));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MessageCode.GET_BRAND.getMessage()))
                .andExpect(jsonPath("$.code").value(MessageCode.GET_BRAND.getCode()))
                .andExpect(jsonPath("$.brand.id").value(brand.getId()))
                .andExpect(jsonPath("$.brand.name").value(brand.getName()));
    }
    @Test
    void 조회_BY_ID_결과가_없을_경우_NOT_FOUND_ERROR() throws Exception {
        // when
        final ResultActions result = mockMvc.perform(get("/brands/{id}",10)
                .accept(MediaType.APPLICATION_JSON));
        // then
        result
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.BRAND_NOT_FOUND.getMessage()));
    }

    @Test
    void 업데이트_완료시_UPDATE_MESSAGE() throws Exception {
        //given
        Brand brand = Brand.builder()
                .id(4L)
                .name("BRAND_D")
                .viewRank(2L)
                .build();

        // when
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .put("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(brand))
                .accept(MediaType.APPLICATION_JSON));
        // then
        result
                .andDo(print())
                .andExpect(jsonPath("$.message").value(MessageCode.UPDATE_BRAND.getMessage()));
    }

    @Test
    void 업데이트시_중복_브랜드명이_있을경우_DUPLICATE_ERROR() throws Exception {
        //given
        Brand brand = Brand.builder()
                .id(3L)
                .name("BRAND_D")
                .viewRank(2L)
                .build();
        // when
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .put("/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(brand))
                .accept(MediaType.APPLICATION_JSON));
        // then
        result
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.DUPLICATE_BRAND_NAME.getMessage()));
    }
    @Test
    void 업데이트시_VIEWRANK_없을_경우_REQUIRED_ERROR() throws Exception {
        //given
        Brand brand = Brand.builder()
                .id(4L)
                .name("BRAND_D")
                .build();
        // when
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .put("/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(brand))
                .accept(MediaType.APPLICATION_JSON));
        // then
        result
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.REQUIRED_BRAND_VIEWRANK.getMessage()));
    }

    @Test
    void 업데이트시_ID_없을_경우_REQUIRED_ERROR() throws Exception {
        //given
        Brand brand = Brand.builder()
                .name("BRAND_D")
                .viewRank(1L)
                .build();
        // when
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .put("/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(brand))
                .accept(MediaType.APPLICATION_JSON));
        // then
        result
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.REQUIRED_BRAND_ID.getMessage()));
    }

    @Test
    void 생성시_중복_브랜드명이_있을_경우_DUPLICATE_ERROR() throws Exception {
        //given
        Brand brand = Brand.builder()
                .name("BRAND_D")
                .viewRank(1L)
                .build();
        // when
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(brand))
                .accept(MediaType.APPLICATION_JSON));
        // then
        result
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.DUPLICATE_BRAND_NAME.getMessage()));
    }

    @Test
    void 생성시_필수값이_없을_경우_REQUIRED_ERROR() throws Exception {
        //given
        Brand brand = Brand.builder()
                .name("BRAND_D")
                .build();
        // when
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(brand))
                .accept(MediaType.APPLICATION_JSON));
        // then
        result
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.REQUIRED_BRAND_VIEWRANK.getMessage()));
    }

    @Test
    void 생성_완료시_CREATE_BRAND_MESSAGE() throws Exception {
        //given
        Brand brand = Brand.builder()
                .name("BRAND_Q")
                .viewRank(1L)
                .build();
        // when
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(brand))
                .accept(MediaType.APPLICATION_JSON));
        // then
        result
                .andDo(print())
                .andExpect(jsonPath("$.message").value(MessageCode.CREATE_BRAND.getMessage()));
    }

    @Test
    void 삭제시_브랜드가_없을_경우_NOT_FOUND_ERROR() throws Exception {
        //given
        Brand brand = Brand.builder()
                .id(11L)
                .build();
        // when
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(brand))
                .accept(MediaType.APPLICATION_JSON));
        // then
        result
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.BRAND_NOT_FOUND.getMessage()));
    }

    @Test
    void 삭제_완료시_DELETE_MESSAGE() throws Exception {
        //given
        Brand brand = Brand.builder()
                .id(9L)
                .name("BRAND_Q")
                .build();
        // when
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(brand))
                .accept(MediaType.APPLICATION_JSON));
        // then
        result
                .andDo(print())
                .andExpect(jsonPath("$.message").value(MessageCode.DELETE_BRAND.getMessage()));
    }
}