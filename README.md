## 1. 개발 환경

- Java17, Spring-Boot 3, Gradle
- JPA, H2 Database
- InteliJ Http client, Spring Boot Test

---

## 2. 요구사항
1. 카테고리 별 최저가격 브랜드와 상품가격, 총액을 조회하는 API
2. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
3. 카테고리 이름으로 최저,최고 가격 브랜드와 상품 가격을 조회하는 API
4. 브랜드 및 상품을 추가/업데이트/삭제하는 API


> [참고사항] <br>
> - 요구사항 2,3,4 의 경우
> - 준비된 데이터 중 카테고리별 최저가격이 동일한 케이스를 처리하기 위해 노출순위(DB products.view_rank)컬럼 추가<br>


| 브랜드        | 카테고리      | 가격       | 노출순위 |
|------------|-----------|----------|------|
| BRAND_A    | Sneakers    | 9000     | 2    |
| BRAND_G    | Sneakers | 9000     | 1    |
> 위 케이스의 경우 가격이 동일하기때문에 노출순위가 높은 BRAND_G 노출


---
## 3. 구현

### 1. H2 DataBase

- console 접속
    - `http://localhost:8080/h2-console`
- Table Schema 및 Data 초기화
  - `data.sql`, `schema.sql`

---


### 2. 브랜드 생성/업데이트/삭제 API
- InteliJ Http client 테스트 작성
- 브랜드 리스트 조회 `GET http://localhost:8080/brands/find/{brand_name}`

```angular2html
[요청]
GET http://localhost:8080/brands/find/{brand_name}

[응답성공]
    [
      {"brand": {
                "id": brand_id,
                "name": brand_name,
                "viewRank": view_rank,
                "createdDt": createdDt,
                "updatedDt": updatedDt 
        }
      },
        ...
    ]
[응답실패]
  {
    "message": "해당 브랜드를 찾을 수 없습니다.",
    "code": 404
  }

[예외]
  {
    "message": "서버 에러가 발생했습니다.",
    "code": 500
  }

```

- 브랜드 단건 조회 `GET http://localhost:8080/brands/{brand_id}`


```angular2html
[요청]
GET http://localhost:8080/brands/{brand_id}

[응답성공]
    {
      "message": "브랜드 조회결과입니다.",
        "code": "B1004",
        "brand": {
                "id": brand_id,
                "name": brand_name,
                "viewRank": view_rank,
                "createdDt": createdDt,
                "updatedDt": updatedDt 
        }
    }

[응답실패]
- 브래드 조회, 유효성 검증 실패값 message
  {
    "message": "해당 브랜드를 찾을 수 없습니다.",
    "code": 404
  }

[예외]
  {
    "message": "서버 에러가 발생했습니다.",
    "code": 500
  }

```
- 브랜드 생성
```angular2html
[요청]
POST http://localhost:8080/brands
  {
    "name": brand_name
    ,"viewRank": view_rank
  }

[응답성공]
    {
      "message": "브랜드 생성이 완료되었습니다.",
        "code": "B1001",
        "brand": {
                "id": brand_id,
                "name": brand_name,
                "viewRank": view_rank,
                "createdDt": createdDt,
                "updatedDt": updatedDt 
        }
    }

[응답실패]
- 브랜드 생성, 유효성 검증 실패값 message
  {
    "message": "브랜드 등록이 실패했습니다.",
    "code": 404
  }

[예외]
  {
    "message": "서버 에러가 발생했습니다.",
    "code": 500
  }

```
- 브랜드 수정
```angular2html
[요청]
PUT http://localhost:8080/brands
  {
    "id": brand_id
    ,"name": brand_name
    ,"viewRank": view_rank
  }

[응답성공]
    {
      "message": "브랜드 변경이 완료되었습니다.",
        "code": "B1002",
        "brand": {
                "id": brand_id,
                "name": brand_name,
                "viewRank": view_rank,
                "createdDt": createdDt,
                "updatedDt": updatedDt 
        }
    }

[응답실패]
- 브랜드 수정, 유효성 검증 실패값 message
  {
    "message": "브랜드 변경이 실패했습니다.",
    "code": 404
  }

[예외]
  {
    "message": "서버 에러가 발생했습니다.",
    "code": 500
  }

```
- 브랜드 삭제
```angular2html
[요청]
DELETE http://localhost:8080/brands
  {
    "id": brand_id
  }

[응답성공]
  {
    "message": "브랜드 삭제가 완료되었습니다.",
    "code": "B1003"
  }

[응답실패]
- 브랜드 삭제, 유효성 검증 실패값 message
  {
    "message": "브랜드 삭제에 실패했습니다.",
    "code": 404
  }

[예외]
  {
    "message": "서버 에러가 발생했습니다.",
    "code": 500
  }

```
---


### 3. 상품 생성/업데이트/삭제 API
- InteliJ Http client 테스트 작성
- 상품 단건 조회 

