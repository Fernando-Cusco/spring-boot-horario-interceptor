package ec.edu.ups.springboot.horario.app;

import ec.edu.ups.springboot.horario.app.interceptors.HorarioInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier("horarioInterceptor")
    private HorarioInterceptor horarioInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(horarioInterceptor).addPathPatterns("/index", "/", "");
    }
}
