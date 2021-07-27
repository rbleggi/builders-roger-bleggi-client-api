package br.com.builders.client.api.config;

import br.com.builders.client.api.helper.MessageHelper;
import br.com.builders.client.api.modules.builders.BuildersModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static java.time.ZoneId.SHORT_IDS;

@Configuration
@EnableAspectJAutoProxy
@Import({BuildersModule.class})
public class AppConfig {

    public static final String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATTER = "yyyy-MM-dd";

    @Bean
    MessageHelper messageHelper(MessageSource messageSource) {
        return new MessageHelper(messageSource);
    }

    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames("classpath:i18n/messages");
        source.setCacheSeconds(3600);
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Bean
    LocalValidatorFactoryBean getValidator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    @Bean
    @Primary
    public ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER)));
        timeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMATTER)));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER)));
        timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMATTER)));
        mapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMATTER));
        mapper.setTimeZone(TimeZone.getTimeZone(ZoneId.of(SHORT_IDS.get("BET"))));
        mapper.setDefaultPropertyInclusion(NON_ABSENT);
        mapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(timeModule).registerModule(new ParameterNamesModule()).registerModules(ObjectMapper.findModules());
        return mapper;
    }

}