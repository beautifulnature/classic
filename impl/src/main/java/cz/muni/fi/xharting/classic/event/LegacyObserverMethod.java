package cz.muni.fi.xharting.classic.event;

import java.lang.reflect.Method;

import javax.enterprise.event.TransactionPhase;
import javax.enterprise.inject.spi.BeanManager;

import org.jboss.solder.reflection.Reflections;

import cz.muni.fi.xharting.classic.metadata.ObserverMethodDescriptor;
import cz.muni.fi.xharting.classic.util.CdiUtils;

/**
 * Represents a Seam 2 observer method.
 * 
 * @author <a href="http://community.jboss.org/people/jharting">Jozef Hartinger</a>
 * 
 */
public class LegacyObserverMethod extends AbstractLegacyObserverMethod {

    private String hostName;
    private Class<?> hostType;
    private Method method;

    public LegacyObserverMethod(String hostName, ObserverMethodDescriptor descriptor, TransactionPhase transactionPhase, BeanManager manager) {
        super(descriptor.getType(), transactionPhase, descriptor.isAutoCreate(), manager);
        this.hostName = hostName;
        this.hostType = descriptor.getBean().getJavaClass();
        this.method = descriptor.getMethod();
    }

    public void notify(EventPayload event) {
        CdiUtils.ManagedBeanInstance<?> hostInstance =  CdiUtils.lookupBeanByName(hostName, hostType, getManager());
        try {
            Reflections.invokeMethod(false, method, Object.class, hostInstance.getInstance(), event.getParameters());
        } finally {
            hostInstance.release();
        }
    }

    @Override
    public Class<?> getBeanClass() {
        return hostType;
    }
}