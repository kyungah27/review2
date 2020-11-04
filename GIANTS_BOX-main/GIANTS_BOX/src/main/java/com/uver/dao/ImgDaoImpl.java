package com.uver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.uver.vo.ImgVO;


@Repository("imgDao")
public class ImgDaoImpl {
	private static final Logger LOG = LoggerFactory.getLogger(ImgDaoImpl.class);
    
    private final JdbcTemplate jdbcTemplate;
    
    public ImgDaoImpl(JdbcTemplate jdbcTemplate) {
    	this.jdbcTemplate = jdbcTemplate;
    }
    
    //---row mapper------------------------------------------------
    RowMapper<ImgVO> rowMapper= new RowMapper<ImgVO>() {
		public ImgVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ImgVO outVO = new ImgVO(
						rs.getInt("img_seq"),
						rs.getString("origin_name"),
						rs.getString("server_name"),
						rs.getString("img_type"),
						rs.getInt("img_size"),
						rs.getString("is_thumbnail"),
						rs.getString("reg_dt"),
						rs.getString("reg_id")
						);
			
			return outVO;
		}
   }; //---row mapper end
    
    
    
   //---메서드----------------------------------------------------------
	/**
	 * 이미지 등록
	 * 생성된 seq 값 돌려받음
	 * 
	 * @param ImgVO
	 * @return int imgSeq
	 */
   public int doInsert(ImgVO img) {
		int   flag 	  			  = 0;	    
	    final KeyHolder keyHolder = new GeneratedKeyHolder();
	    
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO image (		\n");
		sb.append("    img_seq,             \n");
		sb.append("    origin_name,         \n");
		sb.append("    server_name,         \n");
		sb.append("    img_type,            \n");
		sb.append("    img_size,            \n");
		sb.append("    is_thumbnail,        \n");
		sb.append("    reg_dt,              \n");
		sb.append("    reg_id               \n");
		sb.append(") VALUES (               \n");
		sb.append("    IMAGE_SEQ.NEXTVAL,   \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    SYSDATE,             \n");
		sb.append("    ?                    \n");
		sb.append(")                        \n");
		
		LOG.debug("-----------------------------");
		LOG.debug("[SQL]\n"   + sb.toString());
		LOG.debug("[param]\n" + img);
		LOG.debug("-----------------------------");			
		
		flag = this.jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				PreparedStatement ps = con.prepareStatement(sb.toString(),
															new String[] {"IMG_SEQ"});
				ps.setString(1, img.getOriginName());
				ps.setString(2, img.getServerName());
				ps.setString(3, img.getImgType());
				ps.setInt(4, img.getImgSize());
				ps.setString(5, img.getIsThumbnail());
				ps.setString(6, img.getRegId());
				
				return ps;
			}
		}, keyHolder);
		
		
		int seq = keyHolder.getKey().intValue();
		
		LOG.debug("[flag] " + flag);
		LOG.debug("[seq] "+ seq);

		return seq;
	}
	
	
   /**
    * 이미지 삭제
    * 
    * @param int imgSeq
    * @return flag (1:성공 / 0: 실패)
    */
	public int doDelete(int imgSeq) {
		int flag 	  = 0;	    
	    Object[] args = { imgSeq };
	    
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM image \n");
		sb.append("WHERE             \n");
		sb.append("    img_seq = ?   \n");
		
		LOG.debug("-----------------------------");
		LOG.debug("[SQL]\n"   + sb.toString());
		LOG.debug("[param]\n" + imgSeq);
		LOG.debug("-----------------------------");			
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("[flag] "+flag);

		return flag;
	}
	
	
	
	/**
	 * 이미지 단건 조회
	 * 
	 * @param int imgSeq
	 * @return ImgVO
	 */
	public ImgVO doSelectOne(int seq) {
		ImgVO 	 outVO = null;	    
	    Object[] args  = { seq };
	    
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT				\n");
		sb.append("    img_seq,         \n");
		sb.append("    origin_name,     \n");
		sb.append("    server_name,     \n");
		sb.append("    img_type,        \n");
		sb.append("    img_size,        \n");
		sb.append("    is_thumbnail,    \n");
		sb.append("    reg_dt,          \n");
		sb.append("    reg_id           \n");
		sb.append("FROM                 \n");
		sb.append("    image            \n");
		sb.append("WHERE img_seq = ?    \n");
		
		LOG.debug("-----------------------------");
		LOG.debug("[SQL]\n"   + sb.toString());
		LOG.debug("[param]\n" + seq);
		LOG.debug("-----------------------------");			
		
		outVO = (ImgVO) this.jdbcTemplate.queryForObject(sb.toString(), args, rowMapper);
		LOG.debug("[outVO]\n" + outVO);

		return outVO;
	}
	
	
	/**
	 * 등록자ID 기준 이미지 다건 조회
	 * 
	 * @param String regId
	 * @return List<ImgVO>
	 */
	public List<ImgVO> doSelectList(String regId) {
		List<ImgVO> list = null;	    
	    Object[] args  = { regId };
	    
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT				\n");
		sb.append("    img_seq,         \n");
		sb.append("    origin_name,     \n");
		sb.append("    server_name,     \n");
		sb.append("    img_type,        \n");
		sb.append("    img_size,        \n");
		sb.append("    is_thumbnail,    \n");
		sb.append("    reg_dt,          \n");
		sb.append("    reg_id           \n");
		sb.append("FROM                 \n");
		sb.append("    IMAGE            \n");
		sb.append("WHERE reg_id = ?     \n");
		sb.append("ORDER BY reg_dt DESC \n");
		
		LOG.debug("-----------------------------");
		LOG.debug("[SQL]\n"   + sb.toString());
		LOG.debug("[param]\n" + regId);
		LOG.debug("-----------------------------");			
		
		list = (List<ImgVO>) this.jdbcTemplate.query(sb.toString(), args, rowMapper);
		LOG.debug("-----------------------------");
		for (ImgVO vo : list) {
			LOG.debug("[vo] " + list);
		}
		LOG.debug("-----------------------------");
		
		return list;
	}

	/**
	 * 등록자 ID 기준 count
	 * 
	 * @param String regId
	 * @return int cnt
	 */
	public int count(String regId) {
		int  cnt = 0;
	    Object[] args  = { regId };
		
		StringBuilder  sb=new StringBuilder();
		sb.append(" SELECT COUNT(*) cnt \n");
		sb.append(" FROM IMAGE          \n");
		sb.append(" WHERE reg_id = ?    \n");
		LOG.debug("-----------------------------");
		LOG.debug("[SQL]\n"   + sb.toString());
		LOG.debug("[param]\n" + regId);
		LOG.debug("-----------------------------");		
		
		cnt = this.jdbcTemplate.queryForObject(sb.toString(), args, Integer.class);
		LOG.debug("-----------------------------");
		LOG.debug("[count] "+cnt);
		LOG.debug("-----------------------------");

    	return cnt;
	}
	
	/**
	 * 이미지 수정 (썸네일 여부만)
	 * 
	 * @param img
	 * @return flag (1: 성공 / 0: 실패)
	 */
	public int doUpdate(ImgVO img) {
		int flag 	  = 0;	    
	    Object[] args = { 	img.getIsThumbnail(),
	    					img.getImgSeq()
	    				};
	    
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE image			\n");
		sb.append("SET                  \n");
		sb.append("    is_thumbnail = ? \n");
		sb.append("WHERE                \n");
		sb.append("    img_seq = ?      \n");
		
		LOG.debug("-----------------------------");
		LOG.debug("[SQL]\n"   + sb.toString());
		LOG.debug("[param]\n" + img);
		LOG.debug("-----------------------------");			
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("[flag] "+flag);

		return flag;
	}
	
	
    
    
    

}
