package com.sist.ehr;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uver.vo.ImgVO;
import com.uver.vo.MemberVO;

@Repository("ReviewDaoImpl")
public class ReviewDaoImpl implements ReviewDao {
	final static Logger LOG = LoggerFactory.getLogger(ReviewDaoImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public ReviewDaoImpl() {
	}
	
	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	RowMapper<ReviewVO> rowMapper= new RowMapper<ReviewVO>() {
		@Override
		public ReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReviewVO outVO = new ReviewVO(
						rs.getInt("review_seq"),
						rs.getString("title"),
						rs.getString("context"),
						rs.getString("writer"),
						rs.getString("reg_dt"),
						rs.getInt("div"),						
						rs.getString("mod_dt")
						);
			outVO.setSeq(rs.getInt("seq"));
			
			return outVO;
		}
   }; //---row mapper end
	
   
   @Override
public int doUpdate(ReviewVO review) {
	   int flag = 0;
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE review       \n");
	    sb.append(" SET                 \n");
		sb.append("	    review_seq = 	  	?,  \n");
		sb.append("	    div =  ?,  \n");
		sb.append("	    title =	  	?,  \n");
		sb.append("	    context =?,  \n");
		sb.append("	    writer =  ?,  \n");
		sb.append("	    reg_dt = 	?, 	\n");
		sb.append("	    mod_dt = 	  	?   \n");
	    sb.append(" WHERE  review_seq =    ?   \n");

		LOG.debug("==========================");
		LOG.debug("=sql=\n" + sb.toString());
		LOG.debug("=param=\n" + member);
		LOG.debug("==========================");
		
		Object[] args = {
				 member.getName(),
				 member.getPassword(),
				 member.getEmail(),
				 member.getCellPhone(),
				 member.getBirthday(),		
				 member.getGenre(),
				 member.getAuth(),
				 member.getSeq()
		};
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("flag:"+flag);
	   
	   return flag;
   }
   
	
	
	@Override
	public ReveiwVO doSelectOne(int seq) {
		ReveiwVO outVO = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT          \n");
	    sb.append(" review_seq,            \n");
	    sb.append(" div,        \n");
	    sb.append(" title,           \n");
	    sb.append(" context,       \n");
	    sb.append(" writer,          \n");
	    sb.append(" reg_dt,     \n");
	    sb.append(" mod_dt,       \n");	   
		sb.append(" FROM reivew     \n");
		sb.append(" WHERE review_seq = ?   \n");
		
		LOG.debug("-----------------------------");
		//LOG.debug("[SQL]\n"   + sb.toString());
		LOG.debug("[param]\n" + seq);
		LOG.debug("-----------------------------");	
		
		Object args[] = {seq};
		outVO = (ReveiwVO)this.jdbcTemplate.queryForObject(sb.toString(), 
															args, 
															rowMapper);
		
		LOG.debug("==========================");
		LOG.debug("=outVO="+outVO);
		LOG.debug("==========================");

		return outVO;
	}
	
	//작성자로 검색
	@Override
	public ReveiwVO doSelectOneById(String id) {
		ReveiwVO outVO = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT          \n");
	    sb.append(" review_seq,            \n");
	    sb.append(" div,        \n");
	    sb.append(" title,           \n");
	    sb.append(" context,       \n");
	    sb.append(" writer,          \n");
	    sb.append(" reg_dt,     \n");
	    sb.append(" mod_dt,       \n");	   
		sb.append(" FROM review     \n");
		sb.append(" WHERE writer = ?   \n");
		
		LOG.debug("-----------------------------");
		//LOG.debug("[SQL]\n"   + sb.toString());
		LOG.debug("[param]\n" + id);
		LOG.debug("-----------------------------");	
		
		Object args[] = {id};
		outVO = (ReviewVO)this.jdbcTemplate.queryForObject(sb.toString(), 
															args, 
															rowMapper);
		
		LOG.debug("==========================");
		LOG.debug("=outVO="+outVO);
		LOG.debug("==========================");

		return outVO;
	}
	/**
	 * 
	 * @param div
	 * @param searchWord
	 * @return
	 */
	
	//not yet
	@Override
	public List<ReviewVO> doSelectList(String div , String searchWord) {
		

		
		StringBuilder sbWhere=new StringBuilder();
		//寃��깋援щ텇!= null !"".equals(寃��깋援щ텇)
		//�븘�씠�뵒(10),�씠由�(20)
		if(null != div && !"".equals(div)) {
			if("10".equals(div)) {
				sbWhere.append(" WHERE user_id like '%'|| ? ||'%'  \n");
			}else if("20".equals(div)) {
				sbWhere.append(" WHERE name like '%'|| ? ||'%'  \n");
			}
			
		}
		
		StringBuilder sb=new StringBuilder();
		sb.append(" SELECT          \n");
	    sb.append(" review_seq,            \n");
	    sb.append(" div,        \n");
	    sb.append(" title,           \n");
	    sb.append(" context,       \n");
	    sb.append(" writer,          \n");
	    sb.append(" reg_dt,     \n");
	    sb.append(" mod_dt,       \n");	   
		sb.append(" FROM review     \n");
		sb.append(sbWhere.toString());
		LOG.debug("========================");
		LOG.debug("=sql\n="+sb.toString());
		LOG.debug("=param="+div+","+searchWord);
		LOG.debug("========================");	
		
		Object[] args  = { searchWord };	
		List<ReviewVO> list = (List<ReviewVO>)jdbcTemplate.query(sb.toString(), args, rowMapper);
		LOG.debug("list.size()" +list.size());
		for(ReviewVO vo: list) {
			LOG.debug("=vo="+vo);
		}
		
		return list;
	}
	
	/**
	 * �궘�젣
	 * @param ?
	 * @return
	 */
	@Override
	public int doDelete(int seq) {
		int flag = 0;

		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM review \n");
		sb.append(" WHERE review_seq = ?      \n");

		LOG.debug("==========================");
		LOG.debug("=sql=\n" + sb.toString());
		LOG.debug("=param=\n" + seq);
		LOG.debug("==========================");

		Object[] args = { seq };
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("flag:"+flag);
		
		return flag;
	}
	
	
	/**
	 * �궗�슜�옄�벑濡�
	 * �븘吏� �닔�젙以�
	 * @param member
	 * @return
	 */
	@Override
	public int doInsert(ReviewVO member) {
		int flag = 0;
		
		
		StringBuilder sb = new StringBuilder();
		   
		sb.append(" INSERT INTO review ( \n");
		sb.append("     review_seq,             \n");
		sb.append("     div,         \n");
		sb.append("     title,            \n");
		sb.append("     context,        \n");
		sb.append("     writer,           \n");
		sb.append("     reg_dt,      \n");
		sb.append("     mod_dt,        \n");		
		sb.append(" ) VALUES (           \n");
		sb.append("  review_seq.NEXTVAL, \n");
		sb.append("     ?,               \n");
		sb.append("     ?,               \n");
		sb.append("     ?,               \n");
		sb.append("     ?,               \n");
		sb.append("     sysdate,               \n");
		sb.append("     sysdate               \n");		
		sb.append(" )                    \n");
		
		LOG.debug("==========================");
		LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("=param=\n"+member);
		LOG.debug("==========================");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
	
	
	
}
