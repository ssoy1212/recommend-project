package com.item.brand.service;

import com.item.brand.controller.port.BrandService;
import com.item.brand.domain.Brand;
import com.item.brand.domain.BrandRequest;
import com.item.brand.service.port.BrandRepository;
import com.item.common.exception.BaseException;
import com.item.common.exception.ErrorCode;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private static final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);
    private final BrandRepository brandRepository;

    public Brand getById(Long id) {
        return brandRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Brand> findByNameLike(String name) {
        if(name == null || name.isEmpty()) {
            throw new BaseException(ErrorCode.REQUIRED_BRAND_NAME);
        }
        return brandRepository.findByNameLike(name);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Brand create(BrandRequest request) {
        this.validateCreateBrand(request);
        Brand newBrand = Brand.from(request);
        return brandRepository.save(newBrand);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Brand update(BrandRequest request) {
        this.validateUpdateBrand(request);
        Brand brand = this.getById(request.getId()).update(request);
        return brandRepository.save(brand);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void delete(BrandRequest request) {
        brandRepository.deleteById(this.validateDeleteBrand(request));
    }

    @Transactional(rollbackFor = {Exception.class})
    protected void validateCreateBrand(BrandRequest request) {
        try {
            if(request.getName() == null || request.getName().isEmpty()) {
                throw new BaseException(ErrorCode.REQUIRED_BRAND_NAME);
            }
            if(request.getViewRank() == null || request.getViewRank() <= 0) {
                throw new BaseException(ErrorCode.REQUIRED_BRAND_VIEWRANK);
            }

            Brand brand = brandRepository.findByNameIgnoreCase(request.getName());

            if(brand != null && brand.getId() > 0 ){
                throw new BaseException(ErrorCode.DUPLICATE_BRAND_NAME);
            }
        }catch (BaseException e) {
            throw new BaseException(e.getErrorCode());
        }catch (Exception e) {
            throw new BaseException(e.getMessage(), ErrorCode.FAIL_CREATE_BRAND);
        }
    }
    @Transactional(rollbackFor = {Exception.class})
    protected void validateUpdateBrand(BrandRequest request) {
        try {
            if(request.getId() == null || request.getId() <= 0 ) {
                throw new BaseException(ErrorCode.REQUIRED_BRAND_ID);
            }

            if(request.getName() == null || request.getName().isEmpty() ) {
                throw new BaseException(ErrorCode.REQUIRED_BRAND_NAME);
            }
            if(request.getViewRank() == null || request.getViewRank() <= 0 ) {
                throw new BaseException(ErrorCode.REQUIRED_BRAND_VIEWRANK);
            }

            Brand brand = this.getById(request.getId());
            if( brand == null || brand.getId() <= 0 ) {
                throw new BaseException(ErrorCode.BRAND_NOT_FOUND);
            }

            //브랜드아이디는 다른데 브랜드명이 같을 경우
            Brand otherNameBrand = brandRepository.findByNameIgnoreCase(request.getName());
            if( otherNameBrand != null && otherNameBrand.getId() > 0
                    && otherNameBrand.getName().equals(request.getName())
                    && !otherNameBrand.getId().equals(request.getId())) {
                throw new BaseException(ErrorCode.DUPLICATE_BRAND_NAME);
            }
        }catch (BaseException e) {
            throw new BaseException(e.getErrorCode());
        }catch (Exception e) {
            throw new BaseException(e.getMessage(), ErrorCode.FAIL_MODIFY_BRAND);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    protected Brand validateDeleteBrand(BrandRequest request) {
        try {
            Brand brand = brandRepository.findById(request.getId());
            if(brand == null && brand.getName().isEmpty()) {
                throw new BaseException(ErrorCode.BRAND_NOT_FOUND);
            }
            return Brand.builder()
                    .id(brand.getId())
                    .name(brand.getName())
                    .build();
        }catch (BaseException e) {
            throw new BaseException(e.getErrorCode());
        }catch (EmptyResultDataAccessException e) {
            throw new BaseException(ErrorCode.BRAND_NOT_FOUND);
        }catch (Exception e) {
            throw new BaseException(e.getMessage(), ErrorCode.FAIL_DELETE_BRAND);
        }
    }
}
