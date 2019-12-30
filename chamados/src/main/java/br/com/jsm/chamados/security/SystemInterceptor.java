package br.com.jsm.chamados.security;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.view.Results;
import br.com.jsm.chamados.types.TpUsuarioType;

@Intercepts
public class SystemInterceptor {
	@Inject
	private Result result;
	
	@Inject
	private AuxSession auxSession;
	
	@Accepts
	public boolean isMethodRestrict(ControllerMethod method) {
		if (method.getController().getType().equals(LoginController.class)) {
			return false;
		}
		
		return true;
	}
	
	@AroundCall
	public void autoriza(SimpleInterceptorStack stack, ControllerMethod method) {
		if (! auxSession.isLogado()) {
			result.redirectTo(LoginController.class).formLogin();
		}
		
		if (hasAccess(method)) {
			stack.next();
		} else {
			result.use(Results.http()).sendError(401, "Você não esta autorizado");
		}
	}

	private boolean hasAccess(ControllerMethod method) {
		if (method.getClass().isAnnotationPresent(Admin.class)) {
			if (auxSession.getUsuario().getTpUsuario().equals(TpUsuarioType.ADMIN)) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
}
