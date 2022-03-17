package ec.edu.ups.springboot.horario.app.interceptors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Component("horarioInterceptor")
public class HorarioInterceptor implements HandlerInterceptor {

    @Value("${config.horario.apertura}")
    private Integer apertura;
    @Value("${config.horario.cierre}")
    private Integer cierre;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // obtener la hora actual
        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        if (hora >= apertura && hora < cierre) {
            StringBuilder mensaje = new StringBuilder("Bienvenido, horario de atención desde las"+apertura+" hasta las "+cierre);
            mensaje.append("\n gracias por su visita");
            request.setAttribute("mensaje", mensaje.toString());
            return true;
        }
        request.setAttribute("mensaje", "Lo sentimos, actualmente estamos fuera del horario de atención");
        response.sendRedirect(request.getContextPath().concat("/cerrado"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        String mensaje = (String) request.getAttribute("mensaje");
        if (modelAndView != null && handler instanceof HandlerMethod){
            modelAndView.addObject("mensaje", mensaje);
        }
    }
}
