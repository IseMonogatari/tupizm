package org.preproject.CRUDSecAndFront.enums;

public enum UserStatus {
    NAN(0),
    I_WANT_TO_BE_A_ADMIN(1),
    OK(2),
    NO(3),
    NEW_ADMIN(4),
    REFUSE_REQUEST(5),
    BED_STATUS(100);

    private Integer code;
    UserStatus(Integer code){
        this.code = code;
    }
    public Integer getNumber(){ return code;}
}
