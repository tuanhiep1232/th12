package net.th1232.action;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import net.th1232.action.authorization.Authorization;

public abstract class AbstractAction {
	@Logger protected Log log;
    @In protected EntityManager entityManager;
    @In protected Authorization authorization;
    @In protected Credentials credentials;
}
