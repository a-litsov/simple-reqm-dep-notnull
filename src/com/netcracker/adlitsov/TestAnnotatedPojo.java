package com.netcracker.adlitsov;

public class TestAnnotatedPojo {

    @RequiredFor(methods = {RequestMethod.DELETE})
    private String name;

    private TestAnnotatedInnerPojo inner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestAnnotatedInnerPojo getInner() {
        return inner;
    }

    public void setInner(TestAnnotatedInnerPojo inner) {
        this.inner = inner;
    }
}
