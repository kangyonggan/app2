package com.kangyonggan.service;

import com.kangyonggan.model.Token;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
public interface TokenService {

    String saveToken(String type, Long userId);

    Token findTokenByCode(String code);

}
