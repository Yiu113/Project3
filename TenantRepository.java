package com.example.project2;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TenantRepository extends CrudRepository<Tenant, Long> {

    List<Tenant> findByName(String name);

    Tenant findById(long id);
}
