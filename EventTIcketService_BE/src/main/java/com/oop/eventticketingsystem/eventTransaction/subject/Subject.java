package com.oop.eventticketingsystem.eventTransaction.subject;

import com.oop.eventticketingsystem.eventTransaction.observer.DomainEventObserver;

public interface Subject<T> {
    void addObserver(DomainEventObserver<T> observer);
    void removeObserver(DomainEventObserver<T> observer);
    void notifyObservers(T domainObject);
}
