package com.kh.projectAuth.department;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DepartmentRepository {

    private final EntityManager em;

    public DepartmentEntity findDepartmentEntityByNo(Long departmentNo) {
        return em.find(DepartmentEntity.class, departmentNo);
    }
}
