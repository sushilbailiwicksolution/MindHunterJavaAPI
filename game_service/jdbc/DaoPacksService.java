package com.bailiwick.game_service.jdbc;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bailiwick.game_service.util.Queries;
import com.bailiwick.game_servicei.mapper.PacksRowMapper;
import com.bailiwick.game_servicei.model.ReqPacksDetails;
import com.bailiwick.game_servicei.model.ResPacksDetails;

@Service
public class DaoPacksService {

	private Logger logger = (Logger) LogManager.getLogger(getClass());

	@Autowired
	JDBCTemplateService jdbcService;
	@Autowired
	Queries queries;

	public List<ResPacksDetails> getPacks(String currentDate) {

		List<ResPacksDetails> packsDetails = null;

		try {
			String query = queries.selectAllPacks();
			packsDetails = jdbcService.getJdbcTemplate().query(query, 
					new Object[] {currentDate, currentDate}, 
					new PacksRowMapper());

			return packsDetails;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public List<ResPacksDetails> getPacks(String currentDate, ReqPacksDetails reqPacksDetails) {

		List<ResPacksDetails> packsDetails = null;

		try {
			String query = queries.selectPacksByGameId();
			packsDetails = jdbcService.getJdbcTemplate().query(query, 
					new Object[] {reqPacksDetails.getGameId(), currentDate, currentDate}, 
					new PacksRowMapper());

			return packsDetails;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}


}
