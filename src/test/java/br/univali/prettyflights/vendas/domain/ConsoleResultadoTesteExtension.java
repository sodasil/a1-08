package br.univali.prettyflights.vendas.domain;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class ConsoleResultadoTesteExtension implements TestWatcher {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println(GREEN + "[PASS]" + RESET + " " + nomeTeste(context));
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println(RED + "[FAIL]" + RESET + " " + nomeTeste(context));
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println(YELLOW + "[ABORT]" + RESET + " " + nomeTeste(context));
    }

    @Override
    public void testDisabled(ExtensionContext context, java.util.Optional<String> reason) {
        System.out.println(CYAN + "[SKIP]" + RESET + " " + nomeTeste(context));
    }

    private String nomeTeste(ExtensionContext context) {
        String displayName = context.getDisplayName();
        String methodName = context.getRequiredTestMethod().getName();
        if (displayName.equals(methodName)) {
            return methodName;
        }
        return displayName + " (" + methodName + ")";
    }
}
