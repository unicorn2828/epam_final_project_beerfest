package by.kononov.fest.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * SessionRequestContent class.
 * 
 * @author Vitaly Kononov
 * @version 1.0
 * @since 2020-01-12
 */
public class SessionRequestContent{
	private Map<String, Object> requestAttributes;
	private Map<String, Object> sessionAttributes;
	private Map<String, String[]> requestParameters;
	private boolean invalidateSession;

	public SessionRequestContent() {
		requestAttributes = new HashMap<>();
		sessionAttributes = new HashMap<>();
		requestParameters = new HashMap<>();
	}

	public void extract(HttpServletRequest request) {
		Enumeration<String> attributeNames = request.getAttributeNames();
		String attributeName;
		while (attributeNames.hasMoreElements()) {
			attributeName = attributeNames.nextElement();
			requestAttributes.put(attributeName, request.getAttribute(attributeName));
		}
		Enumeration<String> parameterNames = request.getParameterNames();
		String parameterName;
		while (parameterNames.hasMoreElements()) {
			parameterName = parameterNames.nextElement();
			requestParameters.put(parameterName, request.getParameterValues(parameterName));
		}
		Enumeration<String> sessionAttributess = request.getSession().getAttributeNames();
		String sessionAttribute;
		while (sessionAttributess.hasMoreElements()) {
			sessionAttribute = sessionAttributess.nextElement();
			sessionAttributes.put(sessionAttribute, request.getSession().getAttribute(sessionAttribute));
		}
	}

	public void release(HttpServletRequest request) {
		if (!requestAttributes.isEmpty()) {
			requestAttributes.forEach(request::setAttribute);
		}
		if (!sessionAttributes.isEmpty()) {
			HttpSession session = request.getSession();
			sessionAttributes.forEach(session::setAttribute);
		}
		if (invalidateSession) {
			request.getSession().invalidate();
		}
	}

	public Map<String, Object> getRequestAttributes() {
		return requestAttributes;
	}

	public void setAttribute(String name, Object value) {
		requestAttributes.put(name, value);
	}

	public Map<String, Object> getSessionAttributes() {
		return sessionAttributes;
	}

	public Map<String, String[]> getRequestParameters() {
		return requestParameters;
	}

	public Optional<String> getRequestParameter(String param) {
		if (requestParameters.get(param) != null) {
			return Optional.of(requestParameters.get(param)[0]);
		}
		return Optional.empty();
	}

	public void invalidateSession() {
		invalidateSession = true;
	}
}