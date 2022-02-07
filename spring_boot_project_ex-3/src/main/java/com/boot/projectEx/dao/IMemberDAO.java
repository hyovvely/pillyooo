package com.boot.projectEx.dao;

import java.util.HashMap;

import com.boot.projectEx.model.MemberVO;

public interface IMemberDAO {
	MemberVO loginCheck(HashMap<String, Object> map);
	String memIdCheck(String memId);
}
