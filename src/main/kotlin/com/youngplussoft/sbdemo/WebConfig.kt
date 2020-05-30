package com.youngplussoft.sbdemo



import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
import org.springframework.context.annotation.*
import org.springframework.format.FormatterRegistry
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.validation.Validator
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.method.support.HandlerMethodReturnValueHandler
import org.springframework.web.servlet.config.annotation.*
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver


import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

import javax.servlet.Filter
import javax.servlet.FilterRegistration

@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@ImportResource("classpath:/app-context.xml")
class StoreConfig : WebMvcConfigurer {

    @Bean
    fun responseBodyConverter(): HttpMessageConverter<String> {
        return StringHttpMessageConverter(Charset.forName("UTF-8"))
    }




    /*
     * spring-boot locale 변경 인터셉터
     *
     * @return LocaleChangeInterceptor
     */
    @Bean
    fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val localeChangeInterceptor = LocaleChangeInterceptor()
        // request로 넘어오는 language parameter를 받아서 locale로 설정 한다.
        localeChangeInterceptor.setParamName("language")
        return localeChangeInterceptor
    }

    @Bean(name = ["localeResolver"])
    fun sessionLocaleResolver(): SessionLocaleResolver {
        // 세션 기준으로 로케일을 설정 한다.
        val localeResolver = SessionLocaleResolver()
        // 쿠키 기준(세션이 끊겨도 브라우져에 설정된 쿠키 기준으로)
        // CookieLocaleResolver localeResolver = new CookieLocaleResolver();

        // 최초 기본 로케일을 강제로 설정이 가능 하다.
        localeResolver.setDefaultLocale(Locale("ko"))
        return localeResolver
    }


    fun configurePathMatch(pathMatchConfigurer: PathMatchConfigurer?) {}
    fun configureContentNegotiation(contentNegotiationConfigurer: ContentNegotiationConfigurer?) {}
    fun configureAsyncSupport(asyncSupportConfigurer: AsyncSupportConfigurer?) {}
    fun configureDefaultServletHandling(defaultServletHandlerConfigurer: DefaultServletHandlerConfigurer?) {}
    fun addFormatters(formatterRegistry: FormatterRegistry?) {}
    fun addInterceptors(interceptorRegistry: InterceptorRegistry?) {}
    fun addResourceHandlers(registry: ResourceHandlerRegistry) {

        registry.addResourceHandler("/**")
                .addResourceLocations(RESOURCE_LOCATIONS)

    }

    fun addCorsMappings(corsRegistry: CorsRegistry?) {}
    fun addViewControllers(viewControllerRegistry: ViewControllerRegistry?) {}
    fun configureViewResolvers(viewResolverRegistry: ViewResolverRegistry?) {}


    fun addReturnValueHandlers(list: List<HandlerMethodReturnValueHandler?>?) {}
    fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>?>) {
        val builder = Jackson2ObjectMapperBuilder()
        builder.indentOutput(true).dateFormat(SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
        converters.add(MappingJackson2HttpMessageConverter(builder.build()))
    }

    fun extendMessageConverters(list: List<HttpMessageConverter<*>?>?) {}
    val validator: Validator?
        get() = null


    companion object {
        private val RESOURCE_LOCATIONS = arrayOf( /*   "classpath:/META-INF/resources/",  "classpath:/resources/", */
                "classpath:/static/", "classpath:/templates/")
    }
}