//package com.antonio.piglet;
//
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//
//@EntityScan(basePackages = "com.antonio")
//@ComponentScan(basePackages = "com.antonio", excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = PigletApplication.class),
//        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.antonio\\..*")
//})
//public class ApplicationWorker {
//
//    public static void  main(String[] args) {
//        new SpringApplicationBuilder(ApplicationWorker.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
//    }
//}
