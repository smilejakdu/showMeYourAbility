package com.example.showmeyourability.Field.infrastructure.repository;

import com.example.showmeyourability.Field.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

}
