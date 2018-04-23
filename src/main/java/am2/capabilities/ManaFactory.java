package am2.capabilities;

import java.util.concurrent.Callable;

public class ManaFactory implements Callable<IMana> {
    @Override
    public IMana call() throws Exception {
        return new Mana();
    }
}
