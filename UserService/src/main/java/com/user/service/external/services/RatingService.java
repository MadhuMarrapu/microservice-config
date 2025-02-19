package com.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;


@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
}
