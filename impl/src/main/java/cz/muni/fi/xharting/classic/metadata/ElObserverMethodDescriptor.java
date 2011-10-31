package cz.muni.fi.xharting.classic.metadata;

public class ElObserverMethodDescriptor extends AbstractObserverMethodDescriptor {

    private final String methodExpression;
    
    public ElObserverMethodDescriptor(String type, String methodExpression) {
        super(type);
        this.methodExpression = methodExpression;
    }

    public String getMethodExpression() {
        return methodExpression;
    }
}