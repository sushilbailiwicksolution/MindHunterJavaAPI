package com.bailiwick.game_service.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bailiwick.game_service.interfaces.GameInterface;
import com.bailiwick.game_service.jdbc.DaoGameService;
import com.bailiwick.game_servicei.model.AnswerData;
import com.bailiwick.game_servicei.model.Authentication;
import com.bailiwick.game_servicei.model.GameInstanceResponse;
import com.bailiwick.game_servicei.model.GameInstanceUsers;
import com.bailiwick.game_servicei.model.GamePlayRequest;
import com.bailiwick.game_servicei.model.PaytmRequest;
import com.bailiwick.game_servicei.model.PlayerDataDetail;
import com.bailiwick.game_servicei.model.PlayerDetail;
import com.bailiwick.game_servicei.model.PriceRequest;
import com.bailiwick.game_servicei.model.SaveAnswerDetail;
import com.bailiwick.game_servicei.model.SupportQueryDetails;
import com.bailiwick.game_servicei.model.UserLoginRequest;
import com.bailiwick.game_servicei.model.WinnerResponse;
import com.google.gson.Gson;
import com.paytm.pg.merchant.CheckSumServiceHelper;




@Service
public class GameService implements GameInterface {
	//com.paytm.merchant.CheckSumServiceHelper checkSumServiceHelper = com.paytm.merchant.CheckSumServiceHelper.getCheckSumServiceHelper();
	@Value("${paytm.callback}")
	String paytmCallback;
	@Value("${wrapper.service}")
	String wrapperServiceUrl;
	
	
	
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
        String uri = wrapperServiceUrl+"/cms/question/saveAnswer";
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
if (playerDetail.getGameId()==5) {
	String result = daoService.claimPrizeForQuickWin(playerDetail);	
	return result;		
}

else if (playerDetail.getGameId()==3) {
			String result = daoService.endMegaSharkOperation(playerDetail);	
			return result;		
}
		
		
		else {
			String result = daoService.claimPrize(playerDetail);
			return result;
		}
		//return result;
	}


	public StringBuilder paytmGateway(PaytmRequest paytmRequest) {
		try {
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
        String callbackUrl = paytmCallback;
        System.out.println("callback Url ==>" + callbackUrl);
    	
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
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}


	public void updateTransactionDetail(Map<String, String> paytmResponse) {
		daoService.updateTransactionDetail(paytmResponse);
		
	}


	public String claimPrizeForQuickWin(PlayerDetail playerDetail) {
		String result = daoService.claimPrizeForQuickWin(playerDetail);
		return result;
	}


	public String claimPrizeForPoolPrize(PlayerDetail playerDetail) {
		String result = daoService.claimPrizeForPoolPrize(playerDetail);
		return result;
	}


	public String paytmRefund(PlayerDetail playerDetail) {
	
		String transactionURL = "https://securegw-stage.paytm.in/refund/HANDLER_INTERNAL/REFUND";
		String merchantMid = "MINDHU40541790566351";
		String orderId = "order1";
		String merchantKey = "YCZEJxIB%FRv6pHb";
		String transactionType = "REFUND";
		String refundAmount = "18";
		String transactionId = "20190205111212800110168397700221125";
		String refId = "reforder1";
		String comment = "comment string";
		TreeMap<String, String> paytmParams = new TreeMap<String, String>();
		paytmParams.put("MID", merchantMid);
		paytmParams.put("REFID", refId);
		paytmParams.put("TXNID", transactionId);
		paytmParams.put("ORDERID", orderId);
		paytmParams.put("REFUNDAMOUNT", refundAmount);
		paytmParams.put("TXNTYPE", transactionType);
		paytmParams.put("COMMENTS", comment);
		try {
		    String paytmChecksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(merchantKey, paytmParams);
		    paytmParams.put("CHECKSUM", paytmChecksum);
		    JSONObject obj = new JSONObject(paytmParams);
		    String postData = "JsonData=" + obj.toString();
            //URLConnection connection = transactionURL.openConnection();
		    URL url;
		    url = new URL(transactionURL);
		    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("contentType", "application/json");
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
		    requestWriter.writeBytes( postData);
		    requestWriter.close();
		    String responseData = "";
		    InputStream is = connection.getInputStream();
		    BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
		    if((responseData = responseReader.readLine()) != null) {
		        System.out.append("Response Json = " + responseData);
		    }
		    System.out.append("Requested Json = " + postData + " ");
		    responseReader.close();
		    return responseData;
		} catch (Exception exception) {
		    exception.printStackTrace();
		}
		
		
		
		return null;
	}


	public String paytmGratification(PlayerDetail playerDetail) {
		// TODO Auto-generated method stub
		return null;
	}


	public Object getQuestion(PlayerDetail playerDetail) {
		Object returnDta = null; 
		RestTemplate rt = new RestTemplate();
		if(playerDetail.getGameId() == 1) {
			playerDetail.setGameId(-1);
		}
		
	        rt.getMessageConverters().add(new StringHttpMessageConverter());
	        System.out.println("wrapperServiceUrl==>" + wrapperServiceUrl);
	        String uri = wrapperServiceUrl+"/cms/userHistory/question";
	        System.out.println("request url ===>"+uri);
	        returnDta = rt.postForObject(uri, playerDetail, Object.class, new Object[0]);		
	        return returnDta;
	
	}


	public void updateimage() {
		daoService.updateimage();
		
	}


	public String addSupportQuery(SupportQueryDetails supportQueryDetails) {
		String result = daoService.addSupportQuery(supportQueryDetails);
		return result;
	}


	public Object getGameList() {
		RestTemplate rt = new RestTemplate();
	    
	    rt.getMessageConverters().add(new StringHttpMessageConverter());
	    String uri = wrapperServiceUrl+"/cms/game/getGames";
	    Object returns = null;
	    Authentication authentication = new Authentication();
	    authentication.setPlatformId("plat01");
	    authentication.setUserId("1");
	    returns = rt.postForObject(uri, authentication, Object.class, new Object[0]);
	    System.out.println(returns);
	    return returns;	
	   }


	public Object getStopWheelPoint(GamePlayRequest gamePlayRequest) {
		RestTemplate rt = new RestTemplate();
	    rt.getMessageConverters().add(new StringHttpMessageConverter());
	    String uri = wrapperServiceUrl+"/cms/game/spin/result";
	    Object returns = null;
	    GamePlayRequest gpr = new GamePlayRequest();
	    gpr.setGameId("2");
	    Authentication authentication = new Authentication();
	    authentication.setPlatformId("plat01");
	    authentication.setUserId("1");
	    gpr.setAuthentication(authentication);
	    returns = rt.postForObject(uri, gpr, Object.class, new Object[0]);
	    return returns;
	  
	}


	public Object getQuestionForOther(GamePlayRequest gamePlayRequest) {
		Object returns = null;
	    //List<QuestionDetail> lstquestiondetail = new ArrayList();
	    //QuestionDetail questionDetail = new QuestionDetail();
	    Authentication authentication = new Authentication();
	    authentication.setUserId("1");
	    authentication.setPlatformId("plat1");
	    gamePlayRequest.setAuthentication(authentication);
	    RestTemplate rt = new RestTemplate();
	    
	    rt.getMessageConverters().add(new StringHttpMessageConverter());
	    String uri = wrapperServiceUrl+"/cms/question/nextQuestion";
	    
	    returns = rt.postForObject(uri, gamePlayRequest, Object.class, new Object[0]);
	    System.out.println(returns);
	    return returns;
	}


	public Object getPackList(GamePlayRequest gamePlayRequest) {
		 RestTemplate rt = new RestTemplate();
		    
		    rt.getMessageConverters().add(new StringHttpMessageConverter());
		    String uri = wrapperServiceUrl+"/cms/packs/getPacks";
		    Object returns = null;
		    Authentication authentication = new Authentication();
		    authentication.setPlatformId("plat01");
		    authentication.setUserId("1");
		    gamePlayRequest.setAuthentication(authentication);
		    returns = rt.postForObject(uri, gamePlayRequest, Object.class, new Object[0]);
		    System.out.println(returns);
		    if (gamePlayRequest.getGameId().equalsIgnoreCase("1")) {
		      Object newResult = daoService.getnewPackListData(returns);
		      return newResult;
		   
	}
			return returns;


	}


	public Object getActivePlayerDetail(GamePlayRequest gamePlayRequest) {
		 Object result = daoService.getActivePlayerDetail(gamePlayRequest); 
		return result;
	}


	public Object saveActivePlayerDetail(GamePlayRequest gpr) {
		  Object result = daoService.saveActivePlayerDetail(gpr);
		    return result;
	}


	public Object getTier(GamePlayRequest gpr) {
		Authentication auth = new Authentication();
	    auth.setUserId("1");
	    auth.setPlatformId("plat01");
	    gpr.setAuthentication(auth);
	    RestTemplate rt = new RestTemplate();
	    rt.getMessageConverters().add(new StringHttpMessageConverter());
	    
	    String uri = wrapperServiceUrl+"/cms/game/getTiers";
	    
	    Object returns = rt.postForObject(uri, gpr, Object.class, new Object[0]);
	    
	    return returns;
	}


	

	public int userLeft(int userId, int instanceId) {
		int update = daoService.userLeft(userId, instanceId);
	    return update;
	}


	public int updateInstanceStatus(int instanceId) {
	    int updateInstanceStatus = daoService.updateInstanceStatus(instanceId);
	    return updateInstanceStatus;
	}


	public GameInstanceResponse getInstance(int userId, int gameId, int packId) {
		GameInstanceResponse res = new GameInstanceResponse();
	    res = daoService.getInstance(userId, gameId, packId);
	    
	    return res;
	}


	public int getDailyWinners(GameInstanceUsers req) {
		// TODO Auto-generated method stub
		return 0;
	}



		
		


	
}
