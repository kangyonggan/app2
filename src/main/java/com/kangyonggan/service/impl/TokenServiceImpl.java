package com.kangyonggan.service.impl;

import com.kangyonggan.constants.ShiroConstants;
import com.kangyonggan.model.Token;
import com.kangyonggan.service.TokenService;
import com.kangyonggan.util.Digests;
import com.kangyonggan.util.Encodes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author kangyonggan
 * @since 16/6/29
 */
@Service
@Transactional
public class TokenServiceImpl extends BaseService<Token> implements TokenService {

    @Override
    public String saveToken(String type, Long userId) {
        Token token = new Token();
        token.setType(type);
        token.setUserId(userId);

        String code = genTokenCode();
        token.setCode(code);

        Date now = new Date();
        token.setCreatedTime(now);
        token.setUpdatedTime(now);
        token.setExpireTime(new Date(now.getTime() + 24 * 60 * 60 * 1000));

        super.insertSelective(token);
        return code;
    }

    @Override
    public Token findTokenByCode(String code) {
        Token token = new Token();
        token.setCode(code);

        return super.selectOne(token);
    }

    private String genTokenCode() {
        byte[] hashKey = Digests.sha1(Digests.generateSalt(ShiroConstants.SALT_SIZE));
        return Encodes.encodeHex(hashKey);
    }
}
