package com.azaroff.x3.confirmation.rest;

import com.azaroff.x3.confirmation.dao.entity.Confirmation;
import com.azaroff.x3.confirmation.service.ConfirmService;
import com.azaroff.x3.notification.model.CommonResponse;
import com.azaroff.x3.notification.util.CommonResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ConfirmationController {

    @Autowired
    private ConfirmService confirmService;

    @GetMapping("/confirm/{correlationId}")
    public CommonResponse confirm(@PathVariable String correlationId) {
        if (StringUtils.isEmpty(correlationId)) {
            CommonResponseFactory<CommonResponse> commonResponseFactory = CommonResponse::new;
            return commonResponseFactory.create(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Correlation ID must not be null");
        }
        Confirmation confirmation = confirmService.confirm(correlationId);
        //TODO: call bpm process
        confirmService.sendToBusinessAccountProcess(correlationId, confirmation);

        CommonResponseFactory<CommonResponse> commonResponseFactory = CommonResponse::new;
        return commonResponseFactory.create(HttpStatus.ACCEPTED.value(), HttpStatus.ACCEPTED.getReasonPhrase(), null);
    }

    @GetMapping("/verify/{userId}")
    public boolean verify(@PathVariable long userId) {
        return confirmService.verify(userId);
    }
}