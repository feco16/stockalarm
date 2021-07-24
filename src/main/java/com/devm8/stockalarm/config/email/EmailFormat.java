package com.devm8.stockalarm.config.email;

public class EmailFormat {

    private String email;
    private String firstName;
    private String alarmName;

    public EmailFormat(String email, String firstName, String alarmName) {
        this.email = email;
        this.firstName = firstName;
        this.alarmName = alarmName;
    }

    public String constructBody() {
        final StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("Welcome ").append(firstName).append(",")
                .append(System.lineSeparator())
                .append("Your alarm '").append(alarmName).append("' has reached the target.")
                .append(System.lineSeparator())
                .append("Check stockalarm8.com for details.")
                .toString();
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return "Your stock alarm has reached the target";
    }

}
