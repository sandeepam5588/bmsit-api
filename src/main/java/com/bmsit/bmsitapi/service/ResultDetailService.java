package com.bmsit.bmsitapi.service;

import com.bmsit.bmsitapi.repository.ResultDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultDetailService {
    @Autowired
    private ResultDetailRepository resultDetailRepository;
}
