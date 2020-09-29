package edu.pasudo123.study.inflearn.member.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * 내장타입 설정
 */
@Embeddable
@Getter
@Setter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
