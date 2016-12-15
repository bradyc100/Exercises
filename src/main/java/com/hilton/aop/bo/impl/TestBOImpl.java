package com.hilton.aop.bo.impl;

import com.hilton.aop.bo.TestBO;

public class TestBOImpl implements TestBO{

	public Double calculate(char operator, Double... operands) throws Exception {
		if(operator != '\\'
			&& operator != '*'
			&& operator != '+'
			&& operator != '-'
		) {
			throw new Exception("Invalid operator!");
		}
		
		double result = 0;
		
		return result;
	}
	
}
