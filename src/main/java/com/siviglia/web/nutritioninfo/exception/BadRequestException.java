/** 
 * Copyright (C) 2019 Nicholas Siviglia - All Rights Reserved. 
 * Unauthorized copying of this software, via any medium is strictly prohibited. 
 * Proprietary and confidential.
**/
/**
 * @file NotFoundException.java
 * @brief used to throw 404 page is item is not found.
 * @author Nicholas Siviglia
 */

package com.siviglia.web.nutritioninfo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

//If you throw an exception of type NotFoundException in your controllers, 
//Spring MVC's exception resolver would catch that exception and convert 
//it to a 404 Not Found HTTP response.
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {}
