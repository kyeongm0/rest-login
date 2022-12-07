package com.lcomputerstudy.example.mapper;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;
import com.lcomputerstudy.example.domain.User;
import com.lcomputerstudy.example.domain.UserInfo;
import com.lcomputerstudy.example.request.JoinRequest;

@Mapper
public interface UserMapper {
	//유저읽기
		public User readUser(String username);
	//유저읽기 새로고침
		public UserInfo readUser_refresh(String username);
	//유저권한읽기 새로고침
		public List<GrantedAuthority>  readAuthorities_refresh(String username);
	
	 //유저생성
		public void createUser(User user);
//	 // 유저목록 불러오기
//		public List<UserInfo> readUserList();
	//
		public List<UserInfo>read_user_list();
//	//유저목록 권한 불러오기
//		public List<UserInfo>  readAuthorities_all();
	 // 권한 읽기
		public List<GrantedAuthority> readAuthorities(String username);
	 // 권한 생성
		public void createAuthority(User user);
		
		public List<User> selectUserList(User user);
		
		public User selectUserDetail(String username);
		
		public void selectUserEdit(User user);
		
		public void deleteUser(String username);
		
		public void deleteAuth(String username);
		
		public int userCount();
		
		public void upgradeLevel(User user);
		
		public void downgradeLevel(User user);
		
		public void selectUserLevel(User user);
		
		public void selectUserAuth(User user);
		
		public void selectUserGrade(User user);
}
