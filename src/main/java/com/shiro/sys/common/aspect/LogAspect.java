package com.shiro.sys.common.aspect;

import java.lang.reflect.Method;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.shiro.sys.common.annotation.Log;
import com.shiro.sys.pojo.User;

@Aspect
@Component
public class LogAspect {

	@Pointcut("@annotation(com.shiro.sys.common.annotation.Log)")
	public void cut(){
		
	}
	
//	@Around("cut()")
	@Around("@annotation(com.shiro.sys.common.annotation.Log)")
	public Object time(ProceedingJoinPoint point) throws Throwable{
		System.out.println("进入LogAspect");
		long begin = System.currentTimeMillis();
		Object result = point.proceed();
		System.out.println(System.currentTimeMillis() - begin + "ms");
		showInfo(point);
		return result;
	}
	
	private void showInfo(ProceedingJoinPoint point){
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		
		Log log = method.getAnnotation(Log.class);
		System.out.println("注解信息: " + log.value());
		
		String className = point.getTarget().getClass().getName();
		String methodName = signature.getName();
		System.out.println("请求方法: " + className + "." + methodName + "()");
		
		Object[] args = point.getArgs();
		if(args.length > 0){
			System.out.print("参数: ");
			for (Object arg : args) {
				System.out.print(arg.toString() + "  ");
			}
			System.out.println();
		}
		
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		if(user != null)
			System.out.println("用户名: " + user.getUsername());
	}
}
