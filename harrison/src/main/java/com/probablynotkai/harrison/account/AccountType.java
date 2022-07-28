package com.probablynotkai.harrison.account;

public enum AccountType
{
    LIMITED,
    PREMIUM
    ;

    public String normalize(){
        return String.valueOf(name().toCharArray()[0]).toUpperCase() + name().substring(1).toLowerCase();
    }
}
