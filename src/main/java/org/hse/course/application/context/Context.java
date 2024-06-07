package org.hse.course.application.context;

import java.util.Optional;

public interface Context {
    static Context getInstance(){
        return new ContextDefaultImpl();
    }

    <T> Optional<T> getObjectByName(String name, Class<T> clazz);
}
