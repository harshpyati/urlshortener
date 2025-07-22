package com.harsh.urlshortener.service.impl;

import com.harsh.urlshortener.domain.URLIdRange;
import com.harsh.urlshortener.repo.IdRepository;
import com.harsh.urlshortener.service.IdGenService;
import com.harsh.urlshortener.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

import static com.harsh.urlshortener.utils.Utils.isNotNullOrEmpty;

public class IdGenServiceImpl implements IdGenService {

    @Autowired
    IdRepository idRepository;

    @Override
    public int generatePseudoRandomId() {
        List<Integer> ids = idRepository.getAllIds();
        if (isNotNullOrEmpty(ids)) {
            int randomId = new Random().nextInt(ids.size());
            URLIdRange urlIdRange = idRepository.getById(randomId);
            int generatedId = urlIdRange.getCurrent() + 1;
            urlIdRange.setCurrent(generatedId);
            idRepository.save(urlIdRange);
            return generatedId;
        }
        return 0;
    }
}
