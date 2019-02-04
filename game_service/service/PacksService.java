package com.bailiwick.game_service.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bailiwick.game_service.jdbc.DaoPacksService;
import com.bailiwick.game_service.util.DateUtil;
import com.bailiwick.game_servicei.model.ReqPacksDetails;
import com.bailiwick.game_servicei.model.ResPacksDetails;

@Service
public class PacksService {

	private Logger logger = (Logger) LogManager.getLogger(getClass());

	@Autowired
	DaoPacksService daoService;
	
	@Autowired
	DateUtil dateUtil;

	public List<ResPacksDetails> getPacks(ReqPacksDetails reqPacksDetails) {
		String currentDate = dateUtil.currentDate().toString();
		
		logger.debug("currentDate : " + currentDate);
		
		try {
			if(reqPacksDetails.getGameId()== null) {
				return daoService.getPacks(currentDate);
			}else {
				return daoService.getPacks(currentDate, reqPacksDetails);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<>();
		}
	}

}
