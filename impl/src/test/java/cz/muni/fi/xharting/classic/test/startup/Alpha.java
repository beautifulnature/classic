package cz.muni.fi.xharting.classic.test.startup;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;

@Name("alpha")
@Scope(ScopeType.APPLICATION)
@Startup(depends = { "bravo", "charlie" })
public class Alpha extends Superclass {

    @Inject
    void initialize(@Started Event<String> event) {
        verifyStartupOrder("bravo", "charlie/foxtrot");
        event.fire("alpha");
    }
}
