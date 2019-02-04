package com.bailiwick.game_service.jdbc;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bailiwick.game_service.util.Queries;
import com.bailiwick.game_servicei.mapper.TierRowMapper;
import com.bailiwick.game_servicei.model.ReqGameDetails;
import com.bailiwick.game_servicei.model.ResTiersDetails;

@Service
public class DaoTierService {
	
	private Logger logger = (Logger) LogManager.getLogger(getClass());
	
	@Autowired
	JDBCTemplateService jdbcService;
	@Autowired
	Queries queries;
	
	public List<ResTiersDetails> getTiers(ReqGameDetails reqGameDetails) {
		
		List<ResTiersDetails> tierDetails = null;

		try {
			String query = queries.selectTiers();
			tierDetails = jdbcService.getJdbcTemplate().query(query, 
					new Object[] {reqGameDetails.getGameId()}, 
					new TierRowMapper());

			return tierDetails;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
}
