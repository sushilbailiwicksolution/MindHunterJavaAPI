package com.bailiwick.game_service.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class Queries {

	private Logger logger = (Logger) LogManager.getLogger(Queries.class);

	@Autowired
	Gson gson;

	public String selectAllGames() {
		String query = "SELECT id, title, status, gType, gIcon, start_date, end_date, season, gMode from api_game_details"
				+ " where status=1";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [selectAllGames] [" + query + " ]");

		return query;
	}
	
	public String selectGameByGameId() {
		String query = "SELECT id, title, status, gType, gIcon, start_date, end_date, season, gMode from api_game_details "
				+ "where status=1 and id=?";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [selectGameByGameId] [" + query + " ]");
		
		return query;
	}
	
	public String selectAllPacks() {
		String query = "SELECT packId, gameId, pack_title, entry_fee, description, tAndC, totalQ, reward, lifeLines, max_player, "
				+ "min_player, qlevel, winner_msg, loser_msg, max_play_time, time_per_q, pack_type, season, points_per_Q from api_game_details"
				+ " where start_date<=? and end_date>=?";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [selectAllPacks] [" + query + " ]");

		return query;
	}
	
	public String selectPacksByGameId() {
		String query = "SELECT packId, gameId, pack_title, entry_fee, description, tAndC, totalQ, reward, lifeLines, max_player, "
				+ "min_player, qlevel, winner_msg, loser_msg, max_play_time, time_per_q, pack_type, season, points_per_Q from api_packs_details"
				+ " where gameId=? and start_date<=? and end_date>=?";
				
		if(logger.isDebugEnabled())
			logger.debug("Query for [selectPacksByGameId] [" + query + " ]");
		
		return query;
	}
	
	public String selectQuestionById() {
		String query = "SELECT qId, qLevel, qText, qImage, ans_option, category from api_question_details"
				+ " where qId=? and gameId=-1 and season='NA'";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [selectQuestionById] [" + query + " ]");
		
		return query;
	}
	
	public String selectQuestionForQuickWin(String currentDate) {
		
		String query = "SELECT qId, qText, qImage, ans_option, qLevel, category from api_question_details"
				+ " where Date(createdOn)=Str_to_date('" + currentDate + "', '%Y-%m-%d') and gameId=5";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [api_QuickWin_Question_Details] [" + query + " ]");
		
		return query;
	}
	
	public String selectQuestionSeasonWise() {
		
		String query = "SELECT qId, qText, qImage, ans_option, qLevel, category from api_question_details"
				+ " where gameId=? and season=?";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [selectQuestionSeasonWise] [" + query + " ]");
		
		return query;
	}
	
	public String selectValidOptionByQuestionId() {
		String query = "SELECT valid_option as value from api_question_details"
				+ " where qId=?";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [selectQuestionById] [" + query + " ]");
		
		return query;
	}
	
	public String selectMaxQuestionId() {
		String query = "SELECT MAX(qId) as value from api_question_details";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [selectMaxQuestionId] [" + query + " ]");
		
		return query;
	}
	
	public String updateInActiveQdetails() {
		String query = "update api_usedQ_details set lastUsedQId=lastUsedQId+1";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [updateInActiveQdetails] [" + query + " ]");
		
		return query;
	}
	
	public String insertUsedQdetails() {
		String query = "insert into api_usedQ_details (category, lastUsedQId) values(1, 1)";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [updateInActiveQdetails] [" + query + " ]");
		
		return query;
	}
	
	public String selectCurrentQuestionId() {
		String query = "select lastUsedQId as value from api_usedQ_details";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [selectCurrentQuestionId] [" + query + " ]");
		
		return query;
	}
	
	public String resetActiveQdetails() {
		String query = "update api_usedQ_details set lastUsedQId=1";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [resetActiveQdetails] [" + query + " ]");
		
		return query;
	}
	public String getSpinBlocks() {
		String query = "select id, imgUrl, price, bgColor, text, nextAction from api_spin_blocks where gameId=? order by id";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [getSpinBlocks] [" + query + " ]");
		
		return query;
	}
	
	public String selectTiers() {
		String query = "SELECT no_of_question, reward from api_tier_details where gameId=?";
		
		if(logger.isDebugEnabled())
			logger.debug("Query for [selectAllGames] [" + query + " ]");

		return query;
	}
}
