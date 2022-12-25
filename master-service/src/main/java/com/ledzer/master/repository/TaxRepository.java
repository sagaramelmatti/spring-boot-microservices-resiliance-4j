package com.ledzer.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ledzer.master.entity.Tax;

public interface TaxRepository extends JpaRepository<Tax,Long> {

}
