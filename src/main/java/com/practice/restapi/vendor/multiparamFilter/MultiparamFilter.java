package com.practice.restapi.vendor.multiparamFilter;

import com.practice.restapi.vendor.Vendor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MultiparamFilter {
    public static Specification<Vendor> getSpecification(String search){
        return new Specification<Vendor>() {
            @Override
            public @Nullable Predicate toPredicate(Root<Vendor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if(search==null || search.isEmpty()){
                    criteriaBuilder.conjunction();
                };

                List<Predicate> list = new ArrayList<>();

                list.add(criteriaBuilder.like(root.get("vendorName"), search));
                list.add(criteriaBuilder.like(root.get("vendorMobileNumber"), search));

                return criteriaBuilder.and(list.toArray(new Predicate[0]));
            };
        };
    }
}
