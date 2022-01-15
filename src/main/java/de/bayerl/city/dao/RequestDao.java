package de.bayerl.city.dao;

import de.bayerl.city.model.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestDao extends CrudRepository<Request, Long> {

    List<Request> findByOrderByIdDesc();

}
