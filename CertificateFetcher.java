

import java.io.*;

public class CertificateFetcher {

    public static String fetchCertificate(String domain) throws IOException {
        String command = "openssl s_client -connect " + domain + " < /dev/null | openssl x509 -noout -text";
        ProcessBuilder builder =  new ProcessBuilder("cmd.exe", "/c", "openssl s_client -connect " + domain + " < nul | openssl x509 -noout -text");
        // use "cmd", "/c", command for Windows

        builder.redirectErrorStream(true);
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        return output.toString();
    }
}
