
import java.text.*;
        import java.util.*;

public class CertificateValidator {

    public static String validate(String certText) {
        try {
            String notBefore = extract(certText, "Not Before:");
            String notAfter = extract(certText, "Not After :");

            SimpleDateFormat sdf = new SimpleDateFormat("MMM d HH:mm:ss yyyy z", Locale.ENGLISH);
            Date startDate = sdf.parse(notBefore);
            Date endDate = sdf.parse(notAfter);
            Date currentDate = new Date();

            if (currentDate.before(startDate)) return "Invalid (Not Yet Valid)";
            if (currentDate.after(endDate)) return "Expired";
            return "Valid";
        } catch (Exception e) {
            return "Invalid Certificate";
        }
    }

    private static String extract(String text, String key) {
        for (String line : text.split("\n")) {
            if (line.contains(key)) {
                return line.split(key)[1].trim();
            }
        }
        return null;
    }
}
