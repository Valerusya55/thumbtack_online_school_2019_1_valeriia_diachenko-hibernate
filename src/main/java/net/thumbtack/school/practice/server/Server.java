package net.thumbtack.school.practice.server;
import net.thumbtack.school.practice.settings.MyResourceConfig;
import net.thumbtack.school.practice.settings.Settings;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Server { ;
    private static org.eclipse.jetty.server.Server jettyServer;

    private static void attachShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                stopServer();
            }
        });
    }

    public static void createServer() {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(Settings.getRestHTTPPort()).build();
        MyResourceConfig config = new MyResourceConfig();
        jettyServer = JettyHttpContainerFactory.createServer(baseUri, config);
    }

    public static void stopServer() {
        try {
            jettyServer.stop();
            jettyServer.destroy();
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        attachShutDownHook();
        createServer();
    }

}
