package com.netcracker.adlitsov;

public class TestAnnotatedInnerPojo {

    @RequiredFor(methods = {RequestMethod.POST})
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
