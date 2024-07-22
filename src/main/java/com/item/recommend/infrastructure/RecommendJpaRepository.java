package com.item.recommend.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecommendJpaRepository extends JpaRepository<RecommendEntity, Long> {
    @Query(value = "SELECT\n" +
                        "(SELECT name FROM BRANDS  WHERE id = t.brand_id) AS brand_name\n" +
                        ", (SELECT name FROM CATEGORIES  WHERE id = t.category_id) AS category_name\n" +
                        ", t.price\n" +
                        ", SUM(price) OVER() AS total_price\n" +
                    "FROM (\n" +
                        "SELECT brand_id\n" +
                            ", category_id\n" +
                            ", price\n" +
                            ", ROW_NUMBER() OVER(PARTITION BY category_id ORDER BY price,view_rank) AS rn\n" +
                        "FROM PRODUCTS ) t\n" +
                    "WHERE rn=1\n" +
                    "ORDER BY category_id ASC", nativeQuery = true)
    Optional<List<RecommendResponsesEntity>> getLowPriceGroupByCategory();

    @Query(value = "SELECT\n" +
                        "(SELECT name FROM brands a WHERE a.id = z.brand_id) AS brand_name" +
                        ",(SELECT name FROM categories a WHERE a.id = z.category_id) AS category_name" +
                        ", price,total_price\n" +
                    "FROM (\n" +
                        "SELECT\n" +
                            "brand_id, category_id ,price,total_price\n" +
                            ", DENSE_RANK() OVER (ORDER BY total_price ASC) AS last_rn\n" +
                        "FROM (\n" +
                            "SELECT\n" +
                                "brand_id, category_id ,price\n" +
                                ", SUM(price)OVER(PARTITION BY brand_id) AS total_price\n" +
                            "FROM (\n" +
                                "SELECT\n" +
                                    "brand_id, category_id ,price\n" +
                                    ", ROW_NUMBER() OVER(PARTITION BY  brand_id, category_id ORDER BY brand_id, category_id, price, view_rank) AS rn\n" +
                                "FROM PRODUCTS\n" +
                            ") t WHERE 1=1\n" +
                                "AND t.rn =1\n" +
                                "ORDER BY total_price\n" +
                        ")\n" +
                    ") z WHERE last_rn = 1", nativeQuery = true)
    Optional<List<RecommendResponsesEntity>> getLowPriceAllCategoryByBrand();

    @Query(value =
            "SELECT " +
                "'MAX' AS price_type" +
                ", (SELECT name FROM BRANDS a WHERE a.id = brand_id) AS brand_name" +
                ", price \n" +
                ", view_rank \n" +
                ", rownum \n" +
            "FROM PRODUCTS " +
            "WHERE (category_id, price, view_rank) IN ( " +
                "SELECT category_id, MAX(price) AS max_price, MIN(view_rank) \n" +
                "FROM PRODUCTS\n" +
                "WHERE category_id = ?1 " +
                "GROUP BY category_id) " +
            "AND rownum =1 " +
        "UNION\n" +
            "SELECT " +
                "'MIN' AS price_type\n" +
                ", (SELECT name FROM BRANDS a WHERE a.id = brand_id) AS brand_name\n" +
                ", price\n" +
                ", view_rank \n" +
                ", rownum \n" +
            "FROM PRODUCTS\n" +
            "WHERE (category_id, price, view_rank) IN (\n" +
                "SELECT category_id, MIN(price) AS min_price , MIN(view_rank) \n" +
                "WHERE category_id = ?1\n" +
                "GROUP BY category_id)\n" +
            "AND rownum =1 " , nativeQuery = true)
    Optional<List<RecommendResponsesEntity>> getHighestAndLowestPriceByCategory(Long id);
}
