package com.example.demo.repo;


import com.example.demo.modeles.Plan;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends  CrudRepository<Plan,Long> {
}

