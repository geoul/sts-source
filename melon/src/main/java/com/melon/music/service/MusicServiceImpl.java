package com.melon.music.service;

import java.util.List;

import com.melon.music.biz.MusicBiz;
import com.melon.music.biz.MusicBizImpl;
import com.melon.music.vo.MusicSearchVO;
import com.melon.music.vo.MusicVO;
import com.melon.user.biz.UserBiz;
import com.melon.user.biz.UserBizImpl;
import com.melon.user.vo.UserVO;

public class MusicServiceImpl implements MusicService {
	
	private MusicBiz musicBiz;
	// 새로운 업무 추가 (Service가 여러개의 Biz를 가질 수 있게 함으로써 여러개의 업무를 묶는다.(통합 업무) 이런 작업은 Service 에서만 할 수 있다.)
	private UserBiz userBiz;
	
	public MusicServiceImpl() {
		musicBiz = new MusicBizImpl();
		userBiz = new UserBizImpl();
	}

	@Override
	public boolean addNewMusic(MusicVO musicVO) {
		return musicBiz.addNewMusic(musicVO);
	}

	@Override
	public List<MusicVO> getAllMusic(MusicSearchVO musicSearchVO) {
		return musicBiz.getAllMusic(musicSearchVO);
	}

	// 그럼 회원이 어떤 음악을 들었는지 어떻게 알 수 있지?
	// 어떤 회원이 어떤 음악을 들었는지 DB로 데이터를 관리해야한다.
	// Music 테이블과 User 테이블은 일대다 관계이므로 userId와 musicId를 공동으로 가질 수 있는 해소 테이블이 추가로 필요하다.
	// 그럼 해소 테이블도 package가 필요할까? (테이블 당 하나의 패키지가 필요하다.)
	@Override
	public MusicVO getOneMusic(String musicId, UserVO userVO) {
		
		MusicVO music = musicBiz.getOneMusic(musicId);
		if (music != null) {
			userBiz.managePoint(userVO.getUserId(), -5);
			int userPoint = userVO.getUserPoint();
			userVO.setUserPoint(userPoint - 5);
		}
		return music;
	}

	@Override
	public boolean deleteOneMusic(String musicId) {
		return musicBiz.deleteOneMusic(musicId);
	}

}
