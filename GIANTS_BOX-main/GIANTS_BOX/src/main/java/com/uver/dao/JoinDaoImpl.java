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

import com.uver.vo.JoinVO;

@Repository("joinDaoImpl")
public class JoinDaoImpl implements JoinDao {
	private static final Logger LOG = LoggerFactory.getLogger(JoinDaoImpl.class);

	@Autowired
	private JdbcTemplate jbcTemplate;

	public JoinDaoImpl() {
		super();
	}
    RowMapper rowMapper= new RowMapper<JoinVO>() {
		@Override
		public JoinVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			JoinVO vo = new JoinVO();
			
			vo.setEventSeq(rs.getInt("event_seq"));
			vo.setMemberSeq(rs.getInt("member_seq"));
			vo.setPriority(rs.getInt("priority"));
			vo.setRegDt(rs.getString("reg_dt"));
			
			return vo;
		}

   };
	
   
	@Override
	public int doInsert(JoinVO vo) {

		int flag = 0;

		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO event_join ( \n");
		sb.append(" 	    event_seq,       \n");
		sb.append(" 	    member_seq,      \n");
		sb.append(" 	    priority   		\n");
		sb.append(" ) VALUES (               \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                	 \n");
		sb.append("     ?                 	 \n");
		sb.append(" )                        \n");
		LOG.debug("=param=\n" + vo);

		LOG.debug("========================");

		Object[] args = { vo.getEventSeq(), vo.getMemberSeq(), vo.getPriority() };
		flag = this.jbcTemplate.update(sb.toString(), args);
		LOG.debug("= doInsert flag=" + flag);

		return flag;
	}

	@Override
	public int doDelete(JoinVO vo) {

		int flag = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM event_join    \n");
		sb.append("WHERE                     \n");
		sb.append("    event_seq = ?       \n");
		sb.append("    AND member_seq = ?  \n");

		Object[] args = { vo.getEventSeq(), vo.getMemberSeq() };
		flag = this.jbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag=" + flag);
		return flag;
	}

	@Override
	public int doUpdate(JoinVO vo) {
		int flag = 0;

		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE event_join      \n");
		sb.append("SET                    \n");
		sb.append("    priority = ?       \n");
		sb.append("WHERE                  \n");
		sb.append("    event_seq = ?      \n");
		sb.append("    AND member_seq = ? \n");
		Object[] args = { vo.getPriority(), vo.getEventSeq(), vo.getMemberSeq() };
		flag = this.jbcTemplate.update(sb.toString(), args);		
		LOG.debug("=flag=" + flag);
		return flag;

	}
	
	@Override
	public JoinVO doSelectOne(JoinVO vo) {
				
		JoinVO outVO = null;		
		StringBuilder  sb=new StringBuilder();
		sb.append("SELECT                  \n");
		sb.append("    event_seq,          \n");
		sb.append("    member_seq,         \n");
		sb.append("    priority,           \n");
		sb.append("    TO_CHAR(reg_dt,'YYYY-MM-DD HH24MISS') AS reg_dt      \n");
		sb.append("FROM event_join         \n");
		sb.append("WHERE event_seq = ?	   \n");
		sb.append("AND member_seq = ?	   \n");
		Object args[] = {vo.getEventSeq() , vo.getMemberSeq()};
		outVO = (JoinVO) this.jbcTemplate.queryForObject(sb.toString(), 
    			                        args, 
    			                        rowMapper);		
		LOG.debug("========================");
		LOG.debug("=outVO="+outVO);
		LOG.debug("========================");			
    	
    	return outVO;	
	}
	
	@Override
	public List doSelectList(JoinVO vo) {
		
		List<JoinVO> list = null;
		StringBuilder  whereSb=new StringBuilder();
		LOG.debug("inVO"+vo);
		if(vo.getEventSeq()!=0 && vo.getMemberSeq()!=0) {
			return list;
		}else if(vo.getEventSeq()!=0) {
			whereSb.append("WHERE event_seq =?");
		}else if(vo.getMemberSeq()!=0) {
			whereSb.append("WHERE member_seq =?");
		}else {
			whereSb.append("WHERE 1 = 0");
		}
		
		StringBuilder  sb=new StringBuilder();
		sb.append("SELECT                  \n");
		sb.append("    event_seq,          \n");
		sb.append("    member_seq,         \n");
		sb.append("    priority,           \n");
		sb.append("    TO_CHAR(reg_dt,'YYYY-MM-DD HH24MISS') AS reg_dt      \n");
		sb.append("FROM event_join         \n");
		sb.append(whereSb.toString());
		sb.append("ORDER BY event_seq \n");
		List<Object> listArg = new ArrayList<Object>();
		if(vo.getMemberSeq()!=0) {
			listArg.add(vo.getMemberSeq());
		}else if(vo.getEventSeq()!=0) {
			listArg.add(vo.getEventSeq());
		}	
		list = this.jbcTemplate.query(sb.toString(), 
				listArg.toArray(),  rowMapper);				
    	return list;	
	}
	
	@Override
	public int doSelectMinReg(int event_seq) {
		
		List<Integer> list = null;
		JoinVO outVO = null;		
		StringBuilder  sb=new StringBuilder();
		sb.append("SELECT member_seq                       \n");
		sb.append("FROM   event_join                       \n");
		sb.append("WHERE  event_seq  = ?                   \n");
		sb.append("AND reg_dt = (Select MIN(reg_dt)        \n");
		sb.append("                FROM event_join         \n");
		sb.append("                WHERE event_seq = ?	 ) \n");
		Object args[] = {event_seq , event_seq};
		list =  this.jbcTemplate.queryForList(sb.toString(), 
    			                        args, 
    			                        Integer.class);	
		
		for(int i : list) {
			LOG.debug("doSelectMinReg" + i);
		}
    	
    	return list.get(0);	
	}
	
	


}
