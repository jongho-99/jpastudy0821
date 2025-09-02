package com.kh.projectAuth.member;

import com.kh.projectAuth.department.DepartmentEntity;
import com.kh.projectAuth.department.DepartmentRepository;
import com.kh.projectAuth.role.RoleEntity;
import com.kh.projectAuth.role.RoleRepository;
import com.kh.projectAuth.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        //1. repo에 가서 Entity 추출해오는넘
        MemberEntity res_entity = memberRepository.findUserById(name);

        //예외처리
        if(res_entity == null) throw new UsernameNotFoundException("User not found: " + name);

        MemberDto dto = MemberDto.from(res_entity);

        //2. 추출된 새끼로 MyUserDetails 객체 생성 후 반환
        return new MyUserDetails(dto);
    }


    public int join(MemberDto dto) {
        try{
            DepartmentEntity dEntity = departmentRepository.findDepartmentEntityByNo(dto.getDepartmentNo());
            RoleEntity rEntity = roleRepository.findRoleEntityByNo(dto.getRoleNo());

            MemberEntity entity = MemberEntity.from(dto, dEntity, rEntity);

            memberRepository.join(entity);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return 1;

    }

    //JWT 처리 방식 로그인은 MyUserDetailsService에서 처리
//    public MemberEntity login(MemberDto dto) {
//        DepartmentEntity dEntity = departmentRepository.findDepartmentEntityByNo(dto.getDepartmentNo());
//        RoleEntity rEntity = roleRepository.findRoleEntityByNo(dto.getRoleNo());
//
//        MemberEntity entity = MemberEntity.from(dto, dEntity, rEntity);
//
//        return memberRepository.login(entity);
//    }
}
