package com.example.demo.repo;

import com.example.demo.modeles.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders,Long > {
}
