package com.tnu;

import org.springframework.data.jpa.repository.JpaRepository;

//class name and primary key data type in JPARepository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
