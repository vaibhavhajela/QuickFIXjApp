import quickfix.*;
import quickfix.field.Text;
import quickfix.fix44.QuoteRequest;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.concurrent.*;

public class FIXApp implements Application {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(FIXApp.class));
    private Session defaultSession;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public FIXApp() {
        scheduledExecutorService.scheduleAtFixedRate(() -> sendMessage(), 5, 5, TimeUnit.SECONDS);
    }

    @Override
    public void onCreate(SessionID sessionID) {
        System.out.println("Successful received - onCreate");
    }

    @Override
    public void onLogon(SessionID sessionID) {
        System.out.println("Successful received - onLogon");
        defaultSession = Session.lookupSession(sessionID);
    }

    @Override
    public void onLogout(SessionID sessionID) {
        System.out.println("Successful received - onLogout");
    }

    @Override
    public void toAdmin(Message message, SessionID sessionID) {
        System.out.println("Successful received - toAdmin");
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        System.out.println("Successful received - fromAdmin");
    }

    @Override
    public void toApp(Message message, SessionID sessionID) throws DoNotSend {
        System.out.println("Message is being sent:" + message);
    }

    @Override
    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println("Message received:" + message);
    }




    private void sendMessage(){
        if (defaultSession != null) {
            QuoteRequest quoteRequest = new QuoteRequest();
            quoteRequest.setString(Text.FIELD, "Hello Quote");
            System.out.println("sending hello world");
            defaultSession.send(quoteRequest);

        }
    }


}
