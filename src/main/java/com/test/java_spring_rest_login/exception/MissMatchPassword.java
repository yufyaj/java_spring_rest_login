package com.test.java_spring_rest_login.exception;

public class MissMatchPassword extends Exception {
    //warningを回避するための宣言
	private static final long serialVersionUID = 1L; 
    
    public MissMatchPassword(){
        super("パスワードが不一致です");
    }
}
