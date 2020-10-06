package com.sqli.ecommerce.reviewv2.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public final class CommentDto implements Serializable {

    private final String username;

    private final String comment;

}
