package com.video.streaming;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

    /**
     * The Class SwaggerConfig.
     */
    @Configuration
    public class SwaggerConfig {

        /** The Constant AUTHORIZATION_HEADER. */
        public static final String AUTHORIZATION_HEADER = "Authorization";

        /**
         * Api key.
         *
         * @return the api key
         */
        private ApiKey apiKey() {
            return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
        }

        /**
         * Gets the release date.
         *
         * @return the release date
         */
        private Date getReleaseDate() {
            return java.util.Calendar.getInstance().getTime();
        }

        /**
         * Api info.
         *
         * @return the api info
         */
        private ApiInfo apiInfo() {
            return new ApiInfo("Video Server REST API", "REST API for Video Stream Data", "V1", "Terms of service",
                    new Contact("Bhanu Addepalli", "bhanuaddepalli.com", "bsladdepalli@gmail.com"),
                    "Video Streaming API Version 1.0, API Realeased On " + getReleaseDate() + " ",
                    "@ Copyright Bhanu Addepalli", Collections.emptyList());
        }
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.video.streaming")).paths(PathSelectors.any())
                    .paths(PathSelectors.any())
                    .build();
        }

    }
