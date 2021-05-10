package model;

import utility.observer.subject.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject {
    void sendMessage(String source) throws Exception;

    void addMessages(String source) throws Exception;
}
