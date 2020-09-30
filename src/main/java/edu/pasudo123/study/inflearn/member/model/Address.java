package edu.pasudo123.study.inflearn.member.model;

import lombok.*;

import javax.persistence.Embeddable;

/**
 * 내장타입 설정
 * 불변하게 만든다.
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address(final String city, final String street, final String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
