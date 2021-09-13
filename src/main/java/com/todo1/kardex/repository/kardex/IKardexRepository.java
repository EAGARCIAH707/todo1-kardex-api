package com.todo1.kardex.repository.kardex;

import com.todo1.kardex.model.entities.KardexEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IKardexRepository extends JpaRepository<KardexEntity, Integer> {}
