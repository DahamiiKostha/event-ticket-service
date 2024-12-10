package com.oop.eventticketingsystem.simulation.data.dbDataProvider;

import java.util.List;

/**
 * A class for DataProvider
 *
 * @param <T> the type of data
 */
public interface DataProvider<T> {
    List<T> populate();
}
