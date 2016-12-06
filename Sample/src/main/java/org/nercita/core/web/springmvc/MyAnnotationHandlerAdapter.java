package org.nercita.core.web.springmvc;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.view.RedirectView;


@SuppressWarnings("deprecation")
public class MyAnnotationHandlerAdapter extends AnnotationMethodHandlerAdapter {
	
	
	public MyAnnotationHandlerAdapter() {
		super();
	}

	@Override
	public ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//unpack flash state from session into request
		unpackFlashState(request);
		
		ModelAndView modelAndView = super.handle(request, response, handler);

		//controller method can return null ModelAndView if handling
		if (modelAndView == null) return null;
		
		final ModelMap modelMap = modelAndView.getModelMap();
		
		//merge in existing flash state from request
		mergeFlashState(request, modelMap);
		
		//outgoing flash parameters should be added to the session
		setFlashState(request, modelMap);
		
		boolean isRedirect = isRedirect(modelAndView);
		
		if (!isRedirect)
			setParameters(request, modelMap);
		
		return modelAndView;
	}


	boolean isRedirect(ModelAndView modelAndView) {
		boolean isRedirect = false;
		if (modelAndView.getView() instanceof RedirectView
				|| (modelAndView.getViewName() != null && 
						modelAndView.getViewName().startsWith("redirect:"))) {
			isRedirect = true;
		}
		return isRedirect;
	}

	@SuppressWarnings("rawtypes")
	void setParameters(HttpServletRequest request,
			final ModelMap modelMap) {
		
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = (String) parameterNames.nextElement();
			if (!modelMap.containsKey(parameterName)) {
				String value = request.getParameter(parameterName);
				modelMap.put(parameterName, value);
			
				if (logger.isDebugEnabled()) {
					logger.debug("Adding parameter " + parameterName + ": " + value );
				}
			}
		}
	}

	void unpackFlashState(HttpServletRequest request) {
		
		final HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			final Map<String,Object> flashState = (Map<String, Object>) session.getAttribute("flashState");
			if (flashState != null) {
				request.setAttribute("flashState", flashState);
				session.removeAttribute("flashState");
				
				Set<String> flashKeys = flashState.keySet();
				for (String flashKey : flashKeys) {
					Object currentRequestAttribute = request.getAttribute(flashKey);
					if (currentRequestAttribute == null) {
						request.setAttribute(flashKey, flashState.get(flashKey));
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	void mergeFlashState(HttpServletRequest request, ModelMap modelMap) {
		final Map<String,Object> flashState = (Map<String, Object>) request.getAttribute("flashState");
		if (flashState != null) {
			modelMap.mergeAttributes(flashState);
			request.removeAttribute("flashState");
		}
	}

	@SuppressWarnings("rawtypes")
	void setFlashState(HttpServletRequest request,
			ModelMap modelMap) {
		
		final Set keys = modelMap.keySet();
		Map<String,Object> flashState = null;
		
		for (Object object : keys) {
			String key = object.toString();
			if (key.startsWith("flash:")) {
				String realKey = key.substring("flash:".length());
				if (flashState == null) flashState = new HashMap<String, Object>();
				flashState.put(realKey, modelMap.get(key));
			}
		}
		
		if (flashState != null) {
			request.getSession().setAttribute("flashState", flashState);
		}
	}

}
