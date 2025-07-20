package org.example.audit.android.module.audit;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Audit {
    private String methodName;
    private Object[] args;
    private Object result;
}
