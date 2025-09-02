package com.kh.projectAuth.role;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRepository {

    private final EntityManager em;

    public RoleEntity findRoleEntityByNo(Long roleNo) {
        return em.find(RoleEntity.class, roleNo);
    }

}
