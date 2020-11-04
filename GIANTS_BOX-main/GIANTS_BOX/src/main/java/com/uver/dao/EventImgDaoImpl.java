package com.uver.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uver.vo.EventImgVO;
import com.uver.vo.ImgVO;


@Repository("eventImgDao")
public class EventImgDaoImpl {
	private static final Logger LOG = LoggerFactory.getLogger(EventImgDaoImpl.class);
    
    private final JdbcTemplate jdbcTemplate;
    
    public EventImgDaoImpl(JdbcTemplate jdbcTemplate) {
    	this.jdbcTemplate = jdbcTemplate;
    }
    
    //---row mapper------------------------------------------------
    RowMapper<EventImgVO> rowMapperJoin= new RowMapper<EventImgVO>() {
		@Override
		public EventImgVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			EventImgVO outVO = new EventImgVO(
									rs.getInt("img_seq"),
									rs.getInt("event_seq"),
									new ImgVO(
											rs.getInt("img_seq"),
											rs.getString("origin_name"),
											rs.getString("server_name"),
											rs.getString("img_type"),
											rs.getInt("img_size"),
											rs.getString("is_thumbnail"),
											rs.getString("reg_dt"),
											rs.getString("reg_id")
											)
									);
			return outVO;
		}
   };
   
   
    
   //---메서드----------------------------------------------------------
	public int doInsert(EventImgVO eventImg) {
		int 	 flag = 0;	    
	    Object[] args = { eventImg.getImgSeq(),
	    				  eventImg.getEventSeq()
	    				};
	    
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO event_image   \n");
		sb.append(" VALUES (                \n");
		sb.append("    ?,                    \n");
		sb.append("    ?                     \n");
		sb.append(")                         \n");
		
		LOG.debug("-----------------------------");
		LOG.debug("[SQL]\n"   + sb.toString());
		LOG.debug("[param]\n" + eventImg);
		LOG.debug("-----------------------------");			
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("[flag] "+flag);

		return flag;
	}

	
	public EventImgVO doSelectOne(int imgSeq){
	    Object[] args  = { imgSeq };
	    
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ei.img_seq			\n");
		sb.append("      ,ei.event_seq            \n");
		sb.append("      ,i.origin_name         \n");
		sb.append("      ,i.server_name         \n");
		sb.append("      ,i.img_type            \n");
		sb.append("      ,i.img_size            \n");
		sb.append("      ,i.is_thumbnail        \n");    
		sb.append("      ,i.reg_dt              \n");
		sb.append("      ,i.reg_id              \n");
		sb.append("FROM event_image ei, image i \n");
		sb.append("WHERE ei.img_seq = i.img_seq \n");
		sb.append("AND   ei.img_seq= ?           \n");
		
		LOG.debug("-----------------------------");
		LOG.debug("[SQL]\n"   + sb.toString());
		LOG.debug("[param]\n" + imgSeq);
		LOG.debug("-----------------------------");			
		
		EventImgVO outVO = (EventImgVO) this.jdbcTemplate.queryForObject(sb.toString(), args, rowMapperJoin);
		LOG.debug("-----------------------------");
		LOG.debug("[EventImgVO] " + outVO);
		LOG.debug("-----------------------------");
		
		return outVO;
	}
	
	
	public List<EventImgVO> doSelectList(int eventSeq) {
		List<EventImgVO> list = null;	    
	    Object[] args  = { eventSeq };
	    
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ei.img_seq			\n");
		sb.append("      ,ei.event_seq            \n");
		sb.append("      ,i.origin_name         \n");
		sb.append("      ,i.server_name         \n");
		sb.append("      ,i.img_type            \n");
		sb.append("      ,i.img_size            \n");
		sb.append("      ,i.is_thumbnail        \n");    
		sb.append("      ,i.reg_dt              \n");
		sb.append("      ,i.reg_id              \n");
		sb.append("FROM event_image ei, image i \n");
		sb.append("WHERE ei.img_seq = i.img_seq \n");
		sb.append("AND   event_seq= ?           \n");
		
		LOG.debug("-----------------------------");
		LOG.debug("[SQL]\n"   + sb.toString());
		LOG.debug("[param]\n" + eventSeq);
		LOG.debug("-----------------------------");			
		
		list = (List<EventImgVO>) this.jdbcTemplate.query(sb.toString(), args, rowMapperJoin);
		LOG.debug("-----------------------------");
		for (EventImgVO vo : list) {
			LOG.debug("[vo] " + list);
		}
		LOG.debug("-----------------------------");
		
		return list;
	}

	public int count(int eventSeq) {
		int  cnt = 0;
	    Object[] args  = { eventSeq };
		
		StringBuilder  sb=new StringBuilder();
		sb.append(" SELECT COUNT(*) cnt   \n");
		sb.append(" FROM event_image      \n");
		sb.append(" WHERE event_seq = ?   \n");
		LOG.debug("-----------------------------");
		LOG.debug("[SQL]\n"   + sb.toString());
		LOG.debug("[param]\n" + eventSeq);
		LOG.debug("-----------------------------");		
		
		cnt = this.jdbcTemplate.queryForObject(sb.toString(), args, Integer.class);
		LOG.debug("-----------------------------");
		LOG.debug("[count] "+cnt);
		LOG.debug("-----------------------------");

    	return cnt;
	}
	
	
	
    
    
    

}
