package com.marco.myhotelbackend.specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.marco.myhotelbackend.models.Booking;

public class BookingSpecificationBuilder {
	
	private final List<SearchCriteria> parameters;
	
	public BookingSpecificationBuilder() {
		parameters = new ArrayList<SearchCriteria>();
	}
	
    public BookingSpecificationBuilder with(String key, String operation, Object value) {

        parameters.add(new SearchCriteria(key, operation, value));

        return this;
    }
	
	public Specification<Booking> build(){
		
        if (parameters.size() == 0) {
            return null;
        }

        List<BookingSpecification> specs = parameters.stream()
          .map(BookingSpecification::new)
          .collect(Collectors.toList());
        
        Specification<Booking> result = specs.get(0);
        
        for (int i = 1; i < specs.size(); i++) {
        	result = Specification.where(result).and(specs.get(i));
        }       
        
        return result;
	}

}
