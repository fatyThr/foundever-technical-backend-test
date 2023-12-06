package com.founderever.technical.backend.infrastructure.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination<T>{
        private List<T> content;
        private int page;
        private int size;
        private long totalElements;
        private long totalPages;


}
