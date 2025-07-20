package org.example.audit.android.module.audit;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuditLogger {
    void log(String methodName, Object[] args, Object result) throws JsonProcessingException;
}
