package com.bailiwick.game_service.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

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
import com.bailiwick.game_servicei.model.PaytmRequest;
import com.bailiwick.game_servicei.model.PlayerDataDetail;
import com.bailiwick.game_servicei.model.PlayerDetail;
import com.bailiwick.game_servicei.model.PriceRequest;
import com.bailiwick.game_servicei.model.SaveAnswerDetail;
import com.bailiwick.game_servicei.model.UserLoginRequest;
import com.bailiwick.game_servicei.model.WinnerResponse;
import com.google.gson.Gson;
import com.paytm.pg.merchant.CheckSumServiceHelper;




@Service
public class GameService implements GameInterface {
	//com.paytm.merchant.CheckSumServiceHelper checkSumServiceHelper = com.paytm.merchant.CheckSumServiceHelper.getCheckSumServiceHelper();

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
        	daoService.increseWrongAnswerCount(saveAnswerDetail);
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


	public StringBuilder paytmGateway(PaytmRequest paytmRequest) {
		Random rand = new Random();
        Integer n = Integer.valueOf(rand.nextInt(50) + 1);
        String s = n.toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        LocalDateTime now = LocalDateTime.now();
        
        StringBuilder ordrrKey = new StringBuilder((new StringBuilder()).append(s).append(dtf.format(now)).toString());
        paytmRequest.setOrderId(ordrrKey.toString());
        String orderId = ordrrKey.toString();
        String channelId = "WEB";
        String custId = String.valueOf(paytmRequest.getUserId());
        String mobileNo = "8878350558";
        String txnAmount = paytmRequest.getAmount();
        String website = "WEBSTAGING";
        String industryTypeId = "Retail";
        String callbackUrl = "http://13.233.39.58:8092/cms/game//processTransaction/";
        TreeMap paytmParams = new TreeMap();
        paytmParams.put("MID", "MINDHU40541790566351");
        paytmParams.put("ORDER_ID", orderId);
        paytmParams.put("CUST_ID", custId);
        paytmParams.put("INDUSTRY_TYPE_ID", industryTypeId);
        paytmParams.put("CHANNEL_ID", channelId);
        paytmParams.put("MOBILE_NO", mobileNo);
        paytmParams.put("TXN_AMOUNT", txnAmount);
        paytmParams.put("WEBSITE", website);
        paytmParams.put("CALLBACK_URL", callbackUrl);
        String paytmChecksum = "";
        try
        {
        	System.out.println("inside checksum calculation ");
           paytmChecksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum("YCZEJxIB%FRv6pHb", paytmParams);
        }
        catch(Exception exception) { }
        System.out.println((new StringBuilder()).append("checkSum==> ").append(paytmChecksum).toString());
        StringBuilder outputHtml = new StringBuilder();
        outputHtml.append("<!DOCTYPE html PUBLIC -//W3C//DTD HTML 4.01 Transitional//EN http://www.w3.org/TR/html4/loose.dtd>");
        outputHtml.append("<html>");
        outputHtml.append("<head>");
        outputHtml.append("<title>Merchant Checkout Page</title>");
        outputHtml.append("</head>");
        outputHtml.append("<body>");
        outputHtml.append("<center><h1>Please do not refresh this page...</h1></center>");
        String transactionURL = "https://securegw-stage.paytm.in/theia/processTransaction";
        outputHtml.append((new StringBuilder()).append("<form method=post action=").append(transactionURL).append(" name=f1>").toString());
        java.util.Map.Entry entry;
        for(Iterator iterator = paytmParams.entrySet().iterator(); iterator.hasNext(); outputHtml.append((new StringBuilder()).append("<input type=hidden name=").append((String)entry.getKey()).append(" value=").append((String)entry.getValue()).append(">").toString()))
            entry = (java.util.Map.Entry)iterator.next();

        outputHtml.append((new StringBuilder()).append("<input type=hidden name=CHECKSUMHASH value=").append(paytmChecksum).append(">").toString());
        outputHtml.append("</form>");
        outputHtml.append("<script type=text/javascript>");
        outputHtml.append("document.f1.submit();");
        outputHtml.append("</script>");
        outputHtml.append("</body>");
        outputHtml.append("</html>");
        System.out.println(outputHtml);
        daoService.savetransactionDetail(paytmRequest);
        return outputHtml;

	}


	public void updateTransactionDetail(Map<String, String> paytmResponse) {
		daoService.updateTransactionDetail(paytmResponse);
		
	}


	
		
		


	
}
