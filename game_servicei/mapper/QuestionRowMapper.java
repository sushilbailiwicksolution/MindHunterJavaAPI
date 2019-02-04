package com.bailiwick.game_servicei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.bailiwick.game_servicei.model.ResQuestionDetails;

public class QuestionRowMapper implements ResultSetExtractor<ResQuestionDetails>{

	private Logger logger = (Logger) LogManager.getLogger(getClass());

	@Override
	public ResQuestionDetails extractData(ResultSet rs) throws SQLException, DataAccessException {
		ResQuestionDetails resQuestionDetails = null;

		try {
			while(rs.next()) {
				resQuestionDetails = new ResQuestionDetails();

				resQuestionDetails.setAnsOption((rs.getString("ans_option") == null ? "":rs.getString("ans_option")).split("~"));
				resQuestionDetails.setCategory(rs.getString("category"));
				resQuestionDetails.setQid(Integer.parseInt(rs.getString("qId")));
				resQuestionDetails.setqImage(rs.getString("qImage"));
				resQuestionDetails.setqLevel(Integer.parseInt(rs.getString("qLevel")));
				resQuestionDetails.setqText(rs.getString("qText"));

				return resQuestionDetails;
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
