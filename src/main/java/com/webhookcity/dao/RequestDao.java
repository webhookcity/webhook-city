package com.webhookcity.dao;

import com.webhookcity.model.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestDao extends CrudRepository<Request, Long> {

    List<Request> findByOrderByIdDesc();

}
