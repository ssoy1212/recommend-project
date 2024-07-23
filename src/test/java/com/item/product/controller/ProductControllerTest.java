package com.item.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.item.common.enums.MessageCode;
import com.item.common.exception.ErrorCode;
import com.item.product.domain.Product;
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
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void 상품조회_BY_ID() throws Exception {
        // given
        Product product = Product.builder()
                .id(1L)
                .viewRank(1L)
                .brandId(1L)
                .categoryId(1L)
                .price(11200L)
                .viewRank(1L)
                .name("A_Top")
                .build();
        // when
        final ResultActions result = mockMvc.perform(get("/products/find/{id}",1L)
                .accept(MediaType.APPLICATION_JSON));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(MessageCode.GET_PRODUCT.getCode()))
                .andExpect(jsonPath("$.product.id").value(product.getId()))
                .andExpect(jsonPath("$.product.name").value(product.getName()));
    }
    @Test
    void 존재하지않는_상품ID_입력_조회_NOT_FOUND_ERROR() throws Exception {
        // when
        final ResultActions result = mockMvc.perform(get("/products/find/{id}",99L)
                .accept(MediaType.APPLICATION_JSON));
        // then
        result.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(ErrorCode.PRODUCT_NOT_FOUND.getStatus()))
                .andExpect(jsonPath("$.message").value(ErrorCode.PRODUCT_NOT_FOUND.getMessage()));
    }
    @Test
    void 업데이트_성공_UPDATE_MESSAGE() throws Exception {
        //given
        Product product = Product.builder()
                .id(1L)
                .viewRank(1L)
                .brandId(1L)
                .categoryId(1L)
                .price(11200L)
                .viewRank(1L)
                .name("A_Top_TEST")
                .build();
        // when
        final ResultActions updateResult = mockMvc.perform(MockMvcRequestBuilders
                .put("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(product))
                .accept(MediaType.APPLICATION_JSON));
        // then
        updateResult
                .andDo(print())
                .andExpect(jsonPath("$.message").value(MessageCode.UPDATE_PRODUCT.getMessage()))
                .andExpect(jsonPath("$.code").value(MessageCode.UPDATE_PRODUCT.getCode()))
                .andExpect(jsonPath("$.product.name").value(product.getName()));
    }
    @Test
    void 업데이트시_존재하지않는_상품ID_입력_PRODUCT_NOT_FOUND() throws Exception {
        //given
        Product product = Product.builder()
                .id(99L)
                .viewRank(1L)
                .brandId(1L)
                .categoryId(1L)
                .price(11200L)
                .viewRank(1L)
                .name("A_Top_TEST")
                .build();
        // when
        final ResultActions updateResult = mockMvc.perform(MockMvcRequestBuilders
                .put("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(product))
                .accept(MediaType.APPLICATION_JSON));
        // then
        updateResult
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.PRODUCT_NOT_FOUND.getMessage()))
                .andExpect(jsonPath("$.code").value(ErrorCode.PRODUCT_NOT_FOUND.getStatus()));
    }
    @Test
    void 업데이트시_존재하지않는_카테고리_입력_CATEGORY_NOT_FOUND() throws Exception {
        //given
        Product product = Product.builder()
                .id(1L)
                .viewRank(1L)
                .brandId(1L)
                .categoryId(99L)
                .price(11200L)
                .viewRank(1L)
                .name("A_Top_TEST")
                .build();
        // when
        final ResultActions updateResult = mockMvc.perform(MockMvcRequestBuilders
                .put("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(product))
                .accept(MediaType.APPLICATION_JSON));
        // then
        updateResult
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.CATEGORY_NOT_FOUND.getMessage()))
                .andExpect(jsonPath("$.code").value(ErrorCode.CATEGORY_NOT_FOUND.getStatus()));
    }
    @Test
    void 업데이트시_이미_존재하는_상품명_요청_DUPLICATE_PRODUCT_NAME() throws Exception {
        //given
        Product product = Product.builder()
                .id(1L)
                .viewRank(1L)
                .brandId(1L)
                .categoryId(1L)
                .price(11200L)
                .viewRank(1L)
                .name("B_Top")
                .build();
        // when
        final ResultActions updateResult = mockMvc.perform(MockMvcRequestBuilders
                .put("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(product))
                .accept(MediaType.APPLICATION_JSON));
        // then
        updateResult
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.DUPLICATE_PRODUCT_NAME.getMessage()))
                .andExpect(jsonPath("$.code").value(ErrorCode.DUPLICATE_PRODUCT_NAME.getStatus()));
    }
    @Test
    void 상품_생성시_CREATE_MESSAGE() throws Exception {
        //given
        Product product = Product.builder()
                .viewRank(1L)
                .brandId(1L)
                .categoryId(1L)
                .price(11200L)
                .viewRank(1L)
                .name("A_Top_TEST")
                .build();
        // when
        final ResultActions updateResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(product))
                .accept(MediaType.APPLICATION_JSON));
        // then
        updateResult
                .andDo(print())
                .andExpect(jsonPath("$.message").value(MessageCode.CREATE_PRODUCT.getMessage()))
                .andExpect(jsonPath("$.code").value(MessageCode.CREATE_PRODUCT.getCode()));
    }

    @Test
    void 상품_생성시_없는_카테고리_입력_CATEGORY_NOT_FOUND() throws Exception {
        //given
        Product product = Product.builder()
                .viewRank(1L)
                .brandId(1L)
                .categoryId(99L)
                .price(11200L)
                .viewRank(1L)
                .name("A_Top_TEST")
                .build();
        // when
        final ResultActions updateResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(product))
                .accept(MediaType.APPLICATION_JSON));
        // then
        updateResult
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.CATEGORY_NOT_FOUND.getMessage()))
                .andExpect(jsonPath("$.code").value(ErrorCode.CATEGORY_NOT_FOUND.getStatus()));
    }

    @Test
    void 상품_생성시_중복이름_입력_DUPLICATE_PRODUCT_NAME() throws Exception {
        //given
        Product product = Product.builder()
                .viewRank(1L)
                .brandId(1L)
                .categoryId(1L)
                .price(11200L)
                .viewRank(1L)
                .name("A_Top")
                .build();
        // when
        final ResultActions updateResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(product))
                .accept(MediaType.APPLICATION_JSON));
        // then
        updateResult
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.DUPLICATE_PRODUCT_NAME.getMessage()))
                .andExpect(jsonPath("$.code").value(ErrorCode.DUPLICATE_PRODUCT_NAME.getStatus()));
    }

    @Test
    void 상품삭제시_요청ID가_없는_경우() throws Exception {

        //given
        Product product = Product.builder()
                .viewRank(1L)
                .brandId(1L)
                .categoryId(1L)
                .price(11200L)
                .viewRank(1L)
                .name("A_Top")
                .build();
        // when
        final ResultActions updateResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(product))
                .accept(MediaType.APPLICATION_JSON));
        // then
        updateResult
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.REQUIRED_PRODUCT_ID.getMessage()))
                .andExpect(jsonPath("$.code").value(ErrorCode.REQUIRED_PRODUCT_ID.getStatus()));
    }
    @Test
    void 삭제_할_상품이_없는_경우() throws Exception {

        //given
        Product product = Product.builder()
                .id(99L)
                .viewRank(1L)
                .brandId(1L)
                .categoryId(1L)
                .price(11200L)
                .viewRank(1L)
                .name("A_Top")
                .build();
        // when
        final ResultActions updateResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(product))
                .accept(MediaType.APPLICATION_JSON));
        // then
        updateResult
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ErrorCode.PRODUCT_NOT_FOUND.getMessage()))
                .andExpect(jsonPath("$.code").value(ErrorCode.PRODUCT_NOT_FOUND.getStatus()));
    }
    @Test
    void 상품삭제_완료시_DELETE_MESSAGE() throws Exception {

        //given
        Product product = Product.builder()
                .id(1L)
                .viewRank(1L)
                .brandId(1L)
                .categoryId(1L)
                .price(11200L)
                .viewRank(1L)
                .name("A_Top")
                .build();
        // when
        final ResultActions updateResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(product))
                .accept(MediaType.APPLICATION_JSON));
        // then
        updateResult
                .andDo(print())
                .andExpect(jsonPath("$.message").value(MessageCode.DELETE_PRODUCT.getMessage()))
                .andExpect(jsonPath("$.code").value(MessageCode.DELETE_PRODUCT.getCode()));
    }
}