package com.olam.score.common.util;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author Mohit_Agrawal08
 *
 */
public class ErrorMessageUtil {

	/**
	 * @param objs. Values of the attribute
	 * @param errorBeans. Object of errorbeans defined in the for custom exception(List<ScoreBaseExceptionResponse>)
	 * @param errorCode as defined in the properties file
	 * @param errorType
	 */
	public static void errorHandler(Object[] objs, List<ScoreBaseExceptionResponse> errorBeans, String errorCode,
			String errorType) {
		ScoreBaseExceptionResponse response = new ScoreBaseExceptionResponse();
		String errorMessage = CommonUtil.getTranslationMessage(Locale.getDefault(), errorCode);
		response.setErrorCodes(errorCode);
		response.setErrorMessage(new MessageFormat(errorMessage).format(objs));
		response.setErrorType(errorType);
		errorBeans.add(response);
	}

}
