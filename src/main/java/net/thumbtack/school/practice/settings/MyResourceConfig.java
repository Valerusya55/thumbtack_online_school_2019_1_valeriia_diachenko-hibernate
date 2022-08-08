package net.thumbtack.school.practice.settings;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class MyResourceConfig extends ResourceConfig {
    public MyResourceConfig() {
        packages("net.thumbtack.school.practice.resources");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }

}
