package com.uver.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uver.cmn.Search;
import com.uver.vo.EventVO;

@Repository("eventDaoImpl")
public class EventDaoImpl {

	private static final Logger LOG = LoggerFactory.getLogger(EventDaoImpl.class);
	
	private final JdbcTemplate jdbcTemplate;
	
	public EventDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	RowMapper<EventVO> rowMapper = new RowMapper<EventVO>(){

		@Override
		public EventVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			EventVO outVO = new EventVO();
			
			outVO.setEventSeq(rs.getInt("event_seq"));
			outVO.setUserId(rs.getString("user_id"));
			outVO.setEventNm(rs.getString("event_nm"));
			outVO.setContent(rs.getString("content"));
			outVO.setCapacity(rs.getInt("capacity"));
			outVO.setMovieInfo(rs.getString("movie_info"));
			outVO.setStartDt(rs.getString("start_dt"));
			outVO.setEndDt(rs.getString("end_dt"));
			outVO.setLocation(rs.getString("location"));
			outVO.setRegDt(rs.getString("reg_dt"));
			outVO.setTargetDt(rs.getString("target_dt"));
			
			return outVO;
		}
		
	};
	
	/**
	 * 등록
	 * @param event
	 * @return 1(성공) / 0(실패)
	 */
	public int doInsert(EventVO event) {
		
		int flag = 0;
		Object[] args = { event.getEventSeq(),
						  event.getUserId(),
						  event.getEventNm(),
						  event.getContent(),
						  event.getCapacity(),
						  event.getMovieInfo(),
						  event.getStartDt(),
						  event.getEndDt(),
						  event.getLocation(),
						  event.getTargetDt()
						};
 		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO event (		\n");
		sb.append("    event_seq,           \n");
		sb.append("    user_id,             \n");
		sb.append("    event_nm,            \n");
		sb.append("    content,             \n");
		sb.append("    capacity,            \n");
		sb.append("    movie_info,          \n");
		sb.append("    start_dt,            \n");
		sb.append("    end_dt,              \n");
		sb.append("    location,            \n");
		sb.append("    reg_dt,              \n");
		sb.append("    target_dt               \n");
		sb.append(") VALUES (               \n");
		sb.append("    ?,                 \n");
		sb.append("    ?,                 \n");
		sb.append("    ?,                 \n");
		sb.append("    ?,                 \n");
		sb.append("    ?,                 \n");
		sb.append("    ?,                 \n");
		sb.append("    ?,                 \n");
		sb.append("    ?,                 \n");
		sb.append("    ?,                 \n");
		sb.append("    sysdate,                 \n");
		sb.append("    ?                 \n");
		sb.append(")                        \n");
		
		LOG.debug("===========================");
		LOG.debug("=param=\n"+event);
		LOG.debug("===========================");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
	
	/**
	 * 삭제
	 * @param event
	 * @return 1(성공) / 0(실패)
	 */
	public int doDelete(EventVO event) {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM event	\n"); 	
		sb.append("WHERE event_seq = ?  \n");
		
		LOG.debug("=====================================");
		LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("=param="+event);
		LOG.debug("=====================================");
		
		Object[] args = {event.getEventSeq()};
		flag = this.jdbcTemplate.update(sb.toString(), args);
		
		return flag;
	}
	
	/**
	 * 수정
	 * @param event
	 * @return 1(성공) / 0(실패)
	 */
	public int doUpdate(EventVO event) {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE event			 \n");
		sb.append("SET user_id = ?,      \n");
		sb.append("    event_nm = ?,     \n");
		sb.append("    content = ?,      \n");
		sb.append("    capacity = ?,     \n");
		sb.append("    movie_info = ?,   \n");
		sb.append("    start_dt = ?,     \n");
		sb.append("    end_dt = ?,       \n");
		sb.append("    location = ?,     \n");
		sb.append("    target_dt = ?  \n");
		sb.append("WHERE event_seq = ?     \n");
		
		LOG.debug("=====================================");
		//LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("=param="+event);
		LOG.debug("=====================================");
		
		Object[] args = { 
						  event.getUserId(),
						  event.getEventNm(),
						  event.getContent(),
						  event.getCapacity(),
						  event.getMovieInfo(),
						  event.getStartDt(),
						  event.getEndDt(),
						  event.getLocation(),
						  event.getTargetDt(),
						  event.getEventSeq()
				};
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
	
	/**
	 * 단건조회
	 * @param eventSeq
	 * @return
	 */
	public EventVO doSelectOne(int eventSeq) {
		EventVO outVO = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT event_seq,								    \n");	 
		sb.append("    user_id,										    \n");
		sb.append("	   event_nm,                                        \n");
		sb.append("	   content,                                         \n");
		sb.append("	   capacity,                                        \n");
		sb.append("	   movie_info,                                      \n");
		sb.append("	   start_dt,                                        \n");
		sb.append("	   end_dt,                                          \n");
		sb.append("	   location,                                        \n");
		sb.append("	   TO_CHAR(reg_dt, 'YY/MM/DD HH24MISS') AS reg_dt,   \n");
		sb.append("	   target_dt   \n");
		sb.append("FROM event                                           \n");
		sb.append("WHERE event_seq = ?                                  \n");
		
		LOG.debug("=====================================");
		LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("=param="+eventSeq);
		LOG.debug("=====================================");
		
		Object args[] = {eventSeq};
		outVO = (EventVO) this.jdbcTemplate.queryForObject(sb.toString(), args, rowMapper);
		
		LOG.debug("=====================================");
		LOG.debug("=outVO="+outVO);
		LOG.debug("=====================================");
		
		return outVO;
	}
	
	public List<EventVO> doSelectList(Search search){
		StringBuilder sbWhere = new StringBuilder();
		
		if(null!= search.getSearchDiv() && !"".equals(search.getSearchDiv())) {
			if("10".equals(search.getSearchDiv())) {
				sbWhere.append(" WHERE event_nm like '%'|| ? ||'%' \n");
			}else if("20".equals(search.getSearchDiv())) {
				sbWhere.append(" WHERE target_dt like '%'|| ? ||'%' \n");
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT T1.*,T2.*                                                                \n");
		sb.append(" FROM                                                                            \n");
		sb.append("  (                                                                              \n");
		sb.append("      SELECT B.rnum,                                                             \n");
		sb.append("             B.event_seq,                                                             \n");
		sb.append("             B.user_id,                                                             \n");
		sb.append("             B.event_nm,                                                             \n");
		sb.append("             B.content,                                                           \n");
		sb.append("             B.capacity,                                                          \n");
		sb.append("             B.movie_info,                                                            \n");
		sb.append("             B.start_dt,                                                        \n");
		sb.append("             B.end_dt,                                                             \n");
		sb.append("             B.location,                                                             \n");
		sb.append("             --등록일이 당일이면:HH24MI 그렇치 않으면: YYYY-MM-DD                            \n");
		sb.append("             DECODE(TO_CHAR(SYSDATE,'YYYYMMDD'),TO_CHAR(B.reg_dt,'YYYYMMDD')     \n");
		sb.append("                   ,TO_CHAR(B.reg_dt,'HH24:MI')                                  \n");
		sb.append("                   ,TO_CHAR(B.reg_dt,'YYYY-MM-DD'))reg_dt,                        \n");
		sb.append("             B.target_dt                                                             \n");
		sb.append("      FROM (                                                                     \n");
		sb.append("          SELECT ROWNUM rnum, A.*                                                \n");
		sb.append("          FROM(                                                                  \n");
		sb.append("              SELECT *                                                           \n");
		sb.append("              FROM event                                                    \n");
		//----------------------------------------------------------------------------------------------
		//Where조건 
		//----------------------------------------------------------------------------------------------		
		sb.append(sbWhere.toString() );
		
		sb.append("              ORDER BY reg_dt DESC                                               \n");
		sb.append("          )A                                                                     \n");
		//sb.append("          WHERE ROWNUM <=(&PAGE_SIZE *(&PAGE_NUM-1)+&PAGE_SIZE)                  \n");
		sb.append("          WHERE ROWNUM <=(? *(?-1)+?)                  \n");
		
		sb.append("      )B                                                                         \n");
		//sb.append("      WHERE b.rnum >=(&PAGE_SIZE *(&PAGE_NUM-1)+1)                               \n");
		sb.append("      WHERE b.rnum >=(? *(?-1)+1)                               \n");
		sb.append("      )T1                                                                        \n");
		sb.append("      CROSS JOIN                                                                 \n");
		sb.append("      --전체COUNT                                                                  \n");
		sb.append("      (SELECT COUNT(*) total_cnt                                                 \n");
		sb.append("       FROM  event                                                          \n");
		//----------------------------------------------------------------------------------------------
		//Where조건 
		//----------------------------------------------------------------------------------------------		
		sb.append(sbWhere.toString() );
		
		sb.append("      )T2                                                                        \n");
		
		//Param처리
		List<Object>  listArg = new ArrayList<Object>();
		
		//검색조건+:7개 ?
		if(null != search.getSearchDiv() && !"".equals(search.getSearchDiv())) {
			listArg.add(search.getSearchWord());
			
			listArg.add(search.getPageSize());
			listArg.add(search.getPageNum());
			listArg.add(search.getPageSize());
			listArg.add(search.getPageSize());
			listArg.add(search.getPageNum());
			
			listArg.add(search.getSearchWord());
		}else {
			//&PAGE_SIZE *(&PAGE_NUM-1)+&PAGE_SIZE
			//&PAGE_SIZE *(&PAGE_NUM
			listArg.add(search.getPageSize());
			listArg.add(search.getPageNum());
			listArg.add(search.getPageSize());
			listArg.add(search.getPageSize());
			listArg.add(search.getPageNum());
		}
	    LOG.debug("========================");
		LOG.debug("=sql\n="+sb.toString());
		LOG.debug("=param="+search);
		LOG.debug("========================");			
		
		List<EventVO> list = (List<EventVO>)jdbcTemplate.query(sb.toString(), listArg.toArray(), rowMapper);
		for(EventVO vo: list) {
			LOG.debug("=vo="+vo);
		}
		
		return list;
	}
}
