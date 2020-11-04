package com.uver.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.uver.cmn.Search;
import com.uver.dao.EventImgDaoImpl;
import com.uver.dao.ImgDaoImpl;
import com.uver.vo.EventImgVO;
import com.uver.vo.ImgVO;

@Service("eventImgService")
public class EventImgServiceImpl implements EventImgService {
	private static final Logger LOG = LoggerFactory.getLogger(EventImgServiceImpl.class);
	
	
	private final EventImgDaoImpl eventImgDao;
	private final ImgDaoImpl 	  imgDao;
	
	
	public EventImgServiceImpl(EventImgDaoImpl eventImgDao, ImgDaoImpl imgDao) {
		this.eventImgDao = eventImgDao;
		this.imgDao 	 = imgDao;
	}

	@Override
	public int doInsert(EventImgVO eventImg) {
		int flag = 0;

		int imgSeq = imgDao.doInsert(eventImg.getImgVO());
		flag = eventImgDao.doInsert(new EventImgVO(imgSeq, eventImg.getEventSeq()));
		
		return flag;
	}
	
	@Override
	public int doDelete(int imgSeq) {
		//---EventImg 테이블 데이터 동시에 삭제됨
		return imgDao.doDelete(imgSeq);
	}
	
	@Override
	public int doUpdate(EventImgVO eventImg) {
		return imgDao.doUpdate(eventImg.getImgVO());
	}

	@Override
	public EventImgVO doSelectOne(int imgSeq) {
		return eventImgDao.doSelectOne(imgSeq);
	}


	@Override
	public List<EventImgVO> doSelectAll(int eventSeq) {
		return eventImgDao.doSelectList(eventSeq);
	}
	
	
	
	@Override
	public List<EventImgVO> doSelectList(Search search) {
		return null;
	}

}
