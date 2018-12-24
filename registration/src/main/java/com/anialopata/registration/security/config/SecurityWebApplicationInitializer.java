package com.anialopata.registration.security.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by Ania on 2018-11-15.
 */
@CrossOrigin(origins = "http://localhost:4200")
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}
