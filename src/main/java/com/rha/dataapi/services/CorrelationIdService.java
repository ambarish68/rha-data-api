package com.rha.dataapi.services;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CorrelationIdService {

    private final String DELIMITER = "|";

    public void setMDC(String emailId, String nonce) {
        DateTime dateTime = new DateTime();
        if (StringUtils.isEmpty(nonce)) {
            nonce = UUID.randomUUID().toString();
        }
        String correlationId = emailId + DELIMITER + dateTime.getHourOfDay() + DELIMITER + dateTime.getMinuteOfDay() + nonce;
        MDC.put("correlationId", correlationId);
    }
}
