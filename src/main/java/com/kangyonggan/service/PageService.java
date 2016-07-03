package com.kangyonggan.service;

import com.kangyonggan.model.Page;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
public interface PageService {

    List<Page> findPagesByUserId(Long userId);

    Page getPage(Long id);

    void savePage(Page page);

    void updatePage(Page page);

    void deletePage(Long id);
}
