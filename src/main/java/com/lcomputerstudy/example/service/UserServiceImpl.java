package com.lcomputerstudy.example.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.lcomputerstudy.example.domain.User;
import com.lcomputerstudy.example.domain.UserInfo;
import com.lcomputerstudy.example.mapper.UserMapper;
import com.lcomputerstudy.example.request.JoinRequest;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.readUser(username);
		user.setAuthorities(getAuthorities(username));	
		return user;
	}


	@Override
	public User readUser(String username) {
		return userMapper.readUser(username);
	}


	@Override
	public Collection<GrantedAuthority> getAuthorities(String username) {
		List<GrantedAuthority> authorities = userMapper.readAuthorities(username);
		return authorities;
	}

	 @Override
	   public void createUser(User user) {
	      userMapper.createUser(user);
	   }

	@Override
	public void createAuthority(User user) {
		userMapper.createAuthority(user);
	}
	
	@Override
	public List<UserInfo>read_user_list() {
		return userMapper.read_user_list();
	}
	
//	@Override
//	public List<UserInfo> readUserList() {
//		return userMapper.readUserList();
//	}
//	//유저목록 권한 불러오기
//		public List<UserInfo>  readAuthorities_all() {
//			return userMapper.readAuthorities_all();
//		}
	
	//유저읽기 새로고침
		public UserInfo readUser_refresh(String username) {
			return userMapper.readUser_refresh(username);
		}
	//유저권한읽기 새로고침
		public List<GrantedAuthority> readAuthorities_refresh(String username) {
			return userMapper.readAuthorities_refresh(username);
		}
	
	@Override
	public List<User> selectUserList(User user) {
		return userMapper.selectUserList(user);
	}
	
	@Override
	public User selectUserDetail(String username) {
		return userMapper.selectUserDetail(username);
	}
	
	@Override
	public void selectUserEdit(User user) {
		userMapper.selectUserEdit(user);
	}
	
	@Override
	public void deleteUser(String username) {
		userMapper.deleteAuth(username);
		userMapper.deleteUser(username);
	}
	
	@Override
	public int userCount() {
		return userMapper.userCount();
	}
	
	@Override
	public void selectUserLevel(User user) {
		userMapper.selectUserLevel(user);
		userMapper.selectUserGrade(user);
		
		if(user.getAuthorities().size() == 1) {
			userMapper.upgradeLevel(user);
		}
		else {
			userMapper.downgradeLevel(user);
		}
	}
}
