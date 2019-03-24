package ch.schmarcel.console.command;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class StringValidator {
    public boolean isByte(String string) {
        try {
            Byte.parseByte(string);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    public boolean isShort(String string) {
        try {
            Short.parseShort(string);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    public boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    public boolean isLong(String string) {
        try {
            Long.parseLong(string);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    public boolean isFloat(String string) {
        try {
            Float.parseFloat(string);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    public boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    public boolean isChar(String string) {
        return string != null && string.length() == 1;
    }

    public boolean isBoolean(String string) {
        return string != null && (string.equalsIgnoreCase("true") || string.equalsIgnoreCase("false"));
    }
}
