package com.bailiwick.game_service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.bailiwick.game_service.service.GameService;
import com.bailiwick.game_servicei.model.GenericResponse;
import com.bailiwick.game_servicei.model.PaytmRequest;
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

	@Value("${paytm.redirect}")
	String paytmRedirectUrl;
	
	
	@Autowired
	GameService gameInterface;

	@CrossOrigin
	@PostMapping("/getPrice")
	public GenericResponse getPrice(@RequestBody PriceRequest priceRequest) {
		GenericResponse gr = new GenericResponse();

		try {
			logger.info("REQUEST :  \"/getPrice\" " + priceRequest);

			Integer result = gameInterface.getPrice(priceRequest);
			if (result != null) {
				gr.setStatus("successful");
				gr.setStatusMessage("data Retrive Successfully");
				gr.setStatusCode("0");
				gr.setData(result);
				logger.info("RESPONSE : " + result);
				return gr;

			} else {
				gr.setStatus("Fail");
				gr.setStatusMessage("No Data To Retrive");
				gr.setStatusCode("1");
				gr.setData(result);
			}
		} catch (Exception e) {
			logger.error("error in getPrice ", e.getMessage());
			gr.setStatus("Error");
			gr.setStatusMessage("Application Server Error");
			gr.setStatusCode("-1");
		}
		return gr;

	}

	@CrossOrigin
	@PostMapping("/getCorrectAnswerCount")
	public GenericResponse getCorrectAnswerCount(@RequestBody PlayerDetail playerDetail) {
		GenericResponse gr = new GenericResponse();

		try {
			logger.info("REQUEST :  \"/getCorrectAnswerCount\" " + playerDetail);

			Integer result = gameInterface.getCorrectAnswerCount(playerDetail);
			if (result != null) {
				gr.setStatus("successful");
				gr.setStatusMessage("data Retrive Successfully");
				gr.setStatusCode("0");
				gr.setData(result);
				logger.info("RESPONSE : " + result);

			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("error in getPrice ", e.getMessage());
			gr.setStatus("Error");
			gr.setStatusMessage("Application Server Error");
			gr.setStatusCode("-1");

		}
		return gr;
	}

	@CrossOrigin
	@PostMapping("/getwinnerDetail")
	public GenericResponse getwinnerDetail(@RequestBody PlayerDetail playerDetail) {
		GenericResponse gr = new GenericResponse();
		try {
			logger.info("REQUEST :  \"/getwinnerDetail\" " + playerDetail);

			WinnerResponse innerResponse = gameInterface.getwinnerDetail(playerDetail);
			if (innerResponse != null) {
				gr.setStatus("successful");
				gr.setStatusMessage("data Retrive Successfully");
				gr.setStatusCode("0");
				gr.setData(innerResponse);
				logger.info("RESPONSE : " + innerResponse);
				return gr;

			} else {
				gr.setStatus("Fail");
				gr.setStatusMessage("No Data To Retrive");
				gr.setStatusCode("1");
				// gr.setData(result);
				return gr;
			}
		} catch (Exception e) {
			logger.error("error in getPrice ", e.getMessage());
			gr.setStatus("Error");
			gr.setStatusMessage("Application Server Error");
			gr.setStatusCode("-1");

		}

		return gr;

	}

	@CrossOrigin
	@PostMapping("/saveAnswer")
	public GenericResponse saveAnswer(@RequestBody SaveAnswerDetail saveAnswerDetail) {
		GenericResponse gr = new GenericResponse();
		logger.info("REQUEST :  \"/saveAnswer\" " + saveAnswerDetail);
		try {
			Object result = gameInterface.saveAnswer(saveAnswerDetail);
			if (result != null) {
				gr.setStatus("successful");
				gr.setStatusMessage("data Retrive Successfully");
				gr.setStatusCode("0");
				gr.setData(result);
				logger.info("RESPONSE : " + result);
				return gr;

			} else {
				gr.setStatus("Fail");
				gr.setStatusMessage("No Data To Retrive");
				gr.setStatusCode("1");
				// gr.setData(result);
				return gr;
			}
		} catch (Exception e) {
			logger.error("error in getPrice ", e.getMessage());
			gr.setStatus("Error");
			gr.setStatusMessage("Application Server Error");
			gr.setStatusCode("-1");

		}
		return gr;

	}

	@CrossOrigin
	@PostMapping("/verifyOtp")
	public GenericResponse verifyOtp(@RequestBody UserLoginRequest userLoginRequest) {
		GenericResponse gr = new GenericResponse();
		try {
			logger.info("REQUEST :  \"/verifyOtp\" " + userLoginRequest);
			UserLoginRequest userInfo = new UserLoginRequest();

			userInfo = gameInterface.verifyOtp(userLoginRequest);
			if (userInfo.getUserId() != 0) {
				gr.setStatus("successful");
				gr.setStatusMessage("data Retrive Successfully");
				gr.setStatusCode("0");
				gr.setData(userInfo.getUserId());
				logger.info("RESPONSE : " + userInfo.getUserId());
				return gr;

			} else {
				gr.setStatus("Fail");
				gr.setStatusMessage("Otp not matched");
				gr.setStatusCode("1");
				// gr.setData(result);
				return gr;
			}
		} catch (Exception e) {
			logger.error("error in getPrice ", e.getMessage());
			e.printStackTrace();
			gr.setStatus("Error");
			gr.setStatusMessage("Application Server Error");
			gr.setStatusCode("-1");

		}
		return gr;

	}

	@CrossOrigin
	@PostMapping("/getPlayersDetail")
	public GenericResponse getPlayersDetail(@RequestBody PlayerDetail playerDetail) {
		GenericResponse gr = new GenericResponse();
		try {
			logger.info("REQUEST :  \"/getPlayersDetail\" " + playerDetail);
			List<PlayerDataDetail> lstPlayerDataDetail = new ArrayList<>();

			lstPlayerDataDetail = gameInterface.getPlayersDetail(playerDetail);
			if (lstPlayerDataDetail != null) {
				gr.setStatus("successful");
				gr.setStatusMessage("data Retrive Successfully");
				gr.setStatusCode("0");
				gr.setData(lstPlayerDataDetail);
				logger.info("RESPONSE : " + lstPlayerDataDetail);
				return gr;

			} else {
				gr.setStatus("Fail");
				gr.setStatusMessage("No data Retrive");
				gr.setStatusCode("1");
				// gr.setData(result);
				return gr;
			}
		} catch (Exception e) {
			logger.error("error in getPrice ", e.getMessage());
			gr.setStatus("Error");
			gr.setStatusMessage("Application Server Error");
			gr.setStatusCode("-1");

		}
		return gr;

	}

	@CrossOrigin
	@PostMapping("/claimPrize")
	public GenericResponse claimPrize(@RequestBody PlayerDetail playerDetail) {
		GenericResponse gr = new GenericResponse();
		try {
			logger.info("REQUEST :  \"/claimPrize\" " + playerDetail);
			// List<PlayerDataDetail> lstPlayerDataDetail = new ArrayList<>();

			String result = gameInterface.claimPrize(playerDetail);

			if (result != null) {
				gr.setStatus("successful");
				gr.setStatusMessage("data Retrive Successfully");
				gr.setStatusCode("0");
				// gr.setData(lstPlayerDataDetail);
				// logger.info("RESPONSE : " + lstPlayerDataDetail);
				return gr;

			} else {
				gr.setStatus("Fail");
				gr.setStatusMessage("Operation fail ");
				gr.setStatusCode("1");
				// gr.setData(result);
				return gr;
			}
		} catch (Exception e) {
			logger.error("error in getPrice ", e.getMessage());
			gr.setStatus("Error");
			gr.setStatusMessage("Application Server Error");
			gr.setStatusCode("-1");

		}
		return gr;

	}

	@CrossOrigin
	@RequestMapping(value = "/paytmGateway/{amount}/{userId}/{packId}/{instanseId}/{gameId}", method = RequestMethod.GET, produces = {
			"text/html" })
	public String paytmGateway(@PathVariable String amount, @PathVariable int userId, @PathVariable int packId,
			@PathVariable int instanseId, @PathVariable int gameId) {

		logger.info("REQUEST :  \"/paytmGateway amount \" " + amount + "userId" + userId);
		// List<PlayerDataDetail> lstPlayerDataDetail = new ArrayList<>();
		PaytmRequest paytmRequest = new PaytmRequest();
		paytmRequest.setAmount(amount);
		paytmRequest.setGameId(gameId);
		paytmRequest.setInstanseId(instanseId);
		paytmRequest.setPackId(packId);
		paytmRequest.setUserId(userId);
		;
		StringBuilder res = gameInterface.paytmGateway(paytmRequest);
		return res.toString();
	}

	@RequestMapping(value = "/processTransaction", method = RequestMethod.POST, produces = { "text/html" })
	public RedirectView processTransaction(@RequestBody String text) {
		Map<String, String> paytmResponse = new HashMap<>();
		System.out.println("paytm response body :- " + text);
		String[] value = text.split("&");
		for (String newValue : value) {
			System.out.println("new Value " + newValue);
			String[] keyValue = newValue.split("=");
			paytmResponse.put(keyValue[0], keyValue[1]);
		}
		try {
			System.out.println("map ==>>" + paytmResponse);
			updateTransaction(paytmResponse);
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(paytmRedirectUrl);
			return redirectView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Async
	void updateTransaction(Map<String, String> paytmResponse) {
		gameInterface.updateTransactionDetail(paytmResponse);

	}

	@CrossOrigin
	@PostMapping("/claimPrizeForQuickWin")
	public GenericResponse claimPrizeForQuickWin(@RequestBody PlayerDetail playerDetail) {
		GenericResponse gr = new GenericResponse();
		try {
			logger.info("REQUEST :  \"/claimPrizeForQuickWin\" " + playerDetail);
			// List<PlayerDataDetail> lstPlayerDataDetail = new ArrayList<>();

			String result = gameInterface.claimPrizeForQuickWin(playerDetail);

			if (result != null) {
				gr.setStatus("successful");
				gr.setStatusMessage("data Retrive Successfully");
				gr.setStatusCode("0");
				// gr.setData(lstPlayerDataDetail);
				// logger.info("RESPONSE : " + lstPlayerDataDetail);
				return gr;

			} else {
				gr.setStatus("Fail");
				gr.setStatusMessage("Operation fail ");
				gr.setStatusCode("1");
				// gr.setData(result);
				return gr;
			}
		} catch (Exception e) {
			logger.error("error in getPrice ", e.getMessage());
			gr.setStatus("Error");
			gr.setStatusMessage("Application Server Error");
			gr.setStatusCode("-1");

		}
		return gr;

	}

	@CrossOrigin
	@PostMapping("/claimPrizeForPoolPrize")
	public GenericResponse claimPrizeForPoolPrize(@RequestBody PlayerDetail playerDetail) {
		GenericResponse gr = new GenericResponse();
		try {
			logger.info("REQUEST :  \"/claimPrizeForQuickWin\" " + playerDetail);
			// List<PlayerDataDetail> lstPlayerDataDetail = new ArrayList<>();

			String result = gameInterface.claimPrizeForPoolPrize(playerDetail);

			if (result != null) {
				gr.setStatus("successful");
				gr.setStatusMessage("data Retrive Successfully");
				gr.setStatusCode("0");
				// gr.setData(lstPlayerDataDetail);
				// logger.info("RESPONSE : " + lstPlayerDataDetail);
				return gr;

			} else {
				gr.setStatus("Fail");
				gr.setStatusMessage("Operation fail ");
				gr.setStatusCode("1");
				// gr.setData(result);
				return gr;
			}
		} catch (Exception e) {
			logger.error("error in getPrice ", e.getMessage());
			gr.setStatus("Error");
			gr.setStatusMessage("Application Server Error");
			gr.setStatusCode("-1");

		}
		return gr;

	}

	@CrossOrigin
	@PostMapping("/paytmRefund")
	public GenericResponse paytmRefund(@RequestBody PlayerDetail playerDetail) {
		GenericResponse gr = new GenericResponse();
		try {
			logger.info("REQUEST :  \"/claimPrizeForQuickWin\" " + playerDetail);
			// List<PlayerDataDetail> lstPlayerDataDetail = new ArrayList<>();

			String result = gameInterface.paytmRefund(playerDetail);

			if (result != null) {
				gr.setStatus("successful");
				gr.setStatusMessage("data Retrive Successfully");
				gr.setStatusCode("0");
				// gr.setData(lstPlayerDataDetail);
				// logger.info("RESPONSE : " + lstPlayerDataDetail);
				return gr;

			} else {
				gr.setStatus("Fail");
				gr.setStatusMessage("Operation fail ");
				gr.setStatusCode("1");
				// gr.setData(result);
				return gr;
			}
		} catch (Exception e) {
			logger.error("error in getPrice ", e.getMessage());
			gr.setStatus("Error");
			gr.setStatusMessage("Application Server Error");
			gr.setStatusCode("-1");

		}
		return gr;

	}

	@CrossOrigin
	@PostMapping("/question")
	public GenericResponse paytmGratification(@RequestBody PlayerDetail playerDetail) {
		GenericResponse gr = new GenericResponse();
		try {
			logger.info("REQUEST :  \"/question\" " + playerDetail);
			// List<PlayerDataDetail> lstPlayerDataDetail = new ArrayList<>();

			Object result = gameInterface.getQuestion(playerDetail);

			if (result != null) {
				gr.setStatus("successful");
				gr.setStatusMessage("data Retrive Successfully");
				gr.setStatusCode("0");
				 gr.setData(result);
				// logger.info("RESPONSE : " + lstPlayerDataDetail);
				return gr;

			} else {
				gr.setStatus("Fail");
				gr.setStatusMessage("Operation fail ");
				gr.setStatusCode("1");
				// gr.setData(result);
				return gr;
			}
		}

		catch (Exception e) {
			logger.error("error in getPrice ", e.getMessage());
			gr.setStatus("Error");
			gr.setStatusMessage("Application Server Error");
			gr.setStatusCode("-1");

		}
		return gr;

	}

}