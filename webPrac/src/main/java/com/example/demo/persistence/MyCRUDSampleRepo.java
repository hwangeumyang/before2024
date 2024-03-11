package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.MyCRUDSampleEntity;

//Query 창고
@Repository
public interface MyCRUDSampleRepo extends JpaRepository<MyCRUDSampleEntity, Integer> {

}
