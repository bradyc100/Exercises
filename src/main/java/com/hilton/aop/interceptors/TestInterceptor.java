package com.hilton.aop.interceptors;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.ThrowsAdvice;

@Aspect
public class TestInterceptor implements ThrowsAdvice{
	
	//This breaks on arrays of primitives... Must always specify wrapper class in method signatures
	// for primitive types otherwise Java has metadata to use for reflection.
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
		String argumentsAtRuntime = "";
		for(int i = 0; i < args.length; i++) {
			Object arg = args[i];
			if(i != 0) {
				//Must use reflection isArray function instead of instanceof because
				//instanceof will give a result of false whereas isArray will return true
				//regardless of what the array primitive type is
				if(arg.getClass().isArray()) {
					argumentsAtRuntime += ',' + Arrays.toString((Object[])arg);
				} else {
					argumentsAtRuntime += ',' + arg.toString();
				}
			} else {
				if(arg.getClass().isArray()) {
					argumentsAtRuntime += Arrays.toString((Object[])arg);
				} else {
					argumentsAtRuntime += arg.toString();
				}
			}
		}
		System.out.println("Arguments at time of exception: [" + argumentsAtRuntime + "]");
		throw ex;
	}
	
}
