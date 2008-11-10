package myorg._development;

import java.io.IOException;
import java.util.Properties;

import pt.ist.fenixWebFramework.Config;

public class PropertiesManager extends pt.utl.ist.fenix.tools.util.PropertiesManager {

    private static final Properties properties = new Properties();

    static {
        try {
            loadProperties(properties, "/configuration.properties");
        } catch (IOException e) {
            throw new RuntimeException("Unable to load properties files.", e);
        }
    }

    public static String getProperty(final String key) {
        return properties.getProperty(key);
    }

    public static boolean getBooleanProperty(final String key) {
    	return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static Integer getIntegerProperty(final String key) {
    	return Integer.valueOf(properties.getProperty(key));
    }

    public static void setProperty(final String key, final String value) {
        properties.setProperty(key, value);
    }

    public static Config getFenixFrameworkConfig(final String[] domainModels) {
        return new Config() {{
            domainModelPaths = domainModels;
            dbAlias = getProperty("db.alias");
            dbUsername = getProperty("db.user");
            dbPassword = getProperty("db.pass");
            appName = getProperty("app.name");
            appContext =getProperty("app.context"); 
            filterRequestWithDigest = getBooleanProperty("filter.request.with.digest");
            tamperingRedirect = getProperty("digest.tampering.url");
            errorIfChangingDeletedObject = getBooleanProperty("error.if.changing.deleted.object");
            defaultLanguage = getProperty("language");
            defaultLocation = getProperty("location");
            defaultVariant = getProperty("variant");
            updateDataRepositoryStructure = true;
            casEnabled = getBooleanProperty("cas.enable");
            casLoginUrl = getProperty("cas.loginUrl");
            casValidateUrl = getProperty("cas.ValidateUrl");
            casLogoutUrl = getProperty("cas.logoutUrl");
        }};
    }

}
