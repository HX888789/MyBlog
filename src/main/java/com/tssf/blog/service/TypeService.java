package com.tssf.blog.service;

import com.tssf.blog.domain.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    Type saveType(Type type);

    Type getType(Long id);

    Type updateType(Long id ,Type type);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listTypeTop(Integer size);

    List<Type> listType();

    void deleteType(Long id);
}
