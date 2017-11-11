package com.shiro.sys.security;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.shiro.sys.dao.MenuMapper;
import com.shiro.sys.dao.RoleMapper;
import com.shiro.sys.dao.UserMapper;
import com.shiro.sys.pojo.Role;
import com.shiro.sys.pojo.User;

@Transactional(readOnly=true)
public class MyRealm extends AuthorizingRealm{

	private final static Logger logger = LoggerFactory.getLogger(MyRealm.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private MenuMapper menuMapper;
	
	public MyRealm() {
//		super.setName("myRealm");
		// 设置加密方式为MD5
//		super.setCredentialsMatcher(new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME));
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("shiro调用doGetAuthorizationInfo()");
		Integer userId = (Integer)principals.fromRealm(getName()).iterator().next();
		Role role = roleMapper.selectByPrimaryKey(userId);
		if(role == null)
			return null;
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole(role.getName());
		List<String> permissionList = menuMapper.selectPermissionByRoleId(role.getId());
		info.addStringPermissions(permissionList);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		User user = userMapper.selectByUsername(upToken.getUsername());
		if(user == null)
			return null;
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}
	
}
