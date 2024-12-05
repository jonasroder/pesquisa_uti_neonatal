package com.roderly.pesquisaneonatos.common.Utilitarios;

import lombok.experimental.UtilityClass;

import java.util.function.Function;

@UtilityClass
public class ObjectUtils {

    public static <T, R> R safeGet(T object, Function<T, R> mapper) {
        return object != null ? mapper.apply(object) : null;
    }


}
