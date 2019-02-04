package com.bailiwick.game_service.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bailiwick.game_service.interfaces.GameInterface;
import com.bailiwick.game_service.jdbc.DaoGameService;
import com.bailiwick.game_servicei.model.AnswerData;
import com.bailiwick.game_servicei.model.Authentication;
import com.bailiwick.game_servicei.model.PlayerDataDetail;
import com.bailiwick.game_servicei.model.PlayerDetail;
import com.bailiwick.game_servicei.model.PriceRequest;
import com.bailiwick.game_servicei.model.SaveAnswerDetail;
import com.bailiwick.game_servicei.model.UserLoginRequest;
import com.bailiwick.game_servicei.model.WinnerResponse;
import com.google.gson.Gson;

@Service
public class GameService implements GameInterface {

	private Logger logger = (Logger) LogManager.getLogger(getClass());

	@Autowired
	DaoGameService daoService;


	@Override
	public Integer getPrice(PriceRequest priceRequest) {
		Integer numberOfPlayer = daoService.getNumberOfPlayer(priceRequest);
		System.out.println("number of player === >" + numberOfPlayer  );
	
		
		
		
		Integer result = (priceRequest.getEntryFees()*numberOfPlayer) - ((priceRequest.getEntryFees()*numberOfPlayer *priceRequest.getMarginePrice())/100);
		
		
		
		System.out.println("result "+ result);
		//Integer result = ((priceRequest.getEntryFees()*(9*numberOfPlayer/100))*numberOfPlayer);
		
		return result;
	}


	public Integer getCorrectAnswerCount(PlayerDetail playerDetail) {
		Integer score = daoService.getCorrectAnswerCount(playerDetail);
		return score;
	}


	public WinnerResponse getwinnerDetail(PlayerDetail playerDetail) {
		WinnerResponse winnerResponse = new WinnerResponse();
		
		winnerResponse = daoService.getwinnerDetail(playerDetail);
	return winnerResponse;
	}


	public Object saveAnswer(SaveAnswerDetail saveAnswerDetail) {
		Object returns = null;

		try {
		Authentication auth = new Authentication();
        auth.setUserId("1");
        auth.setPlatformId("plat01");
        saveAnswerDetail.setAuthentication(auth);
        System.out.println((new StringBuilder()).append("save Answer Request ======>>>>>>>>>>>").append(saveAnswerDetail).toString());
        RestTemplate rt = new RestTemplate();
        rt.getMessageConverters().add(new StringHttpMessageConverter());
        String uri = "http://13.232.118.226:8091/cms/question/saveAnswer";
        returns = rt.postForObject(uri, saveAnswerDetail, Object.class, new Object[0]);
        Gson gson = new Gson();
        AnswerData fromJson = (AnswerData)gson.fromJson(returns.toString(), AnswerData.class);
        boolean isOptionCorrect = fromJson.isOptionCorrect();
        System.out.println((new StringBuilder()).append("isOptionCorrect value==>").append(isOptionCorrect).toString());
        if(isOptionCorrect)
        	daoService.increseCorrectAnswerCount(saveAnswerDetail);
        else {
        	daoService.increseCorrectAnswerCount(saveAnswerDetail);
        System.out.println(returns);
    }
	}
    catch(Exception exception) { }
    return returns;
}


	public UserLoginRequest verifyOtp(UserLoginRequest userLoginRequest) {
		UserLoginRequest userInfo = new UserLoginRequest();
	
		userInfo = 	daoService.verifyOtp(userLoginRequest);
		
		
		// TODO Auto-generated method stub
		return userInfo;
	}


	public List<PlayerDataDetail> getPlayersDetail(PlayerDetail playerDetail) {
		List<PlayerDataDetail> lstPlayerDataDetail = new ArrayList<>(); 
		lstPlayerDataDetail = daoService.getPlayersDetail(playerDetail);
		
		return lstPlayerDataDetail;
	}


	public String claimPrize(PlayerDetail playerDetail) {
		String result = daoService.claimPrize(playerDetail);
		return result;
	}
		
		


	
}
