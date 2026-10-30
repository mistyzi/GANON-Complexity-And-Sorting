package appDomain;

/**
 * Parses and validates the command line arguments for the application.
 * <p>
 * Three arguments are required, in any order: {@code -f<file_name>},
 * {@code -t<o|u|d>} and {@code -s<b|s|i|m|q|z>}. Argument letters are
 * case-insensitive, and a leading en dash (as found in the assignment samples)
 * is treated like a normal hyphen.
 * </p>
 */

public class CommandLineParser {

    public static AppConfig parseArgs(String[] args) throws IllegalArgumentException {
        AppConfig config = new AppConfig();

        for (String arg : args) {
            // the assignment samples contain an en dash (e.g. "–sZ"); treat it like "-"
            // (a console that can't decode it may hand us '?' or U+FFFD characters instead)
            String cleaned = arg.replaceFirst("^[\\u0080-\\uFFFF?]+", "-");
            String lowerArg = cleaned.toLowerCase();

            if (lowerArg.startsWith("-f")) {
                String name = cleaned.substring(2).replace("\"", "");
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("No file name given after -f (e.g. -fproducts1.txt).");
                }
                config.setFileName(name);
            } else if (lowerArg.startsWith("-t")) {
                if (lowerArg.length() != 3 || "oud".indexOf(lowerArg.charAt(2)) < 0) {
                    throw new IllegalArgumentException("Invalid compare type '" + arg + "'. Use -to, -tu or -td.");
                }
                config.setCompareType(lowerArg.charAt(2));
            } else if (lowerArg.startsWith("-s")) {
                if (lowerArg.length() != 3 || "bsimqz".indexOf(lowerArg.charAt(2)) < 0) {
                    throw new IllegalArgumentException("Invalid sort algorithm '" + arg + "'. Use -sb, -ss, -si, -sm, -sq or -sz.");
                }
                config.setSortAlgorithm(lowerArg.charAt(2));
            } else {
                throw new IllegalArgumentException("Unknown argument provided: " + arg);
            }
        }

        if (config.getFileName() == null) {
            throw new IllegalArgumentException("Missing the file argument (-f<file_name>).");
        }
        if (config.getCompareType() == '\u0000') {
            throw new IllegalArgumentException("Missing the compare type argument (-t<o|u|d>).");
        }
        if (config.getSortAlgorithm() == '\u0000') {
            throw new IllegalArgumentException("Missing the sort algorithm argument (-s<b|s|i|m|q|z>).");
        }

        return config;
    }
}
