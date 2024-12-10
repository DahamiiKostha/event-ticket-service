package com.oop.eventticketingsystem.eventTransaction.observer;

public interface DomainEventObserver<T> {
    void onDomainEvent(T domainObject);
}

