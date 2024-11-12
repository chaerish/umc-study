package com.umc.study.global.auth.domain;

import com.example.umc7th.member.entity.Member;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails {

    private final Member member;
    private final String role = "ROLE_USER";

    @Override
    // 권한을 가져오는 메소드
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add(role);
        return roles.stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    // username을 가져오는 메소드
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    // 비밀번호를 가져오는 메소드
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    // 사용 가능한지 여부
    public boolean isEnabled() {
        return true;
    }

    @Override
    // 계정이 만료되지 않았는지 여부
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    // 계정이 잠겼는지에 대한 여부
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    // 비밀번호가 만료되었는지 여부
    public boolean isCredentialsNonExpired() {
        return true;
    }
}