package com.marco.myhotelbackend.specifications;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.marco.myhotelbackend.models.Booking;

public class BookingSpecification implements Specification<Booking> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SearchCriteria criteria;

	public BookingSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Booking> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		switch (criteria.getOperation()) {

		case "between":
			return criteriaBuilder.or(
					criteriaBuilder.between(root.<LocalDate>get(criteria.getKey().split(",")[0]),
							fromStringToLocalDate(criteria.getValue().toString().split(",")[0]),
							fromStringToLocalDate(criteria.getValue().toString().split(",")[1])),
					criteriaBuilder.between(root.<LocalDate>get(criteria.getKey().split(",")[1]),
							fromStringToLocalDate(criteria.getValue().toString().split(",")[0]),
							fromStringToLocalDate(criteria.getValue().toString().split(",")[1])));
		case "in":

			List<?> roomIDs = convertObjectToList(criteria.getValue());
			Expression<Integer> parentExpression = root.<Integer>get(criteria.getKey());
			Predicate parentPredicate = parentExpression.in(roomIDs);

			return criteriaBuilder.and(parentPredicate);

		case ">":

			if (criteria.getDate() != null)
				return criteriaBuilder.greaterThanOrEqualTo(root.<LocalDate>get(criteria.getKey()), criteria.getDate());
			else
				return criteriaBuilder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()),
						criteria.getValue().toString());

		case "<":

			if (criteria.getDate() != null)
				return criteriaBuilder.lessThanOrEqualTo(root.<LocalDate>get(criteria.getKey()), criteria.getDate());
			else
				return criteriaBuilder.lessThanOrEqualTo(root.<String>get(criteria.getKey()),
						criteria.getValue().toString());

		case ":":

			if (criteria.getDate() != null)
				return criteriaBuilder.equal(root.<LocalDate>get(criteria.getKey()), criteria.getDate());
			else {
				if (root.get(criteria.getKey()).getJavaType() == String.class) {
					return criteriaBuilder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
				} else {
					return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
				}
			}

		}
		
		return null;

	}

	public SearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	private LocalDate fromStringToLocalDate(String date) {

		return LocalDate.of(Integer.parseInt(date.toString().split("/")[2]),
				Integer.parseInt(date.toString().split("/")[1]), Integer.parseInt(date.toString().split("/")[0]));

	}

	public static List<?> convertObjectToList(Object obj) {
		List<?> list = new ArrayList<>();
		if (obj.getClass().isArray()) {
			list = Arrays.asList((Object[]) obj);
		} else if (obj instanceof Collection) {
			list = new ArrayList<>((Collection<?>) obj);
		}
		return list;
	}

}
