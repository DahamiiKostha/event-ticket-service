package com.oop.eventticketingsystem.eventTransaction.subject;

import com.oop.eventticketingsystem.eventTransaction.observer.DomainEventObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for handling domain event publisher
 */
public class DomainEventPublisher<T> implements Subject<T> {
    private final List<DomainEventObserver<T>> observers = new ArrayList<>();

    public void addObserver(DomainEventObserver<T> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(DomainEventObserver<T> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(T domainObject) {
        for (DomainEventObserver<T> observer : observers) {
            // run the observer on a new thread
            new Thread(() -> observer.onDomainEvent(domainObject)).start();
        }
    }
}

