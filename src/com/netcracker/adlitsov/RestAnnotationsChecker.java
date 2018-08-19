package com.netcracker.adlitsov;

public interface RestAnnotationsChecker {

    void check(Object o, RequestMethod method) throws IllegalAccessException;

}