```angular2html
[요청]
GET http://localhost:8080/products/find/{product_id}

[응답성공]
  {
    "message": "상품 조회결과입니다.",
    "code": "P1004",
    "product": {
          "id": product_id,
          "brandId": brand_id,
          "categoryId": category_id,
          "price": price,
          "viewRank": view_rank,
          "name": product_name,
          "createdDt": createdDt,
          "updatedDt": updatedDt
    }
  }

[응답실패]
- 상품 조회, 유효성 검증 실패값 message
  {
    "message": "해당 상품을 찾을 수 없습니다.",
    "code": 404
  }

[예외]
  {
    "message": "서버 에러가 발생했습니다.",
    "code": 500
  }

```

- 상품 생성

```angular2html
[요청]
POST http://localhost:8080/products
  {
    "brandId": brand_id,
    "categoryId": category_id,
    "price": price,
    "viewRank": view_rank,
    "name": product_name
  }

[응답성공]
  {
    "message": "상품 생성이 완료되었습니다.",
    "code": "P1001",
    "product": {
        "id": product_id,
        "brandId": brand_id,
        "categoryId": category_id,
        "price": price,
        "viewRank": view_rank,
        "name": product_name,
        "createdDt": createdDt,
        "updatedDt": updatedDt
    }
  }

[응답실패]
- 상품 생성, 유효성 검증 실패값 message
  {
    "message": "상품 등록이 실패했습니다.",
    "code": 404
  }

[예외]
  {
    "message": "서버 에러가 발생했습니다.",
    "code": 500
  }

```


- 상품 수정

```angular2html
[요청]
PUT http://localhost:8080/products
  {
    "id": product_id,
    "brandId": brand_id,
    "categoryId": category_id,
    "price": price,
    "viewRank": view_rank,
    "name": product_name
  }
[응답성공]
  {
    "message": "상품 변경이 완료되었습니다.",
    "code": "P1002",
    "product": {
            "id": product_id,
            "brandId": brand_id,
            "categoryId": category_id,
            "price": price,
            "viewRank": view_rank,
            "name": product_name,
            "createdDt": createdDt,
            "updatedDt": updatedDt
    }
  }

[응답실패]
- 상품 수정, 유효성 검증 실패값 message
  {
    "message": "상품 변경이 실패했습니다.",
    "code": 404
  }

[예외]
  {
    "message": "서버 에러가 발생했습니다.",
    "code": 500
  }

```

- 상품 삭제
```angular2html
[요청]
DELETE http://localhost:8080/products
  {
    "id": product_id
  }

[응답성공]
  {
    "message": "상품 삭제가 완료되었습니다.",
    "code": "P1003"
  }

[응답실패]
- 상품 삭제, 유효성 검증 실패값 message
  {
    "message": "상품 삭제에 실패했습니다.",
    "code": 404
  }

[예외]
  {
    "message": "서버 에러가 발생했습니다.",
    "code": 500
  }

```

----

### 4. 요구사항 2.1
#### 카테고리 별 최저가격 브랜드와 상품가격, 총액을 조회하는 API
- InteliJ Http client 테스트 작성

```angular2html
[요청]
GET http://localhost:8080/recommend/getLowPriceGroupByCategory

[응답성공]
  {
    "totalPrice": total_price,
    "items": [
      {
        "brandName": brand_name,
        "categoryName": category_name,
        "price": price
      },
      ...
    ]
  }

[응답실패]
  {
    "message": "추천 상품을 찾을 수 없습니다.",
    "code": 404
  }

[예외]
  {
    "message": "서버 에러가 발생했습니다.",
    "code": 500
  }

```


---

### 5. 요구사항 2.2
#### 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
- InteliJ Http client 테스트 작성

```angular2html
[요청]
GET http://localhost:8080/recommend/getLowPriceAllCategoryByBrand

[응답성공]
  {
    "totalPrice": total_price,
    "brandName": brand_name, 
    "items": [
        {
          "categoryName": category_name,
          "price": price
        },
        ...
    ]
  }

[응답실패]
  {
    "message": "추천 상품을 찾을 수 없습니다.",
    "code": 404
  }

[예외]
  {
    "message": "서버 에러가 발생했습니다.",
    "code": 500
  }

```

---

### 6. 요구사항 2.3
#### 카테고리 이름으로 최저,최고 가격 브랜드와 상품 가격을 조회하는 API
- InteliJ Http client 테스트 작성

```angular2html
[요청]
GET http://localhost:8080/recommend/getHighestAndLowestPriceByCategory

[응답성공]
  {
    "categoryName": category_name,
    "lowestPrice": [
      {
        "brandName": brand_name,
        "priceType": price_type,
        "price": price
      }
    ],
    "highestPrice": [
      {
        "brandName": brand_name,
        "priceType": price_type,
        "price": price
      }
    ]
  }

[응답실패]
  {
    "message": "추천 상품을 찾을 수 없습니다.",
    "code": 404
  }

[예외]
  {
    "message": "서버 에러가 발생했습니다.",
    "code": 500
  }

```
