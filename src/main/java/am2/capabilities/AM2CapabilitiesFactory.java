package am2.capabilities;

import java.util.concurrent.Callable;

public class AM2CapabilitiesFactory implements Callable<IAM2Capabilites> {
    @Override
    public IAM2Capabilites call() throws Exception {
        return new AM2Capabilities();
    }
}
