package com.melon.music.service;

import java.util.List;

import com.melon.music.vo.MusicSearchVO;
import com.melon.music.vo.MusicVO;
import com.melon.user.vo.UserVO;

public interface MusicService {

	public boolean addNewMusic(MusicVO musicVO);
	
	public List<MusicVO> getAllMusic(MusicSearchVO musicSearchVO);
	
	// 이 회원이 이 노래를 들으려 한다. (UserVO를 해주면 Session에 있는 값도 바꿀 수 있고 DB에 있는 값도 바꿀 수 있다)
	public MusicVO getOneMusic(String musicId, UserVO userVO);
	
	public boolean deleteOneMusic(String musicId);
	
}
