package com.todo1.kardex.repository.in;

import com.todo1.kardex.model.entities.InEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInRepository extends JpaRepository<InEntity, Integer> {}
