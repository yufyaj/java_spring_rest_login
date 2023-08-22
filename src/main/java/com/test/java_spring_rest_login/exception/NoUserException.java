package com.test.java_spring_rest_login.exception;

public class NoUserException extends Exception {
    //warningを回避するための宣言
	private static final long serialVersionUID = 1L; 
    
    public NoUserException(){
        super("ユーザーが見つかりませんでした");
    }
}
