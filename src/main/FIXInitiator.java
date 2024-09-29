import quickfix.*;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FIXInitiator {

    public static void main(String[] args) {
        FIXApp fixApp = new FIXApp();
        SessionSettings sessionSettings;
        try {
            getConnector(fixApp,"/Users/vaibhavhajela/IdeaProjects/SimpleFIX/src/resources/initiator.conf").start();
        } catch (ConfigError e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static SocketInitiator getConnector(Application app, String configFile) throws Exception{
        SessionSettings sessionSettings = new SessionSettings(Files.newInputStream(Paths.get(configFile)));
        MessageStoreFactory storeFactory = new FileStoreFactory(sessionSettings);
        LogFactory logFactory = new FileLogFactory(sessionSettings);
        MessageFactory messageFactory = new DefaultMessageFactory();

        return new SocketInitiator(app,storeFactory,sessionSettings,logFactory,messageFactory);
    }
}
