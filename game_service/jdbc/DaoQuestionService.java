package com.bailiwick.game_service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.bailiwick.game_service.util.DateUtil;
import com.bailiwick.game_service.util.Queries;
import com.bailiwick.game_servicei.mapper.IntegerRowMapper;
import com.bailiwick.game_servicei.mapper.QuestionRowMapper;
import com.bailiwick.game_servicei.mapper.SingleValueRowMapper;
import com.bailiwick.game_servicei.model.ResQuestionDetails;

@Service
public class DaoQuestionService {

	private Logger logger = (Logger) LogManager.getLogger(getClass());

	@Autowired
	JDBCTemplateService jdbcService;
	@Autowired
	Queries queries;
	@Autowired
	DateUtil dateUtil;

	public ResQuestionDetails getQuestionById(String questionId) {

		ResQuestionDetails questionDetail = null;

		try {
			String query = queries.selectQuestionById();
			questionDetail = jdbcService.getJdbcTemplate().query(query, new Object[] { questionId },
					new QuestionRowMapper());

			logger.debug(questionDetail);

			return questionDetail;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public String getValidOption(String questionId) {
		String validOption = null;
		try {
			String query = queries.selectValidOptionByQuestionId();
			validOption = jdbcService.getJdbcTemplate()
					.query(query, new Object[] { questionId }, new SingleValueRowMapper()).get(0);

			return validOption;
		} catch (IndexOutOfBoundsException e) {
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public Integer getMaxQuestionId() {
		Integer maxId = null;
		try {
			String query = queries.selectMaxQuestionId();
			maxId = jdbcService.getJdbcTemplate().query(query, new Object[] {}, new IntegerRowMapper()).get(0);

			return maxId;
		} catch (IndexOutOfBoundsException e) {
			return 0;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public synchronized String getCurrentQuestionId() {
		try {
			if (updateInActiveQdetails()) {
				return getCurrentQuestionId(1);
			} else {
				insertUsedQdetails();
				return "1";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return null;
	}

	public String getCurrentQuestionId(int arg0) {
		Integer qId = null;
		try {
			String query = queries.selectCurrentQuestionId();
			qId = jdbcService.getJdbcTemplate().query(query, new Object[] {}, new IntegerRowMapper()).get(0);

			return qId + "";
		} catch (IndexOutOfBoundsException e) {
			return "-1";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "-2";
		}
	}

	public boolean updateInActiveQdetails() {
		int updateStatus = -1;
		String query = queries.updateInActiveQdetails();

		try {
			updateStatus = jdbcService.getJdbcTemplate().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preparedStatement = con.prepareStatement(query);
					return preparedStatement;
				}
			});

		} catch (DataAccessException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}

		return updateStatus > 0;
	}

	public synchronized String resetActiveQdetails() {
		String currentQuestionId = null;
		String query = queries.resetActiveQdetails();

		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcService.getJdbcTemplate().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preparedStatement = con.prepareStatement(query, new String[] { "lastUsedQId" });
					return preparedStatement;
				}
			}, keyHolder);

			currentQuestionId = Integer.toString(keyHolder.getKey().intValue());

			if (logger.isDebugEnabled())
				logger.debug("Returned currentQuestionId: " + currentQuestionId);

		} catch (DataAccessException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}

		return currentQuestionId;
	}

	public boolean insertUsedQdetails() {

		int updateStatus = -1;

		try {
			updateStatus = jdbcService.getJdbcTemplate().update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preparedStatement = con.prepareStatement(queries.insertUsedQdetails());

					return preparedStatement;
				}
			});

			logger.info("iniatialized in api_usedQ_details successfully on MO.");

			return updateStatus > 0;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public ResQuestionDetails getQuestionForQuickWin() {
		ResQuestionDetails resQuestionDetails = null;

		String currentDate = dateUtil.currentDate().toString();
		logger.debug("Get question of date [" + currentDate + "] for QuickWin.");

		try {
			String query = queries.selectQuestionForQuickWin(currentDate);
			resQuestionDetails = jdbcService.getJdbcTemplate().query(query, new QuestionRowMapper());

			return resQuestionDetails;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResQuestionDetails();
		}
	}

	public ResQuestionDetails getQuestionSeasonWise(String gameId, String season) {
		logger.debug("Get question for season [" + season + "].");

		try {
			String query = queries.selectQuestionSeasonWise();
			return jdbcService.getJdbcTemplate().query(query, new Object[] { gameId, season }, new QuestionRowMapper());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResQuestionDetails();
		}
	}

}
