import quickfix.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FIXAcceptor {

    public static void main(String[] args) {
        FIXApp fixApp = new FIXApp();
        SessionSettings sessionSettings;
        try {
             getConnector(fixApp,"/Users/vaibhavhajela/IdeaProjects/SimpleFIX/src/resources/acceptor.conf").start();
        } catch (ConfigError e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static SocketAcceptor getConnector(Application app, String configFile) throws Exception{
        SessionSettings sessionSettings = new SessionSettings(Files.newInputStream(Paths.get(configFile)));
        MessageStoreFactory storeFactory = new FileStoreFactory(sessionSettings);
        LogFactory logFactory = new FileLogFactory(sessionSettings);
        MessageFactory messageFactory = new DefaultMessageFactory();

        return new SocketAcceptor(app,storeFactory,sessionSettings,logFactory,messageFactory);
    }
}
