package com.bailiwick.game_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bailiwick.game_service.service.GameService;
import com.bailiwick.game_servicei.model.GenericResponse;
import com.bailiwick.game_servicei.model.PlayerDataDetail;
import com.bailiwick.game_servicei.model.PlayerDetail;
import com.bailiwick.game_servicei.model.PriceRequest;
import com.bailiwick.game_servicei.model.SaveAnswerDetail;
import com.bailiwick.game_servicei.model.UserLoginRequest;
import com.bailiwick.game_servicei.model.WinnerResponse;


@RestController
@RequestMapping("/cms/game")
public class GameController {

	private Logger logger = (Logger) LogManager.getLogger(getClass());
	
	@Autowired
	GameService gameInterface;
@CrossOrigin	
	@PostMapping("/getPrice")
	public GenericResponse getPrice(@RequestBody PriceRequest priceRequest) {
		GenericResponse gr = new GenericResponse();
		logger.info("REQUEST :  \"/getPrice\" " + priceRequest);
		
		Integer result = gameInterface.getPrice(priceRequest);
		if(result != null) {
			gr.setStatus("successful");
			gr.setStatusMessage("data Retrive Successfully");
			gr.setStatusCode("0");
			gr.setData(result);
			logger.info("RESPONSE : " + result);
			return gr;
			
			}
		else
		{
			gr.setStatus("Fail");
			gr.setStatusMessage("No Data To Retrive");
			gr.setStatusCode("1");
			gr.setData(result);
			return gr;
		}
			
			
	}
	
@CrossOrigin	
@PostMapping("/getCorrectAnswerCount")
public GenericResponse getCorrectAnswerCount(@RequestBody PlayerDetail playerDetail) {
	GenericResponse gr = new GenericResponse();
	logger.info("REQUEST :  \"/getCorrectAnswerCount\" " + playerDetail);
	
	Integer result = gameInterface.getCorrectAnswerCount(playerDetail);
	if(result != null) {
		gr.setStatus("successful");
		gr.setStatusMessage("data Retrive Successfully");
		gr.setStatusCode("0");
		gr.setData(result);
		logger.info("RESPONSE : " + result);
		return gr;
		
		}
	else {
		return null;
}

}
@CrossOrigin	
@PostMapping("/getwinnerDetail")
public GenericResponse getwinnerDetail(@RequestBody PlayerDetail playerDetail) {
	GenericResponse gr = new GenericResponse();
	logger.info("REQUEST :  \"/getwinnerDetail\" " + playerDetail);
	
	WinnerResponse innerResponse = gameInterface.getwinnerDetail(playerDetail);
	if(innerResponse != null) {
		gr.setStatus("successful");
		gr.setStatusMessage("data Retrive Successfully");
		gr.setStatusCode("0");
		gr.setData(innerResponse);
		logger.info("RESPONSE : " + innerResponse);
		return gr;
		
		}
	else
	{
		gr.setStatus("Fail");
		gr.setStatusMessage("No Data To Retrive");
		gr.setStatusCode("1");
		//gr.setData(result);
		return gr;
	}
		
		
}
@CrossOrigin	
@PostMapping("/saveAnswer")
public GenericResponse saveAnswer(@RequestBody SaveAnswerDetail saveAnswerDetail) {
	GenericResponse gr = new GenericResponse();
	logger.info("REQUEST :  \"/saveAnswer\" " + saveAnswerDetail);
	
	Object result = gameInterface.saveAnswer(saveAnswerDetail);
	if(result != null) {
		gr.setStatus("successful");
		gr.setStatusMessage("data Retrive Successfully");
		gr.setStatusCode("0");
		gr.setData(result);
		logger.info("RESPONSE : " + result);
		return gr;
		
		}
	else
	{
		gr.setStatus("Fail");
		gr.setStatusMessage("No Data To Retrive");
		gr.setStatusCode("1");
		//gr.setData(result);
		return gr;
	}
		
		
}


@CrossOrigin	
@PostMapping("/verifyOtp")
public GenericResponse verifyOtp(@RequestBody UserLoginRequest userLoginRequest) {
	GenericResponse gr = new GenericResponse();
	logger.info("REQUEST :  \"/verifyOtp\" " + userLoginRequest);
	 UserLoginRequest userInfo = new UserLoginRequest();
     
	 userInfo = gameInterface.verifyOtp(userLoginRequest);
	if(userInfo.getUserId() != 0) {
		gr.setStatus("successful");
		gr.setStatusMessage("data Retrive Successfully");
		gr.setStatusCode("0");
		gr.setData(userInfo.getUserId());
		logger.info("RESPONSE : " + userInfo.getUserId());
		return gr;
		
		}
	else
	{
		gr.setStatus("Fail");
		gr.setStatusMessage("Otp not matched");
		gr.setStatusCode("1");
		//gr.setData(result);
		return gr;
	}
}	
		

@CrossOrigin	
@PostMapping("/getPlayersDetail")
public GenericResponse getPlayersDetail(@RequestBody PlayerDetail playerDetail){
	GenericResponse gr = new GenericResponse();
	logger.info("REQUEST :  \"/getPlayersDetail\" " + playerDetail);
	List<PlayerDataDetail> lstPlayerDataDetail = new ArrayList<>(); 
     
	lstPlayerDataDetail = gameInterface.getPlayersDetail(playerDetail);
	if(lstPlayerDataDetail != null) {
		gr.setStatus("successful");
		gr.setStatusMessage("data Retrive Successfully");
		gr.setStatusCode("0");
		gr.setData(lstPlayerDataDetail);
		logger.info("RESPONSE : " + lstPlayerDataDetail);
		return gr;
		
		}
	else
	{
		gr.setStatus("Fail");
		gr.setStatusMessage("No data Retrive");
		gr.setStatusCode("1");
		//gr.setData(result);
		return gr;
	}
}	




@CrossOrigin	
@PostMapping("/claimPrize")
public GenericResponse claimPrize(@RequestBody PlayerDetail playerDetail){
	GenericResponse gr = new GenericResponse();
	logger.info("REQUEST :  \"/claimPrize\" " + playerDetail);
	//List<PlayerDataDetail> lstPlayerDataDetail = new ArrayList<>(); 
     
	String result  = gameInterface.claimPrize(playerDetail);
	if(result != null) {
		gr.setStatus("successful");
		gr.setStatusMessage("data Retrive Successfully");
		gr.setStatusCode("0");
	//	gr.setData(lstPlayerDataDetail);
		//logger.info("RESPONSE : " + lstPlayerDataDetail);
		return gr;
		
		}
	else
	{
		gr.setStatus("Fail");
		gr.setStatusMessage("Operation fail ");
		gr.setStatusCode("1");
		//gr.setData(result);
		return gr;
	}
}	

}
