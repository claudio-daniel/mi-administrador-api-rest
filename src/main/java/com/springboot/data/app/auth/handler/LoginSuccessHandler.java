package com.springboot.data.app.auth.handler;

import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler{// extends SimpleUrlAuthenticationSuccessHandler {

//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {
//		
//		SessionFlashMapManager flashManager = new SessionFlashMapManager();
//		
//		FlashMap flashMap = new FlashMap();
//		
//		flashMap.put("success", "Hola, "+ authentication.getName() +" has iniciado sesión con éxito");
//		
//		flashManager.saveOutputFlashMap(flashMap, request, response);
//		
//		if(authentication != null){
//			
//			logger.info("El usuario "+ authentication.getName() + " inicio sesión con éxito");
//			
//		}
//		
//		super.onAuthenticationSuccess(request, response, authentication);
//	}

}
