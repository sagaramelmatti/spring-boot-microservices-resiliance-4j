package com.ledzer.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ledzer.master.entity.Business;

public interface BusinessRepository extends JpaRepository<Business,Long> {

}
