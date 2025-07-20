package org.example.audit.android.module.audit.console;

import lombok.extern.slf4j.Slf4j;
import org.example.audit.android.module.audit.AuditLogger;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component("console")
public class ConsoleAudit implements AuditLogger {
    @Override
    public void log(String methodName, Object[] args, Object result) {

        log.info("Audit -> Method: {}, Args: {}, Result: {}\n console",
                methodName,
                Arrays.toString(args),
                result);
    }
}
