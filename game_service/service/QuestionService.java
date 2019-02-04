package com.bailiwick.game_service.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bailiwick.game_service.enums.GameType;
import com.bailiwick.game_service.jdbc.DaoQuestionService;
import com.bailiwick.game_service.util.Util;
import com.bailiwick.game_servicei.model.ReqNextQuestion;
import com.bailiwick.game_servicei.model.ReqSaveAnswer;
import com.bailiwick.game_servicei.model.ResQuestionDetails;
import com.bailiwick.game_servicei.model.ResSaveAnswer;

@Service
public class QuestionService {

	private Logger logger = (Logger) LogManager.getLogger(getClass());

	@Autowired
	DaoQuestionService daoService;
	
	@Autowired
	Util util;

	public ResQuestionDetails getQuestion(ReqNextQuestion reqNextQuestion, GameType gameType) {
		if(GameType.POOL_PRICE.equals(gameType)) {
			return getQuestionInSequenceForPool(reqNextQuestion, gameType);
		}
		else if(GameType.QUICK_WIN.equals(gameType)) {
			return daoService.getQuestionForQuickWin();
		}

		return getQuestionInSequence(reqNextQuestion, gameType);
	}

	public ResQuestionDetails getQuestionSeasonWise(ReqNextQuestion reqNextQuestion) {
		return daoService.getQuestionSeasonWise(reqNextQuestion.getGameId(), reqNextQuestion.getSeason());
	}

	public ResQuestionDetails getQuestionInSequence(ReqNextQuestion reqNextQuestion, GameType gameType) {
		Integer maxQuestionId = null;
		ResQuestionDetails resQuestionDetails = null;
		try {
			maxQuestionId 	= daoService.getMaxQuestionId();


			int questionId=	util.getQuestionId() ;

			logger.debug("Current Question Id: " + questionId);

			if(maxQuestionId == 0) {
				return new ResQuestionDetails().setQid(-1);
			}

			if(maxQuestionId < questionId) {
				questionId = 1;
			}
			resQuestionDetails = daoService.getQuestionById(Integer.toString(questionId));

			logger.debug(resQuestionDetails);

			if(resQuestionDetails == null) {
				logger.debug("recursive call");
				resQuestionDetails = getQuestion(reqNextQuestion, null);
			}

			return resQuestionDetails;

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public ResQuestionDetails getQuestionInSequenceForPool(ReqNextQuestion reqNextQuestion, GameType gameType) {
		Integer maxQuestionId = null;
		ResQuestionDetails resQuestionDetails = null;
		try {
			maxQuestionId 	= daoService.getMaxQuestionId();

			//	Util.currentQuestionIdOfPool = Util.currentQuestionIdOfPool + 1;
			int questionIdPool = util.getQuestionIdOfPool();

			logger.debug("Current currentQuestionIdOfPool: " + questionIdPool);

			if(maxQuestionId == 0) {
				return new ResQuestionDetails().setQid(-1);
			}

			if(maxQuestionId < questionIdPool) {
				questionIdPool = 1;
			}
			resQuestionDetails = daoService.getQuestionById(Integer.toString(questionIdPool));

			logger.debug(resQuestionDetails);

			if(resQuestionDetails == null) {
				logger.debug("recursive call");
				resQuestionDetails = getQuestion(reqNextQuestion, gameType);
			}

			return resQuestionDetails;

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}


	public ResSaveAnswer saveAnswer(ReqSaveAnswer reqSaveAnswer) {
		String correctAnswer = null;
		ResSaveAnswer resSaveAnswer = null;

		try {
			correctAnswer = daoService.getValidOption(reqSaveAnswer.getqId());
			if(correctAnswer == null) {
				return null;
			}

			resSaveAnswer = new ResSaveAnswer();
			resSaveAnswer.setqId(reqSaveAnswer.getqId());
			resSaveAnswer.setCorrectAnswer(correctAnswer);
			resSaveAnswer.setUserId(reqSaveAnswer.getAuthentication().getUserId());

			if(correctAnswer.equals(reqSaveAnswer.getSelectedOption())) {
				resSaveAnswer.setIsOptionCorrect(true);
			}else {
				resSaveAnswer.setIsOptionCorrect(false);
			}
			logger.debug(resSaveAnswer);
			return resSaveAnswer;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}
