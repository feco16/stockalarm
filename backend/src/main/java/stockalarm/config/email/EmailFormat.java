package stockalarm.config.email;

public class EmailFormat {

    private final String email;
    private final String firstName;
    private final String alarmName;

    public EmailFormat(final String email, final String firstName, final String alarmName) {
        this.email = email;
        this.firstName = firstName;
        this.alarmName = alarmName;
    }

    public String constructBody() {
        String stringBuilder = "Welcome " + firstName + ","
                + System.lineSeparator()
                + "Your alarm '" + alarmName + "' has reached the target."
                + System.lineSeparator()
                + "Check stockalarm8.com for details.";
        return stringBuilder;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return "Your stock alarm has reached the target";
    }

}
