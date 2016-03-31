package org.buffagon.intellij.marionette.coffee;

/**
 * @author Prokofiev Alex
 */
public interface Processor<T, E> {
  E process(T value);
}
