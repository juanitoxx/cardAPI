package com.credibanco.assessment.card.pattern;

import com.credibanco.assessment.card.exceptions.ProcessException;

/**
 * Interface para transformar de una entidad a otra
 *
 * @author jsbuitrago
 * @param <I> Entidad de entrada
 * @param <O> Entidad de salida
 */
@FunctionalInterface
public interface Translator<I, O> {
  O translate(final I input) throws ProcessException;
}
