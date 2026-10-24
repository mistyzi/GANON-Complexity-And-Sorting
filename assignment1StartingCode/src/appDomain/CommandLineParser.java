package appDomain;

public class CommandLineParser {
    
    public static AppConfig parseArgs(String[] args) throws IllegalArgumentException {
        AppConfig config = new AppConfig();
        
        for (String arg : args) {
            String lowerArg = arg.toLowerCase();
            if (lowerArg.startsWith("-f")) {
                config.setFileName(arg.substring(2).replace("\"", ""));
            } else if (lowerArg.startsWith("-t")) {
                config.setCompareType(lowerArg.charAt(2));
            } else if (lowerArg.startsWith("-s")) {
                config.setSortAlgorithm(lowerArg.charAt(2));
            } else {
                throw new IllegalArgumentException("Unknown argument provided: " + arg);
            }
        }
        
        if (config.getFileName() == null || config.getCompareType() == '\u0000' || config.getSortAlgorithm() == '\u0000') {
            throw new IllegalArgumentException("Missing required arguments. Usage: -f<file> -t<type> -s<algorithm>");
        }
        
        return config;
    }
}