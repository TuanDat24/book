package com.example.sell_book.service.impl;

import com.example.sell_book.models.AccountDTO;
import com.example.sell_book.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        AccountDTO accountDTO = accountService.getByUsernameAndActive(username, true);

        if(accountDTO == null)
            throw new UsernameNotFoundException("user nof found");

        Set<GrantedAuthority> authorities = new HashSet<>();

        for (String role : accountDTO.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
            System.out.print(authorities);
        }

        //User user = new User(accountDTO.getUsername(), accountDTO.getPassword(), authorities);

        //return user;
        //return new org.springframework.security.core.userdetails.User(accountDTO.getUsername(), accountDTO.getPassword(), mapRolesToAuthorities(accountDTO.getRoles()));
        return new org.springframework.security.core.userdetails.User(accountDTO.getUsername(), accountDTO.getPassword(), authorities);

    }
	/*private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}*/
}
