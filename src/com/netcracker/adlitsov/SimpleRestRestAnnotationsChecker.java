package com.netcracker.adlitsov;

import java.lang.reflect.Field;
import java.util.Arrays;

public class SimpleRestRestAnnotationsChecker implements RestAnnotationsChecker {

    @Override
    public void check(Object o, RequestMethod requestMethod) throws IllegalAccessException {
        if (o == null) {
            return;
        }
        Class<?> objClass = o.getClass();
        Field[] objFields = objClass.getDeclaredFields();

        for (Field field : objFields) {
            field.setAccessible(true);
            Object fieldValue = field.get(o);
            if (fieldValue == null) {
                if (field.isAnnotationPresent(RequiredFor.class)) {
                    RequestMethod[] requiredMethods = field.getAnnotation(RequiredFor.class).methods();
                    if (requiredMethods == null) {
                        return;
                    }
                    if (Arrays.asList(requiredMethods).contains(requestMethod)) {
                        throw new IllegalArgumentException(requestMethod + " not found in field " + objClass.getName() + "." +
                                field.getName() + "!");
                    }
                }
            } else {
                if (!field.getType().isPrimitive()) {
                    check(fieldValue, requestMethod);
                }
            }

        }

    }

    public static void main(String[] args) throws IllegalAccessException {
        TestAnnotatedPojo testPojo = new TestAnnotatedPojo();
        testPojo.setName("hehe");

        RestAnnotationsChecker checker = new SimpleRestRestAnnotationsChecker();
        checker.check(testPojo, RequestMethod.DELETE);
        // fine

//        testPojo.setName(null);
//        checker.check(testPojo, RequestMethod.DELETE);
        // oops

        testPojo.setName(null);
        testPojo.setInner(new TestAnnotatedInnerPojo());
        checker.check(testPojo, RequestMethod.POST);
        // oops
    }

}
