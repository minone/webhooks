package br.com.minone.webhooks.infrastructure.firebase;


public class LogEntry {

    private String value;

    public LogEntry(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
