package com.kangyonggan.service;

import com.kangyonggan.model.User;

/**
 * @author kangyonggan
 * @since 16/6/29
 */
public interface MailService {

    void sendVerifyMail(User user, String callbackUrl);

    void sendResetMail(User user, String callbackUrl);
}
