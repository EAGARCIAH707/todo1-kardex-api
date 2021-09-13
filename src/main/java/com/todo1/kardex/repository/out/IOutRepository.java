package com.todo1.kardex.repository.out;

import com.todo1.kardex.model.entities.OutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOutRepository extends JpaRepository<OutEntity, Integer> {}
