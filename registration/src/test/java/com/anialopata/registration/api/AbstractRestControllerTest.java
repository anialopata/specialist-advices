package com.anialopata.registration.api;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Ania on 2018-11-23.
 */
public class AbstractRestControllerTest {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
