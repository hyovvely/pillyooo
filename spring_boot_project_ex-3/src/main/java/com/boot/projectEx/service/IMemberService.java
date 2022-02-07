package com.boot.projectEx.service;

import java.util.HashMap;

import com.boot.projectEx.model.MemberVO;

public interface IMemberService {
	MemberVO loginCheck(HashMap<String, Object> map);
	String memIdCheck(String memId);
}
